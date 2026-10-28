package kt2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroSumTriplesTest {

    // Порядок троек и чисел внутри тройки не важен, поэтому сравниваем
    // множества отсортированных троек. Заодно проверяем, что повторов нет.
    private static Set<List<Integer>> normalize(List<List<Integer>> triples) {
        Set<List<Integer>> result = new HashSet<>();
        for (List<Integer> triple : triples) {
            List<Integer> sorted = new ArrayList<>(triple);
            sorted.sort(null);
            result.add(sorted);
        }
        assertEquals(triples.size(), result.size(), "В ответе есть повторяющиеся тройки");
        return result;
    }

    private static void assertTriples(Set<List<Integer>> expected, int[] nums) {
        assertEquals(expected, normalize(ZeroSumTriples.find(nums)));
    }

    // Полный перебор всех троек индексов, O(n^3).
    private static Set<List<Integer>> bruteForce(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
            for (int j = i + 1; j < nums.length; j++)
                for (int k = j + 1; k < nums.length; k++)
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triple = new ArrayList<>(List.of(nums[i], nums[j], nums[k]));
                        triple.sort(null);
                        result.add(triple);
                    }
        return result;
    }

    @Test
    void examplesFromTask() {
        assertTriples(Set.of(List.of(-1, -1, 2), List.of(-1, 0, 1)), new int[] {-1, 0, 1, 2, -1, -4});
        assertTriples(Set.of(List.of(0, 0, 0)), new int[] {0, 0, 0, 0});
        assertTriples(Set.of(), new int[] {1, 2, -2, -1});
    }

    @Test
    void exactlyThreeElements() {
        assertTriples(Set.of(List.of(0, 0, 0)), new int[] {0, 0, 0});
        assertTriples(Set.of(), new int[] {1, 1, 1});
        assertTriples(Set.of(List.of(-3, 1, 2)), new int[] {2, -3, 1});
    }

    @Test
    void manyDuplicates() {
        assertTriples(Set.of(List.of(-2, 0, 2)), new int[] {-2, -2, 0, 0, 2, 2});
        assertTriples(Set.of(List.of(-4, 2, 2), List.of(-2, 0, 2)), new int[] {2, 2, 2, -4, -2, 0, 2});
    }

    @Test
    void inputArrayIsNotModified() {
        int[] nums = {3, -1, -2, 0};
        ZeroSumTriples.find(nums);
        assertEquals(List.of(3, -1, -2, 0), List.of(nums[0], nums[1], nums[2], nums[3]));
    }

    @Test
    void boundaryValues() {
        assertTriples(Set.of(List.of(-100_000, 0, 100_000)), new int[] {100_000, -100_000, 0, 100_000});
    }

    @Test
    void randomArraysMatchBruteForce() {
        Random random = new Random(1);
        for (int test = 0; test < 500; test++) {
            int[] nums = random.ints(3 + random.nextInt(30), -10, 11).toArray();
            assertEquals(bruteForce(nums), normalize(ZeroSumTriples.find(nums)));
        }
    }

    @Test
    @Timeout(5)
    void maximumSizeInput() {
        int[] nums = new Random(2).ints(3000, -100_000, 100_001).toArray();
        normalize(ZeroSumTriples.find(nums));
    }
}
