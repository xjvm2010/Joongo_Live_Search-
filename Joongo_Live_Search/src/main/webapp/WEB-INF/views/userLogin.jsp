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
                <button onclick="submit" class="btn mt-4 w-100 text-white" style="background-color: #000080">로그인하기</button>
                <p class="text-center mt-4"><a href="/userSign" class="link-secondary">회원가입 하러 가기</a></p>
				<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
					<font color="red">
						<p>${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</p>
						<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
					</font>
				</c:if>
            </form>
        </div>
    </div>
</div>


</body>

<script type="text/javascript">
function searchBtn() {
	searchVal = $("#searchWord").val();
	tr_HTML = "";
	$.ajax({
		url : "/search/getItem",
		type : 'POST',
		async : false,
		data : {
			searchWord : searchVal
		},
		success : function(data) {
			tr_HTML += writeTr_Jongo(data);
			$("#tableBody").empty().append(tr_HTML);
		},
		error : function(jqXHR, status, e) {
			console.log(status + " : " + e);
		}
	});
}

	
</script>
</html>