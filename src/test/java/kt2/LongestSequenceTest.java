package kt2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestSequenceTest {

    // Проверочное решение через сортировку: идём по различным числам по возрастанию.
    private static int bruteForce(int[] nums) {
        TreeSet<Integer> sorted = new TreeSet<>();
        for (int x : nums)
            sorted.add(x);

        int best = 0;
        int current = 0;
        Integer previous = null;
        for (int x : sorted) {
            current = (previous != null && x == previous + 1) ? current + 1 : 1;
            best = Math.max(best, current);
            previous = x;
        }
        return best;
    }

    @Test
    void examplesFromTask() {
        assertEquals(4, LongestSequence.length(new int[] {100, 4, 200, 1, 3, 2}));
        assertEquals(9, LongestSequence.length(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}));
        assertEquals(0, LongestSequence.length(new int[] {}));
    }

    @Test
    void singleElement() {
        assertEquals(1, LongestSequence.length(new int[] {5}));
    }

    @Test
    void duplicatesDoNotIncreaseLength() {
        assertEquals(3, LongestSequence.length(new int[] {1, 2, 2, 2, 3}));
        assertEquals(1, LongestSequence.length(new int[] {7, 7, 7}));
    }

    @Test
    void negativeAndBoundaryValues() {
        assertEquals(3, LongestSequence.length(new int[] {1, -1, 0}));
        assertEquals(1, LongestSequence.length(new int[] {-1_000_000_000, 1_000_000_000}));
        assertEquals(2, LongestSequence.length(new int[] {1_000_000_000, 999_999_999}));
    }

    @Test
    void randomArraysMatchBruteForce() {
        Random random = new Random(1);
        for (int test = 0; test < 500; test++) {
            int[] nums = random.ints(random.nextInt(30), -20, 20).toArray();
            assertEquals(bruteForce(nums), LongestSequence.length(nums));
        }
    }

    @Test
    @Timeout(5)
    void largeShuffledSequence() {
        int n = 200_000;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++)
            nums[i] = i - 100_000;
        Random random = new Random(2);
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }

        assertEquals(n, LongestSequence.length(nums));
    }
}
