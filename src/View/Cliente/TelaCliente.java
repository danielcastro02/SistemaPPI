/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cliente;

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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaCliente extends Window {

    private static GUIScreen gui;

    public TelaCliente(GUIScreen gS) {
        super("BPM Bus Payment Manager");
        this.gui = gS;
        init();
    }

    private void init() {
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Menu de Cliente");
        addComponent(label01);

        Button inserir = new Button("Inserir", new Action() {

            @Override
            public void doAction() {
                gui.showWindow(new TelaCliInserir(gui));
            }

        });
        addComponent(inserir);

        Button update = new Button("Lista Geral", new Action() {

            @Override
            public void doAction() {
                try {
                    gui.showWindow(new TelaCliUpdate(gui, 0));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        addComponent(update);

        Button consultar = new Button("Consultar", new Action() {

            @Override
            public void doAction() {
                gui.showWindow(new TelaCliConsultar(gui));
            }

        });
        addComponent(consultar);

        Button Excluir = new Button("Registrar Pagamento", new Action() {

            @Override
            public void doAction() {
                gui.showWindow(new TelaCliPagar(gui, ""));
            }

        });
        addComponent(Excluir);

        Button relatorio = new Button("Relatorio", new Action() {

            @Override
            public void doAction() {
                PDF pd = new PDF();
                try {
                    pd.realtorioCli();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DocumentException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }

                MessageBox.showMessageBox(gui, "Info", "Relatorio Gerado");
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
