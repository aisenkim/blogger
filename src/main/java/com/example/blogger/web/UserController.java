package com.example.blogger.web;

import com.example.blogger.domain.user.Role;
import com.example.blogger.domain.user.User;
import com.example.blogger.domain.user.dto.AddRoleDto;
import com.example.blogger.domain.user.dto.UpdateUserDto;
import com.example.blogger.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @author Aisen Kim
 *
 * Controller related to User
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER_REP')")
    public ResponseEntity<User> findUser() {
        String currentUserName = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }
        return ResponseEntity.ok().body(userService.getUser(currentUserName));
    }

    @PostMapping("/user/save")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_CUSTOMER_REP')")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/user/save").toUriString());
        User returnedUser = userService.saveUser(user);
        if(returnedUser == null) {
            return ResponseEntity.status(1062).build();
        }
        return ResponseEntity.created(uri).body(returnedUser);
//        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/add-role")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public ResponseEntity<?> addRoleToUser(@RequestBody AddRoleDto dto) {
        userService.addRoleToUser(dto.getUsername(), dto.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER', 'ROLE_CUSTOMER_REP')")
    public ResponseEntity<User> updateCustomer(@RequestBody UpdateUserDto userDto) {
        return ResponseEntity.ok().body(userService.updateUser(userDto));
    }

    @DeleteMapping(path = "/users/{customerId}")
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER', 'ROLE_CUSTOMER_REP')")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customerId") Long id) {
        return ResponseEntity.ok().build();
    }

}
