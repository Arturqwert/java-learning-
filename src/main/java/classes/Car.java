package classes;

public class Car implements IMovement {
    String model;
    int maxSpeed;

    public Car(String model, int maxSpeed)
    {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public void Beep()
    {
        System.out.println("Beeeep!");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public void Move() {
        System.out.println("Car rides");
    }
}
