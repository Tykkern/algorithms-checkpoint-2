package kt2;

/**
 * Задача 6. Минимальная подстрока-покрытие.
 */
public final class MinCoveringSubstring {

    private MinCoveringSubstring() {
    }

    /**
     * Скользящее окно. need[c] показывает, сколько символов c ещё не хватает
     * в окне (отрицательное значение - лишние), missing - сколько символов target
     * всего не хватает. Двигаем правую границу, пока окно не покроет target
     * (missing == 0), затем сжимаем окно слева, пока покрытие сохраняется,
     * и запоминаем самое короткое. При равной длине остаётся первое найденное,
     * то есть самое левое окно.
     * Каждый символ source входит в окно и выходит из него один раз:
     * O(|source| + |target|) по времени, память O(1) (массив на все значения char).
     */
    public static String find(String source, String target) {
        int[] need = new int[Character.MAX_VALUE + 1];
        for (int i = 0; i < target.length(); i++)
            need[target.charAt(i)]++;

        int missing = target.length();
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;
        int left = 0;

        for (int right = 0; right < source.length(); right++) {
            char c = source.charAt(right);
            if (need[c] > 0)
                missing--;
            need[c]--;

            while (missing == 0) {
                if (right - left + 1 < bestLength) {
                    bestStart = left;
                    bestLength = right - left + 1;
                }

                char removed = source.charAt(left);
                left++;
                need[removed]++;
                if (need[removed] > 0)
                    missing++;
            }
        }

        return bestLength == Integer.MAX_VALUE ? "" : source.substring(bestStart, bestStart + bestLength);
    }
}
