/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Funcionario;

import Controle.ContFuncion;
import Modelo.Funcionario;
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
 * @author Daniel Castro
 */
public class TelaFunUpdate extends Window {

    private static GUIScreen gui;

    public TelaFunUpdate(GUIScreen gui, int aux) throws ClassNotFoundException {
        super("Update");
        this.gui = gui;
        init(aux);
    }

    public void init(int PrimReg) throws ClassNotFoundException {
        ContFuncion cofun = new ContFuncion();
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Table tbl = new Table(6, "Lista de Funcionarios");
        tbl.setColumnPaddingSize(1);
        tbl.removeAllRows();
        Component[] linha = new Component[6];
        linha[0] = new Label("        ");
        linha[1] = new Label("              Pagina:"+(PrimReg+1));
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

        int totalReg = cofun.totalReg();
        int RegPag = 12;
        Funcionario[] ArrayFun = new Funcionario[totalReg];

        try {
            int cont = 0;
            for (Funcionario fun : cofun.selectFun()) {
                ArrayFun[cont] = fun;
                cont++;
            }

            int numaux = cont;
            for (int i = PrimReg * RegPag; i < numaux && i <= RegPag * (PrimReg + 1); i++) {
                final int a = ArrayFun[i].getCod();
                linha[0] = new Label("" + ArrayFun[i].getCod());
                linha[1] = new Label(ArrayFun[i].getNome());
                linha[2] = new Label(ArrayFun[i].getCpf());
                linha[3] = new Label("R$" + ArrayFun[i].getSal());
                linha[4] = new Button("Alterar", new Action() {
                    @Override
                    public void doAction() {

                        try {
                            gui.showWindow(new TelaNovosDados(gui, a));
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaFunUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        close();
                    }

                });
                linha[5] = new Button("Excluir", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            cofun.delFunc(a);
                            MessageBox.showMessageBox(gui, "Info", "Funcionario Deletado");
                        } catch (ClassNotFoundException ex) {
                            MessageBox.showMessageBox(gui, "Info", "Erro");
                            Logger.getLogger(TelaFunUpdate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                });
                tbl.addRow(linha);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaFunUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        linha[0] = new  Button("Anterior", new Action() {
            @Override
            public void doAction() {
                try {
                    if(PrimReg > 0 ){
                    close();
                    gui.showWindow(new TelaFunUpdate(gui, (PrimReg - 1)));
                    }else{
                        MessageBox.showMessageBox(gui, "Info", "Você está na primeira página!");;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaFunUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
                    gui.showWindow(new TelaFunUpdate(gui, (PrimReg + 1)));
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaFunUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
