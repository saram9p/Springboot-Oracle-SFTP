<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<body>
	<h1>파일관리시스템</h1>
	<div style="display: flex;">
		<h2>${user.name} 님 환영합니다.</h2>
		<button onclick="location.href='/logout'">로그아웃</button>
	</div>

	<table id="table_id" class="display">
	    <thead>
            <tr>
                <th>Name</th>
                <th>Position</th>
                <th>Office</th>
                <th>Extn.</th>
                <th>Start date</th>
                <th>Salary</th>
            </tr>
	    </thead>
	    <tbody>

	    </tbody>
	</table>

</body>
<%@ include file="../layout/footer.jsp"%>