package com.willj.springTweets.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import javax.swing.JFrame;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

@SuppressWarnings("serial")

public class GuestView extends JFrame {

    private JPanel guestPanel;

    private Border titledBorder = BorderFactory.createTitledBorder("Guest Form");
    private Border loweredBevel = BorderFactory.createLoweredBevelBorder();
    private Border raisedBevel = BorderFactory.createRaisedBevelBorder();
    private Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);

    private JTextField firstNameField = new JTextField(10);
    private JTextField lastNameField = new JTextField(10);
    private JTextField addressField = new JTextField(15);
    private JTextField contactField = new JTextField(10);
    private JTextField adultsField = new JTextField(7);
    private JTextField childrenField = new JTextField(7);

    private JLabel firstNameLabel = new JLabel("First name: ");
    private JLabel lastNameLabel = new JLabel("Last name: ");
    private JLabel addressLabel = new JLabel("Address: ");
    private JLabel contactLabel = new JLabel("Contact number: ");
    private JLabel adultsLabel = new JLabel("No. of adults: ");
    private JLabel childrenLabel = new JLabel("No. of children: ");
    private JLabel dayInLabel = new JLabel("Day in: ");
    private JLabel dayOutLabel = new JLabel("Day out: ");

    private Integer[] numOfAdults = { 1, 2, 3, 4, 5 };
    private JComboBox<Integer> numOfAdultsList = new JComboBox<Integer>(numOfAdults);

    private Integer[] numOfChildren = { 0, 1, 2, 3, 4, 5 };
    private JComboBox<Integer> numOfChildrenList = new JComboBox<Integer>(numOfChildren);

    private Date currentDate;
    private Date selectedDate;
    private Date dayIn;
    private Date dayOut;

    private Calendar c = Calendar.getInstance();

    private int mYear = c.get(Calendar.YEAR);
    private int mMonth = c.get(Calendar.MONTH);
    private int mDay = c.get(Calendar.DAY_OF_MONTH);

    private ArrayList<Date> dateList = new ArrayList<>();
    private ArrayList<JDatePickerImpl> datePickers = new ArrayList<>();
    private ArrayList<JTextField> fields;
    private ArrayList<JLabel> labels;

    private JButton nextButton = new JButton("Next");
    private JButton resetButton = new JButton("Reset Fields");

    public GuestView() {
    

        SwingUtilities.invokeLater(new Runnable() {

            public void run() {

                JTextPane text_panel = new JTextPane();

                // create jeditorpane
                JEditorPane jEditorPane = new JEditorPane();

                // make it read-only
                jEditorPane.setEditable(false);

                // create a scrollpane; modify its attributes as desired
                JScrollPane scrollPane = new JScrollPane(jEditorPane);

                //HTMLDocument doc = new HTMLDocument();

                // add an html editor kit
                HTMLEditorKit kit = new HTMLEditorKit();

                jEditorPane.setEditorKit(kit);

                /*try {
                    kit.insertHTML(doc, doc.getLength(), "<form>", 0, 0, null);
                    kit.insertHTML(doc, doc.getLength(), "<label for='fname'>First name:</label>", 0, 0, null);
                    kit.insertHTML(doc, doc.getLength(), "<input type= 'text' id='fname' name='fname' >", 0, 0, null);
                    kit.insertHTML(doc, doc.getLength(), "<br/>", 0, 0, null);
                    kit.insertHTML(doc, doc.getLength(), "<label for='lname'>Last name:</label>", 0, 0, null);
                    kit.insertHTML(doc, doc.getLength(), "<input type= 'text' id='lname' name='lname' >", 0, 0, null);
                    kit.insertHTML(doc, doc.getLength(), "</form>", 0, 0, null);
                } catch (BadLocationException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/

              
              // add some styles to the html
              StyleSheet styleSheet = kit.getStyleSheet();
              styleSheet.addRule("body {color:#000; font-family:times; margin: 4px; }");
              styleSheet.addRule("h1 {color: blue;}");
              styleSheet.addRule("h2 {color: #ff0000;}");
              styleSheet.addRule("pre {font : 10px monaco; color : black; background-color : #fafafa; }");
      
              
              // create some simple html as a string
              String htmlString = "<html>\n"
                                + "<body>\n"
                                + "<h1>Welcome!</h1>\n"
                                + "<div class='container'>"
                                + "<form action='/action_page.php'>"
                                + "<div class='form-group'>"

                                + "<label for='fname'>First name:</label>"
                                + "<input type= 'text' id='fname' name='fname' >"
                                //+ "<input type='text' value='${firstNameField.getText()}'>"

                                + "</div>"
                                + "<div class='form-group'>"
                                + "<label for='lname'>Last name:</label>"
                                + "<input type= 'text' id='lname' name='lname' >"
                                +  "</div>"

                                + "<div class='checkbox'>"
                                + "<label><input type='checkbox' name='remember'> Remember me</label>"
                                + "</div>"
                                + "<input type='submit' value='Submit'>"
                                + " <input type='reset'>"

                                + "</form>"
                                + "</div>"
                                + "</body>\n"
                                + "</html>\n";

            
            // create a document, set it on the jeditorpane, then add the html
            Document doc = kit.createDefaultDocument();
            jEditorPane.setDocument(doc);
            jEditorPane.setText(htmlString);
      
              
              // now add it all to a frame
              JFrame j = new JFrame("HtmlEditorKit Test");
              j.getContentPane().add(scrollPane, BorderLayout.CENTER);
      
              // make it easy to close the application
              j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
              //display the frame
              j.setTitle("Hotel Reservation System - The Mirage ");
              j.setExtendedState(j.getExtendedState() | j.MAXIMIZED_BOTH);
              j.setBounds(300, 150, 500, 500);
              j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

              
              // pack it, if you prefer
              j.pack();
              
              // center the jframe, then make it visible
              j.setLocationRelativeTo(null);
              j.setVisible(true);
            }
          });
        }


    public void createGuestUI() {

        GraphicsEnvironment.getLocalGraphicsEnvironment();
        this.setTitle("Hotel Reservation System - The Mirage ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(this.getExtendedState() | GuestView.MAXIMIZED_BOTH);

        //create vehiclePanel
        guestPanel = new JPanel();
        guestPanel.setLayout(new BoxLayout(guestPanel, BoxLayout.Y_AXIS));
        guestPanel.setBounds(300, 150, 500, 500);
        guestPanel.setBorder(titledBorder);
        guestPanel.setBackground(Color.white);
        guestPanel.setSize(500, 500);
        guestPanel.setVisible(true);

        createComponentPanel();
        this.add(guestPanel);
        this.setVisible(true);
    }

    public void addComponentsToList() {

        labels = new ArrayList<>();
        labels.add(firstNameLabel);
        labels.add(lastNameLabel);
        labels.add(addressLabel);
        labels.add(contactLabel);
        labels.add(adultsLabel);
        labels.add(childrenLabel);

        fields = new ArrayList<>();
        fields.add(firstNameField);
        fields.add(lastNameField);
        fields.add(addressField);
        fields.add(contactField);
        fields.add(adultsField);
        fields.add(childrenField);

    }

    //TODO refactor method...
    public void createComponentPanel() {

        addComponentsToList();

        //creating components from each list
        do {

            JPanel componentPanel = new JPanel();
            int i = 0;

            for (JLabel label : labels) {

                componentPanel.add(label, BorderLayout.LINE_START);

                if (label.equals(adultsLabel)) {

                    componentPanel.add(numOfAdultsList, BorderLayout.LINE_END);
                    adultsField.setVisible(false); //is set to false to utilize numOfAdults drop menu
                    childrenField.setVisible(false); //is set to false to utilize numOfChildren drop menu
                }

                if (label.equals(childrenLabel)) {
                    componentPanel.add(numOfChildrenList, BorderLayout.LINE_END);
                }

                labels.remove(i);
                break;
            }

            for (JTextField field : fields) {
                componentPanel.add(field, BorderLayout.LINE_END);

                fields.remove(i);
                break;
            }

            guestPanel.add(componentPanel, BorderLayout.AFTER_LINE_ENDS);

        } while ((!fields.isEmpty()) && (!labels.isEmpty()));

        datePickerGUI();
        datePickerAction();
        createButtons();
    }

    public void createButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(compound);
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(resetButton);
        buttonPanel.add(nextButton);
        add(buttonPanel, BorderLayout.PAGE_END);
    }

    public void resetFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        addressField.setText("");
        contactField.setText("");
        adultsField.setText("");
        childrenField.setText("");
        datePickers.get(0).getModel().setDate(mYear, mMonth, mDay);
        datePickers.get(1).getModel().setDate(mYear, mMonth, mDay);
    }

    public void datePickerGUI() {

        JDatePickerImpl datePicker;
        JPanel componentPanel;
        datePickers = new ArrayList<>();
        labels.add(dayInLabel);
        labels.add(dayOutLabel);

        for (JLabel label : labels) {

            UtilDateModel model = new UtilDateModel();
            model.setDate(mYear, mMonth, mDay);
            model.setSelected(true);
            currentDate = model.getValue();

            Properties dateField = new Properties();
            dateField.put("text.today", "Today");
            dateField.put("text.month", "Month");
            dateField.put("text.year", "Year");

            JDatePanelImpl datePanel = new JDatePanelImpl(model, dateField);
            datePicker = new JDatePickerImpl(datePanel, new DatePicker.DateLabelFormatter());

            componentPanel = new JPanel();
            componentPanel.add(label);
            componentPanel.add(datePicker);
            datePickers.add(datePicker);
            guestPanel.add(componentPanel);
        }
    }

    //TODO:: implement in/out time for datepicker
    public Date getDateOnly(final Date input) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(input);
        cal.clear(Calendar.HOUR_OF_DAY);
        cal.clear(Calendar.MINUTE);
        cal.clear(Calendar.SECOND);
        cal.clear(Calendar.MILLISECOND);
        return cal.getTime();
    }

    public ArrayList<Date> noDateSelected() {

        if (dayIn == null && dayOut == null) {
            dateList.set(0, getCurrentDate());
            dateList.set(1, getCurrentDate());
        } else if (dayIn == null && dayOut != null) {
            dateList.set(0, getCurrentDate());
        } else if (dayOut == null && dayIn != null) {
            dateList.set(1, getCurrentDate());
        }
        return dateList;
    }

    public ArrayList<Date> datePickerAction() {

        for (int i = 0; i < datePickers.size(); i++) {

            JDatePickerImpl finalDatePicker = datePickers.get(i);

            //NOTE: Unable to reference a local variable i inside your ActionListener implementation unless i is final (or effectively final).
            final int final_i = i;

            datePickers.get(i).addActionListener(e -> {
                //JOptionPane.showMessageDialog(null, "Button: " + final_i + " " + finalDatePicker.getModel().getValue());

                selectedDate = (Date) finalDatePicker.getModel().getValue();
                selectedDate = getDateOnly(selectedDate);

                if (dateList.size() == 2) {
                    dateList.clear();
                }

                if (final_i == 0) {
                    dayIn = selectedDate;
                }

                if (final_i == 1) {
                    dayOut = selectedDate;
                }

                dateList.add(dayIn);
                dateList.add(dayOut);

            });
        }

        dateList.add(dayIn);
        dateList.add(dayOut);

        return dateList;
    }

    public boolean datePickerValidation() {

        noDateSelected();

        for (int i = 0; i < dateList.size(); i++) {

            dateList.get(i);

            //Validating dates before currentDate
            if (dateList.get(i).before(getCurrentDate())) {
                displayInfo("Anything before " + getCurrentDate() + " is unavailable!");
                return false;
            }

            //Validating check out date w/ currentDate and check in date
            else if (i == 0 && dateList.get(1).equals(getCurrentDate()) || i == 1 && dateList.get(1).before(dateList.get(0))) {
                displayInfo("Unable to check out on this date, please select another.");
                return false;
            }

            //Validating same check in/out dates
            else if (i == 0 && dateList.get(0).equals(dateList.get(1)) || i == 1 && dateList.get(1).equals(dateList.get(0))) {
                displayInfo("You cannot check in/out on the same day!");
                return false;
            }

        }
        return true;
    }

    //display messages
    public void displayRequiredFieldMessage(String fieldMessage1) {
        JOptionPane.showMessageDialog(this, fieldMessage1, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayInfo(String infoMessage1) {
        JOptionPane.showMessageDialog(this, infoMessage1, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void displaySavedEntryMessage(String saveEntryMessage) {
        JOptionPane.showMessageDialog(this, saveEntryMessage);
    }

    //listeners
    public void addNextButtonListener(ActionListener listenForNextButton) {
        nextButton.addActionListener(listenForNextButton);
    }

    public void addResetButtonListener(ActionListener listenForResetButton) {
        resetButton.addActionListener(listenForResetButton);
    }

    //getters
    public String getFirstName() {
        return firstNameField.getText();
    }

    public String getLastName() {
        return lastNameField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getContactNum() {
        return contactField.getText();
    }

    public int getNumOfAdults() {
        int numOfAdults = (int) numOfAdultsList.getSelectedItem();
        return numOfAdults;
    }

    public int getNumOfChildren() {
        int numOfChildren = (int) numOfChildrenList.getSelectedItem();
        return numOfChildren;
    }

    public Date getCurrentDate() {
        currentDate = getDateOnly(currentDate);
        return currentDate;
    }

    public ArrayList<Date> getDateList() {
        return dateList;
    }
}