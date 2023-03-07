import java.util.Date;

public class Person {
    protected String name;
    protected String sName;
    private Date dOB;
    private int mobileNum;


    //Constructor
    public  Person(String name, String sName, Date dOB, int mobileNum){
        this.name = name;
        this.sName = sName;
        this.dOB = dOB;
        this.mobileNum = mobileNum;
    }

    // Getter method for name attribute
    public String getName(){
        return name;
    }

    //Setter method for name attribute
    public  void setName(String name){
        this.name = name;
    }

    //Getter method for surname attribute
    public String getsName() {
        return sName;
    }

    public String getFullName() {
        return sName + ',' + name;
    }

    //Setter method for Surname attribute
    public void setsName(String sName) {
        this.sName = sName;
    }

    //Getter method for date of birth attribute
    public Date getDOB() {
        return dOB;
    }

    //setter methods for date of birth attribute
    public void setDOB(Date dOB) {
        this.dOB = dOB;
    }

    //Getter methods for mobile number attribute
    public int getMobileNum() {
        return mobileNum;
    }

    //setter methods for mobile number attribute
    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }

}
