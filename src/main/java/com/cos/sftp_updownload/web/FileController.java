package com.cos.sftp_updownload.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.sftp_updownload.config.auth.PrincipalDetails;
import com.cos.sftp_updownload.service.FileService;
import com.cos.sftp_updownload.web.dto.file.FileTrasferDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class FileController {

	private final FileService fileService;
	
	@GetMapping({"/", "/filemanagement"})
	public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		model.addAttribute("user", principalDetails.getUser());
		return "filemanagement/filemanagement";
	}
	
	@PostMapping("/file/transfer")
	public String fileTransfer(FileTrasferDto fileTrasferDto) {
		
		fileService.파일업로드(fileTrasferDto);
		
		return "redirect:/";
	}
}
