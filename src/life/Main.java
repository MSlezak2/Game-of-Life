package life;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Algorithm.simulate(12,new Universe());

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


