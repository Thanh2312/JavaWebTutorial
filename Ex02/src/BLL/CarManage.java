package BLL;

import DTO.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class CarManage {
    private DAL.CarManage dalCar;
    private ResultSet table;

    public CarManage(){
        dalCar = new DAL.CarManage();
    }

    private Vector<String> vectorNumberPlate;

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
            }else if(type==2){
                car = new ModernCar();
                car = addRandomProperties(car);
            }
            car = addRandomProperties(car);
            car.setId("Car #"+(total + i +1));
            cars[i] = car;

            dalCar.addCar(cars[i]);
        }
        return cars;
    }

    private Car addRandomProperties(Car car) {
        car.setNumberPlate(randomNumberPlate());
        car.setBrand(randomBrand());
        car.setYearManufacture(randomYear());
        car.setHaveInsurance(randomBoolean());
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
            haveInDB = DAL.CarManage.sameNumberPlate(numberPlate);
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

    public static void main(String[] args){
        CarManage carManage = new CarManage();
        Car[] cars = carManage.createCars(10);
        for(int i = 0; i<10; i++)
        {
            System.out.println(cars[i].toString());
        }
    }

    public void assignAssurance(String id, String insurancePackage) {
        if (dalCar.haveInsurance(id) == true) {
            System.out.println("Unavailable Buying!");
        }
        else {
            if (choiceInsurance() != getPackType()) {
                System.out.println("Invalid Package!");
            }
            else
            {
                System.out.println("Successful Buying!");
                dalCar.updateInfo(id, insurancePackage);
            }
        }
    }

    public String choiceInsurance(){
        String insurance;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap goi bao hiem muon mua: ");
        insurance = scanner.nextLine();
        String pack = dalCar.insurancePack(insurance);
        return pack;
    } // mua bao hiem

    public String getPackType() {
        String id;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap goi bao hiem muon mua: ");
        id = scanner.nextLine();
        String packageType = dalCar.insurancePackCar(id);
        return packageType;
    } // chon goi

    public void ShowCarInfo(){
        DisplaySpecificCarList();
    }

    public void ShowCarInfo(String carType){
        DisplaySpecificCarList(carType);
    }

    private void DisplaySpecificCarList(String carType) {     //gọi có điều kiện, tương tự select *from where
        try {
            table = dalCar.getList(carType);
            ResultSetMetaData rsmd = table.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (table.next()) {
                for (int i = 1; i <= colCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = table.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    private void DisplaySpecificCarList() {					//tương tự select * from
        try {
            table = dalCar.getList();
            ResultSetMetaData rsmd = table.getMetaData();
            int colCount = rsmd.getColumnCount();
            while (table.next()) {
                for (int i = 1; i <= colCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = table.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }

    }


}
