package com.digitalnurture.week1.singleton;

public class SingletonPatternTest {

    public static void main(String[] args) {
        Logger first = Logger.getInstance();
        Logger second = Logger.getInstance();
        Logger third = Logger.getInstance();

        first.log("Message from the first reference");
        second.log("Message from the second reference");

        System.out.println("first == second : " + (first == second));
        System.out.println("second == third : " + (second == third));
        System.out.println("first.hashCode() : " + first.hashCode());
        System.out.println("second.hashCode(): " + second.hashCode());

        if (first == second && second == third) {
            System.out.println("PASS: only one Logger instance exists");
        } else {
            System.out.println("FAIL: multiple Logger instances were created");
        }
    }
}
