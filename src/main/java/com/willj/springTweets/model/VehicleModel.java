package com.willj.springTweets.model;

public class VehicleModel {

    private VehicleModel vehicle;

    private String makeOfVehicle;
    private String carType;
    private String modelOfVehicle;
    private String plateNum;

    public VehicleModel() {
    }

    public VehicleModel(String makeOfVehicle, String carType, String modelOfVehicle, String plateNum) {

        this.makeOfVehicle = makeOfVehicle;
        this.carType = carType;
        this.modelOfVehicle = modelOfVehicle;
        this.plateNum = plateNum;
    }

    public VehicleModel(VehicleModel vehicle) {
    }

    public String showCarDetails() {

        return "Vehicle Details " +
                "\nMake of Vehicle: " + makeOfVehicle +
                "\nType of Vehicle: " + carType +
                "\nModel of Vehicle: " + modelOfVehicle +
                "\nPlate Number: " + plateNum + "\n";
    }

    public String noVehicleOnFile() {
        return "No vehicle on file." + "\n";
    }

    public void saveVehicle(String makeOfVehicle, String carType, String modelOfVehicle, String plateNum) {
        vehicle = new VehicleModel(makeOfVehicle, carType, modelOfVehicle, plateNum);
    }

    public VehicleModel getVehicle() {
        return vehicle;
    }

}
