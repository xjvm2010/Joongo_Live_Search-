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
				<table class="table table-hover text-center">
					<thead>
						<tr class="table-primary">
							<th scope="col" style="width: 50%">제목</th>
							<th scope="col" style="width: 25%">가격</th>
							<th scope="col" style="width: 10%">출처</th>
							<th scope="col" style="width: 10%">바로가기</th>
						</tr>
					</thead>
					<tbody id="tableBody">
						<tr>
							<td colspan="4"> 검색결과가 없습니다. </td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-5" style="text-align: center;">
				<button type="button" class="btn btn-primary btn-lg" onclick="location.href='/'" >more</button>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	var tr_HTML="";
	
	function searchBtn() {
		var  searchVal = $("#searchWord").val();
		tr_HTML="";
		console.log("load Joongo");
		$.ajax({
			url : "/search/getContent",
			type : 'POST',
			async : false,
			data : {
				searchWord : searchVal
			},
			success : function(data) {
				tr_HTML += writeTr_Jongo(data);
			},
			error : function(jqXHR, status, e) {
				console.log(status + " : " + e);
			}
		});
		
		console.log("load Daangn");
		$.ajax({
			url : "/search/getContent2",
			type : 'POST',
			async : false,
			data : {
				searchWord : searchVal
			},
			success : function(data) {
				tr_HTML += writeTr_Dang(data);
				$("#tableBody").empty().append(tr_HTML);
			},
			error : function(jqXHR, status, e) {
				console.log(status + " : " + e);
			}
		});
	}
	
	
	function writeTr_Jongo(data) {
		
		var tbody = $("#tableBody");		
		var tr_HTML="";
		
		for (var i = 0; i < data.length; i++) {
			var url = "https://web.joongna.com/product/detail/"+data[i].seq;
			var encodeUrl = encodeURI(url);
			var price = data[i].price.toLocaleString('ko-KR');
			
			tr_HTML +="\n";
			tr_HTML +="<tr class='light'>";
			tr_HTML +=	"<td style='text-align: start;'>"+data[i].title+"</td>";
			tr_HTML +=	"<td>"+price+"원</td>";
			tr_HTML +=	"<td>중고나라</td>";
			tr_HTML +=	"<td style='cursor: pointer;' onclick=window.open('"+encodeUrl+"')>바로가기</td>";
			tr_HTML +="</tr>";
		}
		return tr_HTML;
	}
	
	function writeTr_Dang(data) {
		var tr_HTML="";
		
		for (var i = 0; i < data.length; i++) {
			tr_HTML +="\n";
			tr_HTML +="<tr class='light'>";
			tr_HTML +=	"<td style='text-align: start;'>"+data[i].title+"</td>";
			tr_HTML +=	"<td>"+data[i].price+"</td>";
			tr_HTML +=	"<td>당근</td>";
			tr_HTML +=	"<td style='cursor: pointer;' onclick=window.open('"+data[i].urlLink+"')>바로가기</td>";
			tr_HTML +="</tr>";
		}
		return tr_HTML;
	}
	
	function writeTable_Jongo() {
		
	}
</script>
</html>