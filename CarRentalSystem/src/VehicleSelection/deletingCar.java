package VehicleSelection;

import Database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class deletingCar {
    static Scanner scanner = new Scanner(System.in);

    public static void deletingcar() {
        while (true) {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            System.out.println("Enter vehicleNum");
            String vehicleNum = scanner.nextLine();
            try {
                Statement statement = connection.createStatement();
                String deleteQuery  = String.format("DELETE FROM CarRental WHERE VehicleNumber = '%s'", vehicleNum);
                int AffactedRow = statement.executeUpdate(deleteQuery );
                if (AffactedRow>0) {
                    System.out.println("Booking Successfully");
                    break;
                }
                else {
                    System.out.println("Invalid Vehcile Number! Please ty again....");
                    continue;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                continue;
            }



        }
    }
}