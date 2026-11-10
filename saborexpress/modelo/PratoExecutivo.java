package saborexpress.modelo;

import saborexpress.excecao.PrecoBaseInvalidoException;

public class PratoExecutivo extends Prato {
    public PratoExecutivo(String nome, double precoBase, int tempoPreparo)
            throws PrecoBaseInvalidoException {
        super(nome, precoBase, tempoPreparo);
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase();
    }
}
