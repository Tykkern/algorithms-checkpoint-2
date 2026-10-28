package kt2;

import java.util.Arrays;

/**
 * Прогон примеров из задания.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("1. Самая длинная последовательность номеров");
        printLength(new int[] {100, 4, 200, 1, 3, 2});
        printLength(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1});
        printLength(new int[] {});

        System.out.println();
        System.out.println("2. Тройки с нулевой суммой");
        printTriples(new int[] {-1, 0, 1, 2, -1, -4});
        printTriples(new int[] {0, 0, 0, 0});
        printTriples(new int[] {1, 2, -2, -1});

        System.out.println();
        System.out.println("3. Самый длинный участок не более чем с двумя типами");
        printSegment(new int[] {1, 2, 1});
        printSegment(new int[] {0, 1, 2, 2});
        printSegment(new int[] {1, 2, 3, 2, 2});

        System.out.println();
        System.out.println("4. Количество подмассивов с суммой k");
        printSubarrays(new int[] {1, 1, 1}, 2);
        printSubarrays(new int[] {1, -1, 0}, 0);

        System.out.println();
        System.out.println("5. Максимальный объём воды");
        printVolume(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7});
        printVolume(new int[] {1, 1});

        System.out.println();
        System.out.println("6. Минимальная подстрока-покрытие");
        printCover("ADOBECODEBANC", "ABC");
        printCover("a", "aa");
        printCover("AAABBC", "AABC");
    }

    private static void printLength(int[] nums) {
        System.out.println("  " + Arrays.toString(nums) + " -> " + LongestSequence.length(nums));
    }

    private static void printTriples(int[] nums) {
        System.out.println("  " + Arrays.toString(nums) + " -> " + ZeroSumTriples.find(nums));
    }

    private static void printSegment(int[] items) {
        System.out.println("  " + Arrays.toString(items) + " -> " + TwoTypesSegment.maxLength(items));
    }

    private static void printSubarrays(int[] nums, long k) {
        System.out.println("  " + Arrays.toString(nums) + ", k = " + k + " -> " + SubarraySum.count(nums, k));
    }

    private static void printVolume(int[] height) {
        System.out.println("  " + Arrays.toString(height) + " -> " + WaterContainer.maxVolume(height));
    }

    private static void printCover(String source, String target) {
        System.out.println("  source = \"" + source + "\", target = \"" + target + "\" -> \""
                + MinCoveringSubstring.find(source, target) + "\"");
    }
}
