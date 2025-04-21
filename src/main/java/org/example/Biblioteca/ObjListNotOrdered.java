package org.example.Biblioteca;

import java.util.Comparator;


public class ObjListNotOrdered<T> extends ObjList<T> {
    public ObjListNotOrdered() {
        super();
    }

    public void adiciona(T element) {// adiciona elemento a lista
        if (element == null) {
            throw new IllegalArgumentException("Elemento não pode ser nulo.");
        }
        try {
            this.list.add(element);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T pesquisar(T valor) {//busca o elemento na lista
        if (valor == null) {
            return null;
        }
        for (T elemento : this.list) {
            if (valor.equals(elemento)) {
                return elemento;
            }
        }
        return null;
    }
}