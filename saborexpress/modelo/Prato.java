package saborexpress.modelo;

import saborexpress.excecao.PrecoBaseInvalidoException;

public abstract class Prato {
    private final String nome;
    private final double precoBase;
    private final int tempoPreparo;

    public Prato(String nome, double precoBase, int tempoPreparo)
            throws PrecoBaseInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do prato é obrigatório.");
        }
        if (precoBase < 0) {
            throw new PrecoBaseInvalidoException("O preço base não pode ser negativo.");
        }
        if (tempoPreparo <= 0) {
            throw new IllegalArgumentException("O tempo de preparo deve ser maior que zero.");
        }
        this.nome = nome;
        this.precoBase = precoBase;
        this.tempoPreparo = tempoPreparo;
    }

    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }
    public int getTempoPreparo() { return tempoPreparo; }

    public abstract double calcularPreco();

    public String getTipo() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return nome + " (" + getTipo() + ") - R$ "
                + String.format("%.2f", calcularPreco())
                + " | preparo: " + tempoPreparo + " min";
    }
}
