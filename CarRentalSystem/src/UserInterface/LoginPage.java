package UserInterface;
import BookingProcess.ReturnCar;
import Database.DatabaseConnectionManager;
import VehicleSelection.ShowCar;

import java.sql.*;
import java.util.Scanner;

import static BookingProcess.ReturnCar.returncar;
import static BookingProcess.bookingprocess.process;
import static BookingProcess.checkingbooking.*;
import static VehicleSelection.InsertCar.insertCar;
import static VehicleSelection.ShowCar.showcar;
import static VehicleSelection.ShowCar.showuser;
import static VehicleSelection.deletingCar.deletingcar;

public class LoginPage {

     DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
     Connection connection = connectionManager.getConnection();
    static Scanner scanner = new Scanner(System.in);

    public LoginPage() throws SQLException {
    }

    public static void loginPannel()  {



//        Scanner scanner = new Scanner(System.in);
        System.out.println("=====\tWelcome to Car Rental System\t=====");
        System.out.println("Select the Option");
        System.out.println("1: Login\n2:Login as Admin\n3: Signup");
        int ch = scanner.nextInt();
        scanner.nextLine();

        switch (ch){
            case 1:
                System.out.println("=====\tLogin Page as (USER)\t=====");

                System.out.println("1: Booking Car");
                System.out.println("2: Returning Car");

                int op = scanner.nextInt();
                scanner.nextLine();
                switch (op){
                    case 1:
                        System.out.println("===============\tWelcome to Bookig Car Setion\t================");
                        String username = loginasuser();
                        showcar();
                        process(username);
                        break;
                    case 2:
                    {
                        returncar();
                        break;
                    }
                    default:
                        System.out.println("Invalid Option you selected");
                }

                break;
            case 2:
                System.out.println("=====\tLogin as (Admin)\t=====");
                adminpannel();
                break;
            case 3:
                System.out.println("=====\tSignUp Page\t=====");
                SignUp();
                break;
            default:
                System.out.println("Invalid Option you selected");
        }


    }
    static void adminpannel(){
        while (true) {
            System.out.println("Login as admin");
            final String adminuser = "admin@admin.tr";
            final String adminpassword = "adminali";

            System.out.println("Enter your userName");
            String username = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();

            if (adminuser.equals(username) && adminpassword.equals(password)) {
                System.out.println("Welcome to admin DashBoard");
                break;
            } else {
                System.out.println("Incorrect username and password!");
                continue;
            }
        }
        while (true){
            System.out.println("===============\tWelcome to admin Pannel\t===============");
            System.out.println("1: Insert Car");
            System.out.println("2: Show Car");
            System.out.println("3: Delete Car");
            System.out.println("4: Show Booking");
            System.out.println("5: Cancel Booking");
            System.out.println("6:Show Users");
            System.out.println("7: Delete User");
            System.out.println("8: Exit");

            int ch = scanner.nextInt();
            scanner.nextLine();
            switch (ch){
                case 1:
                    insertCar();
                    continue;
                case 2:
                    showcar();
                    continue;
                case 3:
                    System.out.println("===============\tDeleting Car Pannel\t===============");
                    deletingcar();
                    continue;
                case 4:
                    checkingbooking();
                    continue;
                case 5:
                    cancelbooking();
                    continue;
                case 6:
                    showuser();
                    continue;
                case 7:
                    deluser();
                    continue;
                case 8:
                    break;
                default:
                    System.out.println("Invalid Selection! please try again");
                    continue;
            }
            break;





        }
    }

    static String loginasuser() {
        String username;
        while (true) {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            System.out.println("Enter your username");
            username = scanner.nextLine();
            System.out.println("Enter your password");
            String password = scanner.nextLine();

            try {
                Statement statement = connection.createStatement();
                String lgoincheck = String.format("SELECT username FROM userreg WHERE username = '%s' AND password = '%s'", username, password);
                ResultSet resultSet = statement.executeQuery(lgoincheck);
                if (resultSet.next()) {
                    System.out.println("Login SuccessFully!");

                    break;
                } else {
                    System.out.println("incorrect username or password");
                    continue;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return username;


    }
    static void SignUp() {
        while(true){
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            System.out.println("Enter Your FullName");
            String fName = scanner.nextLine();
            System.out.println("Enter Your username");
            String username = scanner.nextLine();
            try{
                Statement statement = connection.createStatement();
                String checkusername = String.format("SELECT username FROM userreg WHERE username = '%s'",username);
                ResultSet resultSet = statement.executeQuery(checkusername);
                if(resultSet.next()){
                    System.out.println("Username already exist!");
                    continue;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Enter Your Gender");
            String gender = scanner.nextLine();
            System.out.println("Enter Your dateOfBirth in Format(YYYY/MM/DD)");
            String dateOfBirth = scanner.nextLine();
            System.out.println("Enter Your cnicNum");
            String cnicNum = scanner.nextLine();
            System.out.println("Enter Your PhoneNumber");
            String phoneNum = scanner.nextLine();
            System.out.println("Enter Your licenseNum");
            String licenseNum = scanner.nextLine();
            System.out.println("Enter Your Password");
            String password = scanner.nextLine();
            System.out.println("Enter Your Confirm-Password");
            String cpassword = scanner.nextLine();

            if (password.equals(cpassword)) {
                UserRegistration user = new UserRegistration(fName,username, gender, dateOfBirth, cnicNum, phoneNum, licenseNum, password);



                try {
                    Statement statement = connection.createStatement();
                    String queryInsertData = String.format("INSERT INTO userreg(fName,username,gender,dateOfBirth,cnicNum,phoneNum,licenseNum,password) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')", user.getfName(),user.getUsername(), user.getGender(), user.getDateOfBirth(), user.getCnicNum(), user.getPhoneNum(), user.getLicenseNum(), user.getPassword());
                    int rowsAffected = statement.executeUpdate(queryInsertData);
                    if (rowsAffected > 0) {
                        System.out.println("Data Inserted Successfully");
                        System.out.println("Welcome to login Page");
                        loginPannel();
                        break;
                    } else {
                        System.out.println("Data not inserted");
                        continue;
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            } else {
                System.out.println("Password are not matched");
                continue;
            }
        }
    }
}
