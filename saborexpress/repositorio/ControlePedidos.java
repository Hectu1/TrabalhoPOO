package saborexpress.repositorio;

import saborexpress.modelo.Pedido;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ControlePedidos {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void adicionar(Pedido pedido) {
        if (pedido == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
        pedidos.add(pedido);
    }

    public List<Pedido> listarTodos() {
        return Collections.unmodifiableList(pedidos);
    }
}
