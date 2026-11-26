
package model;

public class BoxClimatizado extends Box {

    private double temperatura;
    private static final double TAXA_CLIMA = 80.0;
    
    public BoxClimatizado(String numero, String tamanho, double temperatura) {
        super(numero, tamanho);
        this.temperatura = temperatura;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

       @Override
    public double calcularValorMensal() {
        return super.calcularValorMensal() + TAXA_CLIMA;
    }

    @Override
    public String toString() {
        return "Box Climatizado" +
                " | Número: " + getNumero() +
                " | Tamanho: " + getTamanho() +
                " | Temperatura: " + temperatura + " °C";
                " | Valor mensal: R$ " + calcularValorMensal();
    }
}
