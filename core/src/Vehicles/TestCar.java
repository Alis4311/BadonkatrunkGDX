package Vehicles;

import com.badlogic.gdx.audio.Sound;

import javax.swing.*;

public class TestCar {
    public static void main(String[] args) {
        Car car = new Car();
        System.out.println(car.getSpeed());
        System.out.println(car.getAccelerationRate());
        System.out.println(car.getJumpHeight());
    }
}