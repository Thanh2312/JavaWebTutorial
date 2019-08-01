package DTO;

public class Insurance {
    private String insurancePackage;
    private PackageType packageType;
    private boolean assigned;
    public Insurance(){

    }
    public Insurance(String insurancePackage, PackageType packageType, boolean assigned) {
        this.insurancePackage = insurancePackage;
        this.packageType = packageType;
        this.assigned = assigned;
    }

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

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }

    @Override
    public String toString(){
        return "name: " + getInsurancePackage() + "|| Package " + getPackageType().typeP+"|| Assigned: "+ isAssigned();
    }
}
