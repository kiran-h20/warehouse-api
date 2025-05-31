package com.example.warehouse.service;


import com.example.warehouse.dto.request.RoomRequest;
import com.example.warehouse.dto.response.RoomResponse;

public interface RoomService {
    RoomResponse creatRoom(RoomRequest roomRequest,String wareHouseId);
}
