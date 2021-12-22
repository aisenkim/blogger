package com.example.blogger.domain.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Aisen Kim
 *
 * Different Roles
 * 2. ROLE_MANAGER
 * 3. ROLE_CUSTOMER_REP
 * 4. ROLE_CUSTOMER
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Builder
    public Role(String name) {
        this.name = name;
    }
}
