<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 전송</title>
</head>
<body>

<!-- 파일 인풋 -->
<div class="container">
	<form action="/file/transfer" method="post">
	<div class="row">
		<div class="col">
			<input type="text"  name="username" placeholder="유저네임" />
		</div>
		<div class="col">
			<input type="password"  name="password" placeholder="비밀번호" />
		</div>
		<div class="col">
			<input type="text"  name="host" placeholder="전송지 IP" />
		</div>
		<div class="col">
			<input type="text"  name="port" placeholder="포트" />
		</div>
		<div class="col">
			<input type="text"  name="outputFilePath" placeholder="파일경로" />
		</div>
		<div class="col">
			<input type="text"  name="destinationFilePath" placeholder="전송경로" />
		</div>
		<div class="col">
			<input type="text"  name="filename" placeholder="파일이름" />
		</div>
		<button>전송하기</button>
	</div>
	</form>
</div>

</body>
</html>