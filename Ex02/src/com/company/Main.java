package com.company;

import BLL.CarManage;
import BLL.InsuranceManage;
import DTO.Car;
import DTO.Insurance;

public class Main {
    private static DAL.CarManage dalCar;
//    public Main(){
//        dalCar = new DAL.CarManage();
//    }

    public static void main(String[] args) {
        try {
            dalCar = new DAL.CarManage();

            CarManage carManage = new CarManage();
            Car[] cars = carManage.createCars(dalCar.total());
            for (int i = 0; i < 1; i++) {
                System.out.println(cars[i].toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
//        InsuranceManage insuranceManage = new InsuranceManage();
//        Insurance[] insurances = insuranceManage.createInsurance();

    }
}
