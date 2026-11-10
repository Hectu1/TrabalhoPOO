package saborexpress.modelo;

import saborexpress.excecao.PrecoBaseInvalidoException;

public class Combo extends Prato {
    private static final double DESCONTO = 0.15;

    public Combo(String nome, double precoBase, int tempoPreparo)
            throws PrecoBaseInvalidoException {
        super(nome, precoBase, tempoPreparo);
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() * (1 - DESCONTO);
    }
}
