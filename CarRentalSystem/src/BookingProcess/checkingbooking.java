package BookingProcess;

import java.util.Formatter;
import Database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class checkingbooking {
    public static void checkingbooking() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();
            Formatter fmt = new Formatter();
            fmt.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", "BookingId", "Username", "ModelNumber","Brand", "BookingDate", "ReturnDate","ReturnDate");
            try {
                Statement statement = connection.createStatement();
                String ShowBooking = "SELECT * FROM Bookings";
                ResultSet resultSet = statement.executeQuery(ShowBooking);
                while (resultSet.next()) {
                    String bookingid = resultSet.getString("bookingid");
                    String username = resultSet.getString("username");
                    String ModelNumber = resultSet.getString("ModelNumber");
                    String Brand = resultSet.getString("Brand");
                    String BookingDate = resultSet.getString("BookingDate");
                    String ReturnDate = resultSet.getString("ReturnDate");
                    double Fare = resultSet.getDouble("Fare");
//                    System.out.printf("VehicleNum: %s\tModel: %s\tBrand: %s\tYOM: %d\tColor: %s\tFuelType: %s\tRentalPrice/Day: %f\tMileage: %dkm\tFeatures: %s\n",VehicleNumber,Model,Brand,YOM,Color,FuelType,RentalPrice,Mileage,Features);
//                    System.out.printf("%s\t%s\t%s\t%d\t%s\t%s\t%f\t%dkm\t%s\n",VehicleNumber,Model,Brand,YOM,Color,FuelType,RentalPrice,Mileage,Features);

                    fmt.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", bookingid, username, ModelNumber, Brand, BookingDate, ReturnDate, ReturnDate);
                }
                System.out.println(fmt);
                break;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }
    }
    public static void cancelbooking(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            System.out.println("Enter bookingid");
            int bookingid =  scanner.nextInt();
                    scanner.nextLine();
            try {
                Statement statement = connection.createStatement();
                String deleteQuery  = String.format("DELETE FROM Bookings WHERE bookingid = %s", bookingid);
                int AffactedRow = statement.executeUpdate(deleteQuery );
                if (AffactedRow>0) {
                    System.out.println("Booking Cancel Successfully");
                    break;
                }
                else {
                    System.out.println("Invalid booking id! Please ty again....");
                    continue;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }

    }


    public static void deluser(){
        Scanner scanner = new Scanner(System.in);
        while (true) {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            System.out.println("Enter username");
            String username =  scanner.next();
            scanner.nextLine();
            try {
                Statement statement = connection.createStatement();
                String deleteQuery  = String.format("DELETE FROM userreg WHERE username = '%s'", username);
                int AffactedRow = statement.executeUpdate(deleteQuery );
                if (AffactedRow>0) {
                    System.out.println("User has been  Deleted Successfully");
                    break;
                }
                else {
                    System.out.println("Invalid username! Please ty again....");
                    continue;
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
                continue;
            }

        }

    }
}

