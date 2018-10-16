public class Job {
    private int processingTimeP;
    private int earlinessA;
    private int tardinessB;

    public Job(int p, int a, int b){
        processingTimeP = p;
        earlinessA = a;
        tardinessB = b;
    }

    public int getprocessingTimeP() {
        return processingTimeP;
    }

    public void setprocessingTimeP(int processingTimeP) {
        this.processingTimeP = processingTimeP;
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

    public String toString(){
        return "["+this.getprocessingTimeP()+","+this.getEarlinessA()+","+this.getTardinessB()+"]";
    }
}
