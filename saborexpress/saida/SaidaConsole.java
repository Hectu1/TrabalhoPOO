package saborexpress.saida;

public class SaidaConsole implements SaidaRelatorio {
    @Override
    public void exibir(String conteudo) {
        System.out.println(conteudo);
    }
}
