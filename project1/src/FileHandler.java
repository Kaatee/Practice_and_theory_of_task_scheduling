import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    ArrayList<Problem> readFile(String fileneme){
        ArrayList<Problem> problems = new ArrayList<Problem>();
        try {

            File file = new File(fileneme);
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
            }


        } catch(FileNotFoundException e)
        {
            System.out.println("Problem z odczytem!");
        }
        return problems;
    }

    Solution readSolutionFromFile(String filename){
        try {
            File file = new File(filename);
            Scanner in = new Scanner(file);

            float h = Float.parseFloat(in.nextLine())/10;
            int f = Integer.parseInt(in.nextLine());
            int n_numOfJobs = Integer.parseInt(in.nextLine());
            int r = Integer.parseInt(in.nextLine());

            ArrayList<Job> jobs = new ArrayList<Job>();

            for (int i = 0; i < n_numOfJobs; i++) {
                String line = in.nextLine();
                String[] parts = line.trim().split("\\s+");

                Job job = new Job(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                jobs.add(job);
            }
            Solution solution = new Solution(jobs, f, r);
            solution.setH(h);
            return solution;

        } catch(FileNotFoundException e)
        {
            System.out.println("Problem z odczytem!");
        }

        return new Solution(null,0, 0);
    }
}
