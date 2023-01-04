package com.api.parkingcontrol.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="tb_car")
public class CarModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 7)
    private String licensePLateCar;

    @Column(nullable = false, length = 70)
    private String brandCar;

    @Column(nullable = false, length = 70)
    private String modelCar;

    @Column(nullable = false, length = 70)
    private String color;

    @OneToOne
    private ParkingSpotModel parkingSpot;

    public CarModel(){

    }

    public CarModel(Long id, String licensePLateCar, String brandCar, String modelCar, String color) {
        this.id = id;
        this.licensePLateCar = licensePLateCar;
        this.brandCar = brandCar;
        this.modelCar = modelCar;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePLateCar() {
        return licensePLateCar;
    }

    public void setLicensePLateCar(String licensePLateCar) {
        this.licensePLateCar = licensePLateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ParkingSpotModel getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpotModel parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
