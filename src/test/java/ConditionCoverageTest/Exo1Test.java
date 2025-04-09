package ConditionCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Exo1Correction;

public class Exo1Test {

    @Test
    void testIdenticalCharacters() {
        assertTrue(Exo1Correction.isPalindrome("aa")); // s.charAt(i) == s.charAt(j)
    }

    @Test
    void testDifferentCharacters() {
        assertFalse(Exo1Correction.isPalindrome("ab")); // s.charAt(i) != s.charAt(j)
    }

    @Test
    void testCaseInsensitiveMatch() {
        assertTrue(Exo1Correction.isPalindrome("A a")); // 'A' == 'a' after toLowerCase
    }

    @Test
    void testWithSpacesAndCase() {
        assertTrue(Exo1Correction.isPalindrome("Léon Noél")); // test avec accents + casse + espace
    }

    @Test
    void testNull() {
        assertThrows(NullPointerException.class, () -> Exo1Correction.isPalindrome(null));
    }
}
