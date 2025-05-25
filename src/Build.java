import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Build {

  /**
   * Prints words that are reachable from the given vertex and are strictly shorter than k characters.
   * If the vertex is null or no reachable words meet the criteria, prints nothing.
   *
   * @param vertex the starting vertex
   * @param k the maximum word length (exclusive)
   */
  public static void printShortWords(Vertex<String> vertex, int k) {
    Set<Vertex<String>> set = new HashSet<>();
    
    printSHortWordsHelper(set, k, vertex);
  }

  public static void printSHortWordsHelper(Set<Vertex<String>> current, int k, Vertex<String> vertex)
  {
    if(vertex == null || current.contains(vertex))
    {
      return;
    }

    current.add(vertex);

    String name = vertex.data;
    if(name.length() < k)
    {
      System.out.println(name);
    }

    for(Vertex<String> item : vertex.neighbors)
    {
      printSHortWordsHelper(current, k, item);
    }

  }

  /**
   * Returns the longest word reachable from the given vertex, including its own value.
   *
   * @param vertex the starting vertex
   * @return the longest reachable word, or an empty string if the vertex is null
   */
  public static String longestWord(Vertex<String> vertex) {
    if(vertex == null) return "";

    Set<Vertex<String>> set = new HashSet<>();

    return longestWordHelper(set, vertex, vertex.data);
  }

  public static String longestWordHelper(Set<Vertex<String>> set, Vertex<String> vertex, String word)
  {

    if(vertex == null || set.contains(vertex)) return "";

    set.add(vertex);

    String newWord = "";
    if(word.length() < vertex.data.length())
    {
      newWord = vertex.data;
    }
    else
    {
      newWord = word;
    }

    for(Vertex<String> item : vertex.neighbors)
    {
     String longestNeighbor = longestWordHelper(set, item, newWord);
     if(longestNeighbor.length() > newWord.length())
     {
      newWord = longestNeighbor;
     }
    }

    return newWord;
  }

  /**
   * Prints the values of all vertices that are reachable from the given vertex and 
   * have themself as a neighbor.
   *
   * @param vertex the starting vertex
   * @param <T> the type of values stored in the vertices
   */
  public static <T> void printSelfLoopers(Vertex<T> vertex) {
    if(vertex == null) return;

    Set<Vertex<T>> set = new HashSet<>();

    printSelfLoopersHelper(vertex, set);
  }


  public static <T> void printSelfLoopersHelper(Vertex<T> vertex, Set<Vertex<T>> set)
  {
    if(set.contains(vertex)) return;

    set.add(vertex);

    if(vertex.neighbors.contains(vertex))
    {
      System.out.println(vertex.data);
    }

    for(Vertex<T> item : vertex.neighbors)
    {
      printSelfLoopersHelper(item, set);
    }
  }

  /**
   * Determines whether it is possible to reach the destination airport through a series of flights
   * starting from the given airport. If the start and destination airports are the same, returns true.
   *
   * @param start the starting airport
   * @param destination the destination airport
   * @return true if the destination is reachable from the start, false otherwise
   */
  public static boolean canReach(Airport start, Airport destination) {
    Set<Airport> set = new HashSet<>();

    return canReachHelper(start, destination, set);
  }

  public static boolean canReachHelper(Airport start, Airport des, Set<Airport> set)
  {
    if(start == des) return true;

    set.add(start);

    for(Airport item: start.getOutboundFlights())
    {
      if(!set.contains(item) && item.getAirportCode() != start.getAirportCode())
      {
        if(canReachHelper(item, des, set) == true)
        {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns the set of all values in the graph that cannot be reached from the given starting value.
   * The graph is represented as a map where each vertex is associated with a list of its neighboring values.
   *
   * @param graph the graph represented as a map of vertices to neighbors
   * @param starting the starting value
   * @param <T> the type of values stored in the graph
   * @return a set of values that cannot be reached from the starting value
   */
  public static <T> Set<T> unreachable(Map<T, List<T>> graph, T starting) {
    return new HashSet<>();
  }
}
