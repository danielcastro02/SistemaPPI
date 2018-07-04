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

public class TelaPrincipal extends Window{
    
    private static GUIScreen gui;
    
    
    public TelaPrincipal(GUIScreen gS) {
        super("BPM Bus Payment Manager");
        this.gui = gS;
        init();
    }
    
    private void init(){
        setBorder(new Border.Standard());
        Panel painel01 = new Panel(Panel.Orientation.VERTICAL);
        painel01.setBetweenComponentsPadding(1);
        Label label01 = new Label("Menu de opções");
        addComponent(label01);
        Button botaocliente = new Button("Clientes", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaCliente(gui));
            }
        });
        addComponent(botaocliente);
        
        Button botaoFuncionarios = new Button("Funcionarios", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaFuncionario(gui));
            }
        });
        addComponent(botaoFuncionarios);
        
        Button onibus = new Button("Ônibus", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaOnibus(gui));
            }
        });
        addComponent(onibus);
        
        Button botaoGasto = new Button("Gastos", new Action(){
            @Override
            public void doAction(){
                close();
                gui.showWindow(new TelaGasto(gui));
            }
        });
        addComponent(botaoGasto);
        
        Button botaoSair = new Button("Sair", new Action() {
            @Override
            public void doAction() {
                System.exit(0);                
            }
        });
        addComponent(botaoSair);
    }
}
