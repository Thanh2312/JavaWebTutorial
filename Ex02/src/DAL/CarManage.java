package DAL;

import DTO.Brand;
import DTO.Car;
import DTO.MediumCar;
import DTO.OldCar;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarManage {

    private static String query;
    private ConnectDB connectDB;
    public CarManage(){ connectDB = new ConnectDB();
    }

    public static boolean sameNumberPlate(String numberPlate) {
        query = "select * from Car where NumberPlate like '" + numberPlate + "'" ;
        return false;
    }

    public int total(){
        query = "select count(*) from Car";
        int value = 0;
        try {
            value = connectDB.GetTable(query).getInt(1);
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
        finally {
            return value;
        }
    }

    public void addCar(Car car) {//truyền vào là 1 cái car thôi, vấn đề bây giờ là mình lấy 1 cái car từ đâu để truyền
        query = "INSERT INTO Car (IDCar, NumberPlate,YearOfManufacture,Brand,HaveInsurance,CarType) VALUES ('" + car.getId() + "', '" + car.getNumberPlate() + "', '" +
                car.getYearManufacture() + "', '" + car.getBrand() + "', '" +
                car.isHaveInsurance() + "', '" + car.getTypeCar() + "')";
        connectDB.DataHandle(query);
    }

    public ResultSet getList(String carType) {
        query = "SELECT IDCar, NumberPlate, YearOfManufacture, CarType, HaveInsurance FROM dbo.Car";
        return connectDB.GetTable(query);
    }

    public ResultSet getList() {
        query = "SELECT CarID, NumberPlate, YearOfManufacture, CarType, HaveInsurance FROM dbo.Car";
        return connectDB.GetTable(query);
    }

    public boolean haveInsurance(String id)    {
        query = "SELECT HaveInsurance FROM dbo.Car WHERE IDCar = '" + id + "'";
        return true;
    }

    public String insurancePack(String insurance){
        query = "select PackageType from Insurance where InsurancePackage = " + "'" + insurance + "'";
        return insurancePack(insurance);
    }

    public String insurancePackCar(String id){
        query="select PackageType from PackType, Car where IDCar = " +
                "'" + id + "' and IDCar.CarType = PackType.CarType";
        return insurancePackCar(id);
    }

    public void updateInfo(String carID, String insurancePack) {
        query = "UPDATE Car" +
                "SET HaveInsurance = true, InsurancePackage = '" + insurancePack + "'" +
                "WHERE IDCar = '" + carID + "'";
        connectDB.DataHandle(query);
    }

    public static void main(String[] args) {
        CarManage carManage = new CarManage();
//        int total = carManage.total();
        Car car = new MediumCar();
        car.setId("new5");
        car.setYearManufacture(1980);
        car.setBrand(Brand.HUYNDAI);
        car.setNumberPlate("AAD02");
        car.setHaveInsurance(true);
        //-- car
        Car car1 = new OldCar();
        car1.setId("new6");
        car1.setYearManufacture(1980);
        car1.setBrand(Brand.HUYNDAI);
        car1.setNumberPlate("AAD02");
        car1.setHaveInsurance(true);
        // car 1
        Car car2 = new OldCar();
        car2.setId("new7");
        car2.setYearManufacture(1980);
        car2.setBrand(Brand.HUYNDAI);
        car2.setNumberPlate("AAD02");
        car2.setHaveInsurance(true);
        carManage.addCar(car);
        carManage.addCar(car1);
        carManage.addCar(car2);

    }
}
