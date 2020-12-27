package com.black_queen.room_management_service.services;

import com.black_queen.commons.exceptions.RoomNotFoundException;
import com.black_queen.commons.models.Room;

public interface RoomManagementService {
	
	public Room createRoom(String userId) throws Exception;
	
	public boolean joinRoom(String roomId, String userId) throws RoomNotFoundException, Exception;

	public Room getRoom(String id) throws RoomNotFoundException;

}
