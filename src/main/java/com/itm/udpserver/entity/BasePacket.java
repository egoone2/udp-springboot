package com.itm.udpserver.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BasePacket {

    private Short equipId;

    private Integer coordX;

    private Integer coordY;

    private Double coordAzimuth;

    private Integer status;

    private Short material;      //  deep

    private Integer location;

    private Integer holeName;

    private Short gradename;     // condition_id/assign_lu

    public BasePacket() {

    }

}
