package View.Cliente;

import Controle.ContCli;
import Modelo.Cliente;
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
        ContCli cocli = new ContCli();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label1 = new Label("Nome");
        addComponent(label1);
        Cliente cli = cocli.selecCli(cod);
        TextBox nome = new TextBox(cli.getNome(), 25);
        addComponent(nome);
        Label label2 = new Label("CPF");
        addComponent(label2);
        TextBox cpf = new TextBox(cli.getCpf(), 12);
        addComponent(cpf);
        
        Button enviar = new Button("Confirmar", new Action(){
            @Override
            public void doAction() {
                try {
                    cocli.updCli(cod, nome.getText(), cpf.getText(), cli.getDiv());
                    MessageBox.showMessageBox(gui, "Info", "Cliente Atualizado");
                } catch (ClassNotFoundException ex) {
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
                MessageBox.showMessageBox(gui, "Info", "Operação cancelada");
            }
            
        });
        addComponent(sair);
    }

}
