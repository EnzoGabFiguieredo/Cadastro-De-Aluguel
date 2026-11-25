import View.CadastroView;
import controll.CadastroController;

public class Cadastro {
    public static void main(String[] args) {
        CadastroView view = new CadastroView();
        CadastroController controller = new CadastroController(view);

        view.setVisible(true);
    }
}