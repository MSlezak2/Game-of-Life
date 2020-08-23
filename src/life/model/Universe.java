package life.model;

public class Universe {

    private boolean[][] currentGeneration;
    private int aliveNumber;
    private int generationNumber;

    public Universe() {
        generationNumber = 0;
    }

    //getters

    public boolean[][] getCurrentGeneration() {
        return currentGeneration;
    }
    public int getAliveNumber() {
        return aliveNumber;
    }
    public int getGenerationNumber() {
        return generationNumber;
    }

    //setters

    public void setCurrentGeneration(boolean[][] currentGeneration) {
        this.currentGeneration = currentGeneration;
    }
    public void setGenerationNumber(int generationNumber) {
        this.generationNumber = generationNumber;
    }
    public void setAliveNumber(int aliveNumber) {
        this.aliveNumber = aliveNumber;
    }
    public void incrementAliveNumber(){
        aliveNumber++;
    }
    public void incrementGenerationNumber(){
        generationNumber++;
    }

}

