package ca.uqam.info.inf5153.ptg;


import userInterface.UserArgs;

public class Main {

    public static void main(String[] args){
        UserArgs parsedArgs = new UserArgs(args);

        WorldGenerator c = new WorldGenerator(parsedArgs);
        c.createWorld();
    }
}
