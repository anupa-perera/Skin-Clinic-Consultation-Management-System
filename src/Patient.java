import java.util.Date;

public class Patient extends Person{
    private String patientId;

    //Constructor
    public Patient(String name, String SName, Date dOB, int mobileNum, String patientId) {
        //Invoke parent class constructor method
        super(name, SName, dOB, mobileNum);
        this.patientId = patientId;
    }

    //Getter method for medical PatientId
    public String getPatientId() {
        return patientId;
    }

    //Setter method for medical PatientId
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
