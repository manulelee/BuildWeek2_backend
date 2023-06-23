package com.epicode.security.runners;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.epicode.security.enumerates.UserRole;
import com.epicode.security.models.Role;
import com.epicode.security.models.User;
import com.epicode.security.repositories.RoleRepository;
import com.epicode.security.repositories.UserRepository;
import com.epicode.security.services.AuthService;

@Component
public class AuthRunner implements ApplicationRunner {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthService authService;

	private Set<Role> adminRole;
	private Set<Role> userRole;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		setRoleDefault();
		saveUserDefault();
	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRole(UserRole.ROLE_ADMIN);

		Role user = new Role();
		user.setRole(UserRole.ROLE_USER);

		if (!roleRepository.existsByRole(UserRole.ROLE_ADMIN)) {
			roleRepository.save(admin);
		}

		if (!roleRepository.existsByRole(UserRole.ROLE_USER)) {
			roleRepository.save(user);
		}

		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(user);

		userRole = new HashSet<Role>();
		userRole.add(user);
	}

	public void saveUserDefault() {
		if (userRepository.findByEmail("andrea.ragalzi@epicode.com").isEmpty()) {
			User admin = new User();
			admin.setUsername("andrea.ragalzi");
			admin.setEmail("andrea.ragalzi@epicode.com");
			admin.setPassword(passwordEncoder.encode("root"));
			admin.setFirstname("Andrea");
			admin.setLastname("Ragalzi");
			admin.setRoles(adminRole);
			userRepository.save(admin);
		}

		if (userRepository.findByEmail("lucy.parsons@epicode.com").isEmpty()) {
			User user = new User();
			user.setUsername("lucy.parsons");
			user.setEmail("lucy.parsons@epicode.com");
			user.setPassword(passwordEncoder.encode("user"));
			user.setFirstname("Lucy");
			user.setLastname("Parsons");
			user.setRoles(userRole);
			userRepository.save(user);
		}

	}

}
