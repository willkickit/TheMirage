package com.willj.springTweets.view;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("serial")

public class DatePicker extends JDatePickerImpl {

    public DatePicker(JDatePanelImpl datePanel, JFormattedTextField.AbstractFormatter formatter) {
        super(datePanel, formatter);
    }

    public static class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {

        private String datePattern = "MM-dd-yyyy";
        private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

        @Override
        public Object stringToValue(String text) throws ParseException {
            return dateFormatter.parseObject(text);
        }

        @Override
        public String valueToString(Object value) throws ParseException {
            if (value != null) {
                Calendar cal = (Calendar) value;
                return dateFormatter.format(cal.getTime());
            }

            return "";
        }

    }
}

