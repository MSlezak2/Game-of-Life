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

    // generates next generation (iteration of the algorithm)
    public static void generateNext(Universe universe) {

        int universeSize = universe.getCurrentGeneration().length;
        boolean[][] NextGeneration = new boolean[universeSize][universeSize];
        int neighbourX;
        int neighbourY;
        int aliveNeighbours = 0;

        universe.setAliveNumber(0); //computing amount of alive cells
        for (int x = 0; x < universeSize; x++) {
            for (int y = 0; y < universeSize; y++) {

                //counting the number of 'alive' neighbours of current cell
                neighbourX = (x + universeSize - 1) % universeSize;
                neighbourY = (y + universeSize - 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = (x + universeSize - 1) % universeSize;
                neighbourY = y;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = (x + universeSize - 1) % universeSize;
                neighbourY = (y + universeSize + 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = x;
                neighbourY = (y + universeSize - 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = x;
                neighbourY = (y + universeSize + 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = (x + universeSize + 1) % universeSize;
                neighbourY = (y + universeSize - 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = (x + universeSize + 1) % universeSize;
                neighbourY = y;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }
                neighbourX = (x + universeSize + 1) % universeSize;
                neighbourY = (y + universeSize + 1) % universeSize;
                if (universe.getCurrentGeneration()[neighbourX][neighbourY]) {
                    aliveNeighbours++;
                }

                //computing the state of current cell
                NextGeneration[x][y] = false;
                if (universe.getCurrentGeneration()[x][y] && (aliveNeighbours>= MIN_ALIVE && aliveNeighbours<= MAX_ALIVE) ) {
                    NextGeneration[x][y] = true;
                    universe.incrementAliveNumber();    //computing amount of alive cells
                } else if (!universe.getCurrentGeneration()[x][y] && aliveNeighbours== REBORN_VALUE) {
                    NextGeneration[x][y] = true;
                    universe.incrementAliveNumber();    //computing amount of alive cells
                }
                aliveNeighbours = 0;

            }
        }
        universe.setCurrentGeneration(NextGeneration);
        universe.incrementGenerationNumber();
    }

}