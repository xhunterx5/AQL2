package org.example.TP2.ex4;

public interface Joueur {
    int mise(); // On suppose que la mise est toujours positive
    void debiter(int somme) throws DebitImpossibleException;
    void crediter(int somme);
}