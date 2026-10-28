package kt2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubarraySumTest {

    // Перебор всех подмассивов, O(n^2).
    private static long bruteForce(int[] nums, long k) {
        long count = 0;
        for (int start = 0; start < nums.length; start++) {
            long sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == k)
                    count++;
            }
        }
        return count;
    }

    @Test
    void examplesFromTask() {
        assertEquals(2, SubarraySum.count(new int[] {1, 1, 1}, 2));
        assertEquals(3, SubarraySum.count(new int[] {1, -1, 0}, 0));
    }

    @Test
    void singleElement() {
        assertEquals(1, SubarraySum.count(new int[] {5}, 5));
        assertEquals(0, SubarraySum.count(new int[] {5}, 4));
    }

    @Test
    void negativeK() {
        assertEquals(3, SubarraySum.count(new int[] {-1, -1, 1}, -1));
    }

    @Test
    void noSuitableSubarrays() {
        assertEquals(0, SubarraySum.count(new int[] {1, 2, 3}, 100));
    }

    @Test
    void randomArraysMatchBruteForce() {
        Random random = new Random(1);
        for (int test = 0; test < 500; test++) {
            int[] nums = random.ints(1 + random.nextInt(40), -5, 6).toArray();
            long k = random.nextInt(11) - 5;
            assertEquals(bruteForce(nums, k), SubarraySum.count(nums, k));
        }
    }

    @Test
    @Timeout(5)
    void answerDoesNotFitIntoInt() {
        int n = 200_000;
        int[] zeros = new int[n];

        // Подходит любой из n * (n + 1) / 2 подмассивов, это больше Integer.MAX_VALUE.
        assertEquals((long) n * (n + 1) / 2, SubarraySum.count(zeros, 0));
    }

    @Test
    @Timeout(5)
    void sumDoesNotFitIntoInt() {
        int n = 200_000;
        int[] nums = new int[n];
        Arrays.fill(nums, 1_000_000_000);

        assertEquals(1, SubarraySum.count(nums, (long) n * 1_000_000_000));
        assertEquals(2, SubarraySum.count(nums, (long) (n - 1) * 1_000_000_000));
    }
}
