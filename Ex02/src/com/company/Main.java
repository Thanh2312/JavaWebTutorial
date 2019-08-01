package com.company;

import BLL.CarManage;
import BLL.InsuranceManage;
import DAL.CarTableConnection;
import DAL.InsuranceTableConnection;
import DTO.Car;

import java.util.Scanner;

public class Main {
//    private static DAL.CarTableConnection dalCar;
    public static void main(String[] args) {
        CarManage carManage = new CarManage();
        InsuranceManage insuranceManage = new InsuranceManage();
        CarTableConnection carConnection = new CarTableConnection();
        InsuranceTableConnection insuranceConnection = new InsuranceTableConnection();
        System.out.println("CHAO MUNG DEN VOI BAI TAP SO 2 MADE BY THANH2312");
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------");
        System.out.println("Menu:");
        System.out.println("1. Tao moi 10 chiec xe o to trong CSDL");
        System.out.println("2. Tao moi 10 goi bao hiem trong CSDL");
        System.out.println("3. Dang ki bao hiem cho 1 chiec xe");
        System.out.println("4. Hien thi danh sach xe");
        while (true){
            System.out.println("nhap vao duoi day lua chon 1,2,3,4 hoac exit(neu muon thoat) roi Enter");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("exit")) break;
            if (choice.equals("1")){
                carManage.createCars(carConnection.getCount());
                System.out.println("Thong bao: Tao moi 10 xe thanh cong");
                continue;
            }else if (choice.equals("2")){
                insuranceManage.createInsurance(insuranceConnection.getCount());
                System.out.println("Thong bao: Tao moi 10 goi bao hiem: thanh cong");
                continue;
            }else if (choice.equals("3")){
                carManage.assignAssurance();
                System.out.println("Thong bao: Dang ki bao hiem cho xe: KET THUC");
            }else if (choice.equals("4")){
                System.out.println("Chon loai xe hien thi(1, 2 hoac 3; neu khong phai 1,2,3 thi se hien thi toan bo xe trong CSDL) roi Enter");
                System.out.println("1. Modern Car \n2. Medium Car\n3.Old Car");
                String carChoosen = scanner.nextLine();
                if (carChoosen.equals("1")){
                    carManage.showCarInfo("Modern Car");
                }else if (carChoosen.equals("2")){
                    carManage.showCarInfo("Medium Car");
                }else if (carChoosen.equals("3")){
                    carManage.showCarInfo("Old Car");
                }else carManage.showCarInfo();
            }else {
                System.out.println("Canh bao: Nhap ko chinh xac!!! Yeu cau nhap lai!");
                continue;
            }
        }

    }
}
