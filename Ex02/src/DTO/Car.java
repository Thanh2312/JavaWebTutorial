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

    public void setId(String param){
        this.id = param; // dòng này : this.id ngĩa là id của đối tượng car, còn String Id thì Id là tham số truyền vào
    //ý nghĩa của dòng bên trên là cho id bên trong đối tượng car bằng vói Id tham số truyền vào
        // có khả năng là c viết tay cái set nay, hoặc c đổi tên cái biến Id thành id nên gây lỗi
        // viết giống y mấy dòng dưới kìa mà, đây mới là giống nhé. nãy cậu sửa chỗ nào nhờ, ban đầu nó là thế này: vì
        // viết thế kia nên hóa ra id lại bằng chính nó
        //còn viết thế này: thì id = tham số truyền vào, đó là tham só truyền vào
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
                " numberPlate='" + numberPlate + '\'' +
                ", yearManufacture=" + yearManufacture +
                ", brand=" + brand +
                ", haveInsurance=" + haveInsurance
                ;
    }
}
