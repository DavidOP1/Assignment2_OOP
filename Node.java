package api;

import java.util.Hashtable;

public class Node implements NodeData{
    private int id;
    private GeoLocation location;
    private double weight;
    private String info;
    private int tag;
    Hashtable<Integer,EdgeData> Node = new Hashtable<>();//Hash table of all edges getting out of node,and their key will be the dest. Because since we are in this node the src id is known to us.
    public Node(double x,double y, double z, int id){
        this.id = id;
        this.location = new Location(x, y, z);

    }
    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = p;

    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;

    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;

    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;

    }
}
