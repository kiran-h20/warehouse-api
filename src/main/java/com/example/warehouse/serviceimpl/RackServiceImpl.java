package com.example.warehouse.serviceimpl;

import com.example.warehouse.dto.request.RackRequest;
import com.example.warehouse.dto.response.RackResponse;
import com.example.warehouse.entity.Block;
import com.example.warehouse.entity.Rack;
import com.example.warehouse.entity.RackedBlock;
import com.example.warehouse.entity.Room;
import com.example.warehouse.exception.BlockNotFoundByIdException;
import com.example.warehouse.mapper.RackMapper;
import com.example.warehouse.repository.BlockRepository;
import com.example.warehouse.repository.RackRepository;
import com.example.warehouse.service.RackService;
import com.example.warehouse.shared.BarCodeGeneration;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@AllArgsConstructor
@Service
public class RackServiceImpl implements RackService {

    private final RackRepository rackRepository;
    private final BlockRepository blockRepository;
    private final RackMapper rackMapper;

    @Override
    public RackResponse createRoom(RackRequest rackRequest,String userId) {

                Block block = blockRepository.findById(userId).orElseThrow(() -> new BlockNotFoundByIdException("Block not found with id: " + userId));
        if(block instanceof RackedBlock rackedBlock){
            Rack rack = rackMapper.toEntity(rackRequest, new Rack());
            rack.setRackedblock(rackedBlock);
            blockRepository.save(block);
            rackRepository.save(rack);
            return rackMapper.toResponse(rack);
        } else {
            throw new BlockNotFoundByIdException("Block is not of type RackedBlock with id: " + userId);
        }
    }

    @Override
    public byte[] generateQRCodeImage(String rackId) {

        Rack rack= rackRepository.findById(rackId).orElseThrow(()->new RuntimeException("Rack not found"));
        RackedBlock rackedBlock=rack.getRackedblock();
        Room room= rackedBlock.getRoom();

        String content=String.format("""
                {
                "roomId":"%s",
                "blockId":"%s",
                "rackId":"%s"
                }
                """,room.getRoomId(),rackedBlock.getBlockId(),rack.getRackId());

        byte[] bytes=null;
        try{
            bytes =BarCodeGeneration.generateQRCodeImage(content,100,100);
            return bytes;
        }catch (IOException | WriterException e){
            System.out.println("barcode not gen");
        }
        return bytes;
    }




}
