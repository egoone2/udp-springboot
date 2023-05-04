package com.itm.udpserver.repository;

import com.itm.udpserver.entity.BasePacket;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EquipRepository {

    private final Map<Short, BasePacket> equipmentMap = new HashMap<>();

    public void update(BasePacket packet) {
        equipmentMap.put(packet.getEquipId(), packet);
    }
}
