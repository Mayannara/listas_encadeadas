package org.example;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerArquivoAlunos {

    public LerArquivoAlunos() {
    }

    public static OrderedList<Aluno> lerArquivoOrdenado(String caminhoArquivo) {
        OrderedList<Aluno> alunos = new OrderedList<>(new MatriculaComparator());
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine(); // Ignora a primeira linha
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    try {
                        int matricula = Integer.parseInt(dados[0].trim());
                        String nome = dados[1].trim();
                        double nota = Double.parseDouble(dados[2].trim().replace(',', '.')); // Substitui vírgula por ponto
                        alunos.adicionar(new AlunoTurma(matricula, nome, (float) nota));
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter dados da linha: " + linha);
                    }
                } else {
                    System.err.println("Formato inválido na linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return alunos;
    }

    public objListNotOrdenada<Aluno> lerArquivoDesrdenado(String caminhoArquivo) {
        objListNotOrdenada<Aluno> alunos = new objListNotOrdenada<>();
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            br.readLine(); // Ignora a primeira linha
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    try {
                        int matricula = Integer.parseInt(dados[0].trim());
                        String nome = dados[1].trim();
                        double nota = Double.parseDouble(dados[2].trim().replace(',', '.')); // Substitui vírgula por ponto
                        alunos.adiciona(new AlunoTurma(matricula, nome, (float) nota));
                    } catch (NumberFormatException e) {
                        System.err.println("Erro ao converter dados da linha: " + linha);
                    }
                } else {
                    System.err.println("Formato inválido na linha: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return alunos;
    }
}