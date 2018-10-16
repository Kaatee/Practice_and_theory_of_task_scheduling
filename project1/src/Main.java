import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MakeSolution makeSolution = new MakeSolution();

        for (int k=0; k<10; k++){
            makeSolution.makeSolution(k,500000,0.2f);
        }

    }



}


