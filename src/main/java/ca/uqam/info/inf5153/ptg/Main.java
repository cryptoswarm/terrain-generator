package ca.uqam.info.inf5153.ptg;

import UserInterface.UserArgs;
import ca.uqam.ace.inf5153.mesh.io.Structs.Mesh;
import map.Carte;
import reader.MeshFileReader;
import reader.Reader;
import writer.Writer;

//import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        //valider si le fichier existe avant de le donner au reader
        UserArgs parsedArgs = new UserArgs(args);

        Reader reader = new MeshFileReader();

        Carte carte =  reader.readFile( parsedArgs.getInputFile());

        Writer writer = new Writer();
        Mesh endMesh = writer.generateEndMesh(parsedArgs, carte);

       // parsedArgs.createOutputFile(endMesh);
        writer.createOutputFile(endMesh, parsedArgs);
    }
    //atoll ou tortga est une liste de tuiles
}
