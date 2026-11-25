
package controll;

import View.CadastroView;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CadastroController {

    private CadastroView view;

    private ArrayList<Aluguel> alugueis;
    private Set<String> boxesOcupados;
    private ArrayList<String> listaExibicao;

    public CadastroController(CadastroView view) {
        this.view = view;

        alugueis = new ArrayList<>();
        boxesOcupados = new HashSet<>();
        listaExibicao = new ArrayList<>();

        this.view.getBtnSalvar().addActionListener(e -> salvarAluguel());

        atualizarDisponibilidade();
    }

    private void salvarAluguel() {

        try {
            // Obtendo os dados da View
            String nome = view.getNome().trim();
            String cpf = view.getCpf().trim();
            String telefone = view.getTelefone().trim();
            String email = view.getEmail().trim();

            String numeroBox = view.getNumeroBox();
            String tamanho = view.getTamanho();
            String tipoBox = view.getTipoBox();

            String temperaturaTexto = view.getTemperatura().trim();
            String dataTexto = view.getData().trim();

            // Validando os campos
            if (nome.isEmpty() || cpf.isEmpty() ||
                    telefone.isEmpty() || email.isEmpty() ||
                    dataTexto.isEmpty()) {

                view.exibirErro("Preencha todos os campos obrigatórios!");
                return;
            }

            // Verificando disponibilidade
            if (boxesOcupados.contains(numeroBox)) {
                view.exibirErro("Este box já está alugado!");
                return;
            }

            // Convertendo a data
            DateTimeFormatter formato =
                    DateTimeFormatter.ofPattern("dd/MM/uuuu")
                            .withResolverStyle(ResolverStyle.STRICT);

            LocalDate dataLimite = LocalDate.parse(dataTexto, formato);

            // Criando o cliente
            Cliente cliente = new Cliente(
                    nome, cpf, telefone, email
            );

            // Criando o tipo de box
            Box box;

            if (tipoBox.equals("Comum")) {

                box = new BoxComun(numeroBox, tamanho);

            } else {

                if (temperaturaTexto.isEmpty()) {
                    view.exibirErro("Informe a temperatura do box climatizado!");
                    return;
                }

                double temperatura = Double.parseDouble(
                        temperaturaTexto.replace(",", ".")
                );

                box = new BoxClimatizado(
                        numeroBox, tamanho, temperatura
                );
            }

            // Criando o aluguel
            Aluguel aluguel = new Aluguel(
                    cliente, box, dataLimite
            );

            // Registrando o aluguel
            alugueis.add(aluguel);
            boxesOcupados.add(numeroBox);

            listaExibicao.add(
                    "Cliente: " + nome +
                            "\nCPF: " + cpf +
                            "\nTelefone: " + telefone +
                            "\nE-mail: " + email +
                            "\nBox: " + numeroBox +
                            "\nTamanho: " + tamanho +
                            "\nTipo: " + tipoBox +
                            (tipoBox.equals("Climatizado")
                                    ? "\nTemperatura: " + temperaturaTexto + " °C"
                                    : "") +
                            "\nData limite: " + dataTexto +
                            "\n----------------------------------"
            );

            // Atualizando a tela
            view.exibirAlugueis(
                    String.join("\n", listaExibicao)
            );

            atualizarDisponibilidade();

            view.limparCampos();

        } catch (NumberFormatException e) {

            view.exibirErro("Temperatura inválida!");

        } catch (java.time.format.DateTimeParseException e) {

            view.exibirErro("Data inválida! Utilize dd/MM/aaaa.");

        } catch (Exception e) {

            view.exibirErro("Erro ao realizar cadastro: " + e.getMessage());
        }
    }

    private void atualizarDisponibilidade() {

        StringBuilder texto = new StringBuilder();

        for (int i = 101; i <= 110; i++) {

            String numero = String.valueOf(i);

            if (boxesOcupados.contains(numero)) {
                texto.append("Box ").append(numero)
                        .append(" - OCUPADO\n");
            } else {
                texto.append("Box ").append(numero)
                        .append(" - DISPONÍVEL\n");
            }
        }

        view.atualizarDisponibilidade(texto.toString());
    }
}
