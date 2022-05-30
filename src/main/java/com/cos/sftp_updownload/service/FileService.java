package com.cos.sftp_updownload.service;

import java.util.Properties;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cos.sftp_updownload.web.dto.file.FileTrasferDto;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

@Service
public class FileService {

	private static final Logger log = LoggerFactory.getLogger(FileService.class);

	@Transactional
	public void 파일업로드(FileTrasferDto fileTrasferDto) { // 유틸 에 넣어도 되지않나?
		System.out.println(fileTrasferDto);
		
		//JSch 객체 생성
        JSch jsch = new JSch();
     
        String username = fileTrasferDto.getUsername(); // 계정
        String password = fileTrasferDto.getPassword(); // 비밀번호
        String host = fileTrasferDto.getHost(); // 전송지 ip
        int port = fileTrasferDto.getPort(); // 포트
        String outputFilePath = fileTrasferDto.getOutputFilePath(); // 파일경로
        String destiationFilePath = fileTrasferDto.getDestinationFilePath(); // 전송 경로
        //byte[] data = (byte[]) param.get("data");
        String fileName =fileTrasferDto.getFilename();
		
        //Session 생성, 원격서버에 대한 접속정보
        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
		try {
			session = jsch.getSession(username, host, port);
			
	        session.setPassword(password);
			
	        // properties 객체생성, StrictHostKeyChecking 이라는 설정값을 no 로 설정
	        Properties config = new Properties();
	        config.put("StrictHostKeyChecking", "no");
	        session.setConfig(config);
			
	        //서버에 접속 
	        session.connect();
	        System.out.println(session);
	        
	        //sftp 채널열기
	         channel = session.openChannel("sftp");
			
	        //원격지에 명령어 호출, 채널을 ssh용 채널 객체로 캐스팅한다.
	        channel.connect();
	        channelSftp = (ChannelSftp) channel;
			
	        //
	        String filePath = outputFilePath + fileName;
			String DestinyPath = destiationFilePath + fileName;
	        //ByteArrayInputStream in = new ByteArrayInputStream(data);
			
	        //파일업로드
	        //channelSftp.cd(path);
	        channelSftp.put(filePath, DestinyPath);
	        
		} catch (JSchException e) {
			log.error("JSchException", e);
		} catch (SftpException e1) {
			log.error("SftpException", e1);
		} finally {
	        //채널, 세션 연결해제
			if(channelSftp != null) channelSftp.disconnect();
	        if(channel != null) channel.disconnect();
	        if(session != null) session.disconnect();
		}
	}
	
}
