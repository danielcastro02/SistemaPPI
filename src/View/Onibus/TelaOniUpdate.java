/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Onibus;

import Controle.ContOni;
import Modelo.Onibus;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dcastro
 */
public class TelaOniUpdate extends Window{
    
    GUIScreen gui;
    
    public TelaOniUpdate(GUIScreen gui, int aux) throws ClassNotFoundException {
        super("Relação Geral");
        this.gui = gui;
        init(aux);
    }

    public void init(int PrimReg) throws ClassNotFoundException {
        ContOni coni = new ContOni();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(4, "Lista de Onibus");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[4];
        linha[0] = new Label("        ");
        linha[1] = new Label("        Pagina:"+(PrimReg+1));
        linha[2] = new Label("       ");
        linha[3] = new Label("         ");
        tbl.addRow(linha);
        linha[0] = new Label("Codigo  ");
        linha[1] = new Label("Modelo                       ");
        linha[2] = new Label("Gasto     ");
        linha[3] = new Label("Excluir");
        tbl.addRow(linha);

        linha[0] = new Label("-------");
        linha[1] = new Label("-----------------------------");
        linha[2] = new Label("-----------");
        linha[3] = new Label("---------");
        tbl.addRow(linha);

        int totalReg = coni.totalReg();
        int RegPag = 12;
        Onibus[] ArrayOni = new Onibus[totalReg];

        try {
            int cont = 0;
            for (Onibus oni : coni.selectOni()) {
                ArrayOni[cont] = oni;
                cont++;
            }

            int numaux = cont;
            for (int i = PrimReg * RegPag; i < numaux && i <= RegPag * (PrimReg + 1); i++) {
                final int a = ArrayOni[i].getCod();
                linha[0] = new Label("" + ArrayOni[i].getCod());
                linha[1] = new Label(ArrayOni[i].getModelo());
                linha[2] = new Label("R$" + ArrayOni[i].getGasto());
                linha[3] = new Button("Excluir", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            coni.delOni(a);
                            MessageBox.showMessageBox(gui, "Info", "Onibus Deletado");
                            close();
                            gui.showWindow(new TelaOniUpdate(gui, PrimReg));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaOniUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });
                tbl.addRow(linha);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaOniUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        linha[0] = new  Button("Anterior", new Action() {
            @Override
            public void doAction() {
                try {
                    if(PrimReg > 0 ){
                    close();
                    gui.showWindow(new TelaOniUpdate(gui, (PrimReg - 1)));
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "Você está na primeira página!");;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaOniUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        linha[1] = new Label("-----------------------------");
        linha[2] = new Label("-----------");
        linha[3] = new Button("Proximo", new Action() {
            @Override
            public void doAction() {
                try {
                    close();
                    gui.showWindow(new TelaOniUpdate(gui, (PrimReg + 1)));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaOniUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
