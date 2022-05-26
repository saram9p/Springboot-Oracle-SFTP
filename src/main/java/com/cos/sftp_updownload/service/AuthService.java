package com.cos.sftp_updownload.service;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cos.sftp_updownload.domain.User;
import com.cos.sftp_updownload.domain.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional // Write(insert, Update, Delete), 이 함수가 실행되고 종료될 때까지 트랜잭션 관리를 해준다.
	public User 회원가입(User user) {
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 암호화된 패스워드
		
		user.setPassword(encPassword);
		user.setRole("ROLE_USER"); // 관리자 ROLE_ADMIN
		
		User userEntity = userRepository.save(user);
		
		return userEntity;
	}
}
