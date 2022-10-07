<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta id="_csrf" name="_csrf" th:content="${_csrf.token}" />
<meta id="_csrf_header" name="_csrf_header" th:content="${_csrf.headerName}"/>
<link rel="icon" href="/resources/img/joongo_live_search_LOGO.png">
<title>userSign</title>
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/font-awesome.min.css">

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
</style>

</head>
<body>
	<div class="container">
	<div class="mt-5" style="text-align: center;">
		<img alt="logo" src="/resources/img/joongo_live_search2.png" onclick="location.href='/'" style="cursor: pointer;">
    </div>
		<div class="row mt-5 pt-2">
		<!--######################### 가입 / 수정  -START- #########################-->
		<div id="joinBox" class="box mt-2" style="width: 560px; margin: 0 auto;">
			<h3 id="joinHead" class="mb-3">회원가입</h3>
			<form id="register_form" class="needs-validation" method="get" action="#">
			
				<div class="form-group">
					<label class="col-form-label" for="inputID">ID</label> 
					<input type="text" class="form-control joinInput" placeholder="ID" id="inputID" name="id" maxlength="12" >
					<div class="invalid-feedback" id="idMsg">ID는 필수 입력사항입니다.</div>
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputPW">Password</label>
					<input type="password" class="form-control joinInput" placeholder="Password" id="inputPW" name="pw" maxlength="20" >
					<div class="invalid-feedback" id="pwMsg">password는 필수 입력사항입니다.</div>
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputPWCK">Password Check</label>
					<input type="password" class="form-control joinInput" placeholder="Password" id="inputPWCK" name="pwCk" maxlength="20" oninvalid="" >
					<div class="invalid-feedback" id="pwckMsg">입력한 비밀번호와 다릅니다.</div>
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputName">Name</label>
					<input type="text" class="form-control joinInput" placeholder="Name" id="inputName" name="name" maxlength="20">
					<div class="invalid-feedback" id="nameMsg">필수 입력사항입니다.</div>
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputEmail">Email</label>
					<input type="email" class="form-control userInput joinInput" placeholder="Email" id="inputEmail" name="email" maxlength="40" >
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputGender">Gender</label>
					<div>
						<label class="btn btn-primary active">
							<input type="radio" name="gender" autocomplete="off" value="남자" checked>남자
						</label>
						<label class="btn btn-primary">
							<input type="radio" name="gender" autocomplete="off" value="여자">여자
						</label>
					</div>
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputBirth">Birth</label>
					<input type="email" class="form-control userInput joinInput" placeholder="Birth" id="inputBirth" name="birth" maxlength="40" >
				</div>
				
				<div class="form-group">
					<label class="col-form-label" for="inputAddress">address</label>
					<input type="email" class="form-control userInput joinInput" placeholder="address" id="inputAddress" name="address" maxlength="40" >
				</div>
				
				<div class="d-grid gap-2 mt-3">
					<div class="d-flex p-2">
						<div class="me-auto  ">
							<button type="button" class="btn btn-danger" onclick="">취소</button>
						</div>
						<div>
							<input id="submit_UserInfo_Btn" class="btn btn-success" type="button" value="완료" onclick="joinSubmit()">
						</div>
					</div>
				</div>
				
			</form>
		</div>
		<!--######################### 가입 / 수정 -END- #########################-->
		</div>
	</div>
</body>

<script type="text/javascript">

var token = $("meta[name='_csrf']").attr("th:content");
var header = $("meta[name='_csrf_header']").attr("th:content");

	//유효성 체크
	function validation() {
		
		var id = "";
		var pw = "";
		var name = "";
		var validation = "Y";
			
		//id 체크
		if($("#inputID").val() == "") {
			$('#idMsg').show();
			validation = "N";
		}else {
			$('#idMsg').hide();
		}
		
		//pw 체크
		if($("#inputID").val() == "") {
			$('#pwMsg').show();
			validation = "N";
		}else {
			$('#pwMsg').hide();
		}
		
		if($("#inputPW").val() != $("#inputPWCK").val()) {
			$('#pwckMsg').show();
			validation = "N";
		}else {
			$('#pwckMsg').hide();
		}
		
		//이름 체크
		if($("#inputName").val() == "") {
			$('#nameMsg').show();
			validation = "N";
		}else {
			$('#nameMsg').hide();
		}
		
		if(validation == "Y") {
			return true;
		}else {
			alert("입력 정보를 다시 확인 해주세요.")
			return false;
		}
	}


	//가입 submit
	function joinSubmit(){
		
		if(validation()) {
			var queryString = $("#register_form").serializeArray();
			
			$.ajax({
				url : "/join",
				type : 'POST',
				async : false,
				data : queryString,
				success : function() {
					console.log("성공..");
					alert("축하합니다 가입이 완료 되었습니다.")
					window.location.href = "/";
				}, error : function(e){
					console.log("실패..");
				}
			});
		}else {
			return false;
		}
	}

	


</script>
</html>