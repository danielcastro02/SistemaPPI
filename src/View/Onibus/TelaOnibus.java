/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Onibus;

import Controle.PDF;
import View.Cliente.TelaCliente;
import View.TelaPrincipal;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Panel;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaOnibus extends Window {
    private static GUIScreen gui;
    
    
    public TelaOnibus(GUIScreen gS) {
        super("Menu Ônibus");
        this.gui = gS;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        
        Button inserir = new Button("Inserir", new Action(){

            @Override
            public void doAction() {
                gui.showWindow(new TelaOniInserir(gui));
            }
            
        });
        addComponent(inserir);
        
        Button update = new Button("Lista Geral", new Action(){

            @Override
            public void doAction() {
                try {
                    gui.showWindow(new TelaOniUpdate(gui, 0));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaOnibus.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        });
        addComponent(update);
        
        Button relatorio = new Button("Relatório", new Action() {
            @Override
            public void doAction() {
                PDF pd = new PDF();
                try {
                    pd.realtorioOni();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaOnibus.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaOnibus.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(TelaOnibus.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaOnibus.class.getName()).log(Level.SEVERE, null, ex);
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
