package VehicleSelection;

import java.awt.*;

public class CarCategories {
    private String vehicleNum;
    private String ModelNum;
    private String Brand;
    private int YOM;
    private String color;
    private String fuelType;
    private double rentPrice;
    private int mileage;
    private String features;

    public CarCategories(String vehicleNum, String modelNum, String brand, int YOM, String color, String fuelType, double rentPrice, int mileage, String features) {
        this.vehicleNum = vehicleNum;
        ModelNum = modelNum;
        Brand = brand;
        this.YOM = YOM;
        this.color = color;
        this.fuelType = fuelType;
        this.rentPrice = rentPrice;
        this.mileage = mileage;
        this.features = features;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public String getModelNum() {
        return ModelNum;
    }

    public String getBrand() {
        return Brand;
    }

    public int getYOM() {
        return YOM;
    }

    public String getColor() {
        return color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getRentPrice() {
        return rentPrice;
    }



    public int getMileage() {
        return mileage;
    }

    public String getFeatures() {
        return features;
    }
}
