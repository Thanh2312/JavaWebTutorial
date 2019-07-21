package DTO;

import DAL.ConnectDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Vector;

public class CarManager {
    private Vector<String> vectorNumberPlate;
    public Car[] createCars(int total){
        vectorNumberPlate = new Vector<>();
        Car[] cars = new Car[10];
        Car car = null;
        for (int i = 0; i<10;i++){
            int type = new Random().nextInt(3);
            if (type ==0){
                car = new OldCar();
                car = addRandomProperties(car);
                ((OldCar) car).setActionDuration(new Random().nextInt(nowYear()-car.getYearManufacture() +1));
            }else if (type == 1){
                car = new MediumCar();
                car = addRandomProperties(car);
            }else {
                car = new ModernCar();
                car = addRandomProperties(car);
            }
            car = addRandomProperties(car);

            car.setId("Car #"+ (total+i+1)); // car 1, car 2, ... car 12, Car #13 Car #14 Car #123
            cars[i] = car;
        }
        return cars;
    }

    private Car addRandomProperties(Car car) {
        car.setHaveIsurance(randomBoolean());
        car.setNumberPlate(randomNumberPlate());
        car.setYearManufacture(randomYear());
        car.setBrand(randomBrand());
        return car;
    }

    private Brand randomBrand() {
        int type = new Random().nextInt(3);
        switch (type){
            case 0: return Brand.TOYOTA;
            case 1: return Brand.BMW;
            case 2: return Brand.HUYNDAI;
        }
        return Brand.TOYOTA;
    }

    private int randomYear() {
        int year = 1980;
        year += new Random().nextInt(33);
        return year;
    }

    private String randomNumberPlate() {

        String numberPlate = "";
        boolean haveInDatabase = true;
        boolean inVectorNumberPlate = false ;
        while (haveInDatabase || !inVectorNumberPlate) {
            for (int i = 0; i < 5; i++) {
                int number = new Random().nextInt(9);
                numberPlate += number;
            }
            haveInDatabase = ConnectDatabase.sameNumberPlate(numberPlate); // dung thi quay lai , gia su true
            inVectorNumberPlate = vectorNumberPlate.add(numberPlate); // sai thi quay lai
        }
        return numberPlate;
    }

    private boolean randomBoolean() {
        return new Random().nextBoolean();
    }

    private int nowYear(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime localDateTime = LocalDateTime.now();
        return Integer.parseInt(dateTimeFormatter.format(localDateTime));
    }

    public static void main(String[] args) {
        CarManager carManager = new CarManager();
        Car[] cars = carManager.createCars(10);
        for (int i = 0; i < 10; i++) {
            System.out.println(cars[i].toString());
        }
    }
}
