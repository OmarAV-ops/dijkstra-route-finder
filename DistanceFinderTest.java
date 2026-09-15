
package inclass;   

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Map;

public class DistanceFinderTest {

    @Test
    public void testStartingCityNotNull() {
        DistanceFinder d = new DistanceFinder("cities1.txt");
        assertNotNull(d.getStartingCity());
    }

    @Test
    public void testStartingCityDistanceZero() {
        DistanceFinder d = new DistanceFinder("cities1.txt");
        Map<String, Integer> result = d.shortestDistances();

        assertEquals(0, (int) result.get(d.getStartingCity()));
    }

    @Test
    public void testResultMapNotEmpty() {
        DistanceFinder d = new DistanceFinder("cities1.txt");
        Map<String, Integer> result = d.shortestDistances();

        assertTrue(result.size() > 0);
    }

    @Test
    public void testDifferentFilesProduceResults() {
        DistanceFinder d1 = new DistanceFinder("cities1.txt");
        DistanceFinder d2 = new DistanceFinder("cities2.txt");

        assertNotEquals(d1.shortestDistances(), d2.shortestDistances());
    }
}