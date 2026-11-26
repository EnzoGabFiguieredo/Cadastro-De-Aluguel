package model;

public class Box {
    private int numero;
    private String tamanho;
    protected double valorMensal;
    private boolean ocupado;

    public Box(int numero, String tamanho, double valorMensal, boolean ocupado) {
        this.numero = numero;
        this.tamanho = tamanho;
        this.valorMensal = valorMensal;
        this.ocupado = ocupado;
    }

    public int getNumero() {
        return numero;
    }

    public String getTamanho() {
        return tamanho;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public double calcularValorMensal() {
        return valorMensal;
    }

    @Override
    public String toString() {
        return "Box " + numero + " | Tamanho: " + tamanho + " | Valor: R$ " + calcularValorMensal() + " | Ocupado: " + ocupado;
    }
}
