package classes;

public class Aircraft implements IMovement{
    String model;
    String color;


    @Override
    public void Move() {
        System.out.println("Aircraft is flying");
    }
}
