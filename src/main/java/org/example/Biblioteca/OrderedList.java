package org.example.Biblioteca;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//Generics de forma que na instanciação
public class OrderedList<T> {
    private List<T> list;
    private Comparator<T> comparator;

    //Construtor que recebe o comparador e o armazena
    public OrderedList(Comparator<T> comparator) {
        // Inicia a lista vazia4
        this.list = new ArrayList<>();
        this.comparator = comparator;
    }

    //função adicionar procura a posição do elemento passado na lista e o adiciona nela
    public void adicionar(T novoValor) {
        if (novoValor == null) {
            throw new IllegalArgumentException("Elemento não pode ser nulo.");
        }

        int insertIndex = 0;
        for (T item : list) {
            if (this.comparator.compare(novoValor, item) > 0) {
                insertIndex++;
            } else {
                break;
            }
        }
        this.list.add(insertIndex, novoValor);
    }

    //Dada uma posição a função a remove da lista
    public boolean remove(T element) {
        return this.list.remove(element);
    }

    //Dada um index ela retorna o elemento
    public T get(int index) {
        if (index < 0 || index >= this.list.size()) {
            throw new IndexOutOfBoundsException("Índice fora dos limites.");
        }
        return this.list.get(index);
    }

    //retorna o tamanho da lista
    public int size() {
        return this.list.size();
    }

    //Verifica se está vazio
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    //Verifica se o elemento passado está na lista
    public boolean contains(T element) {
        return this.list.contains(element);
    }

    //Pesquisar valor não encontrado retorna null, se encontrar retorna a posição
    public T pesquisar(T valor){
        for (T item : list) {
            if (this.comparator.compare(valor, item) == 0) {
                  return item;
            }
        }
        return null;
    }

    //Limpa a lista
    public void clear() {
        this.list.clear();
    }

<<<<<<< HEAD:src/main/java/org/example/Biblioteca/OrderedList.java

    //Método de busca binária retorna true ou false se o elemento está ou não na lista
=======
    //Metodo de busca binária
>>>>>>> 5aad4fe (teste de commit):src/main/java/org/example/OrderedList.java
    public boolean containsBinarySearch(T element) {
        return binarySearchIndex(element) >= 0;
    }

    private int binarySearchIndex(T key) {
        int low = 0;
        int high = this.list.size() - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            T midVal = this.list.get(mid);
            int cmp = this.comparator.compare(key, midVal);

            if (cmp < 0) {
                high = mid - 1;
            } else if (cmp > 0) {
                low = mid + 1;
            } else {
                return mid; // key found
            }
        }
        return -(low + 1);  // key not found.
    }
}
