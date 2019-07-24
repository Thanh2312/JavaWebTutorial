package com.company;

import BLL.CarManage;
import BLL.InsuranceManage;
import DTO.Car;
import DTO.Insurance;

public class Main {
    public static void main(String[] args) {
//        CarManage carManage = new CarManage();
//        Car[] cars = carManage.createCars(10);

        InsuranceManage insuranceManage = new InsuranceManage();
        Insurance[] insurances = insuranceManage.createInsurance();
        for(int i = 0; i<10; i++)
        {
            System.out.println(insurances[i].toString());
        }

    }
}
