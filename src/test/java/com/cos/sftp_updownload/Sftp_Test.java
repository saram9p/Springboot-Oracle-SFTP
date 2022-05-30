package com.cos.sftp_updownload;

import java.io.ByteArrayInputStream;
import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.mockito.internal.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class Sftp_Test {

	
	private static final Logger log = LoggerFactory.getLogger(Sftp_Test.class);

	@Test
	public void test1() {
        //JSch 객체 생성
        JSch jsch = new JSch();
        
        //String username, String password, String host, int port, String path, byte[] data, String filename
//        String username = StringUtil.fixNull(param.get("username"));
//        String password = StringUtil.fixNull(param.get("password"));
//        String host = StringUtil.fixNull(param.get("host"));
//        int port = Integer.parseInt(StringUtil.fixNull(param.get("port"), "-1"));
//        String path = StringUtil.fixNull(param.get("path"));
//        byte[] data = (byte[]) param.get("data");
//        String filename = StringUtil.fixNull(param.get("filename"));
        
        String username = "root"; // 계정
        String password = "tpa%1437!fWj16&d4r59Y"; // 비밀번호
        String host = "192.168.241.128"; // 전송지 ip
        int port = 22; // 포트
        String outputFilePath = "F:/다운로드/"; // 파일경로
        String destiationFilePath = "/home/"; // 전송 경로
        //byte[] data = (byte[]) param.get("data");
        String fileName = "test.txt";
		
		
//        if ( username.length() <= 0 || password.length() <= 0 || host.length() <= 0
//           || port < 0 || path.length() <= 0 || data.length <= 0 || filename.length() <= 0 ) {
//           return;
//        }

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
