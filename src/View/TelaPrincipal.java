package View;

import View.Onibus.TelaOnibus;
import View.Cliente.TelaCliente;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import View.Funcionario.TelaFuncionario;
import View.Gasto.TelaGasto;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Daniel Castro
 */
public class TelaPrincipal extends Window{
    //BPM Bus Payment Manager do Sistema
    private static GUIScreen gui;
    
    //Construtor que define a tela e recebe a GUI
    public TelaPrincipal(GUIScreen gS) {
        super("BPM Bus Payment Manager");
        this.gui = gS;
        try {
            init();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Define os componentes da tela
    private void init() throws ClassNotFoundException, SQLException{
        setBorder(new Border.Standard());
        //Painel opcional para organização dos elementos em modo horizontal
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Menu de opções");
        addComponent(label01);
        
        //Envia para a tela do cliente
        Button botaocliente = new Button("Clientes", new Action(){
            @Override
            public void doAction(){
                close();//Fecha a janela para não 
                gui.showWindow(new TelaCliente(gui));
            }
        });
        addComponent(botaocliente);
        
        
        //Envia para a tela de funcionarios
        Button botaoFuncionarios = new Button("Funcionarios", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaFuncionario(gui));
            }
        });
        addComponent(botaoFuncionarios);
        
        
        //Envia para a tela de ônibus
        Button onibus = new Button("Ônibus", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaOnibus(gui));
            }
        });
        addComponent(onibus);
        
        
        //Envia para a tela de gastos
        Button botaoGasto = new Button("Gastos", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaGasto(gui));
            }
        });
        addComponent(botaoGasto);
        
        //Encerra o processo do sistema
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                System.exit(0);                
            }
        });
        addComponent(botaoSair);
    }
}
