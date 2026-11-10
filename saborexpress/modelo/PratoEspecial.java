package saborexpress.modelo;

import saborexpress.excecao.PrecoBaseInvalidoException;

public class PratoEspecial extends Prato {
    private final double custoIngredientePremium;

    public PratoEspecial(String nome, double precoBase, int tempoPreparo,
                         double custoIngredientePremium)
            throws PrecoBaseInvalidoException {
        super(nome, precoBase, tempoPreparo);
        if (custoIngredientePremium < 0) {
            throw new IllegalArgumentException("O custo do ingrediente premium não pode ser negativo.");
        }
        this.custoIngredientePremium = custoIngredientePremium;
    }

    public double getCustoIngredientePremium() {
        return custoIngredientePremium;
    }

    @Override
    public double calcularPreco() {
        return getPrecoBase() + custoIngredientePremium;
    }
}
