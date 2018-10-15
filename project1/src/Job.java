public class Job {
    private int penaltyP;
    private int earlinessA;
    private int tardinessB;

    public Job(int p, int a, int b){
        penaltyP = p;
        earlinessA = a;
        tardinessB = b;
    }

    public int getPenaltyP() {
        return penaltyP;
    }

    public void setPenaltyP(int penaltyP) {
        this.penaltyP = penaltyP;
    }

    public int getEarlinessA() {
        return earlinessA;
    }

    public void setEarlinessA(int earlinessA) {
        this.earlinessA = earlinessA;
    }

    public int getTardinessB() {
        return tardinessB;
    }

    public void setTardinessB(int tardinessB) {
        this.tardinessB = tardinessB;
    }
}
