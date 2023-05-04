package com.itm.udpserver.service;

import com.itm.udpserver.entity.BasePacket;
import com.itm.udpserver.repository.EquipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacketService {

    private final static int coordCenterX = 82500;
    private final static int coordCenterY = 21500;

    private final EquipRepository equipRepository;
    @Autowired
    public PacketService(EquipRepository equipRepository) {
        this.equipRepository = equipRepository;
    }

    public BasePacket enrichEntity(byte[] data) {

        BasePacket basePacket = new BasePacket();
        short equipName = (short) getShortValueFromTwoBytes(new byte[]{data[0], data[1]});
        int coordX = (short) (getShortValueFromTwoBytes(new byte[]{data[2], data[3]}) - 32768) + coordCenterX;
        int coordY = (short) (getShortValueFromTwoBytes(new byte[]{data[4], data[5]}) - 32768) + coordCenterY;
        double coordAzimuth = castUnsignedByteToShort(data[6]) * 1.0 / 256 * 360;
        int status = getShortValueFromTwoBytes(new byte[]{data[7], data[8]});
        short material = castUnsignedByteToShort(data[9]);
        int location = getShortValueFromTwoBytes(new byte[]{data[10], data[11]});
        int holeName = getShortValueFromTwoBytes(new byte[]{data[12], data[13]});
        short gradename = castUnsignedByteToShort(data[14]);


        
        basePacket.setEquipId(equipName);
        basePacket.setCoordX(coordX);
        basePacket.setCoordY(coordY);
        basePacket.setCoordAzimuth(coordAzimuth);
        basePacket.setStatus(status);
        basePacket.setMaterial(material);
        basePacket.setLocation(location);
        basePacket.setHoleName(holeName);
        basePacket.setGradename(gradename);

        return basePacket;
    }

    private static int getShortValueFromTwoBytes(byte[] arr) {
        return (arr[0] & 0xFF) << 8 | (arr[1] & 0xFF);
    }

    private static short castUnsignedByteToShort(byte data) {    // принимает byte, переводит в short, без знака
        return (short) ((byte) (data - 128) + 128);
    }


    public void update(BasePacket packet) {
        equipRepository.update(packet);
    }
}
