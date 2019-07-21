package DTO;

public abstract class Car {
    private  String numberPlate;
    private  int yearManufacture;
    private Brand brand;
    private  boolean haveInsurance;
    private String id;
    public Car(String numberPlate, int yearManufacture, Brand brand, boolean haveIsurance) {

        this.numberPlate = numberPlate;
        this.yearManufacture = yearManufacture;
        this.brand = brand;
        this.haveInsurance = haveIsurance;
    }

    public Car() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Car(String id){
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public int getYearManufacture() {
        return yearManufacture;
    }

    public void setYearManufacture(int yearManufacture) {
        this.yearManufacture = yearManufacture;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public boolean isHaveIsurance() {
        return haveInsurance;
    }

    public void setHaveIsurance(boolean haveInsurance) {
        this.haveInsurance = haveInsurance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "numberPlate='" + numberPlate + '\'' +
                ", yearManufacture=" + yearManufacture +
                ", brand=" + brand +
                ", haveIsurance=" + haveInsurance +
                "ID: " +id
                ;
    }
}
