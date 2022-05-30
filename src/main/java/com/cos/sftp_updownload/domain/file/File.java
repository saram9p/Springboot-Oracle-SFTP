package com.cos.sftp_updownload.domain.file;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.sftp_updownload.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // 번호 증가 전략이 데이터베이스를 따라간다.
	private  int id; // 서비스하는 프로그램이 아니라서 int로 함
	
	@Column(length = 100, unique = true) // 제약조건
	private String filename; // 파일이름
	@Column(nullable = false)
	private String filesize; // 파일크기
	@Column(nullable = false)
	private String caption; // 설명
	private String fileUrl; // 파일을 전송받아서 그 파일을 서버에 특정 폴더에 저장
	
	@JoinColumn(name = "userId")
	@ManyToOne(fetch = FetchType.EAGER) // 이미지를 select하면 조인해서 User정보를 같이 들고옴
	private User user; // 1, 1
	
	private LocalDateTime createDate;
	
	@PrePersist // DB에 INSERT 되기 직전에 실행
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}
