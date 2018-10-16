import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MakeSolution {
    void makeSolution(int k, int numberOfIterations, float h){
        ArrayList<Problem> problems = readFile();
        ArrayList<Solution> solutions = makeSolutions(problems.get(k), numberOfIterations, h);
        Solution bestSolution = findBestSolution(solutions);
        System.out.println("Moje najlepsze rozwiązanie dla k = " + k + " to: "+bestSolution.toString());
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
                //System.out.println(i + " SUM_P: "+calculateSumP(problem)+" F="+calculeteF(problem,0.2f));
            }


        } catch(FileNotFoundException e)
        {
            System.out.println("Problem z odczytem!");
        }
        return problems;
    }

    ArrayList<Solution> makeSolutions(Problem problem, int numberOfIterations, float h){
        ArrayList<Solution> solutions = new ArrayList<>();

        for (int i=0; i<numberOfIterations; i++){
            Solution sol = new Solution(problem.getJobs(), calculeteF(problem, h));
            solutions.add(sol);
            problem = new Problem(rank(problem));
        }

        return solutions;
    }

    Solution findBestSolution(ArrayList<Solution> solutions){
        Collections.sort(solutions);
        return solutions.get(0);
    }


    int calculateSumP(Problem problem){
        int sum = 0;
        ArrayList<Job> jobs = problem.getJobs();

        for(Job j: jobs){
            sum += j.getprocessingTimeP();
        }

        return sum;
    }

    float calculeteF(Problem problem, float h){
        float f = 0;
        float sum_P = calculateSumP(problem);
        int d = (int) Math.floor(sum_P * h);
        int numOfJobs = problem.getNumberOfJobs();
        ArrayList<Float> ciArray = calculateCi(problem.getJobs());

        for (int j=0;j<problem.getJobs().size();j++){//Job j: rankedArray){
            float ci=ciArray.get(j);
            f+= problem.getJobs().get(j).getEarlinessA()*Math.max(d-ci,0) + problem.getJobs().get(j).getTardinessB()*Math.max(ci-d, 0);
        }
        return f;
    }

    ArrayList<Float> calculateCi(ArrayList<Job> rankedArray){ //ci - end job time
        ArrayList<Float> ciArray = new ArrayList<>();
        for (int i=0; i<rankedArray.size(); i++){
            ciArray.add(calculateSingleCi(rankedArray, i));
        }
        return ciArray;
    }

    float calculateSingleCi(ArrayList<Job> rankedArray, int idx){
        float ci=0.0f;
        for (int i=0; i<=idx; i++){
            ci+=rankedArray.get(i).getprocessingTimeP();
        }
        return ci;
    }

    ArrayList<Job> rank(Problem problem){
        ArrayList<Job> rankedArray = new ArrayList<>();
        ArrayList<Job> jobsList = problem.getJobs();

        //rankedArray = jobsList;

        //shuffle array
        List<Job> list = new ArrayList<>();
        for(int i=0; i<jobsList.size(); i++){
            list.add(jobsList.get(i));
        }

        Collections.shuffle(list);

        for(int i=0; i<list.size(); i++){
            rankedArray.add(list.get(i));
        }

        return rankedArray;
    }
}
