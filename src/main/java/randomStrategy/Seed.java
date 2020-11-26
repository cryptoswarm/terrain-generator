package randomStrategy;

import java.util.Random;

public class Seed implements RandomStrategy {
    private Random randomness;

    public Seed(int seed) {
        randomness = new Random(seed);
    }

    public int getRandom(int range){
        return randomness.nextInt(range+1);
    }
}