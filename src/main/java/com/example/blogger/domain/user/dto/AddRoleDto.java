package com.example.blogger.domain.user.dto;

import lombok.Data;

/**
 * For adding new role to a user
 */
@Data
public class AddRoleDto {
    private final String username;
    private final String roleName;
}
