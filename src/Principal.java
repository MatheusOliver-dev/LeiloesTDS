
import VIEW.cadastroVIEW;
import VIEW.vendasVIEW;

public class Principal {

    public static void main(String[] args) {
        cadastroVIEW telaPrincipal = new cadastroVIEW();
        telaPrincipal.setVisible(true);
        
        vendasVIEW vendas = new vendasVIEW();
        vendas.setVisible(true);
    }
}