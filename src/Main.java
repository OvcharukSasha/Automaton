import java.util.*;
import java.io.*;
import java.lang.*;
import java.io.File;

public class Main {



    public static void main(String []args)
    {
        try {
            FindingWords fw = new FindingWords();
            fw.solve("out/production/sislab2_automaton/autom");
        }

        catch(Exception e)
        {
            System.out.println("Error!");
        }


    }








}
