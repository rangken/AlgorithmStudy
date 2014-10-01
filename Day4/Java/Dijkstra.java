import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
/**
 * 다익스트라 최단 경로 non-negative 경로에만 적용함
 *
 * Worst case: O(|E| + |V| log |V|)
 *
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
public class Dijkstra {
  private static Map<Graph.Vertex<Integer>, Graph.CostVertexPair<Integer>> costs = null; // 경로 버텍스 테이블
  private static Map<Graph.Vertex<Integer>, Set<Graph.Edge<Integer>>> paths = null; // VERTEX 당 지나온 경로들
  private static Queue<Graph.CostVertexPair<Integer>> unvisited = null; // 방문하지 노드들 기록

  private Dijkstra() { }

  public static Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> getShortestPaths(Graph<Integer> g, Graph.Vertex<Integer> start) {
      getShortestPath(g, start, null);
      Map<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>> map = new HashMap<Graph.Vertex<Integer>, Graph.CostPathPair<Integer>>();
      for (Graph.CostVertexPair<Integer> pair : costs.values()) {
          int cost = pair.getCost();
          Graph.Vertex<Integer> vertex = pair.getVertex();
          Set<Graph.Edge<Integer>> path = paths.get(vertex);
          map.put(vertex, new Graph.CostPathPair<Integer>(cost, path));
      }
      return map;
  }

  public static Graph.CostPathPair<Integer> getShortestPath(Graph<Integer> g, Graph.Vertex<Integer> start, Graph.Vertex<Integer> end) {
      if (g == null)
          throw (new NullPointerException("Graph must be non-NULL."));

      costs = null;
      paths = null;
      unvisited = null;

      // nagative edge 의 경우에는 안됨
      boolean hasNegativeEdge = checkForNegativeEdges(g.getVerticies());
      if (hasNegativeEdge)
          throw (new IllegalArgumentException("Negative cost Edges are not allowed."));

      paths = new TreeMap<Graph.Vertex<Integer>, Set<Graph.Edge<Integer>>>();
      for (Graph.Vertex<Integer> v : g.getVerticies()) {
          paths.put(v, new LinkedHashSet<Graph.Edge<Integer>>());
      }

      costs = new TreeMap<Graph.Vertex<Integer>, Graph.CostVertexPair<Integer>>();
      for (Graph.Vertex<Integer> v : g.getVerticies()) {
          if (v.equals(start))
              costs.put(v, new Graph.CostVertexPair<Integer>(0, v)); // 시작 경로는 cost 0
          else
              costs.put(v, new Graph.CostVertexPair<Integer>(Integer.MAX_VALUE, v)); // 나머지 경로는 MAX
      }

      // 우선 순위 큐에 CostVertexPair 로 비교해서 최고 적은 Cost 인게 제거되도록 log(V)
      unvisited = new PriorityQueue<Graph.CostVertexPair<Integer>>();
      unvisited.addAll(costs.values()); // 깊은 복사

      Graph.Vertex<Integer> vertex = start;
      unvisited.remove(vertex);
      while (true) {
          // 현재 경로에서 접근 가능한 vertex 들에 접근! 이미 접근한곳은 안감
          for (Graph.Edge<Integer> e : vertex.getEdges()) {
              Graph.CostVertexPair<Integer> pair = costs.get(e.getToVertex());
              Graph.CostVertexPair<Integer> lowestCostToThisVertex = costs.get(vertex);
              int cost = lowestCostToThisVertex.getCost() + e.getCost();
              if (pair.getCost() == Integer.MAX_VALUE) {
                  // 아직 접근 한번도 안한곳
                  pair.setCost(cost);
                  Set<Graph.Edge<Integer>> set = paths.get(e.getToVertex());
                  set.addAll(paths.get(e.getFromVertex())); // 이미 왔던 경로
                  set.add(e); // 현재 새로 도착한 경로 추가해줌
              } else if (cost < pair.getCost()) {
                  // 접근은 했어도 보다더 짧은 경로를 찾음
                  pair.setCost(cost);
                  Set<Graph.Edge<Integer>> set = paths.get(e.getToVertex());
                  set.clear(); // 새로온 경로이므로 지난 경로는 다지움
                  set.addAll(paths.get(e.getFromVertex())); // 이미온경로 넣어줌
                  set.add(e); // 새로운 경로 넣어줌
              }
          }


          if (end != null && vertex.equals(end)) {
              // 최종 경로를 찾았을때
              break;
          } else if (unvisited.size() > 0) {
              // 아직 방문하지 않은곳이 있을때
              Graph.CostVertexPair<Integer> pair = unvisited.remove();
              System.out.println("REMOVE : " + pair);
              vertex = pair.getVertex();
              if (pair.getCost() == Integer.MAX_VALUE) {
                  // 시작 포인트에서 접근이 불가능한
                  break;
              }
          } else {
              break;
          }
      }

      if (end != null) {
          Graph.CostVertexPair<Integer> pair = costs.get(end);
          Set<Graph.Edge<Integer>> set = paths.get(end);
          return (new Graph.CostPathPair<Integer>(pair.getCost(), set));
      }
      return null;
  }

  private static boolean checkForNegativeEdges(List<Graph.Vertex<Integer>> vertitices) {
      for (Graph.Vertex<Integer> v : vertitices) {
          for (Graph.Edge<Integer> e : v.getEdges()) {
              if (e.getCost() < 0)
                  return true;
          }
      }
      return false;
  }
}
