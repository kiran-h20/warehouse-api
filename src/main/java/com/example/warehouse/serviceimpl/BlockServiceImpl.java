package com.example.warehouse.serviceimpl;

import com.example.warehouse.dto.request.BlockRequest;
import com.example.warehouse.dto.response.BlockResponse;
import com.example.warehouse.dto.response.RoomResponse;
import com.example.warehouse.entity.*;
import com.example.warehouse.exception.RoomNotFoundByIdException;
import com.example.warehouse.exception.UnSupportedBlockTypeException;
import com.example.warehouse.mapper.BlockMapper;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.RoomRepository;
import com.example.warehouse.service.BlockService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BlockServiceImpl implements BlockService {

    private final RoomRepository roomRepository;
    private final BlockMapper blockMapper;
    private final BlockRepository blockRepository;


    @Override
    public BlockResponse creatBlock(BlockRequest blockRequest, String roomID) {

        Room room = roomRepository.findById(roomID).orElseThrow(() -> new RoomNotFoundByIdException("Room is Not Exist!!"));
        Block block = switch (blockRequest.type()) {
            case RACK -> blockMapper.toEntity(blockRequest, new RackedBlock());
            case UNRACK -> blockMapper.toEntity(blockRequest, new UnrackedBlock());
            default -> throw new UnSupportedBlockTypeException(blockRequest.type() + " is Not Available!!");
        };
        block.setRoom(room);
        roomRepository.save(room);
        blockRepository.save(block);
        return blockMapper.toResponse(block);
    }
}
