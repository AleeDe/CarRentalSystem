package BookingProcess;

import Database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class ReturnCar {
    private static String VehNum;
    private static String username;
    private static String BookingtableName ="Bookings";
    private static String CarSelectiontableName ="CarRental";

    public static void returncar(){
        DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
        Connection connection = connectionManager.getConnection();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Username");
        username = scanner.nextLine();

        System.out.println("Enter the VehcileNumber");
        VehNum = scanner.nextLine();
        try {
            Statement statement = connection.createStatement();
            String showCar = String.format("SELECT * FROM %s WHERE VehicleNumber = '%s'",CarSelectiontableName, VehNum);
            ResultSet resultSet = statement.executeQuery(showCar);
            if(resultSet.next()){
                String UpdateCarRetal = String.format("UPDATE %s SET AvailabilityStatus = 'true' WHERE VehicleNumber = '%s'",CarSelectiontableName,VehNum);
                int AffectedRow = statement.executeUpdate(UpdateCarRetal);
                if (AffectedRow>0) {

                    System.out.println("Return Successfully");
                    System.out.printf("Thank %s for using our service don't forget to feedback us!",username);
                }
            }
            else {
                System.out.println("Invalid Vehicle Number!");
            }
//            break;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Invalid Vehicle Number!");
//            continue;
        }
    }
}
