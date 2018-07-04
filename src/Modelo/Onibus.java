package Modelo;

public class Onibus {
    
    //Modelo do objeto onibus
    
    private int cod;
    private String modelo;
    private double gasto;

    public Onibus() {
    
    }

    
    
    public Onibus(String modelo) {
        this.modelo = modelo;
    }

    public Onibus(String modelo, double gasto) {
        this.modelo = modelo;
        this.gasto = gasto;
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getGasto() {
        return gasto;
    }

    public void setGasto(double gasto) {
        this.gasto = gasto;
    }
    
    
            
}
