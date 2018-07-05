package View.Gasto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Controle.ContFuncion;
import Controle.ContGast;
import Controle.ContOni;
import Modelo.Onibus;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Panel.Orientation;
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaGasInserir extends Window {

    private static GUIScreen gui;

    public TelaGasInserir(GUIScreen gui, String codigo) {
        super("Inserir");
        this.gui = gui;
        init(codigo);
    }

    public void init(String codigo) {
        ContGast cogas = new ContGast();

        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.HORISONTAL);
        painel01.setBetweenComponentsPadding(1);

        Label lblNome = new Label("Codigo do Onibus:");
        TextBox txtNome = new TextBox(codigo);

        Button botaoconsulta = new Button("Lista", new Action() {
            @Override
            public void doAction() {
                removeAllComponents();
                ContOni co = new ContOni();
                try {
                    for(Onibus oni : co.selectOni()){
                        Button bt = new Button(oni.getModelo(), new Action(){
                            @Override
                            public void doAction() {
                                String a = ""+oni.getCod();
                                close();
                                gui.showWindow(new TelaGasInserir(gui, a));
                            }
                            
                        });
                        addComponent(bt);
                    }
                    Button bt = new Button("Canecelar", new Action(){

                        @Override
                        public void doAction() {
                            close();
                            gui.showWindow(new TelaGasInserir(gui, ""));
                        }
                        
                    });
                    addComponent(bt);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaGasInserir.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaGasInserir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        Label lblCPF = new Label("Descrição:");
        TextBox txtbox = new TextBox("", 50);
        Label lblSal = new Label("Valor");
        TextBox txtSal = new TextBox();
        addComponent(lblNome);
        painel01.addComponent(txtNome);
        painel01.addComponent(botaoconsulta);
        addComponent(painel01);
        addComponent(lblCPF);
        addComponent(txtbox);
        addComponent(lblSal);
        addComponent(txtSal);

        Button botaoSalvar = new Button("Inserir", new Action() {
            @Override
            public void doAction() {
                try {
                    if (cogas.insGast(Integer.parseInt(txtNome.getText()), txtbox.getText(), Double.parseDouble(txtSal.getText().replace(",", ".")))) {
                        MessageBox.showMessageBox(gui, "Info", "Gasto Inserido");
                    } else {
                        MessageBox.showMessageBox(gui, "Info", "ERRO!");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaGasInserir.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaGasInserir.class.getName()).log(Level.SEVERE, null, ex);
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
