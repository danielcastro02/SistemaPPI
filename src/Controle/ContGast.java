package Controle;

import Modelo.Gasto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Daniel Castro
 */
//classe de constrole dos gastos
public class ContGast {

    ConBD cbm = new ConBD();

    
    //Insere um novo gasto recebe o codigo do onibus um a descrição e o valor
    public boolean insGast(int codo, String desc, double val) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        Gasto gas = new Gasto(codo, desc, val);
        String sql = "insert into gasto (codo, decr, val) values (?,?,?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, gas.getCodbus());
            ps.setString(2, gas.getDesc());
            ps.setDouble(3, gas.getVal());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    
    //Retorna o total de registros na tabela de gastos
    public int totalReg() throws ClassNotFoundException {
        String SQL = "select count(cod) from gasto";
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
//    public boolean insCli(String nome, String cpf) throws ClassNotFoundException {
//        Connection con = cbm.abrirConexao();
//        String sql = "insert into cliente (nome, cpf) values (?,?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, nome);
//            ps.setString(2, cpf);
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//

    
    //Deleta um gasto apenas para fins de correção
    public boolean delGas(int cod) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        String sql = "delete from gasto where cod = (?)";
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
//
//    public boolean updCli(int cod, String nome, String cpf, double sal) throws ClassNotFoundException {
//        Connection con = cbm.abrirConexao();
//        Gasto gas = new Gasto(cod, nome, cpf, sal);
//        String sql = "update cliente set nome = (?), cpf = (?), divd = (?) where cod = (?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, gas.getNome());
//            ps.setString(2, gas.getCpf());
//            ps.setDouble(3, gas.getDiv());
//            ps.setInt(4, cod);
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//
//    public boolean updCli(Gasto gas) throws ClassNotFoundException {
//        Connection con = cbm.abrirConexao();
//        String sql = "update cliente set nome = (?), cpf = (?), divd = (?) where cod = (?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(1, gas.getNome());
//            ps.setString(2, gas.getCpf());
//            ps.setDouble(3, gas.getDiv());
//            ps.setInt(4, gas.getCod());
//            ps.execute();
//            return true;
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//
//    public Gasto selecCli(String s) throws ClassNotFoundException {
//        Connection con = cbm.abrirConexao();
//        String SQL = "select * from cliente where nome like (?) order by nome";
//        ResultSet rs = null;
//        Gasto cli = new Gasto();
//        try {
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setString(1, s);
//            rs = ps.executeQuery();
//            rs.next();
//                    System.out.println("aaa");
//                    cli.setCod(rs.getInt(1));
//                    cli.setNome(rs.getString(2));
//                    cli.setCpf(rs.getString(3));
//                    cli.setDiv(rs.getDouble(4));
//                    return cli;
//            
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return cli;
//    }
//
//    public Gasto selecCli(int s) throws ClassNotFoundException {
//        Connection con = cbm.abrirConexao();
//        String SQL = "select * from cliente where cod =(?) order by nome";
//        ResultSet rs = null;
//        Gasto cli = new Gasto();
//        try {
//            PreparedStatement ps = con.prepareStatement(SQL);
//            ps.setInt(1, s);
//            rs = ps.executeQuery();
//
//            if (rs != null) {
//                while (rs.next()) {
//                    
//                    cli.setCod(rs.getInt(1));
//                    cli.setNome(rs.getString(2));
//                    cli.setCpf(rs.getString(3));
//                    cli.setDiv(rs.getDouble(4));
//
//                    return cli;
//                }
//            }
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return cli;
//    }
//
//    
    //Retorna uma lista de todos os gastos na forma de uma List<>
    public List<Gasto> selecGas() throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        List lista = new ArrayList<Gasto>();
        String SQL = "select * from gasto";
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Gasto gas = new Gasto();
                    gas.setCod(rs.getInt(1));
                    gas.setCodbus(rs.getInt(2));
                    gas.setDesc(rs.getString(3));
                    gas.setVal(rs.getDouble(4));

                    lista.add(gas);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }
//
//    public void regPagto(int cod, double parseInt) throws ClassNotFoundException {
//        Connection con = cbm.abrirConexao();
//        String sql = "update cliente set divd = divd-(?) where cod = (?)";
//        try{
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setDouble(1, parseInt);
//            ps.setInt(2, cod);
//            ps.execute();
//        } catch (SQLException ex) {
//            Logger.getLogger(ContCli.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
