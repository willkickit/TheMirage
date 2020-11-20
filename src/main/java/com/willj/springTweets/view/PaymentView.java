package com.willj.springTweets.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

@SuppressWarnings("serial")

public class PaymentView extends JFrame {

    JPanel paymentPanel;

    private Border titledBorder = BorderFactory.createTitledBorder("Payment Form - " + " (Enter promo code MIRAGE15 to receive a 15% discount!) ");
    private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
    private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    private Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);

    private ArrayList<JTextField> fields;
    private ArrayList<JLabel> labels;

    final String[] roomType = {"--Select--", "Single - (1) King sized bed", "Twin - (2) Queen sized beds", "Suite - (1) Single, (1) Twin"};
    private JComboBox<String> typeOfRoomList = new JComboBox<String>(roomType);

    private JLabel typeOfRoomLabel = new JLabel("Style of Room: ");
    private JLabel numOfRoomsLabel = new JLabel("No. of Rooms: ");
    private JLabel dateInLabel = new JLabel("Date in: ");
    private JLabel dateOutLabel = new JLabel("Date out: ");
    private JLabel tripDurationLabel = new JLabel("Trip Duration: ");

    private String[] typeOfPayment = {"--Select--", "Debit", "Credit"};
    private JComboBox<String> typeOfPaymentList = new JComboBox<String>(typeOfPayment);

    private JLabel paymentLabel = new JLabel("Payment: ");
    private JLabel rateLabel = new JLabel("Rate: ");
    private JLabel depositLabel = new JLabel("Deposit: ");
    private JLabel taxesLabel = new JLabel("Sales Tax: ");
    private JLabel promoCodeLabel = new JLabel("Promo Code: ");
    private JLabel discountLabel = new JLabel("Discount: ");
    private JLabel totalChargesLabel = new JLabel("Total payment: ");

    private Integer[] numOfRooms = {0, 1, 2, 3};
    private JComboBox<Integer> numOfRoomsList = new JComboBox<Integer>(numOfRooms);

    private JTextField numOfRoomsField = new JTextField(0);

    private JTextField dateInField = new JTextField(16);
    private JTextField dateOutField = new JTextField(16);
    private JTextField tripDurationField = new JTextField(10);

    private JTextField rateField = new JTextField(10);
    private JTextField depositField = new JTextField(12);
    private JTextField salesTaxField = new JTextField(7);
    private JTextField promoCodeField = new JTextField(7);
    private JTextField discountField = new JTextField(7);
    private JTextField totalPaymentField = new JTextField(7);

    private JButton previousButton = new JButton("Previous");
    private JButton calculateButton = new JButton("Calculate");
    private JButton checkoutButton = new JButton("Checkout");

    public PaymentView() {
        createPaymentUI();
    }

    public void createPaymentUI() {

        //setTitle, size, and close operation of window
        this.setTitle("Hotel Reservation System - The Mirage ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | PaymentView.MAXIMIZED_BOTH);

        //create vehiclePanel
        paymentPanel = new JPanel();
        paymentPanel.setLayout(new BoxLayout(paymentPanel, BoxLayout.Y_AXIS));
        paymentPanel.setBounds(300, 150, 250, 250);
        paymentPanel.setBorder(titledBorder);
        paymentPanel.setBackground(Color.white);
        paymentPanel.setSize(500, 500);
        paymentPanel.setVisible(true);

        //createFields for vehiclePanel
        createComponentPanel();
        this.add(paymentPanel);
        this.setVisible(true);
    }

    public void addComponentsToList() {

        labels = new ArrayList<>();
        labels.add(typeOfRoomLabel);
        labels.add(numOfRoomsLabel);
        labels.add(dateInLabel);
        labels.add(dateOutLabel);
        labels.add(tripDurationLabel);
        labels.add(rateLabel);
        labels.add(paymentLabel);
        labels.add(depositLabel);
        labels.add(taxesLabel);
        labels.add(promoCodeLabel);
        labels.add(discountLabel);
        labels.add(totalChargesLabel);

        fields = new ArrayList<>();
        fields.add(numOfRoomsField);
        fields.add(dateInField);
        fields.add(dateOutField);
        fields.add(tripDurationField);
        fields.add(rateField);
        fields.add(depositField);
        fields.add(salesTaxField);
        fields.add(promoCodeField);
        fields.add(discountField);
        fields.add(totalPaymentField);

    }

    public void createComponentPanel() {

        addComponentsToList();

        //creating components from each list
        do {

            JPanel componentPanel = new JPanel();
            int i = 0;

            for (JLabel label : labels) {

                componentPanel.add(label, BorderLayout.LINE_START);

                if (label.equals(typeOfRoomLabel)) {

                    componentPanel.add(typeOfRoomList, BorderLayout.LINE_END);
                    componentPanel.add(numOfRoomsLabel, BorderLayout.LINE_START);
                    componentPanel.add(numOfRoomsList, BorderLayout.LINE_END);

                    labels.remove(i);
                    numOfRoomsField.setVisible(false); //set to false to utilize numOfRooms list
                }

                if (label.equals(paymentLabel)) {

                    componentPanel.add(typeOfPaymentList, BorderLayout.LINE_END);
                    componentPanel.add(depositLabel, BorderLayout.LINE_START);

                    labels.remove(i);
                }
                break;
            }

            for (JTextField field : fields) {
                componentPanel.add(field, BorderLayout.LINE_END);
                break;
            }

            paymentPanel.add(componentPanel, BorderLayout.AFTER_LINE_ENDS);

            //removing index from each list to create new component in loop
            labels.remove(i);
            fields.remove(i);

        } while (!fields.isEmpty() && !labels.isEmpty());

        createButtons();
    }

    public void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(compound);
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(previousButton);
        buttonPanel.add(calculateButton);
        buttonPanel.add(checkoutButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    public void resetNumOfRooms() {
        numOfRoomsList.setSelectedIndex(0);
    }

    //listeners
    public void addRoomSelectedListener(ActionListener listenForItemSelected) {
        typeOfRoomList.addActionListener(listenForItemSelected);
        numOfRoomsList.addActionListener(listenForItemSelected);
    }

    public void addPreviousButtonListener(ActionListener listenForPreviousButton) {
        previousButton.addActionListener(listenForPreviousButton);
    }

    public void addCalculateListener(ActionListener listenForCalculateButton) {
        calculateButton.addActionListener(listenForCalculateButton);
    }

    public void addCheckoutListener(ActionListener listenForCheckoutButton) {
        checkoutButton.addActionListener(listenForCheckoutButton);
    }

    //display messages
    public void displayInfoMessage(String infoMessage1) {
        JOptionPane.showMessageDialog(this, infoMessage1, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayErrorMessage(String errorMessage1) {
        JOptionPane.showMessageDialog(this, errorMessage1, "Error", JOptionPane.ERROR_MESSAGE);
    }

    //setters
    public void setDateInField(String dateInField) {
        this.dateInField.setText(dateInField);
    }

    public void setDateOutField(String dateOutField) {
        this.dateOutField.setText(dateOutField);
    }

    public void setTripDurationField(String tripDurationField) {
        this.tripDurationField.setText(tripDurationField);
    }

    public void setRateField(Double rate) {
        NumberFormat formattedRate = NumberFormat.getCurrencyInstance();
        this.rateField.setText(formattedRate.format(rate) + "/Per Night");
    }

    public void setDepositField(Double deposit) {
        NumberFormat formattedDeposit = NumberFormat.getCurrencyInstance();
        this.depositField.setText( "+ " + formattedDeposit.format(deposit) + " (Refundable)");
    }

    public void setSalesTaxField(Double salesTax) {
        NumberFormat formattedPayment = NumberFormat.getCurrencyInstance();
        this.salesTaxField.setText( "+ " + formattedPayment.format(salesTax));
    }

    public void setDiscountField(Double discount) {
        NumberFormat formattedPayment = NumberFormat.getCurrencyInstance();
        this.discountField.setText( "- " + formattedPayment.format(discount));
    }

    public void setPaymentField(Double payment) {
        NumberFormat formattedPayment = NumberFormat.getCurrencyInstance();
        this.totalPaymentField.setText(formattedPayment.format(payment));
    }

    //getters

    //forActionListener
    public String getRoomType() {
        String roomType = (String) typeOfRoomList.getSelectedItem();
        return roomType;
    }

    //forIndex
    public JComboBox<String> getRoomTypeList() {
        return typeOfRoomList;
    }

    //forActionListener
    public int getNumOfRooms() {
        int numOfRoom = (int) numOfRoomsList.getSelectedItem();
        return numOfRoom;
    }

    //forIndex
    public JComboBox<Integer> getNumOfRoomsList() {
        return numOfRoomsList;
    }

    public String getTypeOfPayment() {
        String typeOfPayment = (String) typeOfPaymentList.getSelectedItem();
        return typeOfPayment;
    }

    public JComboBox<String> getTypeOfPaymentList() {
        return typeOfPaymentList;
    }

    public String getPromoCode(){
        return promoCodeField.getText();
    }

}

