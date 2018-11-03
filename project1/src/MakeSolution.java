import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

public class MakeSolution {
    void makeSolution(int k, int numberOfIterations, float h, String filename){
        FileHandler fh = new FileHandler();

        System.out.println("k: "+(k+1));
        ArrayList<Problem> problems = fh.readFile(filename);
        long startTime = System.nanoTime();
        Solution sulution = sortJobs(problems.get(k), h);
        long executionTime = System.nanoTime() - startTime;
        System.out.println("Solution: F: "+ sulution.getF());
        System.out.println("Czas wykonania: " + executionTime*Math.pow(10,-9) +" sekund");
        System.out.println("------------------");
        //ArrayList<Solution> solutions = makeSolutions(problems.get(k), numberOfIterations, h);
        //Solution bestSolution = findBestSolution(solutions, k, h);
        //System.out.println("Moje najlepsze rozwiązanie dla k = " + k + " to: "+bestSolution.toString());
        //saveSolution(bestSolution, h, k);

/*
        Checker ch = new Checker();
        String fileneme = "Solutions/sch500_4_2.txt";
        Solution solutionToCheck = fh.readSolutionFromFile(fileneme);
        float hToCheck = solutionToCheck.getH();
        Problem pr = new Problem(solutionToCheck.getRankedJobs(), solutionToCheck.getRankedJobs().size());

        Solution calcByme = new Solution(solutionToCheck.getRankedJobs(), calculeteF(pr,hToCheck,solutionToCheck.getR()), solutionToCheck.getR());
        boolean isOK = ch.checkF(calcByme, solutionToCheck);
        System.out.println("Sprawdzarka zwróciła: " + String.valueOf(isOK));
        if(!isOK) System.out.println("Moje uszeregowanie: " + calcByme.toString() +"\n"+"Sprawdzane uszeregowanie: "+ solutionToCheck.toString());
*/
    }



    private void saveSolution(Solution solution, float h, int k){
        FileWriter fileWr = null;
        try {
            String fileName = "sch"+String.valueOf(solution.getRankedJobs().size())+"_"+(k+1)+"_"+String.valueOf((int)(h*10))+".txt"; //schN_k_h.txt
            fileWr = new FileWriter(fileName);
            fileWr.write(String.valueOf((int)(h*10))+"\n"); //h
            fileWr.write(String.valueOf(solution.getF())+"\n"); //f
            fileWr.write(String.valueOf(solution.getRankedJobs().size())+"\n"); //n
            fileWr.write(String.valueOf(solution.getR())+"\n");//r
            for(Job j: solution.getRankedJobs()){
                fileWr.write(j.toStringToSave()+"\n");
            }

            fileWr.close();
        }
        catch(Exception e){}
    }

    ArrayList<Solution> makeSolutions(Problem problem, int numberOfIterations, float h){
        ArrayList<Solution> solutions = new ArrayList<>();

        for (int i=0; i<numberOfIterations; i++){
            Solution sol = new Solution(problem.getJobs(), calculeteF(problem, h, 0),0);
            solutions.add(sol);
            problem = new Problem(rank(problem));
        }

        return solutions;
    }

    Solution findBestSolution(ArrayList<Solution> solutions, int k, float h){
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

    Solution sortJobs (Problem problem, float h){
        ArrayList<Job> jobsList = problem.getJobs();

        //sort by a-b
        Collections.sort(jobsList, new a_bComparator());

        //devide arrayList on 2
        ArrayList<Job> first = new ArrayList<>();
        ArrayList<Job> second = new ArrayList<>();
        int half = (int)(jobsList.size()/2);

        for (int i=0; i<jobsList.size(); i++){
            if(i<=half){
                first.add(jobsList.get(i));
            }
            else{
                second.add(jobsList.get(i));
            }
        }

        //sort first half of arrayList by a
        Collections.sort(first, new a_Comparator());

        //sort second half of arrayList by b
        Collections.sort(second, new b_Comparator());

        //calculate end of first half
        Problem p = new Problem(first);
        int end = calculateSumP(p);

        //merge two arrays
        first.addAll(second);

        Problem pr = new Problem(first);

        float sum_P = calculateSumP(pr);
        int d = (int) Math.floor(sum_P * h);

        int r = Math.max(d-end, 0);
        int f = calculeteF(pr, h, r);

        Solution sol = new Solution(first, f, r);
        return sol;
     }


    int calculeteF(Problem problem, float h, int r){
        int f = 0;
        float sum_P = calculateSumP(problem);
        int d = (int) Math.floor(sum_P * h);
        ArrayList<Float> ciArray = calculateCi(problem.getJobs());

        for (int j=0;j<problem.getJobs().size();j++){
            float ci=ciArray.get(j)+r;
            f+= problem.getJobs().get(j).getEarlinessA()*Math.max(d-ci,0) + problem.getJobs().get(j).getTardinessB()*Math.max(ci-d, 0);
        }
        return f;
    }

    class a_bComparator implements Comparator<Job> {
        public int compare(Job x, Job y){
            int xx = x.getEarlinessA() - x.getTardinessB();
            int yy = y.getEarlinessA() - y.getTardinessB();
            int primary = xx-yy;
            return primary;
        }
    }

    class a_Comparator implements Comparator<Job> {
        public int compare(Job x, Job y){
            int primary = x.getEarlinessA() - y.getEarlinessA();
            return primary;
        }
    }

    class b_Comparator implements Comparator<Job> {
        public int compare(Job x, Job y){
            int primary = x.getTardinessB() - y.getTardinessB();
            return -1* primary;
        }
    }
}
