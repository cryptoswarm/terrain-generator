package ca.uqam.info.inf5153.ptg;


import UserInterface.UserArgs;

public class Main {

    public static void main(String[] args) {
        UserArgs parsedArgs = new UserArgs(args);

        Controller c = new Controller(parsedArgs);
        c.createWorld();
    }
}
