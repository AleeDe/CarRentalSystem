package BookingProcess;
import Database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class bookingprocess {
    private static int days;
    private static String VehNum;
    private static String Model;
    private static String Brand;
    private static double RentalPrice;
    private static String BookingDate;
    private static String returnDate;
    private static double fare;
    private static double taxes=30.0;
    private static String BookingtableName ="Bookings";
    private static String CarSelectiontableName ="CarRental";

    public static void process(String username){
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================\tBooking Process=====================\t");
        while (true){
            System.out.println("Enter the VehcileNumber");
            VehNum = scanner.nextLine();


            System.out.println("Enter the Booking Date (yyyy-MM-dd):");
            BookingDate = scanner.nextLine();

            System.out.println("Rental Days");
            days = scanner.nextInt();
            scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            try {
                LocalDate bd = LocalDate.parse(BookingDate, formatter);
                BookingDate = bd.format(formatter);


                LocalDate returnD = bd.plusDays(days);// Adds days
                returnDate = returnD.format(formatter);

            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
                continue;
            }






            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();
            try {
                Statement statement = connection.createStatement();
                String showCar = String.format("SELECT RentalPrice,ModelNumber,Brand FROM %s WHERE VehicleNumber = '%s'",CarSelectiontableName, VehNum);
                ResultSet resultSet = statement.executeQuery(showCar);
                while (resultSet.next()) {
                    RentalPrice = resultSet.getDouble("RentalPrice");
                    Model = resultSet.getString("ModelNumber");
                    Brand = resultSet.getString("Brand");

                }
                break;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid Vehicle Number!");
                continue;
            }
        }

        fare = (RentalPrice*days)+taxes;
//        booking process start

        while (true){
            System.out.println("===============\tBooking Confirmation\t===============");
            System.out.println("Booking Confirmation");
            System.out.println("Customer Name: " + username);
            System.out.println("Model Number: " + Model);
            System.out.println("Brand: " + Brand);
            System.out.println("Booking Date: " + BookingDate);
            System.out.println("Return Date: " + returnDate);
            System.out.println("Fare: Rs" + fare);

            System.out.println("Press Y/N For confirmation");
            String ch = scanner.next();

            if (ch.equalsIgnoreCase("Y")) {
                System.out.println("Thank you for choosing SuperBrand!\n" +
                        "For any inquiries, please contact our customer service at (123) 456-7890 or email us at support@superbrand.com.");
                DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
                Connection connection = connectionManager.getConnection();
                String InsertBooking = String.format("INSERT INTO %s (username, ModelNumber, Brand, BookingDate, ReturnDate, Fare) VALUES ('%s','%s','%s','%s','%s',%f)",BookingtableName,username,Model,Brand,BookingDate,returnDate,fare);
                String UpdateCarRetal = String.format("UPDATE %s SET AvailabilityStatus = 'false' WHERE VehicleNumber = '%s'",CarSelectiontableName,VehNum);
                try {
                    Statement statement = connection.createStatement();
                    int RowAffected = statement.executeUpdate(InsertBooking);
                    if (RowAffected>0){
                        System.out.println("Booking Successfully");
                int RowAfftedByEditing = statement.executeUpdate(UpdateCarRetal);
                        if (RowAfftedByEditing>0){
                            System.out.println("List Updates");
                        }


                break;
            }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            break;

            } else if (ch.equalsIgnoreCase("N")) {
                System.out.println("Your Booking is not process more");
                System.out.println("For any inquiries, please contact our customer service at (123) 456-7890 or email us at support@superbrand.com.");
                break;
            } else {
                System.out.println("Invalid Option! Please try again");
                continue;
            }
        }




    }
}
