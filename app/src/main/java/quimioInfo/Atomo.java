package quimioInfo;

public class Atomo {

    private String simbolo;
    private int cantidad;
    private String name;

    public Atomo(String s, int c, String n) {
        simbolo = s;
        cantidad = c;
        name = n;
    }
    
    public Atomo clonado() {
        Atomo a = new Atomo(this.simbolo, this.cantidad, this.name );
        return a;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.name + ", Símbolo: " + this.simbolo + ", Cantidad: " + this.cantidad + ".";
    }

    public String getSimbolo() {
        return simbolo;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public void setCantidad(int cant) {
        this.cantidad = cant;
    }

    public String getName() {
        return name;
    }

}
