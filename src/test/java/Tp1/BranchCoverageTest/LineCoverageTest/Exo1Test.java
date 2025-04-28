package Tp1.BranchCoverageTest.LineCoverageTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.TP2.ex1.Exo1Correction;

public class Exo1Test {
    @Test
    void testSimplePalindrome() {
        assertTrue(Exo1Correction.isPalindrome("kayak"));
    }

    @Test
    void testPalindromeWithSpaces() {
        assertTrue(Exo1Correction.isPalindrome("Esope reste ici et se repose"));
    }

    @Test
    void testNonPalindrome() {
        assertFalse(Exo1Correction.isPalindrome("bonjour"));
    }

    @Test
    void testEmptyString() {
        assertTrue(Exo1Correction.isPalindrome(""));
    }

    @Test
    void testNullThrowsException() {
        assertThrows(NullPointerException.class, () -> Exo1Correction.isPalindrome(null));
    }
}
