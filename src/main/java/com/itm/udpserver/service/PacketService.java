package com.itm.udpserver.service;

import com.itm.udpserver.entity.BasePacket;


public class PacketService {

    public static BasePacket enrichEntity(byte[] data) {

        BasePacket basePacket = new BasePacket();
        short equipName = (short) getShortValueFromTwoBytes(new byte[]{data[0], data[1]});
        short coordX = (short) getShortValueFromTwoBytes(new byte[]{data[2], data[3]});
        short coordY = (short) getShortValueFromTwoBytes(new byte[]{data[4], data[5]});
        double coordAzimuth = data[6] * 1.0 / 256 * 360;
        int status = getShortValueFromTwoBytes(new byte[]{data[7], data[8]});
        short material = (short) ((byte) (data[9] - 128) + 128);
        int location = getShortValueFromTwoBytes(new byte[]{data[10], data[11]});
        int holeName = getShortValueFromTwoBytes(new byte[]{data[12], data[13]});
        short gradename = (short) ((byte) (data[14] - 128) + 128);

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



}
