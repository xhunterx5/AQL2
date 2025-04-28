package Tp2;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import org.example.TP2.ex4.*;
import org.junit.Before;
import org.junit.Test;

public class JeuTest {
    private Jeu jeu;
    private Banque banque;
    private Joueur joueur;
    private De de1, de2;

    @Before
    public void setUp() {
        banque = mock(Banque.class);
        joueur = mock(Joueur.class);
        de1 = mock(De.class);
        de2 = mock(De.class);
        jeu = new Jeu(banque); // Fresh instance for each test
    }
    @Test(expected = JeuFermeException.class)
    public void testJeuFerme() throws Exception {
        jeu.fermer();
        jeu.jouer(joueur, de1, de2);
    }

    @Test
    public void testJoueurInsolvable() throws Exception {
        when(joueur.mise()).thenReturn(10);
        doThrow(new DebitImpossibleException()).when(joueur).debiter(10);

        jeu.jouer(joueur, de1, de2);

        verify(de1, never()).lancer();
        verify(de2, never()).lancer();
        verify(banque, never()).crediter(anyInt());
        verify(joueur, never()).crediter(anyInt());
    }

    @Test
    public void testDefaite() throws Exception {
        when(joueur.mise()).thenReturn(5);
        when(de1.lancer()).thenReturn(2);
        when(de2.lancer()).thenReturn(4); // somme = 6

        jeu.jouer(joueur, de1, de2);

        verify(joueur).debiter(5);
        verify(banque).crediter(5);
        verify(joueur, never()).crediter(anyInt());
        verify(banque, never()).debiter(anyInt());
    }

    @Test
    public void testVictoireBanqueSolvable() throws Exception {
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4); // 3+4=7
        when(banque.est_solvable()).thenReturn(true);

        jeu.jouer(joueur, de1, de2);

        verify(joueur).debiter(10);
        verify(banque).crediter(10);
        verify(banque).debiter(20);
        verify(joueur).crediter(20);
        assertTrue(jeu.estOuvert());
    }

    @Test
    public void testVictoireBanqueDevientInsolvable() throws Exception {
        when(joueur.mise()).thenReturn(10);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4);

        // First call: initial check (true)
        // Second call: post-payout check (false)
        when(banque.est_solvable())
                .thenReturn(true)
                .thenReturn(false);

        jeu.jouer(joueur, de1, de2);

        verify(joueur).debiter(10);
        verify(banque).crediter(10);
        verify(banque).debiter(20);
        verify(joueur).crediter(20);

        // Now expects exactly 2 calls
        verify(banque, times(2)).est_solvable();

        assertFalse(jeu.estOuvert());
    }

    @Test
    public void testIntegrationBanque() throws Exception {
        BanqueImpl banque = new BanqueImpl(15, 10);
        when(joueur.mise()).thenReturn(5);
        when(de1.lancer()).thenReturn(3);
        when(de2.lancer()).thenReturn(4); // 3+4 = 7

        jeu.jouer(joueur, de1, de2);

        assertEquals(10, banque.getFond());
        assertTrue(jeu.estOuvert());

        when(joueur.mise()).thenReturn(3);
        jeu.jouer(joueur, de1, de2);

        assertEquals(7, banque.getFond());
        assertFalse(jeu.estOuvert());
    }
}