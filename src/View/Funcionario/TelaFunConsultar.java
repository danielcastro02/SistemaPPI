/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionario;

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
 * @author Daniel Castro
 */
public class TelaFunConsultar extends Window {

    private static GUIScreen gui;

    public TelaFunConsultar(GUIScreen gui) {
        super("Tipo de Consulta");
        this.gui = gui;
        init();
    }

    public void init() {
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Opções");
        addComponent(label01);
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        Button codigo = new Button("Codigo", new Action() {
            @Override
            public void doAction() {
                removeComponent(botaoSair);
                Label label02 = new Label("Insira o Codigo");
                addComponent(label02);
                TextBox txt1 = new TextBox();
                addComponent(txt1);
                Button sql = new Button("Enviar", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            gui.showWindow(new TelaConsulta(gui, Integer.parseInt(txt1.getText()), 0));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaFunConsultar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        close();
                    }

                });
                addComponent(sql);
                Button sair = new Button("Cancelar", new Action() {
                    @Override
                    public void doAction() {
                        close();
                        gui.showWindow(new TelaFunConsultar(gui));
                    }
                });

                addComponent(sair);
            }
        });
        addComponent(codigo);

        Button nome = new Button("Nome", new Action() {
            @Override
            public void doAction() {
                removeComponent(botaoSair);
                Label label02 = new Label("Insira o Nome");
                addComponent(label02);
                TextBox txt1 = new TextBox();
                addComponent(txt1);
                Button sql = new Button("Enviar", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            gui.showWindow(new TelaConsulta(gui, txt1.getText(), 0));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaFunConsultar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        close();
                    }
                });
                addComponent(sql);

                Button sair = new Button("Cancelar", new Action() {
                    @Override
                    public void doAction() {
                        close();
                        gui.showWindow(new TelaFunConsultar(gui));
                    }
                });

                addComponent(sair);
            }
        }
        );

        addComponent(nome);

        addComponent(botaoSair);
    }
}
