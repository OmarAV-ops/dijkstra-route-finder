
package inclass;

import java.util.*;
import java.io.*;

/**
   Class for simulating finding the shortest distance
   from a city to all other cities.
*/
public class DistanceFinder
{
   private String startFrom;
   private Map<String, HashSet<DistanceTo>> directConnections;

   /**
      Construct a Distance finder.
      @param filename the file containing the connections.
   */
   public DistanceFinder(String filename)
   {
      directConnections = new HashMap<>();

      try {
         Scanner file = new Scanner(new File(filename));

         boolean firstLine = true;

         while (file.hasNext()) {
            String city1 = file.next();
            String city2 = file.next();
            int distance = file.nextInt();

            if (firstLine) {
               startFrom = city1;
               firstLine = false;
            }

            directConnections.putIfAbsent(city1, new HashSet<>());
            directConnections.putIfAbsent(city2, new HashSet<>());

            // bidirectional connection
            directConnections.get(city1).add(new DistanceTo(city2, distance));
            directConnections.get(city2).add(new DistanceTo(city1, distance));
         }

         file.close();

      } catch (FileNotFoundException e) {
         System.out.println("File not found: " + filename);
      }
   }

   /**
      Return the city that we start from.
   */
   public String getStartingCity()
   {
      return startFrom;
   }

   /**
      Return the shortest distances.
      @return the shortest distances.
   */
   public Map<String, Integer> shortestDistances()
   {
      Map<String, Integer> shortestKnownDistance = new TreeMap<>();
      PriorityQueue<DistanceTo> pq = new PriorityQueue<>();
      Set<String> visited = new HashSet<>();

      shortestKnownDistance.put(startFrom, 0);
      pq.add(new DistanceTo(startFrom, 0));

      while (!pq.isEmpty()) {

         DistanceTo current = pq.poll();
         String city = current.getTarget();

         if (visited.contains(city)) continue;
         visited.add(city);

         if (!directConnections.containsKey(city)) continue;

         for (DistanceTo neighbor : directConnections.get(city)) {

            String nextCity = neighbor.getTarget();
            int newDist = shortestKnownDistance.get(city) + neighbor.getDistance();

            if (!shortestKnownDistance.containsKey(nextCity)
                    || newDist < shortestKnownDistance.get(nextCity)) {

               shortestKnownDistance.put(nextCity, newDist);
               pq.add(new DistanceTo(nextCity, newDist));
            }
         }
      }

      return shortestKnownDistance;
   }
}