package DTO;

public abstract class Car {
    private  String numberPlate;
    private  int yearManufacture;
    private Brand brand;
    private  boolean haveInsurance;
    private String id;
    private String typeCar;

    public String getTypeCar() {
        return typeCar;
    }

    public void setTypeCar(String typeCar) {
        this.typeCar = typeCar;
    }

    public Car(String numberPlate, int yearManufacture, Brand brand, boolean haveInsurance) {

        this.numberPlate = numberPlate;
        this.yearManufacture = yearManufacture;
        this.brand = brand;
        this.haveInsurance = haveInsurance;
    }

    public Car() {
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) { //numberPlate ở dòng này là tham số truyền vào, chứ ko phải property của Object Car
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

    public boolean isHaveInsurance() {
        return haveInsurance;
    }

    public void setHaveInsurance(boolean haveInsurance) {
        this.haveInsurance = haveInsurance;
    }

    @Override
    public String toString() {
        return "Car{" +
                "idCar="+ id+
                ", numberPlate='" + numberPlate + '\'' +
                ", yearManufacture=" + yearManufacture +
                ", brand=" + brand +
                ", haveInsurance=" + haveInsurance +
                ", typeCar=" + typeCar
                ;
    }
}
