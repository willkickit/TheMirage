package com.willj.springTweets.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GuestModel extends VehicleModel{ 
    
    private GuestModel guest;
    private VehicleModel vehicle;
    private String firstName;
    private String lastName;
    private String address;
    private String contactNum;
    private int numOfAdults;
    private int numOfChildren;
    private Date dayIn;
    private Date dayOut;

    public GuestModel(String firstName, String lastName, String address, String contactNum, int numOfAdults,
                      int numOfChildren, Date dayIn, Date dayOut, VehicleModel vehicle) {

        super(vehicle);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNum = contactNum;
        this.numOfAdults = numOfAdults;
        this.numOfChildren = numOfChildren;
        this.dayIn = dayIn;
        this.dayOut = dayOut;
        this.vehicle = vehicle;
    }

    public GuestModel() { }

    public GuestModel(GuestModel guest) { }

    public void saveGuest(String firstName, String lastName, String address, String contactNum, int numOfAdults,
                          int numOfChildren, Date dayIn, Date dayOut, VehicleModel vehicle) {
        guest = new GuestModel(firstName, lastName, address, contactNum, numOfAdults, numOfChildren, dayIn, dayOut, vehicle);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNum() {
        return contactNum;
    }

    public int getNumOfAdults() {
        return numOfAdults;
    }

    public int getNumOfChildren() {
        return numOfChildren;
    }

    public Date getDayIn(){
        return dayIn;
    }

    public Date getDayOut(){
        return dayOut;
    }

    public GuestModel getGuest() {
        return guest;
    }

    //map to correspond guest with vehicle.
    public boolean hasVehicle(GuestModel guest) {

        if (guest.vehicle != null) {

            Map<VehicleModel, GuestModel> map = new HashMap<>();
            map.put(guest.getVehicle(), guest);

            map.get(guest.vehicle);

            return true;
        }
        else
            return false;
    }

    public String showGuestDetails(){
        return "Guest on File " +
                "\nFirst Name: " + firstName +
                "\nLast Name: " + lastName +
                "\nAddress: " + address +
                "\nContact Number: " + contactNum +
                "\nNumber of adults: " + numOfAdults +
                "\nNumber of children: " + numOfChildren +
                "\nDay in: " + dayIn + "\nDay out: " + dayOut + "\n\n";
    }

    @Override
    public String showCarDetails() {
        return vehicle.showCarDetails();
    }

    @Override
    public VehicleModel getVehicle() {
        return vehicle.getVehicle();
    }
    
}
