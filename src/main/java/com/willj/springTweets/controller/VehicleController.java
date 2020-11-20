package com.willj.springTweets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import com.willj.springTweets.model.GuestModel;
import com.willj.springTweets.model.PaymentModel;
import com.willj.springTweets.model.VehicleModel;
import com.willj.springTweets.view.GuestView;
import com.willj.springTweets.view.PaymentView;
import com.willj.springTweets.view.VehicleView;

/*import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin*/


public class VehicleController {

    private VehicleView vView;
    private VehicleModel vModel;
    private GuestModel gModel;
    private int result;

    public VehicleController(VehicleView vView, VehicleModel vModel, GuestModel gModel) {

        this.vView = vView;
        this.vModel = vModel;
        this.gModel = gModel;

        //added listeners for buttons
        this.vView.addNextButtonListener(new NextButtonListener());
        this.vView.addPreviousButtonListener(new PreviousButtonListener());
        this.vView.addResetListener(new ResetButtonListener());

    }
    

    public class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String makeOfVehicle = null;
            String carType = null;
            String carModel = null;
            String plateNum = null;

            try {

                //prompt noVehicle checkbox
                if (vView.noVehicle() == true) {

                    result = JOptionPane.showConfirmDialog(vView, "Would you like to continue without bringing a vehicle?", "Vehicle Form", JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION) {

                        GuestModel guest = gModel.getGuest();
                        VehicleModel vehicle = vModel.getVehicle();

                        gModel.saveGuest(guest.getFirstName(), guest.getLastName(), guest.getAddress(), guest.getContactNum(), guest.getNumOfAdults(),
                                guest.getNumOfChildren(), guest.getDayIn(), guest.getDayOut(), vehicle);

                        gModel.getGuest();

                        vView.setVisible(false);

                        PaymentModel pModel = new PaymentModel();
                        PaymentView pView = new PaymentView();
                        new PaymentController(pView, pModel, gModel);
                        pView.setVisible(true);

                    }else if (result == JOptionPane.NO_OPTION) {
                        throw new Exception();
                    }
                }

                else if(vView.noVehicle() == false) {

                    //Fill out form. Check for required fields
                    carModel = vView.getModelOfVehicle();
                    plateNum = vView.getPlateNum();
                    makeOfVehicle = vView.makeOfVehicle();
                    carType = vView.getCarType();

                    if (carModel.equals("") || plateNum.equals("") || makeOfVehicle.equals("")) {
                        vView.displayRequiredFieldMessage("Please fill out each field!");
                    }
                    if (carType.equals("--Select--")) {
                        vView.displayRequiredFieldMessage("Please select a car type!");
                    }
                }

            } catch (Exception checkBox) {
                JOptionPane.showMessageDialog(vView, "Please fill out each field.", "Vehicle Form", JOptionPane.INFORMATION_MESSAGE);
            }

            //Required fields filled out
            if ((makeOfVehicle != null) && (carType != null) && (carModel != null) && (plateNum != null)) {

                vModel.saveVehicle(makeOfVehicle, carType, carModel, plateNum);
                vView.displaySavedEntryMessage("Entry saved!");
                vView.setVisible(false);

                VehicleModel vehicle = vModel.getVehicle();
                GuestModel guest = gModel.getGuest();

                gModel.saveGuest(guest.getFirstName(), guest.getLastName(), guest.getAddress(), guest.getContactNum(), guest.getNumOfAdults(),
                        guest.getNumOfChildren(), guest.getDayIn(), guest.getDayOut(), vehicle);

                 gModel.getGuest();

                //creating new payment
                PaymentModel pModel = new PaymentModel();
                PaymentView pView = new PaymentView();
                new PaymentController(pView, pModel, gModel);
                pView.setVisible(true);
            }

        }

    }

    public class PreviousButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            result = JOptionPane.showConfirmDialog(vView, "Are you sure you would like to resubmit your Guest information?", "Vehicle Form", JOptionPane.YES_NO_OPTION);

            if (result == JOptionPane.YES_OPTION) {

                vView.setVisible(false);

                //creating new guest
                GuestView gView = new GuestView();
                GuestModel gModel = new GuestModel();
                new GuestController(gView, gModel);
                gView.setVisible(true);

            } else if (result == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(vView, "Please remember to fill out each field.", "Vehicle Form", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    public class ResetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            vView.resetFields();
        }
    }
}
