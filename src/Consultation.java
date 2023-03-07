import java.text.SimpleDateFormat;
import java.util.Date;

public class Consultation {
    private Date date;
    private String timeSlot;
    private Patient patient;
    private Doctor doctor;
    private double cost;
    private String notes;
    private Date selectedDateTime;
    private String patientName;
    private String patientSNname;
    private String patientDob;
    private String patientMobileNum;
    private String patientId;
    private String patientNotes;
    private Date endTime;
    //Constructor
    public Consultation(Date date, String timeSlot, Patient patient, Doctor doctor, double cost, String notes) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.doctor = doctor;
        this.cost = cost;
        this.notes = notes;
    }

    public Consultation(Doctor doctor, Date selectedDateTime, String patientName, String patientSName, String patientDob, String patientMobileNum, String patientId, String patientNotes, Date endTime,double cost) {
        this.doctor= doctor;
        this.selectedDateTime = selectedDateTime;
        this.patientName = patientName;
        this.patientSNname = patientSName;
        this.patientDob = patientDob;
        this.patientMobileNum = patientMobileNum;
        this.patientId = patientId;
        this.patientNotes = patientNotes;
        this.endTime = endTime;
        this.cost=cost;
    }

    //Getter method for getDate
    public Date getDate() {
        return date;
    }

    //Setter method for getDate
    public void setDate(Date date) {
        this.date = date;
    }

    //Getter method Timeslot
    public String getTimeSlot() {
        return timeSlot;
    }

    //Setter method for Timeslot
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    //Getter method for Patient
    public Patient getPatient() {
        return patient;
    }

    //Setter method for patient
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //Getter method for Doctor
    public Doctor getDoctor() {
        return doctor;
    }

    //Setter method for Doctor
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    //Getter method for Cost
    public double getCost() {
        return cost;
    }

    //Setter method for Cost
    public void setCost(double cost) {
        this.cost = cost;
    }

    //Getter method for Notes
    public String getNotes() {
        return notes;
    }

    //Setter method for Notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getSelectedDateTime() {
        return selectedDateTime;
    }

    public void setSelectedDateTime(Date selectedDateTime) {
        this.selectedDateTime = selectedDateTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSNname() {
        return patientSNname;
    }

    public void setPatientSNname(String patientSNname) {
        this.patientSNname = patientSNname;
    }

    public String getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(String patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientMobileNum() {
        return patientMobileNum;
    }

    public void setPatientMobileNum(String patientMobileNum) {
        this.patientMobileNum = patientMobileNum;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientNotes() {
        return patientNotes;
    }

    public void setPatientNotes(String patientNotes) {
        this.patientNotes = patientNotes;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        // create a SimpleDateFormat object to format the date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        // return a string representation of the consultation in the following format:
        // doctorName,selectedDateTime,patientName,patientSName,patientDob,patientMobileNum,patientId,patientNotes,endTime,cost
        return String.join(",",
                doctor.getName() +' '+ doctor.getsName(),
                dateFormat.format(selectedDateTime),
                patientName,
                patientSNname,
                patientDob,
                patientMobileNum,
                patientId,
                patientNotes,
                dateFormat.format(endTime),
                String.valueOf(cost)
        );
    }


}
