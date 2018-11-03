import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MakeSolution makeSolution = new MakeSolution();

        for (int k=0; k<10; k++){
            makeSolution.makeSolution(k,1,0.8f,"sch1000.txt");
        }
        //schN_k_h.txt

        //sch10_1_2.txt
        //sch10_10_8.txt
        //sch100_2_4.txt
        //sch100_9_6.txt
        //sch500_1_2.txt
        //na nastepne i wyslac plik mailem do pon do polnocy: przeliczyc i wygenerowac 4 tabele jak na tablicy dla ustalonego ni i k
        // dla wszystkich k takie obliczenia , w drugirj kolumnie upperbound ze strony, trzecia to wynik naszego algorytmu, czwarta to blad w procentach
        //czas obliczen pojednyczego wyniku (pobieranie instancji nie wchodzi, tylko szeregowanie i obliczanie funkcji celu, bez zapisu i odczytu do plików)
        // obliczanie błędów: tak jak w pliku do zadan
        //tabelka w pdf
        //

    }



}


