package DTO;

public class ModernCar extends Car implements YearOfManufacture {
    private boolean havePositionDevice;
    public ModernCar(){

    }
    public ModernCar(boolean havePositionDevice){
        this.havePositionDevice = havePositionDevice;
    }
    public boolean isHavePositionDevice() {
        return havePositionDevice;
    }

    public void setHavePositionDevice(boolean havePositionDevice) {
        this.havePositionDevice = havePositionDevice;
    }

    @Override
    public String toString() {
        return super.toString() + ", Have Positioning Device = " + havePositionDevice + '}' + "Modern Car";
    }

    @Override
    public void checkYear() {
        System.out.println("year: 2004");
    }
}
