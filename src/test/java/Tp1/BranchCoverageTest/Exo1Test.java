package Tp1.BranchCoverageTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.TP2.ex1.Exo1Correction;

public class Exo1Test {

    @Test
    void testPalindromeLowercase() {
        assertTrue(Exo1Correction.isPalindrome("madam")); // branche où if == false
    }
    @Test
    void testPalindromeMixedCaseWithSpaces() {
        assertTrue(Exo1Correction.isPalindrome("A man a plan a canal Panama")); // branche où if == false
    }
    @Test
    void testNonPalindromeDifferentCharacters() {
        assertFalse(Exo1Correction.isPalindrome("hello")); // branche où if == true
    }
    @Test
    void testSingleCharacter() {
        assertTrue(Exo1Correction.isPalindrome("x")); // ne rentre jamais dans while
    }

    @Test
    void testNullString() {
        assertThrows(NullPointerException.class, () -> Exo1Correction.isPalindrome(null)); // test du if (s == null)
    }
}
