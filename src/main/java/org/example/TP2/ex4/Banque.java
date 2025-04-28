package org.example.TP2.ex4;

public interface Banque {
    void crediter(int somme);
    void debiter(int somme);
    boolean est_solvable();
}