package com.malzzang.tgtg.chatroom.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.malzzang.tgtg.anonymous.dto.AnonymousDTO;

@Service
public class ConnectedUserService {

	private final Map<Integer, List<AnonymousDTO>> chatRoomMemberList = new HashMap<>();
	
	//회원이 접속했을 때
	public void userEntered(int roomId, AnonymousDTO anonymous) {
		
		List<AnonymousDTO> memberList = chatRoomMemberList.getOrDefault(roomId, new ArrayList<>());
	    memberList.add(anonymous);
	    chatRoomMemberList.put(roomId, memberList);
    }

	//회원이 퇴장했을 때
    public void userLeft(int roomId, AnonymousDTO anonymous) {
        
        List<AnonymousDTO> memberList = chatRoomMemberList.getOrDefault(roomId, new ArrayList<>());
        
        Iterator<AnonymousDTO> iterator = memberList.iterator();
        while (iterator.hasNext()) {
            AnonymousDTO member = iterator.next();
            if (member.getAnonymousId()==anonymous.getAnonymousId()) {
                iterator.remove();
                break;
            }
        }
        
        chatRoomMemberList.put(roomId, memberList);
    }

    //현재 접속한 회원 수
    public int getConnectedUserCount(int roomId) {
    	List<AnonymousDTO> memberList = chatRoomMemberList.getOrDefault(roomId, new ArrayList<>());
        return memberList.size();
    }
    
    //특정 방의 모든 회원 가져오기
    public List<AnonymousDTO> getAllMembersInRoom(int roomId) {
        return chatRoomMemberList.getOrDefault(roomId, new ArrayList<>());
    }
    
    //회원 역할
    public List<AnonymousDTO> setRole(int roomId) {
    	System.out.println("멤버리스트>>>>>"+chatRoomMemberList.get(roomId));
    	List<AnonymousDTO> list = chatRoomMemberList.get(roomId);
    	for(int i = 0; i < list.size(); i++) {
    		System.out.println(">>>>>>"+list.get(i).getAnonymousNickname()+"   "+list.get(i).getRole());
    	}
    	//3명일때
    	if(list.size() == 3) {
    		list.get(0).setrole("answerA");
    		list.get(1).setrole("answerB");
    		list.get(2).setrole("judge");
    	}
    	
    	chatRoomMemberList.put(roomId, list);
    	
    	return chatRoomMemberList.get(roomId);
    	
    }
}
