/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cliente;

import Controle.ContCli;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaCliInserir extends Window{
    
    private static GUIScreen gui;
    
    //Construtor que define o titulo da janela
    public TelaCliInserir(GUIScreen gui) {
        super("Inserir");
        this.gui = gui;
        init();
    }
    
    public void init(){
        ContCli cocli = new ContCli();
        
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.HORISONTAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Inserção de Cliente ");
        addComponent(label01);
        
        //Espaço para inserção do novo cliente
        Label lblNome = new Label("Nome:");
        TextBox txtNome = new TextBox();
        Label lblCPF = new Label("CPF:");
        TextBox txtCPF = new TextBox();
        

        addComponent(lblNome);
        addComponent(txtNome);
        addComponent(lblCPF);
        addComponent(txtCPF);
        
        //Envia os dados para o metodo que insere no banco e retorna a mensagem de sucesso ou erro baseado no retorno do metodo de inserir no banco
        Button botaoSalvar = new Button("Inserir", new Action() {
            @Override
            public void doAction() {
                try {
                    if(cocli.insCli(txtNome.getText(), txtCPF.getText())){
                        MessageBox.showMessageBox(gui, "Info", "Cliente Inserido");
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "ERRO!");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliInserir.class.getName()).log(Level.SEVERE, null, ex);
                }
                close();
            }

        });
        addComponent(botaoSalvar);
        
        //fecha a janela
        Button botaoSair = new Button("Cancelar", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(botaoSair);
    }
    
    
}
