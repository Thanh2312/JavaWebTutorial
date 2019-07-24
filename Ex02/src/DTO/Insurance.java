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

    public PackageType getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageType packageType) {
        this.packageType = packageType;
    }

    @Override
    public String toString() {
        return "Insurance" + getInsurancePackage() + getPackageType().typeP;
    }
}
