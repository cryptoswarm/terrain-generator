package RandomStrategy;

public class RandomContexte {

    RandomStategy strategy;

    public RandomContexte(int seed){

        if (seed <= 0){

            strategy = new JustRandom();
        }else{

            strategy = new Seed(seed);
        }

    }

    public int getRandomInt(int range){

        if(range == 0){

            return 0;
        }

        return strategy.getRandom(range);
    }

}
