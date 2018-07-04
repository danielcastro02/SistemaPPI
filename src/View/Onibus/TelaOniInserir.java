/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Onibus;

import Controle.ContOni;
import View.Cliente.TelaCliInserir;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dcastro
 */
class TelaOniInserir extends Window {
    
    GUIScreen gui;
    
    public TelaOniInserir(GUIScreen gui) {
        super("Inserir Onibus");
        this.gui = gui;
        init();
    }
    
    private void init(){
        
        Label label01 = new Label("Modelo");
        TextBox modelo = new TextBox();
        addComponent(label01);
        addComponent(modelo);
        
        ContOni coni = new ContOni();
        Button botaoSalvar = new Button("Inserir", new Action() {
            @Override
            public void doAction() {
                try {
                    if(coni.insOni(modelo.getText())){
                        MessageBox.showMessageBox(gui, "Info", "Onibus Inserido");
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
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        addComponent(botaoSair);
    }
    
}
