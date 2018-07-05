/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionario;

import Controle.ContFuncion;
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

/**
 *
 * @author Daniel Castro
 */
public class TelaFunInserir extends Window{
    
    private static GUIScreen gui;
    public TelaFunInserir(GUIScreen gui) {
        super("Inserir");
        this.gui = gui;
        init();
    }
    
    public void init(){
        ContFuncion cofun = new ContFuncion();
        
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.HORISONTAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Inserção de Contato  ");
        addComponent(label01);
        
        Label lblNome = new Label("Nome:");
        TextBox txtNome = new TextBox();
        Label lblCPF = new Label("CPF:");
        TextBox txtCPF = new TextBox();
        Label lblSal = new Label("Salário");
        TextBox txtSal = new TextBox();
        

        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblCPF);
        addComponent(txtCPF);
        addComponent(lblSal);
        addComponent(txtSal);

        Button botaoSalvar = new Button("Inserir", new Action() {
            @Override
            public void doAction() {
                try {
                    if(cofun.insFunc(txtNome.getText(), txtCPF.getText(), Double.parseDouble(txtSal.getText().replace(",", ".")))){
                        MessageBox.showMessageBox(gui, "Info", "Funcionario Inserido");
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "ERRO!");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaFunInserir.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaFunInserir.class.getName()).log(Level.SEVERE, null, ex);
                }
                close();
            }

        });
        addComponent(botaoSalvar);
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(botaoSair);
    }
    
    
}
