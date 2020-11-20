package com.willj.springTweets.controller;

import javax.swing.*;

import com.willj.springTweets.model.GuestModel;
import com.willj.springTweets.model.PaymentModel;
import com.willj.springTweets.model.VehicleModel;
import com.willj.springTweets.view.PaymentView;
import com.willj.springTweets.view.VehicleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentController {

    private PaymentView pView;
    private PaymentModel pModel;
    private GuestModel gModel;
    private GuestModel guest;
    private int result;
    private String roomType = "";
    private String typeOfPayment = "";
    private String promoCode = "";
    private int numOfRooms = 0;
    private double totalPayment = 0;

    public PaymentController(PaymentView pView, PaymentModel pModel, GuestModel gModel) {

        this.pView = pView;
        this.pModel = pModel;
        this.gModel = gModel;

        //added listeners for buttons
        this.pView.addRoomSelectedListener(new RoomSelectedListener());
        this.pView.addPreviousButtonListener(new PreviousButtonListener());
        this.pView.addCalculateListener(new CalculateButtonListener());
        this.pView.addCheckoutListener(new CheckoutButtonListener());

        //setting guest check in/out dates, trip duration, default rate and deposit fields
        GuestModel guest = gModel.getGuest();
        pView.setDateInField(String.valueOf(guest.getDayIn()));
        pView.setDateOutField(String.valueOf(guest.getDayOut()));
        pView.setTripDurationField((pModel.getNumOfDays(guest.getDayIn(), guest.getDayOut())) + " Days, " + pModel.getNumOfNights() + " Nights");
        pModel.setBaseRate();
        pModel.setDepositFee();
        pView.setRateField(pModel.resetRate());
        pView.setDepositField(pModel.resetDepositFee());
    }

    public boolean noRoomSelected() {

        if (roomType.equals(pView.getRoomTypeList().getItemAt(0))) {
            pView.displayInfoMessage("Please select the style of room you wish to reserve.");
            pView.setRateField(pModel.resetRate());
            return true;//no selection
        } else if (numOfRooms == (pView.getNumOfRoomsList().getItemAt(0))) {
            pView.displayInfoMessage("Please select the number of rooms you wish to reserve.");
            pView.setDepositField(pModel.resetDepositFee());
            return true;// no selection
        }
        return false;
    }

    public boolean suiteReservationClosed() {
        if (roomType.equals(pView.getRoomTypeList().getItemAt(3)) && (numOfRooms == (pView.getNumOfRoomsList().getItemAt(2)))
                || (roomType.equals(pView.getRoomTypeList().getItemAt(3))) && (numOfRooms == (pView.getNumOfRoomsList().getItemAt(3)))) {
            return true;
        } else return false;
    }

    public boolean calculatePayment() throws Exception {

        totalPayment = pModel.getPayment();
        typeOfPayment = pView.getTypeOfPayment();
        promoCode = pView.getPromoCode();

        if (pModel.getTotalRate() == 0 || pModel.getTotalDeposit() == 0)
            throw new Exception();

        else if ((typeOfPayment.equals(pView.getTypeOfPaymentList().getItemAt(0)))) {
            pView.displayInfoMessage("Please select a form of payment.");
            return false;
        }
        return true;
    }

    public class RoomSelectedListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                roomType = pView.getRoomType();
                numOfRooms = pView.getNumOfRooms();

                if (noRoomSelected() == false) {


                    //DepositFee - Suite
                    if ((roomType.equals(pView.getRoomTypeList().getItemAt(3))) &&
                            ((numOfRooms == pView.getNumOfRoomsList().getItemAt(1)))) {
                        pView.setDepositField(pModel.getDeposit(3));

                        //DepositFee - Other
                    } else if ((numOfRooms == (pView.getNumOfRoomsList().getItemAt(1)))) {
                        pView.setDepositField(pModel.getDeposit(1));
                    } else if (numOfRooms == (pView.getNumOfRoomsList().getItemAt(2))) {
                        pView.setDepositField(pModel.getDeposit(2));
                    } else if (numOfRooms == (pView.getNumOfRoomsList().getItemAt(3))) {
                        pView.setDepositField(pModel.getDeposit(3));
                    }

                    //Room type
                    if (roomType.equals(pView.getRoomTypeList().getItemAt(1))) {
                        pModel.getRate(1); //single
                        pView.setRateField(pModel.costPerNight());

                    } else if (roomType.equals(pView.getRoomTypeList().getItemAt(2))) {
                        pModel.getRate(2);//twin
                        pView.setRateField(pModel.costPerNight());

                    } else if (roomType.equals(pView.getRoomTypeList().getItemAt(3))) {
                        pModel.getRate(3);//suite
                        pView.setRateField(pModel.costPerNight());
                    }

                    //Suite is (1) room per guest
                    if (suiteReservationClosed() == true) {
                        throw new Exception();
                    }
                }

            } catch (Exception exception) {
                pView.displayInfoMessage("This room style only allows up to " + pModel.getSuiteMax() + " reservation per applicant.");
                pView.resetNumOfRooms();
                pModel.resetDepositFee();
            }
        }

    }

    public class PreviousButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            result = JOptionPane.showConfirmDialog(pView, "Are you sure you would like to resubmit your Vehicle information?", "Payment Form", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {

                pView.setVisible(false);

                //creating new vehicle
                VehicleView vView = new VehicleView();
                VehicleModel vModel = new VehicleModel();
                new VehicleController(vView, vModel, gModel);
                vView.setVisible(true);

            } else if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(pView, "Please remember to fill out each field.", "Payment Form", JOptionPane.INFORMATION_MESSAGE);
            }

        }

    }

    public class CalculateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                roomType = pView.getRoomType();
                numOfRooms = pView.getNumOfRooms();

                if (noRoomSelected() == false) {

                    if (calculatePayment() == true) {
                        pView.setSalesTaxField(pModel.getSalesTax());
                        pView.setDiscountField(pModel.getDiscount(promoCode));
                        pView.setPaymentField(pModel.getPayment());
                    }
                }

            } catch (Exception ex) {
                pView.displayErrorMessage("Check for null object");
            }

        }
    }


    public class CheckoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {

                roomType = pView.getRoomType();
                numOfRooms = pView.getNumOfRooms();

                if (noRoomSelected() == false) {

                    guest = gModel.getGuest();

                    if (calculatePayment() == true) {

                        pModel.savePaymentInfo(pModel.getTypeOfRoom(roomType), numOfRooms, pModel.getPaymentOption(typeOfPayment),
                                pModel.getDiscount(promoCode), totalPayment, guest);

                        result = JOptionPane.showConfirmDialog(pView, "Submit Payment? ", "Payment Form", JOptionPane.YES_NO_OPTION);

                        if (result == JOptionPane.YES_OPTION) {

                            JOptionPane.showMessageDialog(pView, pModel.paymentReceivedNote(), "Checkout", JOptionPane.INFORMATION_MESSAGE);

                            if (gModel.hasVehicle(guest) == true)
                                JOptionPane.showMessageDialog(pView, guest.showGuestDetails() + guest.showCarDetails() + pModel.showPaymentDetails(), "Mirage Reservation for: " + guest.getFirstName(), JOptionPane.INFORMATION_MESSAGE);
                            else if (gModel.hasVehicle(guest) == false)
                                JOptionPane.showMessageDialog(pView, guest.showGuestDetails() + guest.noVehicleOnFile() + pModel.showPaymentDetails(), "Mirage Reservation for: " + guest.getFirstName(), JOptionPane.INFORMATION_MESSAGE);

                        } else
                            JOptionPane.showMessageDialog(pView, "Checkout when ready.", "Payment Form", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            } catch (Exception exception) {
                pView.displayErrorMessage("Check for null object.");
            }

        }
    }
}
