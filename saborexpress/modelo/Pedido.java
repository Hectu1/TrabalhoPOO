package saborexpress.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private static int proximoId = 1;

    private final int id;
    private final Cliente cliente;
    private final LocalDate data;
    private final List<Prato> pratos;

    public Pedido(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Um pedido precisa ter um cliente.");
        }
        this.id = proximoId++;
        this.cliente = cliente;
        this.data = LocalDate.now();
        this.pratos = new ArrayList<>();
    }

    public void adicionarPrato(Prato prato) {
        if (prato == null) {
            throw new IllegalArgumentException("Não é possível adicionar um prato nulo.");
        }
        pratos.add(prato);
    }

    public double calcularTotal() {
        double total = 0;
        for (Prato prato : pratos) {
            total += prato.calcularPreco();
        }
        return total;
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public LocalDate getData() { return data; }
    public List<Prato> getPratos() {
        return Collections.unmodifiableList(pratos);
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - " + cliente.getNome()
                + " - " + data + " - Total: R$ "
                + String.format("%.2f", calcularTotal());
    }
}
