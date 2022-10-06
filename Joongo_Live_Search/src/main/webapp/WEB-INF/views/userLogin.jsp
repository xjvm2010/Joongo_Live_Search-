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
<link rel="icon" href="/resources/img/joongo_live_search_LOGO.png">
<title>userLogin</title>
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
<div class="container pt-5">
    <div class="card mb-3 text-center mx-auto" style="width: 18rem;">
        <div class="card-body">
            <h5 class="mb-5">로그인 하기</h5>
            <form action="/login" method="post">
                <div class="form-floating mb-3">
                    <input type="text" class="form-control" id="inputID" placeholder="아이디를 입력해주세요." name="id">
                    <label for="floatingInput">아이디를 입력해주세요.</label>
                </div>
                <div class="form-floating">
                    <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호를 입력해주세요." name="pw">
                    <label for="floatingPassword">비밀번호를 입력해주세요.</label>
                </div>
                <button type="submit" class="btn mt-4 w-100 text-white" style="background-color: #000080">로그인하기</button>
                <p class="text-center mt-4"><a href="/userSign" class="link-secondary">회원가입 하러 가기</a></p>
                <div th:if="${param.error}">
		            <div class="alert alert-danger">
		                	사용자ID 또는 비밀번호를 확인해 주세요.
		            </div>
       			</div>
            </form>
        </div>
    </div>
</div>


</body>

<script type="text/javascript">
	
</script>
</html>