public class Main {
    public static void main(String[] args) {

        Universe universe = new Universe();

        Algorithm.universeInit(universe);

        System.out.println("\nHow many mutations should be performed:");
        int m = Algorithm.scanner.nextInt();
        for (int i = 0; i < m; i++) {
            Algorithm.nextGeneration(universe);
        }

        Algorithm.universeDraw(universe);
    }
}


