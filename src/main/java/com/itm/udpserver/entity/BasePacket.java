package com.itm.udpserver.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasePacket {

    private Short equipId;

    private Short coordX;

    private Short coordY;

    private Double coordAzimuth;

    private Integer status;

    private Short material;      // оно же deep

    private Integer location;

    private Integer holeName;

    private Short gradename;     // оно же condition_id/assign_lu

    public BasePacket() {

    }

}
