package Astar;

public class Node implements Comparable<Node> {
    private final String stationName;
    private final double latitude;
    private final double longitude;
    
    public Node parent = null;

    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    
    public double h;

    public Node(String name, double x, double y) {
        this.stationName = name;
        this.latitude = x;
        this.longitude = y;
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public String getStationName() {
        return stationName;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
