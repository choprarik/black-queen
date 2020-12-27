/**
 * 
 */
package com.black_queen.room_management_service.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.exceptions.RoomNotFoundException;
import com.black_queen.commons.models.Room;
import com.black_queen.commons.models.User;
import com.black_queen.room_management_service.clients.UserClient;
import com.black_queen.room_management_service.dao.RoomManagementDao;
import com.black_queen.room_management_service.services.RoomManagementService;

/**
 * @author rakshit.chopra
 *
 */
@Service
public class RoomManagementServiceImpl implements RoomManagementService {

	@Autowired
	UserClient userClient;
	
	@Autowired
	RoomManagementDao roomDao;
	
	@Override
	public Room createRoom(String userId) throws Exception {
		Room newRoom = new Room();
		if (!userId.isEmpty()) {
			User selectedUser = userClient.getUser(userId);
			if (selectedUser != null) {
				newRoom.setHostId(userId);
				newRoom.getMembers().add(userId);
				newRoom.setId(UUID.randomUUID().toString());
				roomDao.addRoom(newRoom);
			}
		} else {
			throw new Exception();
		}
		return newRoom;
	}

	@Override
	public boolean joinRoom(String roomId, String userId) throws Exception {
		boolean isRoomJoined = false;
		if (!userId.isEmpty()) {
			User selectedUser = userClient.getUser(userId);
			if (selectedUser != null) {
				Room room = roomDao.getRoom(roomId);
				if (room != null) {
					if (room.getMembers().size() <= 6) {
						room.getMembers().add(userId);
						roomDao.updateRoom(room);
						isRoomJoined = true;
					}
				} else {
					throw new RoomNotFoundException();
				}
			}
		} else {
			throw new Exception();
		}
		return isRoomJoined;
	}

	@Override
	public Room getRoom(String id) throws RoomNotFoundException {
		return roomDao.getRoom(id);
	}

}
