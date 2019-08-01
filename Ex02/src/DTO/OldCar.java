package DTO;

public class OldCar extends Car {
    private int actionDuration;

    public OldCar(){
        setTypeCar("Old Car");
    }

    public OldCar(int actionDuration){
        this.actionDuration = actionDuration;
        setTypeCar("Old Car");
    }

    public int getActionDuration() {
        return actionDuration;
    }

    public void setActionDuration(int actionDuration) {
        this.actionDuration = actionDuration;
    }

    @Override
    public String toString() {
        return super.toString()+", Action Duration=" + actionDuration + '}'+"Old Car";
    }

}
