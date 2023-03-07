import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;


public class DoctorTableModel extends AbstractTableModel {

    private WestminsterSkinConsultationManager manager;


    public DoctorTableModel(WestminsterSkinConsultationManager manager) {
        this.manager = manager;
    }

    @Override
    public int getColumnCount() {
        //returns  number of columns in the table
        return 6;
    }

    @Override
    public int getRowCount() {
        //returns number of rows in the table
        return manager.getDoctors().size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        //return name of the column at the mentioned index
        switch (columnIndex) {
            case 0:
                return "Name";
            case 1:
                return "Surname";
            case 2:
                return "Date Of Birth";
            case 3:
                return "Mobile Number";
            case 4:
                return "Medical License Number";
            case 5:
                return "Specialisation";
            case 6:
                return "Book Consultation";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //get doctor objects at a specific row
        Doctor doctor = manager.getDoctors().get(rowIndex);
        switch (columnIndex) {
            //return the value for specific column
            case 0:
                return doctor.getName();
            case 1:
                return doctor.getsName();
            case 2:
                //format the date of birth to display only the date and omit time
                return new SimpleDateFormat("dd/MM/yyyy").format(doctor.getDOB());
            case 3:
                return doctor.getMobileNum();
            case 4:
                return doctor.getMedicalLicenseNum();
            case 5:
                return doctor.getSpecialisation();
            default:
                return "";
        }
    }
}

