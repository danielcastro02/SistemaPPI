package View.Gasto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Controle.ContGast;
import Modelo.Gasto;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.Component;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.gui.component.Table;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaGasUpdate extends Window {

    private static GUIScreen gui;

    public TelaGasUpdate(GUIScreen gui, int aux) throws ClassNotFoundException, SQLException {
        super("Update");
        this.gui = gui;
        init(aux);
    }

    public void init(int PrimReg) throws ClassNotFoundException, SQLException {
        ContGast cogas = new ContGast();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(5, "Lista de Gastos");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[5];
        linha[0] = new Label("        ");
        linha[1] = new Label("      ");
        linha[2] = new Label("                Pagina:"+(PrimReg+1));
        linha[3] = new Label("       ");
        linha[4] = new Label("       ");
        tbl.addRow(linha);
        
        linha[0] = new Label("Codigo  ");
        linha[1] = new Label("CodBus");
        linha[2] = new Label("Descrição                                  ");
        linha[3] = new Label("Valor  ");
        linha[4] = new Label("Excluir");
        tbl.addRow(linha);

        linha[0] = new Label("---------");
        linha[1] = new Label("-------");
        linha[2] = new Label("-------------------------------------------");
        linha[3] = new Label("---------");
        linha[4] = new Label("---------");
        tbl.addRow(linha);

        int totalReg = cogas.totalReg();
        int RegPag = 12;
        Gasto[] ArrayCli = new Gasto[totalReg];

        try {
            int cont = 0;
            for (Gasto cli : cogas.selecGas()) {
                ArrayCli[cont] = cli;
                cont++;
            }

            int numaux = cont;
            for (int i = PrimReg * RegPag; i < numaux && i <= RegPag * (PrimReg + 1); i++) {
                final int a = ArrayCli[i].getCod();
                linha[0] = new Label("" + ArrayCli[i].getCod());
                linha[1] = new Label(""+ArrayCli[i].getCodbus());
                linha[2] = new Label(ArrayCli[i].getDesc());
                linha[3] = new Label("R$" + ArrayCli[i].getVal());
                linha[4] = new Button("Excluir", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            cogas.delGas(a);
                            MessageBox.showMessageBox(gui, "Info", "Gasto Deletado");
                            close();
                            gui.showWindow(new TelaGasUpdate(gui, PrimReg));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SQLException ex) {
                            Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });
                tbl.addRow(linha);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        linha[0] = new  Button("Anterior", new Action() {
            @Override
            public void doAction() {
                try {
                    if(PrimReg > 0 ){
                    close();
                    gui.showWindow(new TelaGasUpdate(gui, (PrimReg - 1)));
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "Você está na primeira página!");;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        linha[1] = new Label("-------");
        linha[2] = new Label("-------------------------------------------");
        linha[3] = new Label("---------");
        linha[4] = new Button("Proximo", new Action() {
            @Override
            public void doAction() {
                try {
                    close();
                    gui.showWindow(new TelaGasUpdate(gui, (PrimReg + 1)));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaGasUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tbl.addRow(linha);
        addComponent(tbl);
        

        Button sair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }

        });
        addComponent(sair);
    }

}
