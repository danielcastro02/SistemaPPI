/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Cliente;

import Controle.ContCli;
import Modelo.Cliente;
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
 * @author luubi
 */
public class TelaCliUpdate extends Window {

    private static GUIScreen gui;

    public TelaCliUpdate(GUIScreen gui, int aux) throws ClassNotFoundException {
        super("Update");
        this.gui = gui;
        init(aux);
    }
    public TelaCliUpdate(GUIScreen gui, int aux, String a) throws ClassNotFoundException {
        super("Update");
        this.gui = gui;
        init(aux, a);
    }

    public void init(int PrimReg) throws ClassNotFoundException {
        ContCli cocli = new ContCli();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(6, "Lista de Clientes");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[6];
        linha[0] = new Label("        ");
        linha[1] = new Label("           Pagina:"+(PrimReg+1));
        linha[2] = new Label("               ");
        linha[3] = new Label("           ");
        linha[4] = new Label("         ");
        linha[5] = new Label("       ");
        tbl.addRow(linha);
        linha[0] = new Label("Codigo  ");
        linha[1] = new Label("Nome                          ");
        linha[2] = new Label("CPF            ");
        linha[3] = new Label("Divida     ");
        linha[4] = new Label("Atualizar");
        linha[5] = new Label("Excluir");
        tbl.addRow(linha);

        linha[0] = new Label("-------");
        linha[1] = new Label("-----------------------------");
        linha[2] = new Label("---------------");
        linha[3] = new Label("-----------");
        linha[4] = new Label("-----------");
        linha[5] = new Label("---------");
        tbl.addRow(linha);

        int totalReg = cocli.totalReg();
        int RegPag = 12;
        Cliente[] ArrayCli = new Cliente[totalReg];

        try {
            int cont = 0;
            for (Cliente cli : cocli.selecCli()) {
                ArrayCli[cont] = cli;
                cont++;
            }

            int numaux = cont;
            for (int i = PrimReg * RegPag; i < numaux && i <= RegPag * (PrimReg + 1); i++) {
                final int a = ArrayCli[i].getCod();
                linha[0] = new Label("" + ArrayCli[i].getCod());
                linha[1] = new Label(ArrayCli[i].getNome());
                linha[2] = new Label(ArrayCli[i].getCpf());
                linha[3] = new Label("R$" + ArrayCli[i].getDiv());
                linha[4] = new Button("Alterar", new Action() {
                    @Override
                    public void doAction() {

                        try {
                            gui.showWindow(new TelaNovosDados(gui, a));
                            MessageBox.showMessageBox(gui, "Info", "Cliente Atualizado");
                            close();
                            gui.showWindow(new TelaCliUpdate(gui, PrimReg));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        close();
                    }

                });
                linha[5] = new Button("Excluir", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            cocli.delCli(a);
                            MessageBox.showMessageBox(gui, "Info", "Cliente Deletado");
                            close();
                            gui.showWindow(new TelaCliUpdate(gui, PrimReg));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });
                tbl.addRow(linha);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        linha[0] = new  Button("Anterior", new Action() {
            @Override
            public void doAction() {
                try {
                    if(PrimReg > 0 ){
                    close();
                    gui.showWindow(new TelaCliUpdate(gui, (PrimReg - 1)));
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "Você está na primeira página!");;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        linha[1] = new Label("-----------------------------");
        linha[2] = new Label("---------------");
        linha[3] = new Label("-----------");
        linha[4] = new Label("---------");
        linha[5] = new Button("Proximo", new Action() {
            @Override
            public void doAction() {
                try {
                    close();
                    gui.showWindow(new TelaCliUpdate(gui, (PrimReg + 1)));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
    
     public void init(int PrimReg, String a) throws ClassNotFoundException {
        ContCli cocli = new ContCli();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(3, "Lista de Clientes");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[3];
        linha[0] = new Label("           Pagina:"+(PrimReg+1));
        linha[1] = new Label("------");
        linha[2] = new Label("------");
        tbl.addRow(linha);
        linha[0] = new Label("Nome                          ");
        linha[1] = new Label("CPF            ");
        linha[2] = new Label("Selecionar");
        tbl.addRow(linha);

        linha[0] = new Label("-----------------------------");
        linha[1] = new Label("---------------");
        linha[2] = new Label("----------");
        tbl.addRow(linha);

        int totalReg = cocli.totalReg();
        int RegPag = 12;
        Cliente[] ArrayCli = new Cliente[totalReg];

        try {
            int cont = 0;
            for (Cliente cli : cocli.selecCli()) {
                ArrayCli[cont] = cli;
                cont++;
            }

            int numaux = cont;
            for (int i = PrimReg * RegPag; i < numaux && i <= RegPag * (PrimReg + 1); i++) {
                linha[0] = new Label(ArrayCli[i].getNome());
                linha[1] = new Label(ArrayCli[i].getCpf());
                final String x = ArrayCli[i].getNome();
                linha[2] = new Button("Selecionar", new Action() {
                    @Override
                    public void doAction() {
                        close();
                        gui.showWindow(new TelaCliPagar(gui, x));
                    }

                });
                tbl.addRow(linha);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        linha[0] = new  Button("Anterior", new Action() {
            @Override
            public void doAction() {
                try {
                    if(PrimReg > 0 ){
                    close();
                    gui.showWindow(new TelaCliUpdate(gui, (PrimReg - 1), ""));
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "Você está na primeira página!");;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        linha[1] = new Label("-------------------");
        linha[2] = new Button("Proximo", new Action() {
            @Override
            public void doAction() {
                try {
                    close();
                    gui.showWindow(new TelaCliUpdate(gui, (PrimReg + 1), ""));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
