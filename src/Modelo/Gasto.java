package Modelo;

public class Gasto {
private int cod;
private int codbus;
private String desc;
private double val;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getCodbus() {
        return codbus;
    }

    public void setCodbus(int codbus) {
        this.codbus = codbus;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getVal() {
        return val;
    }

    public void setVal(double val) {
        this.val = val;
    }

    public Gasto(String desc, double val) {
        this.desc = desc;
        this.val = val;
    }

    public Gasto() {
    }

    public Gasto(int codbus, String desc, double val) {
        this.codbus = codbus;
        this.desc = desc;
        this.val = val;
    }

}
