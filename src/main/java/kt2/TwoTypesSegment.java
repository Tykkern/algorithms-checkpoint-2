package kt2;

import java.util.HashMap;
import java.util.Map;

/**
 * Задача 3. Самый длинный участок с ограниченным числом типов.
 */
public final class TwoTypesSegment {
    private static final int MAX_TYPES = 2;

    private TwoTypesSegment() {
    }

    /**
     * Скользящее окно [left, right]. В словаре храним, сколько товаров каждого
     * типа лежит в окне. Правую границу двигаем на каждом шаге, а если типов
     * стало больше двух, сдвигаем левую, пока лишний тип не уйдёт из окна.
     * Каждый элемент входит в окно и выходит из него один раз, поэтому O(n)
     * по времени. В словаре не больше трёх ключей, память O(1).
     */
    public static int maxLength(int[] items) {
        Map<Integer, Integer> counts = new HashMap<>();
        int best = 0;
        int left = 0;

        for (int right = 0; right < items.length; right++) {
            counts.merge(items[right], 1, Integer::sum);

            while (counts.size() > MAX_TYPES) {
                int type = items[left];
                left++;
                if (counts.merge(type, -1, Integer::sum) == 0)
                    counts.remove(type);
            }

            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}
