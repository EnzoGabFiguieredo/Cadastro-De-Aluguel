package model;

public class BoxComun extends Box {

    public BoxComun(int numero, String tamanho, double valorMensal, boolean ocupado) {
        super(numero, tamanho, valorMensal, ocupado);
    }

    public BoxComun(String numeroBox, String tamanho) {
        super(numeroBox, tamanho);
    }

    @Override
    public String toString() {
        return super.toString() + " | Tipo: Comum";
    }
}