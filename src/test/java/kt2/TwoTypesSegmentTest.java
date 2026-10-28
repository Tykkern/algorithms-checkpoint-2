package kt2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TwoTypesSegmentTest {

    // Перебор всех начал участка, участок расширяем, пока типов не больше двух.
    private static int bruteForce(int[] items) {
        int best = 0;
        for (int start = 0; start < items.length; start++) {
            Set<Integer> types = new HashSet<>();
            for (int end = start; end < items.length; end++) {
                types.add(items[end]);
                if (types.size() > 2)
                    break;
                best = Math.max(best, end - start + 1);
            }
        }
        return best;
    }

    @Test
    void examplesFromTask() {
        assertEquals(3, TwoTypesSegment.maxLength(new int[] {1, 2, 1}));
        assertEquals(3, TwoTypesSegment.maxLength(new int[] {0, 1, 2, 2}));
        assertEquals(4, TwoTypesSegment.maxLength(new int[] {1, 2, 3, 2, 2}));
    }

    @Test
    void emptyAndSingle() {
        assertEquals(0, TwoTypesSegment.maxLength(new int[] {}));
        assertEquals(1, TwoTypesSegment.maxLength(new int[] {1_000_000}));
    }

    @Test
    void oneOrTwoTypesTakeWholeArray() {
        assertEquals(5, TwoTypesSegment.maxLength(new int[] {4, 4, 4, 4, 4}));
        assertEquals(6, TwoTypesSegment.maxLength(new int[] {1, 2, 2, 1, 1, 2}));
    }

    @Test
    void allTypesDifferent() {
        assertEquals(2, TwoTypesSegment.maxLength(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    void bestSegmentAtTheEnd() {
        assertEquals(6, TwoTypesSegment.maxLength(new int[] {1, 2, 3, 3, 4, 3, 4, 4}));
    }

    @Test
    void randomArraysMatchBruteForce() {
        Random random = new Random(1);
        for (int test = 0; test < 500; test++) {
            int[] items = random.ints(random.nextInt(40), 0, 1 + random.nextInt(5)).toArray();
            assertEquals(bruteForce(items), TwoTypesSegment.maxLength(items));
        }
    }

    @Test
    @Timeout(5)
    void largeInput() {
        int n = 200_000;
        int[] items = new int[n];
        for (int i = 0; i < n; i++)
            items[i] = i % 2;
        items[n / 2] = 1_000_000;

        // Третий тип посередине делит массив на две части, самая длинная из них - левая, n / 2 элементов.
        assertEquals(n / 2, TwoTypesSegment.maxLength(items));
    }
}
