package com.cafe24.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cafe24.lms.domain.User;
import com.cafe24.lms.repository.UserRepository;
import com.cafe24.security.Auth;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;

	public User getUser(String email, String password) {
		User authUser = userRepository.findByEmailAndPassword(email, password);
		return authUser;
	}

	public Boolean joinUser(User userInfo) {
		User userInDB = userRepository.findByEmail(userInfo.getEmail());
		if (userInDB != null) {
			return false;
		}

		userInfo.setRole(Auth.Role.USER);
		User user = userRepository.save(userInfo);
		if (user == null) {
			return false;
		}

		return true;
	}

	public Boolean modify(User userInfo, User authUser) {
		if(userInfo.getName() != null || !"".equals(userInfo.getName())) {
			authUser.setName(userInfo.getName());
		}
		if(userInfo.getEmail() != null || !"".equals(userInfo.getEmail())) {
			authUser.setEmail(userInfo.getEmail());
		}
		if(userInfo.getPassword() != null || !"".equals(userInfo.getPassword())) {
			authUser.setPassword(userInfo.getPassword());
		}
		
		User user = userRepository.save(authUser);
		if(user == null) {
			return false;
		}
		
		return true;
	}
	
}
