package com.cos.sftp_updownload.web.dto.file;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class FileTrasferDto {
	public String username;
	public String password;
	public String host;
	public int port;
	public String outputFilePath;
	public String destinationFilePath;
	public String filename;
}
