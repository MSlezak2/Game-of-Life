//TODO: refactor code to make it more MVC like ;)
package life.controller;

import static life.model.Algorithm.*;
import life.model.Universe;
import life.view.GameOfLife;

public class Controller {

    //TODO: take a look at warnings
    public static void main(String[] args){

//        Algorithm.simulateInConsole(12,new Universe());
        GameOfLife gol = new GameOfLife(900, 700);
        Universe universe = new Universe();

        Thread simulationThread = new Thread(() -> {

            while (true) {

                //starting / restarting the algorithm
                if ( gol.isRestartRequested() ) {
                    initUniverse(universe,gol.getRequestedSize());
//                    gol.calculateSizeOfCells(); //to get rid of a matrix too big for the frame
                    gol.drawUniverse(universe);
                    gol.setRestartRequested(false);
                }
                try {
                    //waiting for the next iteration of the algorithm
                    Thread.sleep((long)(1000 * ((double)(101 - gol.getRequestedSpeed())/101) ));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //generating next iteration of the algorithm
                if (!gol.isPaused()) {
                    generateNext(universe);
                    gol.drawUniverse(universe);
                }

            }

        });

        simulationThread.start();

    }

//    public static void clearConsole() {
//        //Since in IntelliJ there is no way to clear console
//        //that method just prints several blank lines instead
//        for (int i = 0; i < 8; i++) {
//            System.out.println("");
//        }
//
//        //The following piece of code doesn't work in IntelliJ's terminal
//        try {
//            if (System.getProperty("os.name").contains("Windows"))
//                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
//            else
//                Runtime.getRuntime().exec("clear");
//        }
//        catch (IOException | InterruptedException e) {}
//    }

//    public static void printToConsole(String text) {
//        clearConsole();
//        System.out.println(text);
//    }

}


