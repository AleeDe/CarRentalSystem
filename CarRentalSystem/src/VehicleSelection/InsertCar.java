package VehicleSelection;

import Database.DatabaseConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertCar {
    static Scanner scanner = new Scanner(System.in);

    public static void insertCar() {
        while (true) {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();

            System.out.println("Enter vehicleNum");
            String vehicleNum = scanner.nextLine();
            try {
                Statement statement = connection.createStatement();
                String checkvehcileNum = String.format("SELECT VehicleNumber FROM CarRental WHERE VehicleNumber = '%s'", vehicleNum);
                ResultSet resultSet = statement.executeQuery(checkvehcileNum);
                if (resultSet.next()) {
                    System.out.println("Username VehicleNumber exist!");
                    continue;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Enter ModelNumber");
            String  modelNum= scanner.nextLine();
            System.out.println("Enter Brand");
            String  brand= scanner.nextLine();
            System.out.println("Enter YearOfManufacture");
            int  YOM= scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Color");
            String  color= scanner.nextLine();
            System.out.println("Enter FuelType");
            String  fuelType= scanner.nextLine();
            System.out.println("Enter RentalPrice");
            double  rentalPrice= scanner.nextDouble();
            System.out.println("Enter Mileage");
            int  mileage= scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Features");
            String  features= scanner.nextLine();

            CarCategories car = new CarCategories(vehicleNum,modelNum, brand,YOM,color, fuelType, rentalPrice,mileage, features);

            try {
                Statement statement = connection.createStatement();
                String InsertCar = String.format("INSERT INTO CarRental (VehicleNumber, ModelNumber, Brand, YearOfManufacture, Color, FuelType, RentalPrice, Mileage, Features) VALUES ('%s', '%s', '%s', %d, '%s', '%s', %f, %d, '%s')",car.getVehicleNum(),car.getModelNum(),car.getBrand(),car.getYOM(),car.getColor(),car.getFuelType(),car.getRentPrice(),car.getMileage(),car.getFeatures());
                int AffectedRow = statement.executeUpdate(InsertCar);
                if (AffectedRow>0) {
                    System.out.println("Car Details Inserted SuccessFully!");
                    System.out.println("Are you Inserting more car? Y/N");
                    String ch = scanner.nextLine();
                    if (ch.equalsIgnoreCase("Y")){
                        continue;
                    }
                    else {
                        break;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }



        }
    }
}