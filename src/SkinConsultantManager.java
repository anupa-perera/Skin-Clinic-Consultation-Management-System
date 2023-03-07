import java.util.List;

public interface SkinConsultantManager {
   //method to display the menu
    void displayMenu();

    //method to add a doctor
    void addDoctor(Doctor doctor);

    //method to delete a doctor
    void deleteDoctor(String deleteLicenceNumber);

    //method to print the list of doctors
    String printDoctors();

    //method to save list of doctors
    void saveInformation();

    //method to read list of doctors saved
    void readInformation();

    //method to get list of doctors
    List<Doctor> getDoctors();

}
