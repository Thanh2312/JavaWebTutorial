package BLL;

import DAL.CarTableConnection;
import DAL.InsuranceTableConnection;
import DTO.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class CarManage {
    private CarTableConnection carTableConnection;
    private Vector<String> vectorNumberPlate;

    public CarManage(){
        carTableConnection = new CarTableConnection();
    }

    public Car[] createCars(int total){
        vectorNumberPlate = new Vector<>();
        Car[] cars = new Car[10];
        Car car = null;
        for(int i = 0; i<10; i++){
            int type = new Random().nextInt (3);
            if(type == 0){
                car = new OldCar();
                car = addRandomProperties(car);
                ((OldCar) car).setActionDuration(new Random().nextInt(nowYear()-car.getYearManufacture() +1));
            }else if(type==1){
                car = new MediumCar();
                car = addRandomProperties(car);
                ((MediumCar) car).setHavePowerSteering(randomBoolean());
            }else if(type==2){
                car = new ModernCar();
                car = addRandomProperties(car);
                ((ModernCar) car).setHavePositionDevice(randomBoolean());
            }
            car = addRandomProperties(car);
            car.setId("Car #"+(total + i +1));
            cars[i] = car;
            carTableConnection.addCar(cars[i]);
        }
        return cars;
    }

    private Car addRandomProperties(Car car) {

        car.setNumberPlate(randomNumberPlate());
        car.setBrand(randomBrand());
        car.setYearManufacture(randomYear());
        car.setHaveInsurance(false);
        return car;
    }

    private boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    private String randomNumberPlate() {
        String numberPlate = "";
        boolean haveInDB = true;
        boolean inVectorNumberPlate = false;
        while(haveInDB || !inVectorNumberPlate){
            for(int i = 0; i<5; i++){
                int number = new Random().nextInt(9);
                numberPlate += number;
            }
            haveInDB = CarTableConnection.sameNumberPlate(numberPlate);
            inVectorNumberPlate = vectorNumberPlate.add(numberPlate);
        }
        return numberPlate;
    }

    private int randomYear() {
        int year = 1980;
        year += new Random().nextInt(33);
        return year;
    }

    private Brand randomBrand() {
        int type = new Random().nextInt(3);
        switch (type){
            case 0: return Brand.TOYOTA;
            case 1: return Brand.BMW;
            case 2: return Brand.HUYNDAI;
        }
        return Brand.BMW;
    }

    private int nowYear(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime localDateTime = LocalDateTime.now();
        return Integer.parseInt(dateTimeFormatter.format(localDateTime));
    }

    public void assignAssurance() {
        String id;
        Car car = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap ID cua xe muon dang ki(nhap exit roi Enter neu muon thoat), dam bao rang CSDL da co xe");
        while (true){
            id = scanner.nextLine();
            if (id.equalsIgnoreCase("exit")){
                id = "";
                break;
            }
            if (id.equals("")) {
                System.out.println("Canh bao: Ban chua nhap gi, yeu cau nhap lai ID xe hoac dung chuong trinh dang ki bao hiem (go exit roi Enter)");
                continue;
            }
            car = carTableConnection.getCarFromID(id);
            if (car==null){
                System.out.println("Thong bao: Khong co xe nay trong danh sach xe, nhap lai ID xe hoac thoat chuong trinh dang ki bao hiem(go exit roi Enter)");
                continue;
            }else {
                if (car.isHaveInsurance()) {
                    System.out.println("Unavailable Buying!");
                    continue;
                }else break;
            }
        }
        if (id=="") return;
        System.out.println("Thong tin xe: "+ car.toString());
        //------------ giai doan chon xe mua bao hiem da xong
        Insurance[] insurances = (new InsuranceTableConnection()).getListPackNotAssign();
        if (insurances.length==0){
            System.out.println("Khong co goi bao hiem nao chua duoc mua hoac khong co goi bao hiem nao trong CSDL, chuong trinh dang ki bao hiem buoc dung!");
            System.out.println("Hay dam bao cac goi bao hiem da duoc tao ra!");
            return;
        }
        System.out.println("Danh sach cac goi bao hiem chua duoc dang ki: ");
        for (int i = 0; i< insurances.length; i++){
            System.out.println("Thu tu "+ (i+1)+": " + insurances[i].toString());
        }
        while (true) {
            System.out.println("Xin nhap so thu tu goi bao hiem muon mua sau do Enter(nhap exit roi Enter de thoat chuong trinh dang ki bao hiem): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("exit")){
                break;
            }
            int index = Integer.parseInt(choice);
            Insurance insuranceBeChoiced = insurances[index - 1];
            System.out.println("Ban da chon goi thu tu so: " + index + ": " + insuranceBeChoiced.toString());
            if (car.getTypeCar() != getCarTypeFromPack(insuranceBeChoiced.getPackageType())) {
                System.out.println("Invalid Package!");
            } else {
                System.out.println("Successful Buying!");
                carTableConnection.updateHaveInsurance(id, insuranceBeChoiced.getInsurancePackage());
                break;
            }
        }
    }

    private String getCarTypeFromPack(PackageType packageType) {
        String carType = null;
        switch (packageType){
            case Old:
                carType = "Old Car";
                break;
            case Medium:
                carType = "Medium Car";
                break;
            case Modern:
                carType = "Modern Car";
                break;
        }
        return carType;
    }

    public void showCarInfo(String carType) {     //gọi có điều kiện, tương tự select *from where
        ArrayList<ArrayList<byte[]>> listArrayList = carTableConnection.getList(carType);
        System.out.println("IDCar\t|| NumberPlate\t|| YearOfManufacture\t||CarType\t||HaveInsurance\t");
        for (ArrayList<byte[]> row: listArrayList){
            String id = new String(row.get(0));
            String np = new String(row.get(1));
            int yom = InsuranceTableConnection.byteArrayToInt(row.get(2));
            String ct = new String(row.get(3));
            boolean hi = new Boolean(new String(row.get(4)));
            System.out.println(id+"\t||"+np+"\t||"+yom+"\t||"+ct+"\t||"+hi+"\t||");
        }
    }

    public void showCarInfo() {
        ArrayList<ArrayList<byte[]>> listArrayList = carTableConnection.getList();
        System.out.println("IDCar\t|| NumberPlate\t|| YearOfManufacture\t||CarType\t||HaveInsurance\t");
        for (ArrayList<byte[]> row: listArrayList){
            String id = new String(row.get(0));
            String np = new String(row.get(1));
            int yom = InsuranceTableConnection.byteArrayToInt(row.get(2));
            String ct = new String(row.get(3));
            boolean hi = new Boolean(new String(row.get(4)));
            System.out.println(id+"\t||"+np+"\t||"+yom+"\t||"+ct+"\t||"+hi+"\t||");
        }
    }

    public static void main(String[] args){
        CarManage carManage = new CarManage();
        carManage.assignAssurance();
//        carManage.showCarInfo("Modern Car");
//        carManage.showCarInfo();
    }
}
