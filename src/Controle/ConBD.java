package Controle;

/*Classe de para abrir conexão com banco de Dados*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Daniel Castro
 */
public class ConBD {

    private Connection con;

    public Connection abrirConexao() throws ClassNotFoundException, SQLException {
        Connection conaux = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            //Endereço do Banco, usuario e senha.
            String url = "jdbc:postgresql://localhost:5433/SistemaPPI";
            String usuario = "postgres";
            String senha = "ciet";
            conaux = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão realizada com sucesso!!");
        } catch (Exception e) {
            System.out.println("Ocorreu erro :" + e.getMessage());
            try{
                String url2 = "jdbc:postgresql://localhost:5433";
                String usuario2 = "postgres";
                String senha2 = "ciet";
                conaux = DriverManager.getConnection(url2, usuario2, senha2);
                conaux.prepareStatement("create database SistemaPPI;").execute();
                System.out.println("foi");
            }catch(SQLException x){
                System.out.println("n foi");
                System.out.println(x);
            }
        }
        this.con = conaux;
        return conaux;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void close() throws SQLException {
        con.close();
    }

}
