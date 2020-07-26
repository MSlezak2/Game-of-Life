package life.controller;

import life.model.Algorithm;
import life.model.Universe;
import life.view.GameOfLife;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Algorithm.simulateInConsole(12,new Universe());
        GameOfLife gol = new GameOfLife(600, 700);

    }

    public static void clearConsole() {
        //Since in IntelliJ there is no way to clear console
        //that method just prints several blank lines instead
        for (int i = 0; i < 8; i++) {
            System.out.println("");
        }

        //The following piece of code doesn't work in IntelliJ's terminal
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {}
    }

    public static void printToConsole(String text) {
        clearConsole();
        System.out.println(text);
    }
}


