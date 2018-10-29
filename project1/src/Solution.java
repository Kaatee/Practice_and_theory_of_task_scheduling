import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solution implements Comparable, Serializable {
    private ArrayList<Job> rankedJobs;
    private int f;
    private int r;
    private float h;

    public Solution(ArrayList<Job> rankedJobs, int f, int r) {
        this.rankedJobs = rankedJobs;
        this.f = f;
        this.r = r;
    }

    public ArrayList<Job> getRankedJobs() {
        return rankedJobs;
    }

    public void setRankedJobs(ArrayList<Job> rankedJobs) {
        this.rankedJobs = rankedJobs;
    }

    public int getF() {
        return f;
    }
    public int getR() {
        return r;
    }
    public float getH() {
        return h;
    }

    public void setH(float h) {
        this.h = h;
    }

    public void setF(int f) {
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
