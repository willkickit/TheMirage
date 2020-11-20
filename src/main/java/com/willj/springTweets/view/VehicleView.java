package com.willj.springTweets.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

@SuppressWarnings("serial")

public class VehicleView extends JFrame {

    private JPanel vehiclePanel;

    private Border titledBorder = BorderFactory.createTitledBorder("Vehicle Form");
    private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
    private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    private Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);

    private JLabel makeOfVehicleLabel = new JLabel("Make of vehicle: ");
    private JLabel typeOfVehicleLabel = new JLabel("Type of vehicle: ");
    private JLabel modelOfVehicleLabel = new JLabel("Model of vehicle: ");
    private JLabel plateNumLabel = new JLabel("Plate Number(s): ");
    private JLabel noVehicleLabel = new JLabel("No Vehicle: ");

    private String[] carTypes = {"--Select--", "Coupe/Convertible", "Sedan", "Truck", "Van"};

    private JComboBox<String> typeOfVehicleList = new JComboBox<String>(carTypes);
    private JCheckBox vehicleCheckBox = new JCheckBox();

    private JTextField makeOfVehicle = new JTextField(8);
    private JTextField modelOfVehicleField = new JTextField(8);
    private JTextField plateNumField = new JTextField(8);

    private ArrayList<JTextField> fields;
    private ArrayList<JLabel> labels;

    private JButton previousButton = new JButton("Previous");
    private JButton nextButton = new JButton("Next");
    private JButton resetButton = new JButton("Reset Fields");

    public VehicleView() {
        createVehicleUI();
    }

    public void createVehicleUI() {

        //setTitle, size, and close operation of window
        this.setTitle("Hotel Reservation System - The Mirage ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | VehicleView.MAXIMIZED_BOTH);

        //create vehiclePanel
        vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
        vehiclePanel.setBounds(300, 150, 250, 250);
        vehiclePanel.setBorder(titledBorder);
        vehiclePanel.setBackground(Color.white);
        vehiclePanel.setSize(500, 500);
        vehiclePanel.setVisible(true);

        //createFields for vehiclePanel
        createComponentPanel();
        this.add(vehiclePanel);
        this.setVisible(true);

    }

    public void addComponentsToList() {

        labels = new ArrayList<>();
        labels.add(makeOfVehicleLabel);
        labels.add(modelOfVehicleLabel);
        labels.add(plateNumLabel);

        fields = new ArrayList<>();
        fields.add(makeOfVehicle);
        fields.add(modelOfVehicleField);
        fields.add(plateNumField);
    }

    public void createComponentPanel(){

        addComponentsToList();

        //creating components from each list
        do {

            JPanel componentPanel = new JPanel();
            int i = 0;

            for (JLabel label : labels) {
                componentPanel.add(label, BorderLayout.LINE_START);
                break;
            }

            for (JTextField field : fields) {
                componentPanel.add(field, BorderLayout.LINE_END);
                break;
            }

            vehiclePanel.add(componentPanel, BorderLayout.AFTER_LINE_ENDS);

            //removing index from each list to create new component in loop
            labels.remove(i);
            fields.remove(i);

        } while (!fields.isEmpty() && !labels.isEmpty());

        JPanel componentPanel = new JPanel();
        componentPanel.add(noVehicleLabel, BorderLayout.LINE_START);
        componentPanel.add(vehicleCheckBox, BorderLayout.LINE_END);
        componentPanel.add(typeOfVehicleLabel, BorderLayout.LINE_START);
        componentPanel.add(typeOfVehicleList, BorderLayout.LINE_END);
        vehiclePanel.add(componentPanel, BorderLayout.AFTER_LINE_ENDS);

        createButtons();
    }

    public void createButtons(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(compound);
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(resetButton);
        buttonPanel.add(previousButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    public void resetFields() {
        makeOfVehicle.setText("");
        modelOfVehicleField.setText("");
        plateNumField.setText("");
        typeOfVehicleList.setSelectedIndex(0);
        vehicleCheckBox.setSelected(false);
    }

    public boolean noVehicle(){
        if(vehicleCheckBox.isSelected()){
            return true;
        }
        return false;
    }

    //listeners
    public void addResetListener(ActionListener listenForResetButton) {
        resetButton.addActionListener(listenForResetButton);
    }

    public void addNextButtonListener(ActionListener listenForNextButton) {
        nextButton.addActionListener(listenForNextButton);
    }

    public void addPreviousButtonListener(ActionListener listenForPreviousButton) {
        previousButton.addActionListener(listenForPreviousButton);
    }

    //display messages
    public void displayRequiredFieldMessage(String errorMessage2) {
        JOptionPane.showMessageDialog(this, errorMessage2, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displaySavedEntryMessage(String saveEntryMessage) {
        JOptionPane.showMessageDialog(this, saveEntryMessage);
    }

    //getters
    public String makeOfVehicle() {
        return makeOfVehicle.getText();
    }

    public String getCarType() {
        String carType = (String) typeOfVehicleList.getSelectedItem();
        return carType;
    }

    public String getModelOfVehicle() {
        return modelOfVehicleField.getText();
    }

    public String getPlateNum() {
        return plateNumField.getText();
    }

}
