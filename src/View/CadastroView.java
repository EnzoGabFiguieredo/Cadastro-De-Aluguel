package View;

import javax.swing.*;
import java.awt.*;

/**
 * Tela de cadastro do Self Storage (camada View do MVC).
 * Só desenha a tela e entrega o que foi digitado.
 * Não faz cálculos e não cria objetos do model.
 */
public class CadastroView extends JFrame {

    // Cores da tela
    private static final Color COR_FUNDO = new Color(245, 247, 250);
    private static final Color COR_TITULO = new Color(25, 60, 110);
    private static final Color COR_BOTAO = new Color(30, 130, 76);

    // Campos de texto
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtTemperatura;
    private JTextField txtData;

    // Listas de escolha
    private JComboBox<String> cbNumBox;
    private JComboBox<String> cbTamanho;
    private JComboBox<String> cbTipoBox;

    // Botão e áreas de texto
    private JButton btnSalvar;
    private JTextArea areaDisponibilidade;
    private JTextArea areaLista;

    public CadastroView() {
        setTitle("Self Storage - Cadastro de Aluguel");
        setSize(820, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COR_FUNDO);

        // Título
        JLabel titulo = new JLabel("Self Storage - Cadastro de Aluguel", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        titulo.setForeground(COR_TITULO);
        titulo.setBorder(BorderFactory.createEmptyBorder(12, 0, 0, 0));
        add(titulo, BorderLayout.NORTH);

        // Instancia os componentes
        txtNome = new JTextField(20);
        txtCpf = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtEmail = new JTextField(20);
        txtTemperatura = new JTextField(20);
        txtData = new JTextField(20);

        cbNumBox = new JComboBox<>(new String[]{
                "101", "102", "103", "104", "105",
                "106", "107", "108", "109", "110"});
        cbTamanho = new JComboBox<>(new String[]{"Pequeno", "Médio", "Grande"});
        cbTipoBox = new JComboBox<>(new String[]{"Comum", "Climatizado"});

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(COR_BOTAO);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalvar.setFocusPainted(false);

        areaDisponibilidade = new JTextArea(4, 20);
        areaDisponibilidade.setEditable(false);
        areaDisponibilidade.setLineWrap(true);
        areaDisponibilidade.setWrapStyleWord(true);

        areaLista = new JTextArea(10, 20);
        areaLista.setEditable(false);

        // Temperatura só faz sentido para box climatizado (comportamento visual)
        txtTemperatura.setEnabled(false);
        cbTipoBox.addActionListener(e ->
                txtTemperatura.setEnabled("Climatizado".equals(cbTipoBox.getSelectedItem())));

        // Painel do formulário
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(COR_FUNDO);
        form.setBorder(BorderFactory.createTitledBorder("Dados do cliente e do box"));
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(4, 8, 4, 8);
        g.anchor = GridBagConstraints.WEST;
        g.fill = GridBagConstraints.HORIZONTAL;

        int linha = 0;
        linha = addLinha(form, g, linha, "Nome:", txtNome);
        linha = addLinha(form, g, linha, "CPF:", txtCpf);
        linha = addLinha(form, g, linha, "Telefone:", txtTelefone);
        linha = addLinha(form, g, linha, "E-mail:", txtEmail);
        linha = addLinha(form, g, linha, "Número do box:", cbNumBox);
        linha = addLinha(form, g, linha, "Tamanho:", cbTamanho);
        linha = addLinha(form, g, linha, "Tipo de box:", cbTipoBox);
        linha = addLinha(form, g, linha, "Temperatura (°C):", txtTemperatura);
        linha = addLinha(form, g, linha, "Data limite (dd/MM/aaaa):", txtData);

        g.gridx = 0;
        g.gridy = linha;
        g.gridwidth = 2;
        g.fill = GridBagConstraints.NONE;
        g.anchor = GridBagConstraints.CENTER;
        form.add(btnSalvar, g);

        // Painel lateral: disponibilidade dos boxes
        JScrollPane scrollDisp = new JScrollPane(areaDisponibilidade);
        scrollDisp.setBorder(BorderFactory.createTitledBorder("Disponibilidade dos boxes"));

        JPanel topo = new JPanel(new BorderLayout(10, 10));
        topo.setBackground(COR_FUNDO);
        topo.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        topo.add(form, BorderLayout.CENTER);
        topo.add(scrollDisp, BorderLayout.EAST);
        scrollDisp.setPreferredSize(new Dimension(260, 200));
        add(topo, BorderLayout.CENTER);

        // Lista de aluguéis registrados
        JScrollPane scrollLista = new JScrollPane(areaLista);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Aluguéis registrados"));
        scrollLista.setPreferredSize(new Dimension(800, 190));
        JPanel baixo = new JPanel(new BorderLayout());
        baixo.setBackground(COR_FUNDO);
        baixo.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        baixo.add(scrollLista, BorderLayout.CENTER);
        add(baixo, BorderLayout.SOUTH);
    }

    private int addLinha(JPanel painel, GridBagConstraints g, int linha, String rotulo, JComponent campo) {
        g.gridwidth = 1;
        g.gridy = linha;
        g.gridx = 0;
        g.weightx = 0;
        painel.add(new JLabel(rotulo), g);
        g.gridx = 1;
        g.weightx = 1;
        painel.add(campo, g);
        return linha + 1;
    }

    // ===== Métodos de ação / tela =====

    public void exibirAlugueis(String texto) {
        areaLista.setText(texto);
    }

    public void atualizarDisponibilidade(String texto) {
        areaDisponibilidade.setText(texto);
    }

    public void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        txtTemperatura.setText("");
        txtData.setText("");
        cbNumBox.setSelectedIndex(0);
        cbTamanho.setSelectedIndex(0);
        cbTipoBox.setSelectedIndex(0);
    }

    public void exibirErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    // ===== Métodos de leitura (usados pelo Controller) =====

    public String getNome() {
        return txtNome.getText();
    }

    public String getCpf() {
        return txtCpf.getText();
    }

    public String getTelefone() {
        return txtTelefone.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getNumeroBox() {
        return (String) cbNumBox.getSelectedItem();
    }

    public String getTamanho() {
        return (String) cbTamanho.getSelectedItem();
    }

    public String getTipoBox() {
        return (String) cbTipoBox.getSelectedItem();
    }

    public String getTemperatura() {
        return txtTemperatura.getText();
    }

    public String getData() {
        return txtData.getText();
    }

    public JButton getBtnSalvar() {
        return btnSalvar;
    }
}
