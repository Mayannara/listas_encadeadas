package org.example.Aplicacao;

import java.util.Comparator;

// Comparator para comparar alunos por matrícula
public class MatriculaComparator implements Comparator<Aluno> {
    @Override
    public int compare(Aluno aluno1, Aluno aluno2) {
        return Integer.compare(aluno1.getMatricula(), aluno2.getMatricula());
    }
}