package saborexpress.servico;
import java.util.List;
import saborexpress.modelo.Pedido;
import saborexpress.repositorio.ControlePedidos;
import saborexpress.saida.SaidaRelatorio;

public class GeradorRelatorio {
    private final ControlePedidos controle;
    private final SaidaRelatorio saida;

    public GeradorRelatorio(ControlePedidos controle, SaidaRelatorio saida) {
        if (controle == null || saida == null) {
            throw new IllegalArgumentException("As dependências do relatório são obrigatórias.");
        }
        this.controle = controle;
        this.saida = saida;
    }

    public void gerarRelatorioDoDia() {
        List<Pedido> pedidos = controle.listarTodos();
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("========== SABOREXPRESS - FECHAMENTO DO DIA ==========\n");
        relatorio.append("Quantidade de pedidos: ").append(pedidos.size()).append("\n\n");

        double faturamento = 0;
        Pedido pedidoMaisCaro = null;

        for (Pedido pedido : pedidos) {
            relatorio.append(pedido).append("\n");
            for (int i = 0; i < pedido.getPratos().size(); i++) {
                relatorio.append("  - ").append(pedido.getPratos().get(i)).append("\n");
            }
            faturamento += pedido.calcularTotal();

            if (pedidoMaisCaro == null
                    || pedido.calcularTotal() > pedidoMaisCaro.calcularTotal()) {
                pedidoMaisCaro = pedido;
            }
            relatorio.append("\n");
        }

        relatorio.append(String.format("Faturamento total: R$ %.2f%n", faturamento));
        if (pedidoMaisCaro != null) {
            relatorio.append("Pedido mais caro: #")
                    .append(pedidoMaisCaro.getId())
                    .append(" - ").append(pedidoMaisCaro.getCliente().getNome())
                    .append(String.format(" (R$ %.2f)%n", pedidoMaisCaro.calcularTotal()));
        } else {
            relatorio.append("Pedido mais caro: não há pedidos cadastrados.\n");
        }
        relatorio.append("======================================================");

        saida.exibir(relatorio.toString());
    }
}
