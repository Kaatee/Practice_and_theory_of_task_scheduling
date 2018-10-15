import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MakeSolution {
    void makeSolution(){
        readFile();
    }

    ArrayList<Problem> readFile(){
        ArrayList<Problem> problems = new ArrayList<Problem>();
        try {

            File file = new File("sch10.txt");
            Scanner in = new Scanner(file);

            int numOfProblems = Integer.parseInt(in.nextLine().replace(" ", ""));

            for (int i = 0; i < numOfProblems; i++) {
                int numOfJobs = Integer.parseInt(in.nextLine().replace(" ", ""));
                Problem problem = new Problem(numOfJobs);
                ArrayList<Job> jobs = new ArrayList<Job>();

                for (int j = 0; j < numOfJobs; j++) {
                    String line = in.nextLine();
                    String[] parts = line.trim().split("\\s+");

                    Job job = new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    jobs.add(job);
                }
                problem.setJobs(jobs);
                problems.add(problem);
                System.out.println(i + " SUM_P: "+calculateSumP(problem));
            }


        } catch(FileNotFoundException e)
        {
            System.out.println("Problem z odczytem!");
        }
        return problems;
    }

    int calculateSumP(Problem problem){
        int sum = 0;
        ArrayList<Job> jobs = problem.getJobs();

        for(Job j: jobs){
            sum += j.getPenaltyP();
        }

        return sum;
    }
}
