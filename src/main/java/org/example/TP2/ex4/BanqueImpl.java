package org.example.TP2.ex4;

// BanqueImpl.java
public class BanqueImpl implements Banque {
    private int fond;
    private final int fondMin;

    public BanqueImpl(int fond, int fondMin) {
        this.fond = fond;
        this.fondMin = fondMin;
    }

    @Override
    public void crediter(int somme) {
        if (somme < 0) throw new IllegalArgumentException("Somme négative");
        fond += somme;
    }

    @Override
    public void debiter(int somme) {
        if (somme < 0) throw new IllegalArgumentException("Somme négative");
        fond -= somme;
    }

    @Override
    public boolean est_solvable() {
        return fond >= fondMin;
    }

    // Pour les tests
    public int getFond() {
        return fond;
    }
}