package kt2;

import java.util.HashSet;
import java.util.Set;

/**
 * Задача 1. Самая длинная последовательность номеров.
 */
public final class LongestSequence {

    private LongestSequence() {
    }

    /**
     * Все числа кладём в HashSet: так убираются повторы и проверка "есть ли число"
     * работает за O(1). Последовательность считаем только от её начала, то есть от
     * числа x, для которого x - 1 в множестве нет. Тогда каждое число просматривается
     * не больше двух раз, и общая сложность O(n) по времени и O(n) по памяти.
     * Сортировка тоже решила бы задачу, но за O(n log n).
     */
    public static int length(int[] nums) {
        Set<Integer> numbers = new HashSet<>();
        for (int x : nums)
            numbers.add(x);

        int best = 0;
        for (int x : numbers) {
            if (numbers.contains(x - 1))
                continue;

            int length = 1;
            while (numbers.contains(x + length))
                length++;
            best = Math.max(best, length);
        }
        return best;
    }
}
