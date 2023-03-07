import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Doctor extends Person{
    private String medicalLicenseNum;
    private String specialisation;
    protected List<Consultation> consultations;

    //constructor
    public Doctor(String name, String SName, Date dOB, int mobileNum, String medicalLicenseNum, String specialisation) {
        //Invoke parent class constructor
        super(name, SName, dOB, mobileNum);
        this.medicalLicenseNum = medicalLicenseNum;
        this.specialisation = specialisation;
        this.consultations = new ArrayList<Consultation>();
    }

    //Getter method for medical license number
    public String getMedicalLicenseNum() {
        return medicalLicenseNum;
    }

    //Setter method for MedicalLicenseNum
    public void setMedicalLicenseNum(String medicalLicenseNum) {
        this.medicalLicenseNum = medicalLicenseNum;
    }

    //Getter method for Specialisation
    public String getSpecialisation() {
        return specialisation;
    }

    //Setter method for Specialisation
    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    //setter method for doctor consultation list
    public List<Consultation> getConsultations(){
        return this.consultations;
    }

    //setter method for doctor consultation list
    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    //doctor name to be passed on to Jcombobox
    public String toString(){
        return sName + "," + name;
    }


    //method to add a consultation
    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

}
