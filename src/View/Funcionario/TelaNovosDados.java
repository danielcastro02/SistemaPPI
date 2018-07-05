package View.Funcionario;

import Controle.ContFuncion;
import Modelo.Funcionario;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelaNovosDados extends Window {

    private final GUIScreen gui;
    private int cod;

    public TelaNovosDados(GUIScreen gui, int cod) throws ClassNotFoundException, SQLException {
        super("Insira os novos dados:");
        this.gui = gui;
        this.cod = cod;
        init();
    }

    private void init() throws ClassNotFoundException, SQLException {
        ContFuncion cofun = new ContFuncion();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label1 = new Label("Nome");
        addComponent(label1);
        Funcionario fun = cofun.selecFun(cod);
        TextBox nome = new TextBox(fun.getNome());
        addComponent(nome);
        Label label2 = new Label("CPF");
        addComponent(label2);
        TextBox cpf = new TextBox(fun.getCpf());
        addComponent(cpf);
        Label label3 = new Label("Salario");
        addComponent(label3);
        TextBox sal = new TextBox(""+fun.getSal());
        addComponent(sal);
        
        Button enviar = new Button("Confirmar", new Action(){
            @Override
            public void doAction() {
                try {
                    cofun.updFunc(cod, nome.getText(), cpf.getText(), Double.parseDouble(sal.getText()));
                    MessageBox.showMessageBox(gui, "Info", "Funcionario Atualizado");
                } catch (ClassNotFoundException ex) {
                    MessageBox.showMessageBox(gui, "Info", "Erro");
                    Logger.getLogger(TelaNovosDados.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaNovosDados.class.getName()).log(Level.SEVERE, null, ex);
                }
                close();
            }
            
        });
        addComponent(enviar);
        
        Button sair = new Button("Cancelar", new Action(){
            @Override
            public void doAction() {
                close();
            }
            
        });
        addComponent(sair);
    }

}
