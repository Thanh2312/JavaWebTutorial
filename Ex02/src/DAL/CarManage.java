package DAL;

import DTO.Car;

import java.sql.ResultSet;

public class CarManage {

    private static String query;
    private ConnectDB connectDB;
    public CarManage(){ connectDB = new ConnectDB();
    }

    public static boolean sameNumberPlate(String numberPlate) {
        query = "select * from Car where NumberPlate like '" + numberPlate + "'" ;
        return false;
    }

    public void addCar(Car car) {
        query = "INSERT INTO Car VALUES ('" + car.getId() + "', " + car.getNumberPlate() + ", " +
                car.getYearManufacture() + ", '" + car.getBrand() + "', '" +
                car.isHaveInsurance() + "'," + "" + ","+ "" + ","+ "" + ","+ "" + "," + "'" + car.getTypeCar() + "')";
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
}
