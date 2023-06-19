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
	private Set<Role> moderatorRole;
	private Set<Role> userRole;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Run...");
		setRoleDefault();
		saveUserDefault();
	}

	private void setRoleDefault() {
		Role admin = new Role();
		admin.setRole(UserRole.ADMIN);

		Role moderator = new Role();

		moderator.setRole(UserRole.MODERATOR);
		Role user = new Role();
		user.setRole(UserRole.USER);

		if (!roleRepository.existsByRole(UserRole.ADMIN)) {
			roleRepository.save(admin);
		}

		if (!roleRepository.existsByRole(UserRole.MODERATOR)) {

			roleRepository.save(moderator);
		}

		if (!roleRepository.existsByRole(UserRole.USER)) {
			roleRepository.save(user);
		}

		adminRole = new HashSet<Role>();
		adminRole.add(admin);
		adminRole.add(moderator);
		adminRole.add(user);

		moderatorRole = new HashSet<Role>();
		moderatorRole.add(moderator);
		moderatorRole.add(user);

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

		if (userRepository.findByEmail("emma.goldman@epicode.com").isEmpty()) {
			User moderator = new User();
			moderator.setUsername("emma.goldman");
			moderator.setEmail("emma.goldman@epicode.com");
			moderator.setPassword(passwordEncoder.encode("moderator"));
			moderator.setFirstname("Emma");
			moderator.setLastname("Goldman");
			moderator.setRoles(moderatorRole);
			userRepository.save(moderator);
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
