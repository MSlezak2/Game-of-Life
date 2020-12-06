package life.model;

import java.util.Random;

public class Algorithm {

    static Random random;
    private static final int MIN_ALIVE = 2; //minimal number of alive neighbours
    private static final int MAX_ALIVE = 3; //maximal number of alive neighbours
    private static final int REBORN_VALUE = 3; //number of alive neighbours
    //needed to reborn dead cell



    public static void initUniverse(Universe universe, int universeSize) {

        //creating the initial generation
        long seed = System.currentTimeMillis() % 50;
        universe.setCurrentGeneration(new boolean[universeSize][universeSize]);
        random = new Random(seed);
        universe.setAliveNumber(0);
        boolean currentCell;
        for (int x = 0; x < universeSize; x++) {
            for (int y =0; y < universeSize; y++) {
                currentCell = random.nextBoolean();
                universe.getCurrentGeneration()[x][y] = currentCell;
                if (currentCell == true) {
                    universe.incrementAliveNumber();
                }
            }
        }
        universe.setGenerationNumber(1);
    }

    public static void generateNext(Universe universe) {

        int universeSize = universe.getCurrentGeneration().length;
        boolean[][] NextGeneration = new boolean[universeSize][universeSize];
        int neighbourX;
        int neighbourY;
        int aliveCells = 0;

        universe.setAliveNumber(0); //computing amount of alive cells
        for (int x = 0; x < universeSize; x++) {
            for (int y = 0; y < universeSize; y++) {

                //counting the number of 'alive' neighbours of current cell
                neighbourX = (x + universeSize - 1) % universeSize;
                neighbourY = (y + universeSize - 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = (x + universeSize - 1) % universeSize;
                neighbourY = y;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = (x + universeSize - 1) % universeSize;
                neighbourY = (y + universeSize + 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = x;
                neighbourY = (y + universeSize - 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = x;
                neighbourY = (y + universeSize + 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = (x + universeSize + 1) % universeSize;
                neighbourY = (y + universeSize - 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = (x + universeSize + 1) % universeSize;
                neighbourY = y;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }
                neighbourX = (x + universeSize + 1) % universeSize;
                neighbourY = (y + universeSize + 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveCells++;
                }

                //computing the state of current cell
                NextGeneration[x][y] = false;
                if (universe.getCurrentGeneration()[x][y] && (aliveCells>= MIN_ALIVE && aliveCells<= MAX_ALIVE) ) {
                    NextGeneration[x][y] = true;
                    universe.incrementAliveNumber();    //computing amount of alive cells
                } else if (!universe.getCurrentGeneration()[x][y] && aliveCells== REBORN_VALUE) {
                    NextGeneration[x][y] = true;
                    universe.incrementAliveNumber();    //computing amount of alive cells
                }
                aliveCells = 0;

            }
        }
        universe.setCurrentGeneration(NextGeneration);
        universe.incrementGenerationNumber();
    }

//    public void setDisplayTime(int displayTime) {
//        Algorithm.displayTime = displayTime;
//    }
//
//
//    public static void simulateInConsole(int simDuration, Universe universe) throws InterruptedException {
//
//        if (universe.getGenerationNumber() == 0)
//            initUniverse(universe);
//        drawUniverseInConsole(universe);
//        Thread.sleep(displayTime);
//        for (int i = 0; i < simDuration; i++) {
//            Thread.sleep(displayTime);
//            generateNext(universe);
//            drawUniverseInConsole(universe);
//        }
//
//    }



//    public static void drawUniverseInConsole(Universe universe) {
//        //drawing current generation
//        Controller.clearConsole();
//        System.out.println("Generation #" + universe.getGenerationNumber());
//        System.out.println("Alive: " + universe.getAliveNumber());
//        for (int x = 0; x < universe.getCurrentGeneration().length; x++) {
//            for (int y =0; y < universe.getCurrentGeneration().length; y++) {
//                if (universe.getCurrentGeneration()[x][y]) {
//                    System.out.print("O");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//            System.out.print("\n");
//        }
//    }
//
}