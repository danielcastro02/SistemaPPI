package View;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import java.sql.SQLException;


/**
 *
 * @author Daniel Castro
 */
public class Main {
    //Instancia da janela principal
    public static Terminal terminal;
    public static Screen screen;
    public static GUIScreen guiScreen;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        terminal = TerminalFacade.createTerminal();
        terminal.getTerminalSize().setColumns(100);
        terminal.getTerminalSize().setRows(30);
        screen = new Screen(terminal);
        guiScreen = new GUIScreen(screen);
        screen.startScreen();
        //Não sei como essas coisas funcionam sei que aqui manda pra onde eu sei usar
        guiScreen.showWindow(new TelaPrincipal(guiScreen));
        screen.stopScreen();
    }

}

