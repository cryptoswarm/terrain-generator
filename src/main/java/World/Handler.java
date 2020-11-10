package World;

import java.util.LinkedList;

public class Handler {
    LinkedList<Generator> chain;

    public Handler(){
        chain = new LinkedList<Generator>();
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
