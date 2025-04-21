package org.example;

public class AlunoTurma extends Aluno {
    private String nome;
    private float nota;

    public AlunoTurma(int matricula, String nome, float nota) {
        super(matricula);
        this.nome = nome;
        this.nota = nota;
    }

    public void imprimir() {
        System.out.print("Nome: " + nome + " ");
        System.out.print("Nota: " + nota + " ");
        System.out.print("Matricula: " + this.getMatricula() + "\n");
    }

    @Override
    public int compareTo(Aluno o) {
        return 0;
    }
}
