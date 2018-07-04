package Controle;

import Modelo.Onibus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Daniel Castro
 */
public class ContOni {

    ConBD cbm = new ConBD();
    
    
    //Insere um onibus recebe apenas o modelo
    public boolean insOni(String modelo) throws ClassNotFoundException {
        Connection con = cbm.abrirConexao();
        Onibus func = new Onibus(modelo);
        String sql = "insert into onibus (modelo) values (?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, func.getModelo());
            ps.execute();
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
    
    
    //Retorna uma lista de objetos do tipo List<Onibus> contendo todos os onibus do banco de dados
    public List<Onibus> selectOni() throws ClassNotFoundException{
        Connection con = cbm.abrirConexao();
        List lista = new ArrayList<Onibus>();
        String SQL = "select * from onibus";
        ResultSet rs = null;
        try {
            PreparedStatement ps = con.prepareStatement(SQL);
            rs = ps.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Onibus oni = new Onibus();
                    oni.setCod(rs.getInt(1));
                    oni.setModelo(rs.getString(2));
                    oni.setGasto(rs.getDouble(3));

                    lista.add(oni);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

//    public boolean insOni(Onibus func) {
//        String sql = "insert into funcioanrio (modelo gasto) values (?,?,?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(0, func.getModelo());
//            ps.setDouble(1, func.getGasto());
//            return ps.execute();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//
    
    //Deleta um onibus e retorna true ou false de acordo com o resultado da operação
    public boolean delOni(int cod) {
        String sql = "delete from onibus where cod = (?)";
        try {
            Connection con = cbm.abrirConexao();
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
//    public boolean updOni(String modelo, double gasto, int cod) {
//        Onibus func = new Onibus(modelo, gasto);
//        String sql = "update onibus set modelo = (?), gasto(?) where cod = (?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(0, func.getModelo());
//            ps.setDouble(1, func.getGasto());
//            ps.setInt(2, cod);
//            return ps.execute();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
//
//    public boolean upOni(Onibus func) {
//        String sql = "update onibus set modelo = (?), gasto = (?) where cod = (?)";
//        try {
//            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setString(0, func.getModelo());
//            ps.setDouble(1, func.getGasto());
//            ps.setInt(2, func.getCod());
//            return ps.execute();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return false;
//    }
    
    //retorna um inteiro com o total de registros na tabela onibus do banco de dados
    public int totalReg() {
        String sql = "select totalRegOni();";
        try {
            Connection con = cbm.abrirConexao();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int a = rs.getInt(1);
            return a;
        } catch (Exception e) {
            System.out.println(e);
        }
        return 1;
    }
}
