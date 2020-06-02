import java.util.Random;
import java.util.Scanner;

class Algorithm {

    static Scanner scanner = new Scanner(System.in);
    static Random random;
    static int seed;
    static final int minAlive = 2; //minimal number of alive neighbours
    static final int maxAlive = 3; //maximal number of alive neighbours
    static final int rebornValue = 3; //number of alive neighbours
    //needed to reborn dead cell

    static void universeInit(Universe universe) {
        //scanning the input for the details
        System.out.println("\nEnter the size of the world:");
        int n = scanner.nextInt();      //matrix (world) size
        System.out.println("\nEnter the seed:");
        seed = scanner.nextInt();

        //creating the initial generation
        universe.setCurrentGeneration(new boolean[n][n]);
        random = new Random(seed);
        for (int x = 0; x < n; x++) {
            for (int y =0; y < n; y++) {
                universe.getCurrentGeneration()[x][y] = random.nextBoolean();
            }
        }
    }

    static void universeDraw(Universe universe) {
        //drawing current generation
        for (int x = 0; x < universe.getCurrentGeneration().length; x++) {
            for (int y =0; y < universe.getCurrentGeneration().length; y++) {
                if (universe.getCurrentGeneration()[x][y]) {
                    System.out.print("O");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    static void nextGeneration(Universe universe) {

        int universeSize = universe.getCurrentGeneration().length;
        boolean[][] NextGeneration = new boolean[universeSize][universeSize];
        int neighbourX;
        int neighbourY;
        int aliveCells = 0;

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
                if (universe.getCurrentGeneration()[x][y] && (aliveCells>=minAlive && aliveCells<=maxAlive) ) {
                    NextGeneration[x][y] = true;
                } else if (!universe.getCurrentGeneration()[x][y] && aliveCells==rebornValue) {
                    NextGeneration[x][y] = true;
                }
                aliveCells = 0;
            }
        }
        universe.setCurrentGeneration(NextGeneration);
    }
}