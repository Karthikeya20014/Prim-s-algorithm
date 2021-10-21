import java.util.*;
class Edge implements Comparable<Edge>{
  Vertex _from;
  Vertex _to;
  int _cost;
  static int totalEdges = 0;
  Edge(Vertex _from, Vertex _to, int _cost){
    this._from = _from;
    this._to = _to;
    this._cost = _cost;
  }
  public int compareTo(Edge e) {
    return (int) (this._cost - e._cost);
  }
  public String toString(){
    return this._from.name+"----"+this._to.name;
  }
}
class Vertex{
  char name;
  List<Edge> edges;
  boolean visited;
  Vertex(char name){
    this.name = name;
    this.visited = false;
    this.edges = new ArrayList<>();
  }
  void connect(Vertex ad_vertex, int edge_cost){
    edges.add(new Edge(this, ad_vertex, edge_cost));
    edges.add(new Edge(ad_vertex, this, edge_cost));
    Edge.totalEdges += 2;
  }
}
class Prims{
  PriorityQueue<Edge> pqueue = new PriorityQueue<>();
  List<Edge> mst = new ArrayList<>();
  int totalCost= 0;
  boolean findMST(Vertex s){
    this.addEdges(s);
    int edgeCount = 0;
    while(!this.pqueue.isEmpty() && edgeCount != Edge.totalEdges){
      Edge minEdge = this.pqueue.peek();
      this.pqueue.poll();
      if(minEdge._to.visited)
        continue;
      else{
        edgeCount += 1;
        this.totalCost += minEdge._cost;
        this.mst.add(minEdge);
        this.addEdges(minEdge._to);
      }
    }
    return edgeCount != Edge.totalEdges;
  }
  void addEdges(Vertex s){
    s.visited = true;
    for(Edge edge: s.edges){
      if(!edge._to.visited)
        this.pqueue.add(edge);
    }
  }
}

class Prim {
  public static void main(String[] args) {
    Vertex vertices[] = {new Vertex('A'), new Vertex('B'), new  Vertex('C'), new Vertex('D'), new Vertex('E')};
    vertices[0].connect(vertices[1], 3);
    vertices[0].connect(vertices[3], 5);
    vertices[1].connect(vertices[2], 2);
    vertices[1].connect(vertices[3], 10);
    vertices[2].connect(vertices[3], 7);
    vertices[2].connect(vertices[4], 8);
    vertices[3].connect(vertices[4], 1);
    Prims prims= new Prims();
    if(prims.findMST(vertices[0])){
       System.out.println(prims.mst);
     System.out.println("Total Cost: "+ prims.totalCost);
    }
    else{
      System.out.println("MST not possible for given graph");
    }
  }
}