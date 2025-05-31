package com.example.warehouse.service;

import com.example.warehouse.dto.request.BlockRequest;
import com.example.warehouse.dto.request.RoomRequest;
import com.example.warehouse.dto.response.BlockResponse;
import com.example.warehouse.dto.response.RoomResponse;

public interface BlockService {

    BlockResponse creatBlock(BlockRequest blockRequest, String roomID);
}

