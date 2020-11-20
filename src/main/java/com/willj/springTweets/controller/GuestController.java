package com.willj.springTweets.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import com.willj.springTweets.model.GuestModel;
import com.willj.springTweets.model.VehicleModel;
import com.willj.springTweets.view.GuestView;
import com.willj.springTweets.view.VehicleView;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class GuestController {

    private GuestView gView;
    private GuestModel gModel;
    final int maxPerRoom = 5;
    final int maxDigits = 10;


    public GuestController(GuestView gView, GuestModel gModel) {
        this.gView = gView;
        this.gModel = gModel;

        //added listeners for buttons
        this.gView.addNextButtonListener(new NextButtonListener());
        this.gView.addResetButtonListener(new ResetButtonListener());		
    }

    public class NextButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String firstName = "";
            String lastName = "";
            String address = "";
            String contactNum = "";
            int numOfAdults = 0;
            int numOfChildren = 0;
            ArrayList<Date> dates;

            //Field validation
            try {

                firstName = gView.getFirstName();
                lastName = gView.getLastName();
                address = gView.getAddress();
                contactNum = gView.getContactNum();
                numOfAdults = gView.getNumOfAdults();
                numOfChildren = gView.getNumOfChildren();

                if (firstName.equals("") || lastName.equals("") || address.equals("") || contactNum.equals("")) {
                    throw new Exception();
                }

                if (numOfAdults + numOfChildren > maxPerRoom) {
                    gView.displayInfo("Each room occupies up to (5) people.");
                }

                if (contactNum.length() < maxDigits || contactNum.length() > maxDigits) {
                    gView.displayInfo("Please enter a 10-digit contact number.");
                }

            } catch (Exception exception) {
                gView.displayRequiredFieldMessage("Please fill out each field!");
            }
            finally{

                dates = gView.getDateList();
                if (((!firstName.equals("")) && (!lastName.equals("")) && (!address.equals("")) && 
                (numOfAdults + numOfChildren < maxPerRoom) && (contactNum.length() == maxDigits) 
                && gView.datePickerValidation() == true)) {

                    //Form validated - save new Guest
                    gModel.saveGuest(firstName, lastName, address, contactNum, numOfAdults, numOfChildren,
                            dates.get(0), dates.get(1), null);
                    gView.displaySavedEntryMessage("Entry saved!");
                    gView.setVisible(false);

                    //creating new vehicle
                    VehicleView vView = new VehicleView();
                    VehicleModel vModel = new VehicleModel();
                    new VehicleController(vView, vModel, gModel);
                    vView.setVisible(true);
                }
            }
        }
    }

    public class ResetButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gView.resetFields();
        }
    }
}
