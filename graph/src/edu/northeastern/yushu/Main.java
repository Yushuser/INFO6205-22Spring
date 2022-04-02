package edu.northeastern.yushu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Graph graph = initGraph();
        graph.printAllPaths("A", "D");
        System.out.println();
    }
    public static Graph initGraph() {
        Graph graph = new Graph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "E");
        graph.addEdge("E", "D");
        graph.addEdge("E", "F");
        graph.addEdge("D", "B");
        graph.addEdge("C", "D");
        graph.addEdge("C", "B");
        return graph;
    }
}
class Edge {
    public String startNode;
    public String endNode;
    public int weight;

    public Edge(String startNode, String endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.weight = -1;
    }
}
class Node {
    private String name;
    private boolean visited;
    private LinkedList<Edge> listEdges = null;
    private Node parent;
    private int rank;
    public COLOR color;
    public Node(String name) {
        this.name = name;
        this.visited = false;
        this.listEdges = new LinkedList<>();
        this.rank = 1;
        this.color = COLOR.White;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public LinkedList<Edge> getListEdges() {
        return listEdges;
    }

    public void setListEdges(LinkedList<Edge> listEdges) {
        this.listEdges = listEdges;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public COLOR getColor() {
        return color;
    }

    public void setColor(COLOR color) {
        this.color = color;
    }
}
enum COLOR{White, Black, Grey};
class Graph{
    HashMap<String, Node> nodes;
    public Graph() {
        nodes = new HashMap<>();
    }
    public void addNode(String name) {
        name = name.toUpperCase();
        if (nodes.containsKey(name)) {
            return;
        }
        Node node = new Node(name);
        nodes.put(name, node);
    }
    public void addEdge(String node1Name, String node2Name){
        Node node1 = nodes.get(node1Name);
        Node node2 = nodes.get(node2Name);
        if( !nodes.containsKey(node1.getName()) || !nodes.containsKey(node2.getName())){
            return;
        }

        Edge edge = getEdge(node1, node2);
        if(edge == null){
            edge = new Edge(node1.getName(), node2.getName());
            node1.getListEdges().add(edge);
        }

    }

    private Edge getEdge(Node node1, Node node2){

        for (Edge  edge: node1.getListEdges()) {
            if(edge.endNode.equals(node2.getName())){
                return edge;
            }
        }
        return null;
    }
    public void printAllPaths(String source, String dest){
        source = source.toUpperCase();
        dest = dest.toUpperCase();

        if( !nodes.containsKey(source) || !nodes.containsKey(dest) ){
            return;
        }

        LinkedList<String> visited = new LinkedList<>();
        printAllPaths(visited, source, dest);

    }

    private void printAllPaths(LinkedList<String> visited, String current, String dest){
        if( visited.contains(current) ){
            return;
        }
        if(dest == current){
            for (String str : visited) {
                System.out.print(str + " -> ");
            }
            System.out.println(dest);
        }

        visited.add(current);

        Node node = nodes.get(current);
        for (Edge edge : node.getListEdges()) {
            if( ! visited.contains(edge.endNode) ){
                printAllPaths(visited, edge.endNode, dest);
            }
        }

        visited.remove(current);
    }

}
