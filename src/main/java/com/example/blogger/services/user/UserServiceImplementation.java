package com.example.blogger.services.user;

import com.example.blogger.domain.user.Role;
import com.example.blogger.domain.user.RoleRepository;
import com.example.blogger.domain.user.User;
import com.example.blogger.domain.user.UserRepository;
import com.example.blogger.domain.user.dto.UpdateUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImplementation implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            log.info("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }

        log.info("User found in the database");

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails
                .User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        // check if user exists
        User possibleUser = userRepository.findByUsername(user.getUsername());
        if (possibleUser != null) {
            return null;
        }
        log.info("Saving new user: {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // By default add ROLE_CUSTOMER_REP
        user.getRoles().add(roleRepository.findByName("ROLE_CUSTOMER"));

        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role: {} to the database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }

    @Override
    public User updateUser(UpdateUserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User by provided id doesn't exist"));

        if (userDto.getName() != null && userDto.getName().length() > 0 && !Objects.equals(user.getName(), userDto.getName())) {
            user.setName(userDto.getName());
        }
        if (userDto.getUsername() != null && userDto.getUsername().length() > 0 && !Objects.equals(user.getUsername(), userDto.getUsername())) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
            String encodedPassword = passwordEncoder.encode(userDto.getPassword());

            if (!Objects.equals(encodedPassword, user.getPassword())) {
                user.setPassword(encodedPassword);
            }
        }
        if (userDto.getEmail() != null && userDto.getEmail().length() > 0 && !Objects.equals(user.getEmail(), userDto.getEmail())) {
            user.setEmail(userDto.getEmail());
        }
        return user;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

        Role role = Role.builder().name("ROLE_MANAGER").build();

        User newUser = User.builder()
                .username("admin")
                .password("admin")
                .name("admin")
                .email("admin@gmail.com")
                .roles(List.of(role))
                .build();

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        User existingUser = userRepository.findByUsername(newUser.getUsername());

        if (existingUser != null)
            return;

        userRepository.save(newUser);
        roleRepository.save(role);
    }

}
