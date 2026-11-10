package saborexpress.app;

import saborexpress.excecao.PrecoBaseInvalidoException;
import saborexpress.modelo.*;
import saborexpress.repositorio.ControlePedidos;
import saborexpress.saida.SaidaArquivo;
import saborexpress.saida.SaidaConsole;
import saborexpress.servico.GeradorRelatorio;

public class Principal {
    public static void main(String[] args) {
        try {
            Cliente cliente1 = new Cliente(
                    "Hector", "Rua Recanto, 120", "(55) 99999-1111");
            Cliente cliente2 = new Cliente(
                    "Retardado", "Av. Recanto, 450", "(55) 98888-2222");

            Prato executivo = new PratoExecutivo("Frango grelhado", 28.00, 25);
            Prato combo = new Combo("Combo hambúrguer", 40.00, 20);
            Prato especial = new PratoEspecial("Salmão especial", 55.00, 35, 18.00);

            Pedido pedido1 = new Pedido(cliente1);
            pedido1.adicionarPrato(executivo);
            pedido1.adicionarPrato(combo);

            Pedido pedido2 = new Pedido(cliente2);
            pedido2.adicionarPrato(especial);
            pedido2.adicionarPrato(new PratoExecutivo("Salada", 19.50, 10));

            ControlePedidos controle = new ControlePedidos();
            controle.adicionar(pedido1);
            controle.adicionar(pedido2);

            try {
                Prato pratoInvalido = new PratoExecutivo("Prato inválido", -10.00, 15);
                pedido1.adicionarPrato(pratoInvalido);
            } catch (PrecoBaseInvalidoException e) {
                System.out.println("Erro tratado: " + e.getMessage());
            }

            GeradorRelatorio relatorioConsole =
                    new GeradorRelatorio(controle, new SaidaConsole());
            relatorioConsole.gerarRelatorioDoDia();

            System.out.println("\n--- Também salvando o relatório em arquivo ---");
            GeradorRelatorio relatorioArquivo =
                    new GeradorRelatorio(controle, new SaidaArquivo("relatorio_saborexpress.txt"));
            relatorioArquivo.gerarRelatorioDoDia();

        } catch (PrecoBaseInvalidoException e) {
            System.out.println("Não foi possível cadastrar o prato: " + e.getMessage());
        }
    }
}
