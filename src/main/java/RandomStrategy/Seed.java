package RandomStrategy;

import java.util.Random;

public class Seed implements RandomStategy {

    private Random randomness;

    public Seed(int seed){

        randomness = new Random(seed);
    }

    public int getRandom(int range){

        return randomness.nextInt(range+1);
    }
}