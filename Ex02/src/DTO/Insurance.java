package DTO;

public class Insurance {
    public String insurancePackage;
    public PackageType packageType;


    public String getInsurancePackage() {
        return insurancePackage;
    }

    public void setInsurancePackage(String insurancePackage) {
        this.insurancePackage = insurancePackage;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    public PackageType getPackageType() {
        return packageType;
    }

    @Override
    public String toString(){
        return getInsurancePackage() + " Package " + getPackageType().typeP;
    }
}
