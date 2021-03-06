/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cliente;

import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaCliPagar extends Window {

    private static GUIScreen gui;

    //Construtor que define o titul da tela
    public TelaCliPagar(GUIScreen gui, String s) {
        super("Registrar Pagamento");
        this.gui = gui;
        init(s);
    }

    private void init(String s) {

        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.HORISONTAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Inserção de Contato  ");
        addComponent(label01);

        //Local para inserir o nome de quem ira pagar
        Label lblNome = new Label("Nome:");
        TextBox txtNome = new TextBox(s);

        //ComboBox na gambiarra para listar os clientes
        Button bt = new Button("Lista", new Action() {

            @Override
            public void doAction() {
                close();
                try {
                    gui.showWindow(new TelaCliUpdate(gui, 0 , ""));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliPagar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliPagar.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        addComponent(lblNome);
        painel01.addComponent(txtNome);
        painel01.addComponent(bt);
        addComponent(painel01);

        Button botaoSalvar = new Button("Pesquisar", new Action() {
            @Override
            public void doAction() {
                try {
                    gui.showWindow(new TelaConsulta(gui, txtNome.getText(), "Ola"));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliPagar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliPagar.class.getName()).log(Level.SEVERE, null, ex);
                }
                close();
            }

        });
        addComponent(botaoSalvar);
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
                gui.showWindow(new TelaCliente(gui));
            }
        });
        addComponent(botaoSair);
    }

}
