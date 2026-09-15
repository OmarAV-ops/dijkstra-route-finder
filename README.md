# dijkstra-route-finder
Java implementation of Dijkstra's algorithm to find shortest routes between cities.

# Dijkstra Route Finder

A Java implementation of Dijkstra's shortest-path algorithm that calculates the least-expensive route from a starting city to every other reachable city in a network.


## How It Works

Cities and the direct connections between them (with distance/cost values) are read in from a text file, where each line has the format:

```
city1 city2 distance
```

All connections are treated as bidirectional. The program builds a graph from this data using a `Map<String, HashSet<DistanceTo>>`, then runs Dijkstra's algorithm, using a priority queue to always expand the currently cheapest known route first, to compute the shortest distance from the starting city to every other city in the network.

**Example:** given a network where Pendleton connects to Pierre, Pierre to Pueblo, and so on, the program finds that the cheapest route from Pendleton to Peoria costs 8, going through Pierre and Pueblo, even though a more direct looking path might cost more.

## Project Structure

```
src/inclass/
├── DistanceFinder.java       # Core algorithm: builds the graph and computes shortest distances
├── DistanceTo.java           # Helper class representing a distance to a target city (Comparable)
├── DistanceFinderDemo.java   # Simple command-line demo that runs the algorithm on a chosen file
└── DistanceFinderTest.java   # JUnit test suite covering DistanceFinder's methods

data/
├── cities1.txt
├── cities2.txt
├── cities3.txt
└── cities4.txt
```

## Key Concepts Used

- **Dijkstra's algorithm** for single-source shortest paths
- **Priority queue (min-heap)** to always process the closest unvisited city next
- **HashMap / HashSet** for efficient graph representation and lookups
- **Custom `Comparable` implementation** (`DistanceTo`) so the priority queue can order cities by distance
- **File I/O** to parse the city-connection input files
- **JUnit testing** to verify correctness of the algorithm across multiple input files

## Running It

1. Open the project in Eclipse (or any Java IDE).
2. Run `DistanceFinderDemo.java`.
3. When prompted, enter the filename of a data file (e.g. `cities1.txt`).
4. The program prints the starting city and the shortest distance to every reachable city.

## Testing

`DistanceFinderTest.java` contains JUnit tests covering:
- That a starting city is correctly identified
- That the distance to the starting city itself is 0
- That the algorithm produces a non-empty result set
- That different input files produce different results

Run the tests via Eclipse's built-in JUnit runner, or any JUnit-compatible test runner.
