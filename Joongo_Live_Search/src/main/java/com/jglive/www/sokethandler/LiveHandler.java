package com.jglive.www.sokethandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LiveHandler extends TextWebSocketHandler {

	private List<Map<String, Object>> sessionList = new ArrayList<Map<String, Object>>();
	
	//회원으로 세션을관리하는 경우.
	//Map<String, WebSocketSession> userSessionMap = new HashMap<String, WebSocketSession>();

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		ObjectMapper objMapper = new ObjectMapper();
		Map<String, Object> mapReceive = objMapper.readValue(message.getPayload(), Map.class);
		
		//요청 명령에따라 분기처리
		switch ((String)mapReceive.get("cmd")) {
		
		case "CREATE_LIVE_SESSION":
			
			Map<String, Object> map = new HashMap<String, Object>();
			//검색 키워드로 세션 저장
			map.put("searchKeyWord", mapReceive.get("searchKeyWord"));
			map.put("session", session);
			sessionList.add(map);
			break;

		case "NEW_ITEM":
			//새로운 물품이 검색되면 사용제에게 알림 전송			
			for (int i = 0; i < sessionList.size(); i++) {
				
				Map<String, Object> tempList = sessionList.get(i);
				String searchKeyWord = (String) tempList.get("searchKeyWord");
				String data = (String) mapReceive.get("data");
				WebSocketSession sess = (WebSocketSession) tempList.get("session");
				
				if (searchKeyWord.equals(mapReceive.get("searchKeyWord"))) {
					Map<String, Object> mapToSend = new HashMap<String, Object>();
					mapToSend.put("searchKeyWord", searchKeyWord);
					mapToSend.put("cmd", "NEW_ITEM");
					mapToSend.put("data", data);

					String jsonStr = objMapper.writeValueAsString(mapToSend);
					sess.sendMessage(new TextMessage(jsonStr));
				}
			}
			break;
		}

	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		super.afterConnectionClosed(session, status);

		// 사용자 세션을 리스트에서 제거
		for (int i = 0; i < sessionList.size(); i++) {
			Map<String, Object> map = sessionList.get(i);			
			WebSocketSession sess = (WebSocketSession) map.get("session");

			if (session.equals(sess)) {
				sessionList.remove(map);
				break;
			}
		}
	}

	

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// TODO Auto-generated method stub
		super.handleTransportError(session, exception);
	}

}