package UserInterface;

public class UserRegistration {
//    private int registrationNum;
    private String fName;
    private String username;
    private String gender;
    private String dateOfBirth;
    private String cnicNum;
    private String phoneNum;
    private String licenseNum;
    private String password;


    public UserRegistration() {
        this("Unknown","guest","Not specficed","2004/12/04","N/A","N/A","N/A","123");
    }

    public UserRegistration(String fName,String username, String gender, String dateOfBirth, String cnicNum, String phoneNum, String licenseNum,String password) {

        this.gender = gender;
        this.username = username;
        this.fName = fName;
        this.dateOfBirth = dateOfBirth;
        this.cnicNum = cnicNum;
        this.phoneNum = phoneNum;
        this.licenseNum = licenseNum;
        this.password = password;
    }
    public String getfName() {
        return fName;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getCnicNum() {
        return cnicNum;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public String getPassword() {
        return password;
    }
}
