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
        short material = data[9];
        int location = getShortValueFromTwoBytes(new byte[]{data[10], data[11]});        // TODO в случае чего вернуть в Short
        int holeName = getShortValueFromTwoBytes(new byte[]{data[12], data[13]});
        short gradename = data[14];

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



    public static byte[] shortToBytes(int s) {
        byte[] result = new byte[2];
        result[0] = (byte) (s >> 8);
        result[1] = (byte) (s & 0xFF);
        return result;
    }

    public static String[] shortToHexBytes(int a) {
        String[] result = new String[2];
        result[0] = Integer.toHexString(a >> 8);
        result[1] = Integer.toHexString(a & 0xFF);
        return result;
    }

}
