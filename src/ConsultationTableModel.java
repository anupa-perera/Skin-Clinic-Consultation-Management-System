import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.List;


public class ConsultationTableModel extends AbstractTableModel {
    private WestminsterSkinConsultationManager manager;
    private List<Consultation> consultations;

    public ConsultationTableModel(List<Consultation> consultations) {
        this.consultations = consultations;
    }

    @Override
    public int getColumnCount() {
        // returns the number of columns in the table
        return 6;
    }

    @Override
    public int getRowCount() {
        // returns the number of rows in the table
        return consultations.size();
    }

    @Override
    public String getColumnName(int columnIndex) {
        // returns the name of the column at the mentioned index
        switch (columnIndex) {
            case 0:
                return "Doctor Name";
            case 1:
                return "Start time";
            case 2:
                return "End time";
            case 3:
                return "Patient ID";
            case 4:
                return "Patient name";
            case 5:
                return "Cost";
            default:
                return "";
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // get consultation object at a specific row
        Consultation consultation = consultations.get(rowIndex);
        switch (columnIndex) {
            // return the value for specific column
            case 0:
                String doctor = consultation.getDoctor().getName() + " " + consultation.getDoctor().getsName();
                return doctor;
            case 1:
                // format the date and time to display only the date and time and omit the seconds
                return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(consultation.getSelectedDateTime());
            case 2:
                // format the date and time to display only the date and time and omit the seconds
                return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(consultation.getEndTime());
            case 3:
                return consultation.getPatientId();
            case 4:
                return consultation.getPatientSNname();
            case 5:
                return consultation.getCost();
            default:
                return "";
        }
    }

}