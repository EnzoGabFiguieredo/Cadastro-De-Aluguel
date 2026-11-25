
package model;

import java.time.LocalDate;

public class Aluguel {

    private Cliente cliente;
    private Box box;
    private LocalDate dataLimite;

    public Aluguel(Cliente cliente, Box box, LocalDate dataLimite) {
        this.cliente = cliente;
        this.box = box;
        this.dataLimite = dataLimite;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    @Override
    public String toString() {
        return "Aluguel{" +
                "cliente=" + cliente +
                ", box=" + box +
                ", dataLimite=" + dataLimite +
                '}';
    }
}
