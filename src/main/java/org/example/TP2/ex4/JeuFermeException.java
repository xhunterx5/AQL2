package org.example.TP2.ex4;

public class JeuFermeException extends Exception {
    public JeuFermeException() {
        super("Le jeu est fermé.");
    }
}