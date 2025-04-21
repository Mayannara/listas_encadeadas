package org.example;

import org.example.Aplicacao.Aluno;
import org.example.Aplicacao.AlunoTurma;
import org.example.Aplicacao.LerArquivoAlunos;
import org.example.Aplicacao.MatriculaComparator;

import org.example.Biblioteca.OrderedList;
import org.example.Biblioteca.ObjListNotOrdered;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Main {

    private static JFreeChart chart; // Declare o chart como um atributo estático da classe Main
    private static OrderedList<Aluno> turmaOrdenada;
    private static ObjListNotOrdered<Aluno> turmaDesordenada;

    public static void salvarGrafico(String nomeArquivo) {
        int largura = 800;   // Largura da imagem em pixels
        int altura = 600;    // Altura da imagem em pixels
        File arquivo = new File(nomeArquivo);
        try {
            ChartUtils.saveChartAsPNG(arquivo, chart, largura, altura); // Use o atributo estático chart
            System.out.println("Gráfico salvo com sucesso em: " + arquivo.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Erro ao salvar o gráfico: " + e.getMessage());
        }
    }
    

    public static void main(String[] args) {
        turmaOrdenada = new OrderedList<>(new MatriculaComparator());
        turmaDesordenada = new ObjListNotOrdered<>();

        LerArquivoAlunos ler = new LerArquivoAlunos();
        long iO, fO, iD, fD;

        double TempoSO, TempoSD;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int tamanho;

        int matricula;
        String nome;
        float nota;
        AlunoTurma a;
        boolean boo;

        iO = System.nanoTime();
        turmaOrdenada = ler.lerArquivoOrdenado("alunosBalanceados.txt");
        fO = System.nanoTime();


        iD = System.nanoTime();
        turmaDesordenada = ler.lerArquivoDesrdenado("alunosBalanceados.txt");
        fD = System.nanoTime();

        TempoSO = (fO - iO) / 1_000_000_000.0;
        TempoSD = (fD - iD) / 1_000_000_000.0;

        tamanho = turmaOrdenada.size();


        dataset.addValue(TempoSO,"Tempo","Leitura Ordenada");
        dataset.addValue(TempoSD, "Tempo","Leitura Desordenada");

        // Crie o gráfico e atribua ao atributo estático 'chart'
        chart = ChartFactory.createBarChart(
                "Comparação Tempo de Leitura", // Título do gráfico
                "Tipo de Leitura",         // Rótulo do eixo X
                "Tempo de Leitura (s)",     // Rótulo do eixo Y (segundos)
                dataset,                     // Dados do gráfico
                PlotOrientation.VERTICAL,    // Orientação das linhas
                true,                        // Incluir legenda
                true,                        // Usar tooltips
                false
        );

        // Chame o método para salvar o gráfico
        salvarGrafico("comparacao_leitura.png");

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1 - Pesquisar na lista não ordenada");
            System.out.println("2 - Pesquisar na lista ordenada");
            System.out.println("3 - Pesquisar na lista ordenada com busca Binária");
            System.out.println("4 - Sair");
            System.out.print("Digite sua opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite a matricula do auno");
                    matricula = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome do auno");
                    nome = scanner.nextLine();
                    System .out.println("Digite a nota do auno");
                    nota = scanner.nextFloat();

                    iO = System.nanoTime();
                    System.out.print(turmaDesordenada.pesquisar(new AlunoTurma(matricula, nome, nota)));
                    fO = System.nanoTime();

                    TempoSO = (fO - iO) / 1_000_000_000.0;

                    System.out.println("Tempo de resposta do aluno: " + TempoSO);

                    break;
                case 2:


                    System.out.println("Digite a matricula do auno");
                    matricula = scanner.nextInt();
                    scanner.nextLine();


                    iO = System.nanoTime();
                    a = (AlunoTurma) turmaOrdenada.pesquisar(new AlunoTurma(matricula, " ", 0));
                    fO = System.nanoTime();

                    TempoSO = (fO - iO) / 1_000_000_000.0;

                    if(a != null){
                        a.imprimir();
                    }else{
                        System.out.println("Aluno não encontrado");
                    }

                    System.out.println("Tempo de resposta do aluno: " + TempoSO);

                    break;
                case 3:
                    System.out.println("Digite a matricula do aluno");
                    matricula = scanner.nextInt();
                    scanner.nextLine();

                    iO = System.nanoTime();
                    boo = turmaOrdenada.containsBinarySearch(new AlunoTurma(matricula, " ", 0));
                    fO = System.nanoTime();

                    TempoSO = (fO - iO) / 1_000_000_000.0;

                    if(boo){
                        System.out.println("Aluno encontrado");
                    }else{
                        System.out.println("Aluno não encontrado");
                    }

                    System.out.println("Tempo de resposta do aluno: " + TempoSO);
                    break;
                case 4:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 4);

        scanner.close();
    }
}