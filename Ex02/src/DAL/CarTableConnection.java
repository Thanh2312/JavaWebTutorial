package DAL;

import DTO.*;

import java.util.ArrayList;

public class CarTableConnection {

    private static String query;
    private ConnectDB connectDB;

    public ConnectDB getConnectDB() {
        return connectDB;
    }

    public CarTableConnection(){ connectDB = new ConnectDB();
    }

    public static boolean sameNumberPlate(String numberPlate) {
        query = "select * from Car where NumberPlate like '" + numberPlate + "'" ;
        return false;
    }

    public int getCount(){
        query = "select count(*) from dbo.Car";
        int count = 0;
        ArrayList<byte[]> bytes = connectDB.getResultQuery(query).get(0);
        count = InsuranceTableConnection.byteArrayToInt(bytes.get(0));
        return count;
    }

    public void addCar(Car car) {
        String columnInsert = "IDCar, NumberPlate,YearOfManufacture,Brand,HaveInsurance,CarType";
        String valuesInsert= car.getId() + "', '" + car.getNumberPlate() + "', '" +
                car.getYearManufacture() + "', '" + car.getBrand() + "', '" +
                car.isHaveInsurance() + "', '" + car.getTypeCar();
        if(car.getTypeCar() == "Old Car"){
            columnInsert = columnInsert + ",ActionDuration";
            valuesInsert= valuesInsert+ "', '"+((OldCar) car).getActionDuration();
        }else if (car.getTypeCar() == "Medium Car"){
            columnInsert = columnInsert+",HavePowerSteering";
            valuesInsert= valuesInsert + "', '" + ((MediumCar) car).isHavePowerSteering();
        }else {
            columnInsert = columnInsert+",HavePositioningDevice";
            valuesInsert= valuesInsert + "', '" + ((ModernCar) car).isHavePositionDevice();
        }
        query = "INSERT INTO Car (" + columnInsert + ") VALUES ('" + valuesInsert + "')";

        connectDB.dataHandle(query);
    }

    public ArrayList<ArrayList<byte[]>> getList(String carType) {
        query = "SELECT IDCar, NumberPlate, YearOfManufacture, CarType, HaveInsurance FROM dbo.Car where CarType = '"+carType+"'";
        return connectDB.getResultQuery(query);
    }

    public ArrayList<ArrayList<byte[]>> getList() {
        query = "SELECT IDCar, NumberPlate, YearOfManufacture, CarType, HaveInsurance FROM dbo.Car";
        return connectDB.getResultQuery(query);
    }

    public void updateHaveInsurance(String carID, String insurancePack) {
        query = "UPDATE Car SET HaveInsurance = 'true', InsurancePackage = '" + insurancePack + "'" +
                "WHERE IDCar = '" + carID + "'";
        connectDB.dataHandle(query);
        query = "UPDATE Insurance SET Assigned = 'true'WHERE InsurancePackage = '" + insurancePack+"'";
        connectDB.dataHandle(query);
    }

    public Car getCarFromID(String id) {
        query = "select * from Car where IDCar = '"+id+"'";
        Car car = null;
        ArrayList<ArrayList<byte[]>> listArrayList = connectDB.getResultQuery(query);
        System.out.println("count row: "+listArrayList.size());
        if (listArrayList.size()==0) return null;
        ArrayList<byte[]> row = listArrayList.get(0);
        if (row==null) return null;
        String carType = new String(row.get(9));
        carType  = carType.replaceAll("\\P{Print}","");
        if (carType.equals("Old Car")) {
            car = new OldCar(InsuranceTableConnection.byteArrayToInt(row.get(8)));
        }else if (carType.equals("Medium Car")) {
            car = new MediumCar(new Boolean(new String(row.get(7))));
        } else  {
            System.out.println(carType);
            car = new ModernCar(new Boolean(new String(row.get(6))));
        }
        car.setId((new String(row.get(0))).replaceAll("\\P{Print}","").trim());
        car.setNumberPlate((new String(row.get(1))).replaceAll("\\P{Print}",""));
        car.setYearManufacture(InsuranceTableConnection.byteArrayToInt(row.get(2)));
        String brandString = (new String(row.get(3))).replaceAll("\\P{Print}","");
        if (brandString.equalsIgnoreCase( "TOYOTA")){
            car.setBrand(Brand.TOYOTA);
        }else if (brandString.equalsIgnoreCase("BMW")){
            car.setBrand(Brand.BMW);
        }else car.setBrand(Brand.HUYNDAI);
        car.setHaveInsurance(new Boolean(new String(row.get(4))));
        return car;
    }

    public static void main(String[] args) {
        CarTableConnection carTableConnection = new CarTableConnection();
        Car car = carTableConnection.getCarFromID("heee4");
        System.out.println(car.toString());
    }
}
