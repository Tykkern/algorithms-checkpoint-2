package kt2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Задача 2. Тройки с нулевой суммой.
 */
public final class ZeroSumTriples {

    private ZeroSumTriples() {
    }

    /**
     * Сортируем копию массива. Перебираем первый элемент тройки a[i], а пару
     * к нему ищем двумя указателями на отрезке справа от i: если сумма меньше нуля,
     * двигаем левый указатель вправо, если больше - правый влево.
     * Одинаковые значения пропускаем и для a[i], и для указателей, поэтому
     * повторяющихся троек в ответе нет и отдельное множество не нужно.
     * Сложность O(n^2) по времени, O(n) на копию массива.
     */
    public static List<List<Integer>> find(int[] nums) {
        int[] a = nums.clone();
        Arrays.sort(a);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < a.length - 2; i++) {
            if (i > 0 && a[i] == a[i - 1])
                continue;
            // Дальше все числа не меньше a[i] > 0, нулевой суммы уже не будет.
            if (a[i] > 0)
                break;

            int left = i + 1;
            int right = a.length - 1;
            while (left < right) {
                int sum = a[i] + a[left] + a[right];
                if (sum < 0) {
                    left++;
                } else if (sum > 0) {
                    right--;
                } else {
                    result.add(List.of(a[i], a[left], a[right]));
                    left++;
                    right--;
                    while (left < right && a[left] == a[left - 1])
                        left++;
                    while (left < right && a[right] == a[right + 1])
                        right--;
                }
            }
        }
        return result;
    }
}
