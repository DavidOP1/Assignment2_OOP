package api;

public class Location implements GeoLocation{
    private double x,y,z;
    public Location(double x, double y, double z){
        //sudo
        this.x=x;
        this.y=y;
        this.z=z;
    }
    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }

    @Override
    public double distance(GeoLocation g) {
        double xDist=Math.pow((g.x()-this.x),2);
        double yDist=Math.pow((g.y()-this.y),2);
        double zDist=Math.pow((g.z()-this.z),2);

        double distance=Math.sqrt(xDist+yDist+zDist);
        return distance;
    }
}
