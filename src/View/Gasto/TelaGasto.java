/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Gasto;

import Controle.ContOni;
import Controle.PDF;
import View.TelaPrincipal;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaGasto extends Window {

    private static GUIScreen gui;

    public TelaGasto(GUIScreen gS) {
        super("Tela Principal");
        this.gui = gS;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Menu de Gastos");
        addComponent(label01);

        Button inserir = new Button("Inserir", new Action() {

            @Override
            public void doAction() {
                ContOni co = new ContOni();
                if(co.totalReg()<1){
                    MessageBox.showMessageBox(gui, "Info", "Nenhum ônibus cadastrado, função indisponivel");
                }else{
                gui.showWindow(new TelaGasInserir(gui, ""));
                }
            }

        });
        addComponent(inserir);

        Button update = new Button("Lista Geral", new Action() {

            @Override
            public void doAction() {
                try {
                    gui.showWindow(new TelaGasUpdate(gui, 0));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaGasto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        addComponent(update);
//        
//        Button Excluir = new Button("Excluir", new Action(){
//
//            @Override
//            public void doAction() {
//                gui.showWindow(new TelaExcluirDividas(gui));
//            }
//            
//        });
//        addComponent(Excluir);
//        
        Button relatorio = new Button("Relatorio", new Action() {

            @Override
            public void doAction() {
                PDF pd = new PDF();
                try {
                    pd.realtorioGas();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaGasto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaGasto.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(TelaGasto.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }

        });
        addComponent(relatorio);

        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
                gui.showWindow(new TelaPrincipal(gui));
                
            }
        });
        addComponent(botaoSair);
    }
}
