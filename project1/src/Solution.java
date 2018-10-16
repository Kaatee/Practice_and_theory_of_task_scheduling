import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solution implements Comparable {
    private ArrayList<Job> rankedJobs;
    private float f;

    public Solution(ArrayList<Job> rankedJobs, float f) {
        this.rankedJobs = rankedJobs;
        this.f = f;
    }

    public ArrayList<Job> getRankedJobs() {
        return rankedJobs;
    }

    public void setRankedJobs(ArrayList<Job> rankedJobs) {
        this.rankedJobs = rankedJobs;
    }

    public float getF() {
        return f;
    }

    public void setF(float f) {
        this.f = f;
    }

    public String toString(){
        ArrayList<Job> jobs = this.getRankedJobs();
        String x = "";
        for (int i=0; i<jobs.size(); i++){
            x+=jobs.get(i).toString()+" ";
        }
        return "F= " + this.getF() + " dla uszeregowania: " + x;
    }

    @Override
    public int compareTo(Object o) {
        float comparef = ((Solution)o).getF();
        return (int)this.f-(int)comparef;
    }
}
