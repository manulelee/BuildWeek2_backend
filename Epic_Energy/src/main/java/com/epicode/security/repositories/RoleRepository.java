package com.epicode.security.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epicode.security.enumerates.UserRole;
import com.epicode.security.models.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
	Optional<Role> findByRole(UserRole role);

	Boolean existsByRole(UserRole role);

}
