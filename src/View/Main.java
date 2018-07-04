package View;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import java.sql.SQLException;

public class Main {

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
        guiScreen.showWindow(new TelaPrincipal(guiScreen));
        screen.stopScreen();
    }

}

