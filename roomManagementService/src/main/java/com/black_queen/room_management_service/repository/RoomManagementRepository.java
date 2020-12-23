package com.black_queen.room_management_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.black_queen.commons.models.Room;

public interface RoomManagementRepository extends MongoRepository<Room, String>{

}
