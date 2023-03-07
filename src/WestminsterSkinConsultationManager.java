import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.lang.System.out;

public class WestminsterSkinConsultationManager implements SkinConsultantManager {
    //list to store list of doctors
    private List<Doctor> doctors;

    //list to store consultations
    private List<Consultation> consultations;

    //scanner to read inputs
    private Scanner scn;


    //Constructor
    public WestminsterSkinConsultationManager() {
        //Initialize doctors attribute with a new instance of array list with a capacity of 10 elements
        this.doctors = new ArrayList<>(10);
        this.consultations = new ArrayList<>();
        this.scn = new Scanner(System.in);
    }

    public static void main(String[] args) {
        //creating a new instance of WestminsterSkinConsultationManager
        SkinConsultantManager manager = new WestminsterSkinConsultationManager();
        manager.readInformation();
        manager.displayMenu();
    }

    @Override
    public void displayMenu() {
        //Display menu and prompt user input
        Scanner scn = new Scanner(System.in);
        while (true) {
            out.println("     Welcome to Skin Consultant System");
            out.println("************************************************");
            out.println("A: Add a new doctor");
            out.println("D: Delete a doctor");
            out.println("P: Print the list of doctors");
            out.println("S: Save the current session");
            out.println("R: Recall Previous Session");
            out.print("\nEnter your Selection: ");
            String selection = scn.next();


            //Uppercase conversion
            selection = selection.toUpperCase();

            //switch case for inputs
            switch (selection) {
                case "A":
                    addDoctor(null);
                    break;

                case "D":
                    deleteDoctor(null);
                    break;

                case "P":
                    printDoctors();
                    break;

                case "S":
                    saveInformation();
                    break;

                case "R":
                    readInformation();

                default:
                    out.println("Invalid input, please enter again!");
            }
        }

    }

    @Override
    //method to add a doctor
    public void addDoctor(Doctor inputDoctor) {

        if (inputDoctor != null){
            doctors.add(inputDoctor);
            out.println("Doctor added to the list successfully");
            return;
        }

        //check if consultation centre is full(10 doctors maximum) in terms of doctors
        if (doctors.size() >= 10) {
            //if doctors exceeded capacity display error text
            out.println("Doctors at full capacity cannot add more");
        }

        while (true) {
            try {
                out.println("Enter doctors first name: ");
                String name = scn.next();
                //validate user input for name where only letters are allowed
                if (!name.matches("^[a-zA-Z]+$"))
                    throw new InputMismatchException();

                out.println("Enter doctors surname: ");
                String sName = scn.next();
                //validate user input for surname name where only letters are allowed
                if (!sName.matches("^[a-zA-Z]+$"))
                    throw new InputMismatchException();

                out.println("Enter doctors Date Of Birth(DD/MM/YYYY) :");
                //assigning DOB to a string before parsing
                String dOBString = scn.next();
                //parse DOB string into a Date object
                Date dOB = new SimpleDateFormat("dd/MM/yyyy").parse(dOBString);

                out.println("Enter doctors mobile number :");
                int mobileNum = scn.nextInt();
                // check if mobile number is exactly 10 digits long
                if (Integer.toString(mobileNum).length() != 9)
                    throw new InputMismatchException();

                out.println("Enter doctors medical license number :");
                String medicalLicenseNum = scn.next();

                out.println("Enter doctors specilisation :");
                String specialisation = scn.next();

                //create new doctor object
                Doctor doctor = new Doctor(name, sName, dOB, mobileNum, medicalLicenseNum, specialisation);

                //Add doctor the doctors list
                doctors.add(doctor);
                out.println("Doctor added to the list successfully");

                break;

            } catch (ParseException e) {
                //handle errors while parsing and displays error message
                out.println("Invalid date of brith. Please check the format and try again");
            } catch (InputMismatchException e) {
                //Handle errors related to any mismatched submissions
                out.println("Invalid Input, Please check details and try again");
                //clear scanner's buffer
                scn.nextLine();
            }
        }
    }

    //method to delete a doctor
    @Override
    public void deleteDoctor(String deleteLicenceNumber) {
        //check if the list of doctors is empty
        if (doctors.isEmpty()) {
            out.println("There are currently no doctors in the list to remove, Returning to main menu.");
            return;
        }

        out.println("Please enter license number of the doctor that needs to be deleted :");

        String medicalLicenseNum;

        if (deleteLicenceNumber != null){
            medicalLicenseNum = deleteLicenceNumber;
        }else {
            medicalLicenseNum = scn.next();
        }

        //assigning -1 assuming the element may or may not be present in the list
        int index = -1;
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getMedicalLicenseNum().equals(medicalLicenseNum)) {
                //find index of the doctor with specific medical license number
                index = i;
                break;
            }
        }
        //print error if no element with the matching input is found
        if (index == -1) {
            out.println("No Doctor with the specific medical license number found!, Returning to main menu.");
            return;
        }

        //Remove doctor from the list if the number was found
        Doctor deletedDoctor = doctors.remove(index);
        out.println("Doctor with the medical license number " + medicalLicenseNum + " " + deletedDoctor.getFullName() + " has been removed, Total number of remaining doctors in the system :" + doctors.size());
    }

    //method to print list of doctors
    @Override
    public String printDoctors() {
        //Check if of doctors are empty and prompts an error message
        if (doctors.isEmpty()) {
            out.println("List is currently empty, no doctors to print");
        }

        //sort the list of alphabetically using a comparator
        doctors.sort(Comparator.comparing(Doctor::getsName));

        //removes time component while printing
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

        //print list of doctors alphabetically by surname with relevant information
        out.println("-----List of doctors Available------");
        //
        String output = "";
        for (Doctor doctor : doctors) {
            output+=("Surname, Name :" + doctor.getsName() + ",  " + doctor.getName()+"\n");
            //assigning formatted date without time
            output+=("Date of birth : " + dateFormat.format(doctor.getDOB())+"\n");
            output+=("Mobile Number :" + doctor.getMobileNum()+"\n");
            output+=("Medical License Number :" + doctor.getMedicalLicenseNum()+"\n");
            output+=("Specilisation :" + doctor.getSpecialisation()+"\n");
            output+=("_________________________"+"\n");
        }
        out.println(output);
        return output;
    }

    //method to save list of doctors
    @Override
    public void saveInformation() {
        try {
            //create a new file instance
            File file = new File("save.txt");
            //create a fileoutputstream write the information to a save file
            FileOutputStream fos = new FileOutputStream(file);

            //create an objectoutputstream to write objects to a file
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

            //write vales of each doctor object to the file
            for (Doctor doctor : doctors) {
                bw.write(doctor.getName() + "," + doctor.getsName() + "," + doctor.getDOB() + "," + doctor.getMobileNum() + "," + doctor.getMedicalLicenseNum() + "," + doctor.getSpecialisation());
                bw.newLine();
            }


            //close the streams
            bw.close();
            fos.close();

            out.println("Information saved to a file successfully!");

            //exception handling while saving to file
        } catch (IOException e) {
            //catch exceptions while writing to the file
            out.println("Failed saving information to a file, check for error in " + e.getMessage());
        }


    }

    @Override
    public void readInformation() {
        try {
            //Create new file instance
            File file = new File("Save.txt");

            //create fileinputstream to read information from saved file
            FileInputStream fis = new FileInputStream(file);

            // buffer reader to read values as plain text from file
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            // Check while the reader reads no values (null)
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                // Check if the line represents a Doctor, which is equal to 6 attributes by Doctor
                if (fields.length == 6) {
                    // Create a Doctor object and add it to the doctors list
                    String name = fields[0];
                    String sName = fields[1];
                    String dOBString = fields[2];
                    //convert mobile number to integer
                    int mobileNum = Integer.parseInt(fields[3]);
                    String medicalLicenseNum = fields[4];
                    String specialisation = fields[5];

                    // Convert the DOB string to a Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                    Date DOB = dateFormat.parse(dOBString);

                    //create a new doctor object
                    Doctor doctor = new Doctor(name, sName, DOB, mobileNum, medicalLicenseNum, specialisation);
                    //add doctor to doctors list
                    doctors.add(doctor);
                }
            }

            //close streams
            br.close();
            fis.close();

            out.println("Information read from the previous session successfully!");
        } catch (FileNotFoundException e) {
            out.println("File not found");
        } catch (IOException e) {
            out.println("Failed Recalling information from the previous session, check for error in " + e.getMessage());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //method to get list of doctors for GUI implementation
    public List<Doctor> getDoctors() {
        return doctors;
    }

    public boolean isDoctorAvailable(Doctor doctor, Date startTime) {
        // get the list of consultations for the doctor
        List<Consultation> consultations = doctor.getConsultations();

        // iterate over the list of consultations and check for overlap
        for (Consultation consultation : consultations) {
            // get the start and end times of the consultation
            Date consultationStartTime = consultation.getSelectedDateTime();
            Date consultationEndTime = consultation.getEndTime();

            // and if it is before or equal to the end time of the consultation
            if (startTime.compareTo(consultationStartTime) >= 0 && startTime.compareTo(consultationEndTime) <= 0) {
                // there is overlap, so the doctor is not available
                return false;
            }
        }

        // no overlap was found, so the doctor is available
        return true;
    }


    public Doctor getRandomAvailableDoctor(Date startTime) {
        // create a list of available doctors
        List<Doctor> availableDoctors = new ArrayList<>();

        // iterate over the list of doctors
        for (Doctor doctor : doctors) {
            // check if the doctor is available at the given time
            if (isDoctorAvailable(doctor, startTime)) {
                // add the doctor to the list of available doctors
                availableDoctors.add(doctor);
            }
        }

        // if there are no available doctors, return null
        if (availableDoctors.isEmpty()) {
            return null;
        }

        // generate a random index between 0 and the number of available doctors - 1
        int index = new Random().nextInt(availableDoctors.size());

        // return a random available doctor from the list
        return availableDoctors.get(index);
    }


    public void addConsultation(Consultation consultation) {
        // add the consultation to the list of consultations
        consultations.add(consultation);
    }



    public boolean patientIdExists(String patientId) {
        // iterate over the list of consultations and check for the given patient ID
        for (Consultation consultation : consultations) {
            // get the patient ID of the consultation
            String consultationPatientId = consultation.getPatientId();

            // check if the given patient ID matches the consultation's patient ID
            if (patientId.equals(consultationPatientId)) {
                // the patient ID was found, so it exists
                return true;
            }
        }

        // the patient ID was not found in the list of consultations, so it does not exist
        return false;
    }

    public void saveConsultations() {
        try {
            // create a FileWriter object to write to the file
            FileWriter writer = new FileWriter("consultations.txt");

            // iterate through the list of consultations and write each consultation to the file
            for (Consultation consultation : consultations) {
                writer.write(consultation.toString() + "\n");
            }

            // close the FileWriter object
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadConsultations() {
        try {
            //Create new file instance
            File file = new File("consultations.txt");

            //create fileinputstream to read information from saved file
            FileInputStream fis = new FileInputStream(file);

            // create a BufferedReader to read from the file
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            // read each line of the file
            while ((line = reader.readLine()) != null) {
                // split the line into parts using the "|" delimiter
                String[] parts = line.split(",");
                // get the doctor object corresponding to the name in the first part of the line
                Doctor doctor = findDoctorByFullName(parts[0]);
                // create a Date object from the second part of the line
                Date dateTime = new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(parts[1]);
                // create a new consultation object with the collected information
                Consultation consultation = new Consultation(doctor, dateTime, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], new SimpleDateFormat("dd/MM/yyyy hh:mm a").parse(parts[8]), Double.parseDouble(parts[9]));
                // add the consultation to the list of consultations
                consultations.add(consultation);
            }

            //close streams
            reader.close();
            fis.close();

        }catch (FileNotFoundException e) {
            out.println("File not found");

        } catch (IOException | ParseException e) {
            // handle the exception
            e.printStackTrace();
        }
    }

    //method to get list of consultations
    public List<Consultation> getConsultations() {
        return consultations;
    }

    //method to find the relevant doctor with in doctors
    public Doctor findDoctor(String name) {
        for (Doctor doctor : doctors) {
            if (doctor.getFullName().equals(name)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor findDoctorByFullName(String name) {
        for (Doctor doctor : doctors) {
            if ((doctor.getName() + ' '+ doctor.getsName()).equals(name)) {
                return doctor;
            }
        }
        return null;
    }

}
