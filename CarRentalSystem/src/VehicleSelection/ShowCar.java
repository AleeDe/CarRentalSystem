package VehicleSelection;
import java.util.Formatter;

import Database.DatabaseConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ShowCar {
    public static void showcar(){
        {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();
            System.out.println("====================\tWelcome to Car Rental System\t====================");
            Formatter fmt = new Formatter();
            fmt.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", "VehicleNumber", "Model", "Brand","YOM", "Color", "FuelType","RentalPrice", "Mileage", "Features");

            try {
                Statement statement = connection.createStatement();
                String showCar = ("SELECT * FROM CarRental WHERE AvailabilityStatus = 'true'");
                ResultSet resultSet = statement.executeQuery(showCar);
                while (resultSet.next()){
                    String VehicleNumber = resultSet.getString("VehicleNumber");
                    String Model = resultSet.getString("ModelNumber");
                    String Brand = resultSet.getString("Brand");
                    int YOM = resultSet.getInt("YearOfManufacture");
                    String Color = resultSet.getString("Color");
                    String FuelType = resultSet.getString("FuelType");
                    double RentalPrice = resultSet.getDouble("RentalPrice");
                    int Mileage = resultSet.getInt("Mileage");
                    String Features = resultSet.getString("Features");
//                    System.out.printf("VehicleNum: %s\tModel: %s\tBrand: %s\tYOM: %d\tColor: %s\tFuelType: %s\tRentalPrice/Day: %f\tMileage: %dkm\tFeatures: %s\n",VehicleNumber,Model,Brand,YOM,Color,FuelType,RentalPrice,Mileage,Features);
//                    System.out.printf("%s\t%s\t%s\t%d\t%s\t%s\t%f\t%dkm\t%s\n",VehicleNumber,Model,Brand,YOM,Color,FuelType,RentalPrice,Mileage,Features);

                    fmt.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s%n", VehicleNumber, Model, Brand,YOM, Color, FuelType,RentalPrice, Mileage, Features);
                }
                System.out.println(fmt);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void showuser(){
        {
            DatabaseConnectionManager connectionManager = DatabaseConnectionManager.getInstance();
            Connection connection = connectionManager.getConnection();
            System.out.println("====================\tWelcome to Car Rental System User Lists\t====================");
            Formatter fmt = new Formatter();
            fmt.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", "RegistrationNum", "FullName", "Gender","DateOfBirth", "CnicNumber", "PhoneNumber","LicenseNumber", "username");

            try {
                Statement statement = connection.createStatement();
                String showCar = ("SELECT * FROM userreg");
                ResultSet resultSet = statement.executeQuery(showCar);
                while (resultSet.next()){
                    int RegistrationNum = resultSet.getInt("registrationNum");
                    String fName = resultSet.getString("fName");
                    String gender = resultSet.getString("gender");
                    String dateOfBirth = resultSet.getString("dateOfBirth");
                    String cnicNum = resultSet.getString("cnicNum");
                    String phoneNum = resultSet.getString("phoneNum");
                    String licenseNum = resultSet.getString("licenseNum");
                    String username = resultSet.getString("username");


                    fmt.format("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %n", RegistrationNum,fName, gender, dateOfBirth,cnicNum, phoneNum, licenseNum,username);
                }

                System.out.println(fmt);
                if(!resultSet.next()){
                    System.out.println("Empty............");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
