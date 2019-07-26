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
            Car[] cars = carManage.createCars(80); // chỗ này trả về 1 danh sách car
//            Car[] cars = carManage.createCars(dalCar.addCar(cars));
            for (int i = 0; i < 1; i++) {
                 //truy capaj 1 cai car trong dah sach, vay don gian minh truyền cái car[i] này vào hàm addCar là được
                System.out.println(cars[i].toString());
                dalCar.addCar(cars[i]); // để gọi 1 hàm có trong class khác hoặc đối tượng khác, phải khởi tạo đối tượng đó, rồi dùng toán tử "."
            }// chạy thử
            // vẫn ko insert được
            // lỗi ở addCar rồi

        }catch (Exception e){
            e.printStackTrace();
        }
//        InsuranceManage insuranceManage = new InsuranceManage();
//        Insurance[] insurances = insuranceManage.createInsurance();

    }
}
