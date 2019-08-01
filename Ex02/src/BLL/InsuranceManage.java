package BLL;

import DAL.InsuranceTableConnection;
import DTO.Insurance;
import DTO.PackageType;

import java.util.Random;

public class InsuranceManage {
    private InsuranceTableConnection dalInsurance;
    public InsuranceManage(){
        dalInsurance = new InsuranceTableConnection();
    }

    public Insurance[] createInsurance(int total){
        Insurance[] insurances = new Insurance[10];
        Insurance insurance=null;
        for(int i = 0; i<10; i++){
            insurance = new Insurance();
            insurance.setInsurancePackage("Insurance Package #"+(total + i +1));
            insurance.setPackageType(addRandomPackage());
            insurances[i] = insurance;
            dalInsurance.addPack(insurances[i]);
        }
        return insurances;
    }

    private PackageType addRandomPackage() {
        int i = new Random().nextInt(3);
        switch (i) {
            case 0:
                return PackageType.Modern;
            case 1:
                return PackageType.Medium;
            default:
                return PackageType.Old;
        }
    }

    public static void main(String[] args) {
        InsuranceManage insuranceManage = new InsuranceManage();
        Insurance[] insurances = insuranceManage.createInsurance(0);
        for(int i = 0; i<10; i++) {
            System.out.println(insurances[i].toString());
        }
    }
}
