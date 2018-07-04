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
import com.googlecode.lanterna.gui.component.TextBox;
import com.googlecode.lanterna.gui.dialog.MessageBox;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel Castro
 */
public class TelaConsulta extends Window {

    private static GUIScreen gui;
    private int aux;

    public TelaConsulta(GUIScreen gui, int i, int a) throws ClassNotFoundException {
        super("Lista de Contatos");
        this.gui = gui;
        init(i, a);

    }

    public TelaConsulta(GUIScreen gui, String i, int a) throws ClassNotFoundException {
        super("Lista de Contatos");
        this.gui = gui;
        init(i, a);
    }

    public TelaConsulta(GUIScreen gui, String i, String a) throws ClassNotFoundException {
        super("Lista de Contatos");
        this.gui = gui;
        init(i, a);
    }

    private void init(String z, int PrimReg) throws ClassNotFoundException {
        ContCli cocli = new ContCli();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(6, "Lista de Clientes");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[6];
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


        try {

            Cliente cli = cocli.selecCli(z);
            final int a = cli.getCod();
            linha[0] = new Label("" + cli.getCod());
            linha[1] = new Label(cli.getNome());
            linha[2] = new Label(cli.getCpf());
            linha[3] = new Label("R$" + cli.getDiv());
            linha[4] = new Button("Alterar", new Action() {
                @Override
                public void doAction() {

                    try {
                        gui.showWindow(new TelaNovosDados(gui, a));
                        MessageBox.showMessageBox(gui, "Info", "Cliente Alterado");
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        addComponent(tbl);

        Button sair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }

        });
        addComponent(sair);
    }

    private void init(String z, String PrimReg) throws ClassNotFoundException {
        ContCli cocli = new ContCli();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(5, "Lista de Clientes");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[5];
        linha[0] = new Label("Codigo  ");
        linha[1] = new Label("Nome                          ");
        linha[2] = new Label("CPF            ");
        linha[3] = new Label("Divida     ");
        linha[4] = new Label("RegPagto");

        tbl.addRow(linha);

        linha[0] = new Label("-------");
        linha[1] = new Label("-----------------------------");
        linha[2] = new Label("---------------");
        linha[3] = new Label("-----------");
        linha[4] = new Label("-----------");

        tbl.addRow(linha);

        int totalReg = cocli.totalReg();
        int RegPag = 12;
        Cliente[] ArrayCli = new Cliente[totalReg];

        try {

            Cliente cli = cocli.selecCli(z);
            final int a = cli.getCod();
            linha[0] = new Label("" + cli.getCod());
            linha[1] = new Label(cli.getNome());
            linha[2] = new Label(cli.getCpf());
            linha[3] = new Label("R$" + cli.getDiv());
            linha[4] = new Button("RegPag", new Action() {
                @Override
                public void doAction() {
                    Label valor = new Label("Valor");
                    addComponent(valor);
                    TextBox txtValor = new TextBox();
                    addComponent(txtValor);
                    Button seila = new Button("Confirmar", new Action() {
                        @Override
                        public void doAction() {
                            ContCli cocli = new ContCli();
                            try {
                                cocli.regPagto(cli.getCod(), Double.parseDouble(txtValor.getText().replace(",", ".")));
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(TelaConsulta.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            close();
                        }
                    });
                    addComponent(seila);
                }

            });

            tbl.addRow(linha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        addComponent(tbl);

        Button sair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }

        });
        addComponent(sair);
    }

    private void init(int z, int PrimReg) throws ClassNotFoundException {
        ContCli cocli = new ContCli();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(6, "Lista de Clientes");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[6];
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

        try {

             
            Cliente cli = cocli.selecCli(z);
            final int a = cli.getCod();
            linha[0] = new Label("" + cli.getCod());
            linha[1] = new Label(cli.getNome());
            linha[2] = new Label(cli.getCpf());
            linha[3] = new Label("R$" + cli.getDiv());
            linha[4] = new Button("Alterar", new Action() {
                @Override
                public void doAction() {

                    try {
                        gui.showWindow(new TelaNovosDados(gui, a));
                        MessageBox.showMessageBox(gui, "Info", "Cliente Alterado");
                        close();
                        gui.showWindow(new TelaConsulta(gui, z, 0));
                        
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
                        gui.showWindow(new TelaConsulta(gui, z,0));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });
            tbl.addRow(linha);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCliUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
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
