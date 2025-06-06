package com.example.warehouse.serviceimpl;

import com.example.warehouse.dto.request.RoomRequest;
import com.example.warehouse.dto.response.RoomResponse;
import com.example.warehouse.entity.Room;
import com.example.warehouse.entity.WareHouse;
import com.example.warehouse.exception.WareHouseNotFindByIdException;
import com.example.warehouse.mapper.RoomMapper;
import com.example.warehouse.repository.RoomRepository;
import com.example.warehouse.repository.WareHouseRepository;
import com.example.warehouse.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final WareHouseRepository wareHouseRepository;
    private final RoomMapper roomMapper;




    @Override
    public RoomResponse creatRoom(RoomRequest roomRequest, String wareHouseId) {
        WareHouse wareHouse = wareHouseRepository.findById(wareHouseId).orElseThrow(()->new WareHouseNotFindByIdException("WareHouse Not Find!!"));
        Room room = roomMapper.toEntity(roomRequest,new Room());
        room.setWarehouse(wareHouse);
        wareHouseRepository.save(wareHouse);
        roomRepository.save(room);
        return roomMapper.toResponse(room);
    }
}
