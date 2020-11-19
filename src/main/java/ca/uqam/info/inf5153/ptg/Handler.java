package ca.uqam.info.inf5153.ptg;

import World.Generator.Generator;
import World.World;

import java.util.LinkedList;

public class Handler {
    LinkedList<Generator> chain;

    public Handler(){
        chain = new LinkedList<>();
    }

    public void addGenerator(Generator g){
        chain.add(g);
    }

    public void process(World w){
        while (!chain.isEmpty()){
            Generator g = chain.removeFirst();
            g.generate(w);
        }
    }
}
