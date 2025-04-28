package Tp2;

import org.example.TP2.ex1.Calculatrice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatriceTest {

    @Mock
    private Calculatrice calculatrice;

    @Test
    public void testAdditionner() {
        // Arrange : définition du comportement du mock
        when(calculatrice.additionner(2, 3)).thenReturn(5);

        // Act : appel de la méthode à tester
        int resultat = calculatrice.additionner(2, 3);

        // Assert : vérification du résultat
        assertEquals(5, resultat);

        // Vérification que la méthode "additionner" a été appelée avec les bons arguments
        verify(calculatrice).additionner(2, 3);

        // Vérification qu'aucune autre méthode n'a été appelée
        verifyNoMoreInteractions(calculatrice);

        // Test d’état impossible ici car on utilise un mock → on le fait avec une vraie instance ci-dessous
    }

    // Bonus : test d’état avec une vraie instance de Calculatrice
    @Test
    public void testAdditionner_Etat() {
        Calculatrice vraieCalculatrice = new Calculatrice();

        // Appel réel
        int resultat = vraieCalculatrice.additionner(4, 6);

        // Test du résultat
        assertEquals(10, resultat);

        // Test de l’état interne
        assertEquals(10, vraieCalculatrice.getResult());
    }
}
