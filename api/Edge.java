package api;

public class Edge implements EdgeData{
    private int src,dest;
    private double weight;
    private String info;
    private int tag;
    public Edge(int src, int dest, double weight){
       this.src=src;
       this.dest=dest;
       this.weight=weight;
    }
    @Override
    public int getSrc() {
        return this.src;
    }//Returns the id of the source node

    @Override
    public int getDest() {
        return this.dest;
    }//Returns the id of the destination node

    @Override
    public double getWeight() {
        return this.weight;
    }//Returns weight of the edge

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info =s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
          this.tag=t;
    }
}
