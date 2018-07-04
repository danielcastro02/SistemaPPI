package View.Cliente;

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

//Classe para pesquisar clientes por codigo ou nome
public class TelaCliConsultar extends Window {

    private static GUIScreen gui;

    //Construtor com titulo da tela
    public TelaCliConsultar(GUIScreen gui) {
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
        //Definição do botão sair do estagio 1 da tela
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                close();
            }
        });
        
        //Definição do botão cancelar do estagio 2 da tela
        Button sair = new Button("Cancelar", new Action() {
            @Override
            public void doAction() {
                close();
                gui.showWindow(new TelaCliConsultar(gui));
            }
        });
        
        //Envia para o estaagio 2 pesquisando por codigo
        Button codigo = new Button("Codigo", new Action() {
            @Override
            public void doAction() {
                removeComponent(botaoSair); //Remove o botão sair para inserir a caixa de texto e o botão de pesquisa
                Label label02 = new Label("Insira o Codigo");
                addComponent(label02);
                TextBox txt1 = new TextBox();
                addComponent(txt1);
                
                //Envia para a tela que gera a tabela de pesquisa o codigo do cliente desejado, seguido de um int para identificação do construtor
                Button sql = new Button("Enviar", new Action() {
                    @Override
                    public void doAction() {
                        try {
                            gui.showWindow(new TelaConsulta(gui, Integer.parseInt(txt1.getText()), 0)); //chamada da tabela de pesquisa
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(TelaCliConsultar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        close();
                    }
                });
                addComponent(sql);

                addComponent(sair);
            }
        });
        addComponent(codigo);

        
        //Executa a mesma função do botão codigo, porém para pesquisa com nome
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
                            Logger.getLogger(TelaCliConsultar.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        close();
                    }
                });
                addComponent(sql);
                addComponent(sair);
            }
        });
        addComponent(nome);

        addComponent(botaoSair);
    }
}
