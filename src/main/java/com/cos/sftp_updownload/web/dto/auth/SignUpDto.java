package com.cos.sftp_updownload.web.dto.auth;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.sftp_updownload.domain.user.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpDto {

	@Size(min = 2, max = 20)
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotBlank
	private String email;
	@NotBlank
	private String name;
	
	public User toEntity() {
		return User.builder()
				.username(username)
				.password(password)
				.email(email)
				.name(name)
				.build();
	}
}
