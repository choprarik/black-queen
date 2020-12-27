/**
 * 
 */
package com.black_queen.room_management_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.black_queen.commons.exceptions.InvalidRequestException;
import com.black_queen.commons.exceptions.RoomNotFoundException;
import com.black_queen.commons.models.IdDTO;
import com.black_queen.commons.models.JoinRoomDTO;
import com.black_queen.commons.models.Room;
import com.black_queen.room_management_service.services.RoomManagementService;

/**
 * @author rakshit.chopra
 *
 */
@RestController
public class RoomManagementController {

	@Autowired
	RoomManagementService roomService;
	
	private Logger LOG = LoggerFactory.getLogger(RoomManagementService.class);
	
	/**
	 * GET API returns a room for the provided id.
	 * @param id - Room id
	 * @return - Room object
	 * @throws RoomNotFoundException
	 * @throws InvalidRequestException
	 */
	@GetMapping("/room")
	public Room getRoom(@RequestParam("id") String id) throws RoomNotFoundException, InvalidRequestException {
		LOG.info("In room get api with id " + id);
		if (id.isEmpty()) {
			throw new InvalidRequestException();
		}
		return roomService.getRoom(id);
	}
	
	
	@PostMapping("/room")
	public Room createRoom(@RequestBody IdDTO userIdDto) throws Exception {
		return roomService.createRoom(userIdDto.getId());
	}
	
	@PostMapping("/room/join")
	public void joinRoom(@RequestBody JoinRoomDTO joinInfo) throws Exception {
		boolean isRoomJoined = roomService.joinRoom(joinInfo.getRoomId(), joinInfo.getUserId());
		if (!isRoomJoined) {
			throw new Exception();
		}
	}
}
