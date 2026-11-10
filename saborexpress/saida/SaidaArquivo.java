package saborexpress.saida;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaidaArquivo implements SaidaRelatorio {
    private final String caminho;

    public SaidaArquivo(String caminho) {
        this.caminho = caminho;
    }

    @Override
    public void exibir(String conteudo) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(caminho))) {
            escritor.write(conteudo);
            System.out.println("Relatório salvo em: " + caminho);
        } catch (IOException e) {
            System.out.println("Não foi possível salvar o relatório: " + e.getMessage());
        }
    }
}
