package Controle;

import Modelo.Funcionario;
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
public class ContFuncion {

    ConBD cbm = new ConBD();

    //Insere um funcionario recebendo como parametros os dados para montagem do objeto
    public boolean insFunc(String nome, String cpf, double sal) throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        Funcionario func = new Funcionario(nome, cpf, sal);
        String sql = "insert into funcionario (nome, cpf, sal) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getSal());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

/* Insere um funcionario recebendo como parametro o objeto montado
    public boolean insFunc(Funcionario func) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "insert into funcionario (nome, cpf, sala) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getSal());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
*/
    
    //Deleta um funcionario recebendo o codigo do funcionario a ser deletado e retorna true ou false de acordo com o resultado da operação
    public boolean delFunc(int cod) throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        String sql = "delete from funcionario where cod = (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, cod);
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
    //Atualiza um funcionario, recebendo como parametro os novos dados a serem inseridos
    public boolean updFunc(int cod, String nome, String cpf, double sal) throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        Funcionario func = new Funcionario(nome, cpf, sal);
        String sql = "update funcionario set nome = (?), cpf = (?), sala = (?) where cod = (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getSal());
            ps.setInt(4, cod);
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    /* Atualiza um funcionario recebendo como parametro o objeto montado
    public boolean updFunc(Funcionario func) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "update funcionario set nome = (?), cpf = (?), sala = (?) where cod = (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getNome());
            ps.setString(2, func.getCpf());
            ps.setDouble(3, func.getSal());
            ps.setInt(4, func.getCod());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
*/
    
    //Seleciona todas as informações da tabela funcionario e retorna uma lista de objetos do tipo List<>
    public List<Funcionario> selectFun() throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        List lista = new ArrayList<>();
        String SQL = "select * from funcionario order by nome";
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Funcionario cli = new Funcionario();
                    cli.setCod(rs.getInt(1));
                    cli.setNome(rs.getString(2));
                    cli.setCpf(rs.getString(3));
                    cli.setSal(rs.getDouble(4));

                    lista.add(cli);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    //Retorna o numero total de registros na tabela funcioanrio
    public int totalReg() throws ClassNotFoundException, SQLException {
        String SQL = "select count(cod) from funcionario";
        Connection con = cbm.abrirConexao();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    
    //Seleciona um funcionario pelo codigo no banco de dados
    public Funcionario selecFun(int cod) throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        String SQL = "select * from funcionario where cod = (?) order by nome";
        ResultSet rs = null;
        Funcionario fun = new Funcionario();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    fun.setCod(rs.getInt(1));
                    fun.setNome(rs.getString(2));
                    fun.setCpf(rs.getString(3));
                    fun.setSal(rs.getDouble(4));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return fun;
    }
    //Demonstração
    //Registra o pagamento do salario de um funcionario
    public boolean regPagto(int cod, double parseInt, String a) throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        String sql = "insert into pagfun (codfun, valor) values (?,?)";
        try{
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(2, parseInt);
            ps.setInt(1, cod);
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ContCli.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    //Retorna um objeto do tipo funcionario selecionado do banco pelo nome
    public Funcionario selecFun(String cod) throws ClassNotFoundException, SQLException {
        Connection con = cbm.abrirConexao();
        String SQL = "select * from funcionario where nome = (?) order by nome";
        ResultSet rs = null;
        Funcionario fun = new Funcionario();
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    fun.setCod(rs.getInt(1));
                    fun.setNome(rs.getString(2));
                    fun.setCpf(rs.getString(3));
                    fun.setSal(rs.getDouble(4));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return fun;
    }
}
