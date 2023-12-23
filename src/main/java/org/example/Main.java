package org.example;
import classes.Aircraft;
import classes.Car;
import classes.Card;
import classes.IMovement;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.print("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        var car = new Car("Mercedes", 250);
        car.Beep();
        car.setModel("bmw");
        System.out.println(car.getModel());

        var card = new Card("1234 5678 8999 0000", "1234");
        System.out.println(card.getNumber());
        System.out.println(card.getNumber("1234"));


        car.Move();

        IMovement[] transports = {new Car("Tesla", 300), new Aircraft()};
        for (IMovement i: transports)
        {
            i.Move();
        }


        System.out.println();
        List<IMovement> list = new ArrayList<>(Arrays.asList( new Car("Lexus", 270), new Aircraft()));
        for (IMovement i: list)
        {
            i.Move();
        }

    }
}