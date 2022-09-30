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
<title>Joongo_Live_Search</title>
<link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/jquery-ui.min.css">
<link rel="stylesheet" type="text/css" href="/resources/css/font-awesome.min.css">

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
		<div class="row mt-5 pt-2">
			<div class="input-group mb-3">
				<input type="text" class="form-control" id="searchWord"
					placeholder="Recipient's username"
					aria-label="Recipient's username" aria-describedby="button-addon2">
				<button class="btn btn-primary" onclick="searchBtn()" type="button" id="button-addon2">Search</button>
			</div>
			<hr>
			<div class="bs-component">
				<table class="table table-hover">
					<thead>
						<tr>
							<th scope="col">Type</th>
							<th scope="col">Column heading</th>
							<th scope="col">Column heading</th>
							<th scope="col">Column heading</th>
						</tr>
					</thead>
					<tbody>
						<tr class="table-active">
							<th scope="row">Active</th>
							<td>Column content</td>
							<td>Column content</td>
							<td>Column content</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-5" style="text-align: center;">
				<button type="button" class="btn btn-primary btn-lg" onclick="location.href='/'" style="cursor: pointer;">more</button>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	function searchBtn() {
		var  searchVal = $("#searchWord").val();
		$.ajax({
			url : "/search/getContent",
			type : 'POST',
			async : false,
			data : {
				searchWord : searchVal
			},
			success : function(data) {
				console.log(data);
			},
			error : function(jqXHR, status, e) {
				console.log(status + " : " + e);
			}
		});
	}
</script>
</html>