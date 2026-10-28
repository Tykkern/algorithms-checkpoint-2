package kt2;

/**
 * Задача 5. Максимальный объём воды между стенками.
 */
public final class WaterContainer {

    private WaterContainer() {
    }

    /**
     * Два указателя с краёв массива. На каждом шаге считаем объём и сдвигаем
     * указатель у более низкой стенки. Сдвигать высокую нет смысла: расстояние
     * уменьшится, а высота воды всё равно ограничена низкой стенкой, так что
     * объём не вырастет. Значит, низкая стенка уже ни с какой другой не даст
     * больше, и её можно отбросить. Сложность O(n) по времени, O(1) по памяти.
     *
     * Объём считается в long: 2 * 10^5 * 10^9 не помещается в int.
     */
    public static long maxVolume(int[] height) {
        int left = 0;
        int right = height.length - 1;
        long best = 0;

        while (left < right) {
            long volume = (long) (right - left) * Math.min(height[left], height[right]);
            best = Math.max(best, volume);

            if (height[left] < height[right])
                left++;
            else
                right--;
        }
        return best;
    }
}
