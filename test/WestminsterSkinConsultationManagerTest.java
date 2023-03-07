import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WestminsterSkinConsultationManagerTest {

    @Test
    void testAddDoctor() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        // Add some doctors and consultations to the manager
        String dateString = "01/01/1990";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = null;

        try {
            dob = df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Doctor doctor = new Doctor("Anupa", "Perera", dob, 1234567890, "123456", "Dermatology");

        manager.addDoctor(doctor);

        Doctor searchDoctor = manager.findDoctorByFullName(doctor.getName() + " " + doctor.getsName());

        assertNotNull(searchDoctor);

    }

    @Test
    void testDeleteDoctor() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        // Add some doctors and consultations to the manager
        String dateString = "01/01/1990";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = null;

        try {
            dob = df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Doctor doctor = new Doctor("Anupa", "Perera", dob, 1234567890, "123456", "Dermatology");

        manager.addDoctor(doctor);

        Doctor searchDoctor = manager.findDoctorByFullName(doctor.getName() + " " + doctor.getsName());

        assertNotNull(searchDoctor);

        manager.deleteDoctor(doctor.getMedicalLicenseNum());

        Doctor searchDoctorAfterDelete = manager.findDoctorByFullName(doctor.getName() + " " + doctor.getsName());

        assertNull(searchDoctorAfterDelete);
    }

    @Test
    void testPrintDoctors() {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        // Add some doctors and consultations to the manager
        String dateString = "12/12/2000";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = null;

        try {
            dob = df.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        manager.getDoctors().clear();

        Doctor doctor = new Doctor("abc", "abc", dob, 1234567891, "3453", "ttt");
        manager.addDoctor(doctor);

        String output = manager.printDoctors();

        assertEquals(output, "Surname, Name :abc,  abc\n" +
                "Date of birth : 12/12/00\n" +
                "Mobile Number :1234567891\n" +
                "Medical License Number :3453\n" +
                "Specilisation :ttt\n" +
                "_________________________\n");

    }

    @Test
    void testSaveInformation() throws ParseException {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        String dateString = "01/01/1990";
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dob = df.parse(dateString);

        Doctor doctor = new Doctor("Anupa", "Perera", dob, 1234567890, "w123456", "Dermatology");
        manager.addDoctor(doctor);

        try {
            manager.saveInformation();
        } catch (Exception e) {
            fail("Exception thrown while saving information: " + e.getMessage());
        }

        manager.getDoctors().remove(doctor);

        manager.readInformation();

        Doctor readDoctior = manager.findDoctorByFullName(doctor.getName() + ' ' + doctor.getsName());

        assertNotNull(readDoctior);

    }
}