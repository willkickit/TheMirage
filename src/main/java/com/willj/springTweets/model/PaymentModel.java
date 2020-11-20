package com.willj.springTweets.model;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

public class PaymentModel extends GuestModel implements IBaseRate, IDeposit, ITaxRate, IDiscount{

    private PaymentModel payment;
    private ArrayList<PaymentModel> paymentList = new ArrayList<>();

    private String typeOfRoom;
    private String discountCode = "";
    private String paymentOption;

    private int numOfRooms;
    private int numOfDays;
    private int numOfNights;
    final int suiteMax = 1;

    protected double baseRate;
    protected double baseDeposit;

    private double totalRate;
    private double totalDeposit;
    private double discount;
    private double totalPayment;
    private double salesTax;
    NumberFormat formattedPayment = NumberFormat.getCurrencyInstance();

    public PaymentModel() {
    }

    public PaymentModel(String typeOfRoom, int numOfRooms, String paymentOption, double discountAmount,
            double totalPayment, GuestModel guest) {

        super(guest);
        this.typeOfRoom = typeOfRoom;
        this.numOfRooms = numOfRooms;
        this.paymentOption = paymentOption;
        this.discount = discountAmount;
        this.totalPayment = totalPayment;
    }

    public void savePaymentInfo(String typeOfRoom, int numOfRooms, String paymentOption, double discountAmount, double totalPayment, GuestModel guest) {
        payment = new PaymentModel(typeOfRoom, numOfRooms, paymentOption, discountAmount, totalPayment, guest);
        paymentList.add(payment);
    }

    //setters
    public void setBaseRate() {
        baseRate = getBaseRate();
    }

    public void setDepositFee() {
        baseDeposit = getBaseDeposit();
    }

    //costs
    public double costPerNight(){
        double costPerNight = ( getTotalRate() / getNumOfNights() ) * getNumOfRooms();
        return costPerNight;
    }

    public double totalCost(){
        double totalCost = (costPerNight() * getNumOfNights()) + getSalesTax() + getTotalDeposit();
        return totalCost;
    }

    //getters
    public double getPayment() {
        return totalPayment = totalCost() - discount;
    }

    public double getTotalRate(){
        return totalRate;
    }

    public double getSalesTax(){
        return salesTax = (totalRate * getTaxRate()) * numOfRooms;
    }

    public double getTotalDeposit(){
        return totalDeposit;
    }

    public int getSuiteMax() { return suiteMax; }

    public double getDiscount(String promoCode){
        if(promoCode.equals("MIRAGE15")) {
            discountCode = promoCode;
            discount = totalRate * discountRate();
        }
        else
            discount = 0;
        return discount;
    }

    public int getNumOfDays(Date dayIn, Date dayOut) {
        long difference = dayOut.getTime() - dayIn.getTime();
        float daysBetween = (difference / (1000 * 60 * 60 * 24));
        return numOfDays = (int) daysBetween + 1;
    }

    public int getNumOfNights() {
        return numOfNights = numOfDays - 1;
    }

    public double getRate(int roomChoice) {

        double roomRate = 0;

        if (roomChoice == 1) {
             roomRate = (baseRate * numOfNights);
        } else if (roomChoice == 2) {
             roomRate = (baseRate * 1.5) * numOfNights;
        } else if (roomChoice == 3) {
             roomRate = (baseRate * 2.2) * numOfNights;
        } else
            System.out.println("Invalid roomChoice input");

        totalRate = roomRate;

        return totalRate;
    }

    public double getDeposit(int rooms) {

        double depositFee = 0.0;

        if (rooms == 1)
            depositFee = baseDeposit;

        else if (rooms == 2)
            depositFee = baseDeposit * twoRooms();

        else if (rooms == 3) {
            depositFee = baseDeposit * threeRooms();
        }
        else
            System.out.println("Invalid number of rooms");

        numOfRooms = rooms;
        totalDeposit = depositFee;

        return depositFee;
    }

    public String getTypeOfRoom(String roomType) {
        typeOfRoom = roomType;
        return typeOfRoom;
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public String getPaymentOption(String typeOfPayment) {
        paymentOption = typeOfPayment;
        return paymentOption;
    }

    public double resetRate() {
        double resetRate = baseRate * 0;
        return resetRate;
    }

    public double resetDepositFee() {
        double resetFee = baseDeposit * 0;
        return resetFee;
    }

    public String showPaymentDetails() {
        return "\nRoom and payment details " +
                "\nType of room: " + typeOfRoom +
                "\nNumber of rooms: " + numOfRooms +
                "\nPayment Option: " + paymentOption +
                "\n+Cost per night: " + formattedPayment.format(costPerNight()) + " (" + getNumOfNights() + " Nights)" +
                "\n+Deposit: " + formattedPayment.format(totalDeposit) + " (Refundable)" +
                "\n+Sales Tax: " + formattedPayment.format(salesTax) +
                "\n-Discount: " + formattedPayment.format(discount) + " " + "(" + discountCode + ")" +
                "\nTotal Payment: " + formattedPayment.format(totalPayment);
    }


    public String paymentReceivedNote(){
        return "Payment received!" +
                "\nFor any questions or concerns please contact us at TheMirage@gmail.com or at (404) 813-1831 " +
                "\nWe look forward to your visit!";
    }
}