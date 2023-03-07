import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


public class SkinConsultationSystemGUI extends JFrame {
    private JTable doctorTable;
    private DoctorTableModel doctorTableModel;
    private JTable consultationTable;
    private ConsultationTableModel consultationTableModel;
    private JButton sortButton;
    private WestminsterSkinConsultationManager manager;

    public SkinConsultationSystemGUI(WestminsterSkinConsultationManager manager) {
        setTitle("Skin Consultation Manager");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set manager variable to the parameter value
        this.manager = manager;

        // create table and table model
        doctorTable = new JTable();
        doctorTableModel = new DoctorTableModel(manager);
        doctorTable.setModel(doctorTableModel);

        //create a sort Button
        sortButton = new JButton("A-Z");

        //create a book consultation button
        JButton bookConsultationButton = new JButton("Book Consultation");

        //create a view consultations button
        JButton viewConsultations = new JButton("View consultations");

        // create the consultation table and table model
        consultationTable = new JTable();
        consultationTableModel = new ConsultationTableModel(manager.getConsultations());
        consultationTable.setModel(consultationTableModel);

        //add a listener for the sort button
        sortButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sort the list of doctors A-Z
                Collections.sort(manager.getDoctors(), new Comparator<Doctor>() {
                    @Override
                    public int compare(Doctor o1, Doctor o2) {
                        return o1.getName().compareTo(o2.getName());
                    }
                });

                //update the table model
                doctorTable.setModel(new DoctorTableModel(manager));
            }
        });

        bookConsultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create a new window for booking the consultation
                JFrame bookingWindow = new JFrame("Book Consultation");
                bookingWindow.setSize(900, 400);

                //create a panel to hold components
                JPanel bookingPanel = new JPanel();
                bookingPanel.setLayout(new FlowLayout());

                //create a label for the combo box text
                JLabel doctorSelect = new JLabel("Select Doctor: ");

                //create a combo box for selecting a doctor
                JComboBox<Doctor> doctorComboBox = new JComboBox<>(manager.getDoctors().toArray(new Doctor[0]));
                doctorComboBox.setPreferredSize(new Dimension(200,25));

                //create a label for the Date/time select
                JLabel timeSelect = new JLabel("Date/Time");

                //create time spinner to select time
                SpinnerDateModel time = new SpinnerDateModel();
                JSpinner timeSpinner = new JSpinner(time);
                timeSpinner.setPreferredSize(new Dimension(130,25));

                //create a button for checking the availability of the doctor
                JButton checkButton = new JButton("Check Availability");

                //create a button for checking the availability of the doctor
                JButton  backToMenuButton= new JButton("Back to Main Menu");

                //add check button features
                checkButton.setPreferredSize(new Dimension(150,25));

                //action listener for check button
                checkButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //get selected doctor from the combo box
                        Doctor doctor = manager.findDoctor(doctorComboBox.getSelectedItem().toString());

                        //get selected date and time from the time spinner
                        Date selectedDateTime = (Date) timeSpinner.getValue();

                        //check if no doctor is selected
                        if (doctor == null) {
                            JOptionPane.showMessageDialog(null, "Please select a doctor.");
                            return;
                        }

                        //check whether selected doctor is available
                        if (!manager.isDoctorAvailable(doctor, selectedDateTime)) {
                            // if the doctor is not available prompt the user to choose a different time or doctor
                            int choice = JOptionPane.showConfirmDialog(null, "The selected doctor is not available at the chosen date and time. Do you want to choose a different time or doctor?" + "\n(if no an available doctor will be assigned to you at the selected time)", "Doctor Not Available", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                // user wants to choose a different time or doctor
                                return;
                            } else {
                                // user wants to proceed with the selected doctor and time
                                Doctor randomDoctor = manager.getRandomAvailableDoctor(selectedDateTime);
                                doctorComboBox.setSelectedItem(randomDoctor);
                            }
                        }else {
                            // if the doctor is available, inform the user and show the consultation panel
                            JOptionPane.showMessageDialog(null, "The selected doctor is available at the chosen date and time. Please fill in the form below to book the consultation.");}


                        // create a new panel for entering patient information
                        JPanel consultationPanel = new JPanel(new BorderLayout());

                        JPanel patientInfoPanel = new JPanel(new GridLayout(11, 2,0,15));


                        //create text fields and labels for patient info.
                        JLabel nameLabel = new JLabel("Name: ");
                        JTextField nameField = new JTextField(20);
                        JLabel sNameLabel = new JLabel("Surname: ");
                        JTextField sNameField = new JTextField(20);
                        JLabel dobLabel = new JLabel("Date of Birth(DD/MM/YYYY): ");
                        JTextField dobField = new JTextField(20);
                        JLabel mobileNumLabel = new JLabel("Mobile Number: ");
                        JTextField mobileNumField = new JTextField(20);
                        JLabel patientIdLabel = new JLabel("Patient ID: ");
                        JTextField patientIdField = new JTextField(20);
                        JLabel patientNoteLabel = new JLabel("Notes: ");
                        JTextField patientNoteField = new JTextField(20);
                        JLabel durationLabel = new JLabel("Duration (hours): ");
                        JTextField durationField = new JTextField(20);


                        patientInfoPanel.add(nameLabel);
                        patientInfoPanel.add(nameField);
                        patientInfoPanel.add(sNameLabel);
                        patientInfoPanel.add(sNameField);
                        patientInfoPanel.add(dobLabel);
                        patientInfoPanel.add(dobField);
                        patientInfoPanel.add(mobileNumLabel);
                        patientInfoPanel.add(mobileNumField);
                        patientInfoPanel.add(patientIdLabel);
                        patientInfoPanel.add(patientIdLabel);
                        patientInfoPanel.add(patientIdField);
                        patientInfoPanel.add(durationLabel);
                        patientInfoPanel.add(durationField);
                        patientInfoPanel.add(patientNoteLabel);
                        patientInfoPanel.add(patientNoteField);

                        patientNoteField.setPreferredSize(new Dimension(100, 25));


                        // create the save consultation button
                        JButton saveConsultationButton = new JButton("Save Consultation");

                        //save consultation action listener
                        saveConsultationButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                //check if any of the fields are empty
                                if (doctorComboBox.getSelectedItem() == null || timeSpinner.getValue() == null ||
                                        nameField.getText().isEmpty() || sNameField.getText().isEmpty() ||
                                        dobField.getText().isEmpty() || mobileNumField.getText().isEmpty() ||
                                        patientIdField.getText().isEmpty() || patientNoteField.getText().isEmpty() ||
                                        durationField.getText().isEmpty()) {
                                    // show an error message
                                    JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                                    return;
                                }

                                try {

                                    // get the selected doctor from the combo box
                                    Doctor doctor = manager.findDoctor(doctorComboBox.getSelectedItem().toString());

                                    // get the selected date and time from the time spinner
                                    Date selectedDateTime = (Date) timeSpinner.getValue();

                                    // get the patient's name from the name field
                                    String patientName = nameField.getText();

                                    // get the patient's surname from the surname field
                                    String patientSurname = sNameField.getText();

                                    // get the patient's date of birth from the dob field
                                    String patientDob = dobField.getText();

                                    // get the patient's mobile number from the mobile number field
                                    String patientMobileNum = mobileNumField.getText();

                                    // get the patient's ID from the patient ID field
                                    String patientId = patientIdField.getText();

                                    // define the key for the cipher
                                    String key = "qwertyuiopasdfghjklzxcvbnm";

                                    // get the patient's notes from the patient notes field
                                    String patientNotes = patientNoteField.getText();

                                    // initialize an empty string to hold the encrypted message
                                    String encryptedNotes = "";

                                    // iterate over each character in the patient notes
                                    for (int i = 0; i < patientNotes.length(); i++) {
                                        // get the current character
                                        char c = patientNotes.charAt(i);

                                        // check if the character is a letter
                                        if (Character.isLetter(c)) {
                                            // if it is a letter, encrypt it by replacing it with the corresponding character in the key
                                            int index = (int) c - (int) 'a';
                                            encryptedNotes += key.charAt(index);
                                        } else {
                                            // if it is not a letter, just add it to the encrypted message as is
                                            encryptedNotes += c;
                                        }
                                    }
                                    // get the duration of the consultation from the duration field
                                    int duration = Integer.parseInt(durationField.getText());
                                    //calculate the end time
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(selectedDateTime);
                                    calendar.add(Calendar.HOUR, duration);
                                    Date endTime = calendar.getTime();

                                    double cost=0;
                                    // check if the patient ID already exists in the system
                                    if (manager.patientIdExists(patientId)) {
                                        // if the patient ID already exists, the cost of the consultation is 25 * duration
                                        cost = 25 * duration;
                                        JOptionPane.showMessageDialog(null, "Your consultation has been succesfully saved" +"\n The cost of the consultation is: " + " £" + cost);
                                    } else {
                                        // if the patient ID does not exist, the cost of the consultation is 15 * duration
                                        cost = 15 * duration;
                                        JOptionPane.showMessageDialog(null, "Your consultation has been succesfully saved" +"\n The cost of the consultation is: " + " £" + cost);
                                    }

                                    // create a new consultation object with the collected information
                                    Consultation consultation = new Consultation(doctor, selectedDateTime, patientName, patientSurname, patientDob, patientMobileNum, patientId, encryptedNotes, endTime, cost);

                                    // add the consultation to the system
                                    manager.addConsultation(consultation);

                                    //add the consultation to the doctor
                                    doctor.addConsultation(consultation);
                                    consultation.setDoctor(doctor);

                                    //save consultation after its added to list
                                    manager.saveConsultations();

                                    //hide the consultation panel when information gets saved
                                    consultationPanel.setVisible(false);
                                }catch (ClassCastException ex) {
                                    // handle error when doctorComboBox.getSelectedItem() is not an instance of Doctor
                                    JOptionPane.showMessageDialog(null, "Error: Invalid doctor selected");
                                } catch (NumberFormatException ex) {
                                    // handle error when durationField.getText() is not a valid integer
                                    JOptionPane.showMessageDialog(null, "Error: Invalid duration entered");
                                }
                            }
                        });


                        // create a panel for the save consultation button
                        JPanel buttonPanel = new JPanel();
                        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                        buttonPanel.add(saveConsultationButton);

                        // add the patient information panel and button panel to the consultation panel
                        consultationPanel.add(patientInfoPanel, BorderLayout.SOUTH);
                        consultationPanel.add(buttonPanel, BorderLayout.NORTH);

                        // add consultation panel to the booking panel
                        bookingPanel.add(consultationPanel);

                        // update the booking window with the changes
                        bookingWindow.validate();
                        bookingWindow.repaint();
                    }
                });

                //back to main menu action listener
                backToMenuButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //close the current window
                        bookingWindow.dispose();

                        //show skin consultation system window
                        SkinConsultationSystemGUI.this.setVisible(true);
                    }
                });


                //add select doctor label to the panel
                bookingPanel.add(doctorSelect);

                //add the combo box to the panel
                bookingPanel.add(doctorComboBox);

                // add time select label field to the panel
                bookingPanel.add(timeSelect);

                // add time spinner to the panel
                bookingPanel.add(timeSpinner);

                // add check availability button to the panel
                bookingPanel.add(checkButton);

                // add check availability button to the panel
                bookingPanel.add(backToMenuButton);

                // add the panel to the window
                bookingWindow.add(bookingPanel);

                // close the Skin Consultation Manager window
                setVisible(false);
                dispose();

                // display the "Book Consultation" window
                bookingWindow.setVisible(true);
            }
        });

        //action listener for view consultations
        viewConsultations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // create a new window
                JFrame consultationWindow = new JFrame();
                consultationWindow.setTitle("Consultations");
                consultationWindow.setSize(1000, 400);
                consultationWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // create the consultation table and table model
                JTable consultationTable = new JTable();
                ConsultationTableModel tableModel = new ConsultationTableModel(manager.getConsultations());
                consultationTable.setModel(tableModel);

                // create a "Back" button
                JButton backButton = new JButton("Back to Main Menu");

                // add an action listener to the "Back" button
                backButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // close the consultation window
                        consultationWindow.dispose();

                        //show skin consultation system window
                        SkinConsultationSystemGUI.this.setVisible(true);
                    }
                });

                // add the "Back" button to the window
                consultationWindow.add(backButton, BorderLayout.SOUTH);

                // add the consultation table to the window
                consultationWindow.add(new JScrollPane(consultationTable), BorderLayout.CENTER);

                // make the window visible
                consultationWindow.setVisible(true);

                // close the current window
                SkinConsultationSystemGUI.this.dispose();
            }
        });

        // create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        // add the buttons to the panel
        buttonPanel.add(sortButton);
        buttonPanel.add(bookConsultationButton);
        buttonPanel.add(viewConsultations);

        // add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);

        // add the doctor table to the frame
        add(new JScrollPane(doctorTable), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        //create an instance of the WestminsterSkinConsultationManager class
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        // create an instance of the SkinConsultationSystemGUI class and pass in the list of doctors
        SkinConsultationSystemGUI gui = new SkinConsultationSystemGUI(manager);

        // display the GUI
        gui.setVisible(true);

        //display the console menu
        manager.readInformation();
        manager.loadConsultations();
        manager.displayMenu();
    }
}
