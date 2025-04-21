package org.example;

//Aluno se torna uma classa abastrara,pois para o comparator funcionar necessita que ela seja assim e
//uma classe abstrata não pode ser instanciada diretamente, por isso torna-se necessario criar uma nova
//que será extens de aluno
public abstract class Aluno implements Comparable<Aluno> {
    private int matricula;

    //Constructor da classe aluno
    public Aluno(int matricula) {
        this.matricula = matricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void imprimir() {
        System.out.print("Matricula: " + matricula + "\n");
    }
}