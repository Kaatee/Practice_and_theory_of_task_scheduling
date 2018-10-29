import java.util.ArrayList;

public class Problem {
    private int numberOfJobs;
    private ArrayList<Job> jobs;

    public int getNumberOfJobs() {
        return numberOfJobs;
    }

    public void setNumberOfJobs(int numberOfJobs) {
        this.numberOfJobs = numberOfJobs;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public Problem(int numberOfJobs) {

        this.numberOfJobs = numberOfJobs;
    }

    public Problem(ArrayList<Job> jobs) {

        this.jobs = jobs;
    }

    public Problem(ArrayList<Job> jobs, int numberOfJobs) {

        this.jobs = jobs;
        this.numberOfJobs = numberOfJobs;
    }
}
