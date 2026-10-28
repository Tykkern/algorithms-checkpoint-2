package kt2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WaterContainerTest {

    // Перебор всех пар стенок, O(n^2).
    private static long bruteForce(int[] height) {
        long best = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++)
                best = Math.max(best, (long) (j - i) * Math.min(height[i], height[j]));
        return best;
    }

    @Test
    void examplesFromTask() {
        assertEquals(49, WaterContainer.maxVolume(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, WaterContainer.maxVolume(new int[] {1, 1}));
    }

    @Test
    void zeroHeights() {
        assertEquals(0, WaterContainer.maxVolume(new int[] {0, 0}));
        assertEquals(0, WaterContainer.maxVolume(new int[] {0, 5}));
    }

    @Test
    void wallsAtTheEdges() {
        assertEquals(15, WaterContainer.maxVolume(new int[] {5, 0, 0, 5}));
    }

    @Test
    void increasingHeights() {
        assertEquals(6, WaterContainer.maxVolume(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    void randomArraysMatchBruteForce() {
        Random random = new Random(1);
        for (int test = 0; test < 500; test++) {
            int[] height = random.ints(2 + random.nextInt(40), 0, 20).toArray();
            assertEquals(bruteForce(height), WaterContainer.maxVolume(height));
        }
    }

    @Test
    @Timeout(5)
    void volumeDoesNotFitIntoInt() {
        int n = 200_000;
        int[] height = new Random(2).ints(n, 0, 1000).toArray();
        height[0] = 1_000_000_000;
        height[n - 1] = 1_000_000_000;

        assertEquals((long) (n - 1) * 1_000_000_000, WaterContainer.maxVolume(height));
    }
}
