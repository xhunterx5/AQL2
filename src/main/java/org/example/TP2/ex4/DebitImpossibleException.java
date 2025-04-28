package org.example.TP2.ex4;

public class DebitImpossibleException extends Exception {
    public DebitImpossibleException() {
        super("Débit impossible : fonds insuffisants.");
    }
}