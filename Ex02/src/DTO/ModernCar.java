package DTO;

public class ModernCar extends Car {
    private boolean havePositionDevice;

    public ModernCar(){
        setTypeCar("Modern Car");
    }

    public ModernCar(boolean havePositionDevice){
        this.havePositionDevice = havePositionDevice;
        setTypeCar("Modern Car");
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
}
