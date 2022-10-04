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
<script src="https://cdn.jsdelivr.net/sockjs/1/sockjs.min.js"></script>

<style type="text/css">
</style>

</head>
<body>

	<div class="container">
		<div class="mt-3 mb-2"
			style="display: flex; justify-content: space-between;">
			<div>
				<img alt="logo" src="/resources/img/joongo_live_search_TEXT.png" onclick="location.href='/'" style="cursor: pointer;">
			</div>
			<div>
				<button id="btnGroupDrop1" type="button" class="btn btn-primary" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="true">회원</button>
				<div class="dropdown-menu" aria-labelledby="btnGroupDrop1" data-popper-placement="bottom-end">
					<a class="dropdown-item" href="#">Dropdown link</a>
					<a class="dropdown-item" href="#">Dropdown link</a>
				</div>
			</div>
		</div>
		<div class="row mt-5 pt-2">
			<div class="input-group mb-1">
				<input type="text" class="form-control" id="searchWord" placeholder="Recipient's username" aria-label="Recipient's username" aria-describedby="button-addon2">
				<button class="btn btn-primary" onclick="searchBtn()" type="button" id="button-addon2">Search</button>
			</div>
			<div class=" mb-2" style="display: flex; justify-content: space-between;">
				<button type="button" class="btn btn-primary" onclick="liveSearchCall()">라이브  서치</button>
				<button type="button" class="btn btn-primary" onclick="webSocket.sendChat(searchVal,'testData')">testPop</button>
			</div>

			<hr>	

			<div class="bs-component">
				<table class="table table-hover text-center">
					<thead>
						<tr class="table-primary">
							<th scope="col" style="width: 50%">제목</th>
							<th scope="col" style="width: 10%">가격</th>
							<th scope="col" style="width: 15%">거래희망지역</th>
							<th scope="col" style="width: 10%">출처</th>
							<th scope="col" style="width: 10%">바로가기</th>
						</tr>
					</thead>
					<tbody id="tableBody">
						<tr>
							<td colspan="5">검색결과가 없습니다.</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="mt-3 mb-3" style="text-align: center;">
				<button type="button" class="btn btn-primary btn-lg"
					onclick="moreContent()">more</button>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	var tr_HTML = "";
	var searchVal = "";
	// 페이징 시작값;
	var startIndex = 1;
	//알림 객채
	var notification;
	
	
	function liveSearchCall() {
		searchVal = $("#searchWord").val();
		
		if(ntfcPermission()){
			webSocket.init({ url: '<c:url value="/live"/>' });
			/* webSocket.sendEnter(searchVal); */
		}
	}
	var webSocket = {
		//시작함수
		init : function(param) {
			this._url = param.url;
			this._initSocket();
		},
		
		//새로운아이템 전송처리
		sendChat : function(searchKeyWord, data) {
			this._sendMessage(searchKeyWord, 'NEW_ITEM', data);
		},
		
		//세션생성 처리
		sendEnter : function(searchKeyWord) {
			this._sendMessage(searchKeyWord, 'CREATE_LIVE_SESSION', '');
		},

		//받은 메세지 CMD코드에 따라서 분기 처리
		receiveMessage : function(msgData) {
			// 새로운 아이템정보를 받은경우 처리
			if (msgData.cmd == 'NEW_ITEM') {
				
			}
			// 분기처리 확장용 추가 커맨드 관련.
			else if (msgData.cmd == 'CREATE_LIVE_SESSION') {
				
			}
		},
		closeMessage : function(str) {
			//연결끊김 처리
		},
		
		//소캣닫기
		disconnect : function() {
			this._socket.close();
		},
		
		//소캣열기
		_initSocket : function() {
			//소캣생성
			this._socket = new SockJS(this._url);

			//소캣생성 후 처리
			this._socket.onopen = function(evt) {
				console.log("onopen : " + evt);
			};

			//메세지를 받고나서  처리
			this._socket.onmessage = function(evt) {
				console.log("onmessage: " + evt);
				notificationFn(evt);
			};

			//소켓삭제 후 추가동작
			this._socket.onclose = function(evt) {
				console.log("socket.onclose: " + evt);
			}
		},
		
		//메세지 전송
		_sendMessage : function(searchKeyWord, cmd, data) {
			var msgData = {
				searchKeyWord : searchKeyWord,
				cmd : cmd,
				data : data
			};
			
			//제이슨데이터 파싱
			var jsonData = JSON.stringify(msgData);
			//메세지 전송
			this._socket.send(jsonData);
		}

	}

	//알림 권한 요청
	function ntfcPermission() {
		// 브라우저 지원 여부 체크
		if (!("Notification" in window)) {
			alert("데스크톱 알림을 지원하지 않는 브라우저입니다.");
			return false;
		}

		Notification.requestPermission(function(result) {
			if (result == 'denied') {
				alert('알림을 차단하셨습니다.\n브라우저의 사이트 설정에서 변경하실 수 있습니다.');
				return false;
			}
		});

		return true;
	}

	function notificationFn(msg) {

		// 데스크탑 알림
		notification = new Notification("Joongo_Live_Search", {
			body : msg
		});

		notification;

		//3초뒤 알림 닫기
		setTimeout(function() {
			notification.close();
		}, 5000);

	}

	function searchBtn() {
		searchVal = $("#searchWord").val();
		tr_HTML = "";
		$.ajax({
			url : "/search/getContent",
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

	function writeTr_Jongo(data) {
		var tbody = $("#tableBody");
		tr_HTML = "";
		for (var i = 0; i < data.length; i++) {
			var encodeUrl;
			var origin;
			var price = data[i].price.toLocaleString('ko-KR');
			var location = data[i].mainLocationName;
			if (!location) {
				location = "미기재";
			}
			if (data[i].hasOwnProperty('seq')) {
				var url = "https://web.joongna.com/product/detail/"
						+ data[i].seq;
				encodeUrl = encodeURI(url);
				origin = "중고나라";
				price = price + "원";
			} else {
				encodeUrl = encodeURI(data[i].urlLink);
				origin = "당근마켓";
			}

			tr_HTML += "\n";
			tr_HTML += "<tr class='light'>";
			tr_HTML += "<td style='text-align: start;'>" + data[i].title
					+ "</td>";
			tr_HTML += "<td>" + price + "</td>";
			tr_HTML += "<td>" + location + "</td>";
			tr_HTML += "<td>" + origin + "</td>";
			tr_HTML += "<td style='cursor: pointer;' onclick=window.open('"
					+ encodeUrl + "')>바로가기</td>";
			tr_HTML += "</tr>";
		}
		return tr_HTML;
	}

	function moreContent() {
		startIndex++;
		tr_HTML = "";
		$.ajax({
			url : "/search/getMoreContent",
			type : 'POST',
			async : false,
			data : {
				searchWord : searchVal,
				startIndex : startIndex
			},
			success : function(data) {
				tr_HTML += writeTr_Jongo(data);
				$("#tableBody").append(tr_HTML);
			},
			error : function(jqXHR, status, e) {
				console.log(status + " : " + e);
			}
		});
	}
</script>
</html>