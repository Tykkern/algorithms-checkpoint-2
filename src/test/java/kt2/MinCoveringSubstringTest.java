package kt2;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinCoveringSubstringTest {

    private static boolean covers(String window, String target) {
        int[] counts = new int[128];
        for (char c : window.toCharArray())
            counts[c]++;
        for (char c : target.toCharArray())
            if (--counts[c] < 0)
                return false;
        return true;
    }

    // Перебор подстрок по возрастанию длины, при равной длине берётся самая левая.
    private static String bruteForce(String source, String target) {
        for (int length = 1; length <= source.length(); length++)
            for (int start = 0; start + length <= source.length(); start++) {
                String window = source.substring(start, start + length);
                if (covers(window, target))
                    return window;
            }
        return "";
    }

    private static String randomString(Random random, String alphabet, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++)
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        return sb.toString();
    }

    @Test
    void examplesFromTask() {
        assertEquals("BANC", MinCoveringSubstring.find("ADOBECODEBANC", "ABC"));
        assertEquals("", MinCoveringSubstring.find("a", "aa"));
        assertEquals("AABBC", MinCoveringSubstring.find("AAABBC", "AABC"));
    }

    @Test
    void wholeStringIsAnswer() {
        assertEquals("abc", MinCoveringSubstring.find("abc", "cba"));
        assertEquals("a", MinCoveringSubstring.find("a", "a"));
    }

    @Test
    void noAnswer() {
        assertEquals("", MinCoveringSubstring.find("abc", "d"));
        assertEquals("", MinCoveringSubstring.find("ab", "abc"));
    }

    @Test
    void caseSensitive() {
        assertEquals("", MinCoveringSubstring.find("aaa", "A"));
        assertEquals("A", MinCoveringSubstring.find("aAa", "A"));
    }

    @Test
    void digitsAndLetters() {
        assertEquals("a3", MinCoveringSubstring.find("12a3b", "3a"));
    }

    @Test
    void leftmostOfEqualWindows() {
        assertEquals("ab", MinCoveringSubstring.find("abab", "ab"));
    }

    @Test
    void randomStringsMatchBruteForce() {
        Random random = new Random(1);
        for (int test = 0; test < 500; test++) {
            String source = randomString(random, "aAb1", 1 + random.nextInt(25));
            String target = randomString(random, "aAb1", 1 + random.nextInt(5));
            assertEquals(bruteForce(source, target), MinCoveringSubstring.find(source, target));
        }
    }

    @Test
    @Timeout(5)
    void largeInput() {
        Random random = new Random(2);
        String target = randomString(random, "abcdefghij", 100_000);
        String source = randomString(random, "abcdefghij", 200_000);

        // В source каждой буквы примерно вдвое больше, чем в target, так что ответ точно есть.
        assertTrue(covers(MinCoveringSubstring.find(source, target), target));
    }
}
