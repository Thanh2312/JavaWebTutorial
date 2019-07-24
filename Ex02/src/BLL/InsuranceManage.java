package BLL;

import DTO.Insurance;
import DTO.PackageType;

import java.util.Random;

public class InsuranceManage {
    public Insurance[] createInsurance(){
        Insurance[] insurances = new Insurance[10];
        Insurance insurance = null;

        for(int i = 0; i<10; i++){
            insurance = new Insurance();
            insurance.setInsurancePackage("Insurance Package #"+(i +1));
            insurance.setPackageType(addRandomPackage());
            insurances[i] = insurance;
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


}
