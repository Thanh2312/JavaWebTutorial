package DTO;

public class MediumCar extends Car {
    private boolean havePowerSteering;
    public MediumCar( boolean havePowerSteering){
        this.havePowerSteering = havePowerSteering;
    }
    public MediumCar(){

    }
    public boolean isHavePowerSteering() {
        return havePowerSteering;
    }

    public void setHavePowerSteering(boolean havePowerSteering) {
        this.havePowerSteering = havePowerSteering;
    }

    @Override
    public String toString() {
        return super.toString() + ", Have Power Steering = " + havePowerSteering + '}' + "Medium Car";
    }
}
