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
<title>userInfo</title>
<link rel="stylesheet" type="text/css"
	href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="/resources/css/font-awesome.min.css">

<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="/resources/js/bootstrap.bundle.min.js"></script>

<style type="text/css">
</style>

</head>
<body>
	<div class="container">
		<div class="mt-3 mb-2">
			<img alt="logo" src="/resources/img/joongo_live_search_TEXT.png" onclick="location.href='/'" style="cursor: pointer;">
		</div>
		
		<div class="row">
			<div class="col-md-12">
				<h1>search history</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div style="display: flex;align-items: flex-start;justify-content: flex-end;">
					<div style="margin-right: auto;">
						<div class="btn btn-info"> btn 1</div>
						<div class="btn btn-info"> btn 2</div>
					</div>
					
					<div class="d-flex justify-content-end">
						<div class="form-group">
							<select class="form-select" id="searchType">
								<option value="1">제목</option>
								<option value="2">내용</option>
								<option value="3">이름</option>
							</select>
						</div>
						<div class="input-group mb-3" style="width: 300px;" id="searchBox">
							<input id="searchValue" type="search" class="form-control" aria-describedby="button-addon2">
							<button id="searchBtn" class="btn btn-secondary" type="button">검색</button>
						</div>
					</div>
				</div>
				<table class="table table-hover">
					<tbody>
						<tr class="table-active">
							<th scope="row">순서</th>
							<td>제목</td>
							<td>가격</td>
							<td>거래희망 지역</td>
							<td>바로가기</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	
</script>
</html>