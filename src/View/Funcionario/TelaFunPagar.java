/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionario;

import Controle.ContCli;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luubi
 */
public class TelaFunPagar extends Window {

    private static GUIScreen gui;

    public TelaFunPagar(GUIScreen gui, String nome) {
        super("Registrar Pagamento");
        this.gui = gui;
        init(nome);
    }

    private void init(String nome) {
        ContCli cocli = new ContCli();

        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.HORISONTAL);
        painel01.setBetweenComponentsPadding(1);

        Label lblNome = new Label("Nome:");
        TextBox txtNome = new TextBox(nome);
        Button bt = new Button("Lista", new Action() {

            @Override
            public void doAction() {
                ContFuncion cf = new ContFuncion();
                try {
                    
                    removeAllComponents();
                    for(Funcionario fun : cf.selectFun()){
                        Button b = new Button(fun.getNome(), new Action(){

                            @Override
                            public void doAction() {
                                close();
                                gui.showWindow(new TelaFunPagar(gui, fun.getNome()));
                            }
                            
                        });
                        addComponent(b);
                    }
                    Button b = new Button("Cancelar", new Action() {

                        @Override
                        public void doAction() {
                            close();
                            gui.showWindow(new TelaFunPagar(gui, ""));
                        }

                    });
                    addComponent(b);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaFunPagar.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(TelaFunPagar.class.getName()).log(Level.SEVERE, null, ex);
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
