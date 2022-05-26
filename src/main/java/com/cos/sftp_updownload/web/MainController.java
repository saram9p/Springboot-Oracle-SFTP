package com.cos.sftp_updownload.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.sftp_updownload.config.auth.PrincipalDetails;

@Controller
public class MainController {

	@GetMapping({"/", "/main"})
	public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		model.addAttribute("user", principalDetails.getUser());
		return "main/main";
	}
}
