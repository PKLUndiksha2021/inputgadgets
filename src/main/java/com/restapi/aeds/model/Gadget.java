package com.restapi.aeds.model;

import lombok.Data;
import lombok.experimental.Tolerate;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Gadget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String serialNumber;
    private String brand;
    private String model;
    private double price;

    @Tolerate
    public Gadget() {
    }

}
