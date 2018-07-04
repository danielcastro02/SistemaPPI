package Controle;
//Classe de controle de cliente
import Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Daniel Castro
 */
public class ContCli {
    
    Connection con;
    ConBD cbm = new ConBD();

    
//Método de inserção de cliente recebendo parametros para montagem do objeto, em caso de cliente ja com divida
    
/*    public boolean insCli(String nome, String cpf, double sal) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        Cliente func = new Cliente(nome, cpf, sal);
        func.setDiv(0);
        String sql = "insert into cliente (nome, cpf, divd) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getDiv());
            return ps.execute();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
*/  
    
    
//Metodo para obter o total de registros na tabela cliente
    public int totalReg() throws ClassNotFoundException{
        String SQL = "select count(cod) from cliente";
        Connection con = cbm.abrirConexao();
        try{
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }
        return 0;
    }
    
//Metodo para inserção de novo cliente sem divida pendente    
    public boolean insCli(String nome, String cpf) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "insert into cliente (nome, cpf) values (?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nome);
            ps.setString(2, cpf);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
//Método para deletar cliente apenas com codigo
    public boolean delCli(int cod) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "delete from cliente where cod = (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            return ps.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
//Método para atualizar um cliente recebendo todos os novos dados como parametros para montagem do objeto e atualização
    public boolean updCli(int cod, String nome, String cpf, double sal) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        Cliente func = new Cliente(cod, nome, cpf, sal);
        String sql = "update cliente set nome = (?), cpf = (?), divd = (?) where cod = (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getDiv());
            ps.setInt(4, cod);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
//Método para atualização de cliente recebendo o objeto montado para atualização
    public boolean updCli(Cliente func) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "update cliente set nome = (?), cpf = (?), divd = (?) where cod = (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getDiv());
            ps.setInt(4, func.getCod());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
//Método para seleção de todas as informações da tabela cliente selecionadas por nome e em ordem alfabetica
    
    public Cliente selecCli(String s) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String SQL = "select * from cliente where nome like (?) order by nome";
        ResultSet rs = null;
        Cliente cli = new Cliente();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, s);
            rs = ps.executeQuery();
            rs.next();
                    System.out.println("aaa");
                    cli.setCod(rs.getInt(1));
                    cli.setNome(rs.getString(2));
                    cli.setCpf(rs.getString(3));
                    cli.setDiv(rs.getDouble(4));
                    return cli;
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return cli;
    }

//Método para seleção da tabela cliente por codigo ordsenado por ordem alfabetica
    public Cliente selecCli(int s) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String SQL = "select * from cliente where cod =(?) order by nome";
        ResultSet rs = null;
        Cliente cli = new Cliente();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, s);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    
                    cli.setCod(rs.getInt(1));
                    cli.setNome(rs.getString(2));
                    cli.setCpf(rs.getString(3));
                    cli.setDiv(rs.getDouble(4));

                    return cli;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return cli;
    }

//Médodo de seleção de toda a tabela cliente, retorna uma Lista de objetosdo tipo List<>    
    public List<Cliente> selecCli() throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        List lista = new ArrayList<Cliente>();
        String SQL = "select * from cliente order by nome";
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Cliente cli = new Cliente();
                    cli.setCod(rs.getInt(1));
                    cli.setNome(rs.getString(2));
                    cli.setCpf(rs.getString(3));
                    cli.setDiv(rs.getDouble(4));

                    lista.add(cli);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    
//Registra o pagamento de um cliente, recebe o codigo do cliente que realiza o pagamento e o valor a ser pago
    public void regPagto(int cod, double parseInt) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "update cliente set divd = divd-(?) where cod = (?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, parseInt);
            ps.setInt(2, cod);
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ContCli.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
