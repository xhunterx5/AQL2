package org.example.TP2.ex4;

// Jeu.java
public class Jeu {
    private Banque banque;
    private boolean ouvert = true;

    public Jeu(Banque banque) {
        this.banque = banque;
    }

    public void fermer() {
        ouvert = false;
    }

    public boolean estOuvert() {
        return ouvert;
    }
    public void jouer(Joueur joueur, De de1, De de2) throws JeuFermeException {
        if (!ouvert) throw new JeuFermeException();

        // First solvency check - before accepting bet
        if (!banque.est_solvable()) {
            this.fermer();
            throw new JeuFermeException();
        }

        int mise = joueur.mise();
        try {
            joueur.debiter(mise);
        } catch (DebitImpossibleException e) {
            return;
        }
        banque.crediter(mise);

        int somme = de1.lancer() + de2.lancer();
        if (somme != 7) {
            return;
        }

        int gain = 2 * mise;
        banque.debiter(gain);
        joueur.crediter(gain);

        // Second solvency check - after payout
        if (!banque.est_solvable()) {
            this.fermer();
        }
    }
}