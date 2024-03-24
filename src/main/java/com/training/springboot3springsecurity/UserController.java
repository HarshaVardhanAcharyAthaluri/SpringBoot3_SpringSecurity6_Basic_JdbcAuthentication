package com.training.springboot3springsecurity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private RoleRepository rolerepo;
	
	@Autowired
	private  PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/save")
	public User generateUsers(@RequestBody UserDto user ) {
			   Set<Roles> roleSet = new HashSet<>();
				if(user.getRoles()!=null) {
					user.getRoles().forEach(role->{
						switch (role) {
						case "admin":
							Roles adminrole =  rolerepo.findByRole(Role.ROLE_ADMIN).orElseThrow(()-> new RuntimeException(role + " Role Not found"));
							roleSet.add(adminrole);
							break;
						case "user":
							Roles userrole =  rolerepo.findByRole(Role.ROLE_USER).orElseThrow(()-> new RuntimeException(role+" Role Not found"));
							roleSet.add(userrole);
							break;
						default:
							Roles defoultrrole =  rolerepo.findByRole(Role.ROLE_USER).orElseThrow(()-> new RuntimeException(role+" Role Not found"));
							roleSet.add(defoultrrole);
							break;
						}
					});
				}
				String encoodedpwd = passwordEncoder.encode(user.getPassword());
				User siginUser = new User();
				siginUser.setUname(user.getUname());
				siginUser.setPassword(encoodedpwd); 
				siginUser.setRoles(roleSet);
				return userRepository.save(siginUser);
		}
	
}
