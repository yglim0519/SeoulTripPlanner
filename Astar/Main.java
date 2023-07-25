package Astar;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    public static double calculateH(Node curNode, Node endNode){
        double R = 6372.8;
        double curLat = curNode.getLatitude();
        double endLat = endNode.getLatitude();

        double disX = Math.toRadians(endLat - curLat);
        double disY = Math.toRadians(endNode.getLongitude() - curNode.getLongitude());

        curLat = Math.toRadians(curLat);
        endLat = Math.toRadians(endLat);

        double a = Math.pow(Math.sin(disX/2),2) + Math.pow(Math.sin(disY/2), 2) * Math.cos(curLat) * Math.cos(endLat);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }
    public static Node astar(Node start, Node end, Map<Node, Set<Node>> map) {
        PriorityQueue<Node> openList = new PriorityQueue<>();
        PriorityQueue<Node> closedList = new PriorityQueue<>();
        
        start.f = start.g;
        openList.add(start);

        while(!openList.isEmpty()) {
            Node curNode = openList.peek();
            if(curNode == end) {
                return curNode;
            }
            
            for(Node nextNode : map.get(curNode)) {
                double totalG = curNode.g + nextNode.g;

                if(!openList.contains(nextNode) && !closedList.contains(nextNode)){
                    nextNode.parent = curNode;
                    nextNode.g = totalG;
                    nextNode.f = nextNode.g + calculateH(nextNode, end);
                    openList.add(nextNode);
                }
                else {
                    if(totalG < nextNode.g) {
                        nextNode.parent = curNode;
                        nextNode.g = totalG;
                        nextNode.f = nextNode.g + calculateH(nextNode, end);
                        
                        if(closedList.contains(nextNode)){
                            closedList.remove(nextNode);
                            openList.add(nextNode);
                        }
                    }
                }
            }

            openList.remove(curNode);
            closedList.add(curNode);
        }

        return null;
    }

    public static void printPath(){
        
    }
    public static void main(String[] args) {
        //set Station Nodes
        Node 역삼역 = new Node("역삼역", 37.50049620627384, 127.03608699626439);
        Node 강남역 = new Node("강남역", 37.49762703329083, 127.02713510764242);
        Node 교대역 = new Node("교대역", 37.49349339867693, 127.01418451427759);
        Node 서초역 = new Node("서초역", 37.49204427628725, 127.00805277669251);
        Node 방배역 = new Node("방배역", 37.49762703329083, 127.02713510764242);
        Node 사당역 = new Node("사당역", 37.476702207488415, 126.98186744467074);

        Node 양재역 = new Node("양재역", 37.48416035662368, 127.03437168158308);
        Node 신논현역 = new Node("신논현역", 37.5052616121752, 127.02608161880843);
        Node 남부터미널역 = new Node("남부터미널역", 37.48511378176747, 127.01696591293998);
        Node 고속터미널역 = new Node("고속터미널역", 37.505058790201694, 127.00530131270978);
        Node 남태령역 = new Node("남태령역", 37.46440778879037, 126.98905106505252);
        Node 총신대입구역 = new Node("총신대입구역", 37.48663569797591, 126.98186526742752);
        
        //create subwayMap
        Map<Node, Set<Node>> subwayMap = new HashMap<>();

        //set subwayMap - need to set node first
        Set<Node> 강남근처 = new HashSet<>();
        강남근처.add(역삼역);
        강남근처.add(교대역);
        강남근처.add(양재역);
        강남근처.add(신논현역);
        subwayMap.put(강남역, 강남근처);       

        Set<Node> 교대근처 = new HashSet<>();
        교대근처.add(강남역);
        교대근처.add(서초역);
        교대근처.add(남부터미널역);
        교대근처.add(고속터미널역);
        subwayMap.put(교대역, 교대근처); 

        Set<Node> 서초근처 = new HashSet<>();
        서초근처.add(교대역);
        서초근처.add(방배역);
        subwayMap.put(서초역, 서초근처); 

        Set<Node> 방배근처 = new HashSet<>();
        방배근처.add(서초역);
        방배근처.add(사당역);
        subwayMap.put(방배역, 방배근처);
            
        Set<Node> 사당근처 = new HashSet<>();
        사당근처.add(방배역);
        사당근처.add(남태령역);
        사당근처.add(총신대입구역);
        subwayMap.put(사당역, 사당근처); 
    }
}