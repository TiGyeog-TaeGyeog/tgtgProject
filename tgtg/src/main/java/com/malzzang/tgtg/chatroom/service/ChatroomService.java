package com.malzzang.tgtg.chatroom.service;

import java.util.ArrayList;
import java.util.List;

import com.malzzang.tgtg.chatroom.model.Chatroom;

public interface ChatroomService {
	
	final List<Chatroom> textRooms = new ArrayList<>();
	
	public Chatroom findTextRoom();
}