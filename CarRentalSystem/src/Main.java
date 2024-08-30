import UserInterface.UserRegistration;
import UserInterface.LoginPage;

import java.sql.SQLException;

import static BookingProcess.ReturnCar.returncar;
import static BookingProcess.checkingbooking.cancelbooking;
import static BookingProcess.checkingbooking.deluser;
import static UserInterface.LoginPage.loginPannel;
import static VehicleSelection.InsertCar.insertCar;
import static VehicleSelection.ShowCar.showcar;
import static VehicleSelection.ShowCar.showuser;

public class Main {
    public static void main(String[] args) {

        loginPannel();

    }
}