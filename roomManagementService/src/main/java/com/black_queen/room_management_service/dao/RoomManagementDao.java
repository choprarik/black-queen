package com.black_queen.room_management_service.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.black_queen.commons.exceptions.RoomNotFoundException;
import com.black_queen.commons.exceptions.UserNotFoundException;
import com.black_queen.commons.models.Room;
import com.black_queen.room_management_service.repository.RoomManagementRepository;

@Service
public class RoomManagementDao {
	
	@Autowired
	RoomManagementRepository roomRepo;
	
	/**
	 * Get room for id from DB.
	 * @param id
	 * @return room item
	 * @throws UserNotFoundException
	 */
	public Room getRoom(String id) throws RoomNotFoundException {
		Room room = null;
		try {
			room = roomRepo.findById(id).get();
			if (room == null) {
				throw new RoomNotFoundException();
			}
		} catch(Exception e) {
			throw e;
		}
		return room;
	}
	
	/**
	 * Add a room to DB.
	 * @param room
	 * @return id of added room.
	 * @throws Exception
	 */
	public String addRoom(Room room) throws Exception {
		String id = null;
		try {
			Room newRoom = roomRepo.save(room);
			if (newRoom != null) {
				id = newRoom.getId();
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw e;
		}
		return id;
	}
	
	/**
	 * Update an existing room in DB.
	 * @param room
	 * @return updated room
	 * @throws InternalServerException
	 */
	public Room updateRoom(Room room) {
		Room updatedRoom = null;
		try {
			updatedRoom = roomRepo.save(room);
		} catch (Exception e) {
			throw e;
		}
		return updatedRoom;
	}

	/**
	 * Delete room from DB.
	 * @param id
	 */
	public void deleteRoom(String id) {
		try {
			roomRepo.deleteById(id);
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * Returns true if room exists.
	 * @param id
	 * @return
	 */
	public boolean isRoomExists(String id) {
		return roomRepo.existsById(id);
	}

}
