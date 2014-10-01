import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
public class main{
  static public void main(String argv[]){
    // Dijkstra
    java.util.List<Graph.Vertex<Integer>> verticies = new java.util.ArrayList<Graph.Vertex<Integer>>();
    Graph.Vertex<Integer> v1 = new Graph.Vertex<Integer>(1);
    verticies.add(v1);
    Graph.Vertex<Integer> v2 = new Graph.Vertex<Integer>(2);
    verticies.add(v2);
    Graph.Vertex<Integer> v3 = new Graph.Vertex<Integer>(3);
    verticies.add(v3);
    Graph.Vertex<Integer> v4 = new Graph.Vertex<Integer>(4);
    verticies.add(v4);
    Graph.Vertex<Integer> v5 = new Graph.Vertex<Integer>(5);
    verticies.add(v5);
    Graph.Vertex<Integer> v6 = new Graph.Vertex<Integer>(6);
    verticies.add(v6);

    java.util.List<Graph.Edge<Integer>> edges = new java.util.ArrayList<Graph.Edge<Integer>>();
    Graph.Edge<Integer> e1_2 = new Graph.Edge<Integer>(7, v1, v2);
    edges.add(e1_2);
    Graph.Edge<Integer> e1_3 = new Graph.Edge<Integer>(9, v1, v3);
    edges.add(e1_3);
    Graph.Edge<Integer> e1_6 = new Graph.Edge<Integer>(14, v1, v6);
    edges.add(e1_6);
    Graph.Edge<Integer> e2_3 = new Graph.Edge<Integer>(10, v2, v3);
    edges.add(e2_3);
    Graph.Edge<Integer> e2_4 = new Graph.Edge<Integer>(15, v2, v4);
    edges.add(e2_4);
    Graph.Edge<Integer> e3_4 = new Graph.Edge<Integer>(11, v3, v4);
    edges.add(e3_4);
    Graph.Edge<Integer> e3_6 = new Graph.Edge<Integer>(2, v3, v6);
    edges.add(e3_6);
    Graph.Edge<Integer> e5_6 = new Graph.Edge<Integer>(9, v5, v6);
    edges.add(e5_6);
    Graph.Edge<Integer> e4_5 = new Graph.Edge<Integer>(6, v4, v5);
    edges.add(e4_5);

    Graph<Integer> undirected = new Graph<Integer>(verticies, edges);

    Graph.Vertex<Integer> start = v1;
    System.out.println("---Dijkstra---");
    java.util.Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map1 = Dijkstra.getShortestPaths(undirected, start);
    System.out.println(getPathMapString(start, map1));

    System.out.println("---BellmanFord---");
    Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map2 = BellmanFord.getShortestPaths(undirected, start);
    System.out.println(getPathMapString(start, map2));

    System.out.println("---Prim---");
    Graph.CostPathPair<Integer> pair = Prim.getMinimumSpanningTree(undirected, start);
    System.out.println(pair.toString());
  }
  private static final String getPathMapString(Graph.Vertex<Integer> start, Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map) {
      StringBuilder builder = new StringBuilder();
      for (Graph.Vertex<Integer> v : map.keySet()) {
          Graph.CostPathPair<Integer> pair = map.get(v);
          builder.append("From ").append(start.getValue()).append(" to vertex=").append(v.getValue()).append("\n");
          if (pair != null)
              builder.append(pair.toString()).append("\n");

      }
      return builder.toString();
  }
  private static final String getPathMapString(Map<Graph.Vertex<Integer>, Map<Graph.Vertex<Integer>, Set<Graph.Edge<Integer>>>> paths) {
      StringBuilder builder = new StringBuilder();
      for (Graph.Vertex<Integer> v : paths.keySet()) {
          Map<Graph.Vertex<Integer>, Set<Graph.Edge<Integer>>> map = paths.get(v);
          for (Graph.Vertex<Integer> v2 : map.keySet()) {
              builder.append("From=").append(v.getValue()).append(" to=").append(v2.getValue()).append("\n");
              Set<Graph.Edge<Integer>> path = map.get(v2);
              builder.append(path).append("\n");
          }
      }
      return builder.toString();
  }
}
