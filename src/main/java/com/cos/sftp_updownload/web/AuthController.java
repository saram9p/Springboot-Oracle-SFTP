package com.cos.sftp_updownload.web;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.sftp_updownload.domain.User;
import com.cos.sftp_updownload.service.AuthService;
import com.cos.sftp_updownload.web.dto.auth.SignUpDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class AuthController {

	
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	private final AuthService authService;
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입
	@PostMapping("/auth/signup")
	public String signup(@Valid SignUpDto signUpDto, BindingResult bindingResult) { // key=value (x-www-form-urlencoded)
		
		if(bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			
			for(FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
				System.out.println(errorMap);
			}
		}
		
		User user = signUpDto.toEntity();
		
		authService.회원가입(user);
		
		// 후처리
		log.info(user.toString());
		
		return "redirect:/auth/signin";
	}
}
