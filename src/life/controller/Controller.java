package life.controller;

import static life.model.Algorithm.*;
import life.model.Universe;
import life.view.GameOfLife;

public class Controller {


    public static void main(String[] args){

        GameOfLife gol = new GameOfLife(900, 700);
        Universe universe = new Universe();

        Thread simulationThread = new Thread(() -> {

            while (true) {

                // starting / restarting the algorithm
                if ( gol.isRestartRequested() ) {
                    initUniverse(universe,gol.getRequestedSize());
//                    gol.calculateSizeOfCells(); //to get rid of a matrix too big for the frame
                    gol.drawUniverse(universe);
                    gol.setRestartRequested(false);
                }
                try {
                    // waiting for the next iteration of the algorithm
                    Thread.sleep((long)(1000 * ((double)(101 - gol.getRequestedSpeed())/101) ));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // generating next iteration of the algorithm
                if (!gol.isPauseRequested()) {
                    generateNext(universe);
                    gol.drawUniverse(universe);
                }

            }

        });

        simulationThread.start();

    }

}


