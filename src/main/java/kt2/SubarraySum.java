package kt2;

import java.util.HashMap;
import java.util.Map;

/**
 * Задача 4. Количество подмассивов с заданной суммой.
 */
public final class SubarraySum {

    private SubarraySum() {
    }

    /**
     * Префиксные суммы: сумма подмассива (i, j] равна prefix[j] - prefix[i].
     * Значит, подмассив с суммой k заканчивается в позиции j столько раз,
     * сколько раньше встречалась префиксная сумма prefix[j] - k. Встречавшиеся
     * суммы считаем в словаре, пустой префикс (сумма 0) учитываем сразу.
     * Скользящее окно здесь не подходит: из-за отрицательных чисел сумма окна
     * не растёт монотонно. Сложность O(n) по времени и памяти.
     *
     * Суммы и ответ хранятся в long: префиксная сумма доходит до 2 * 10^14,
     * а число подмассивов до n * (n + 1) / 2, это около 2 * 10^10, больше int.
     */
    public static long count(int[] nums, long k) {
        Map<Long, Integer> prefixCounts = new HashMap<>();
        prefixCounts.put(0L, 1);

        long sum = 0;
        long result = 0;
        for (int x : nums) {
            sum += x;
            result += prefixCounts.getOrDefault(sum - k, 0);
            prefixCounts.merge(sum, 1, Integer::sum);
        }
        return result;
    }
}
