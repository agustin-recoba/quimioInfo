package quimioInfo;

public class atomo {

    String simbolo;
    int cantidad;
    String name;

    atomo(String s, int c, String n) {
        simbolo = s;
        cantidad = c;
        name = n;
    }

    void printAtomo() {
        System.out.println(simbolo);
        System.out.println(cantidad);
        System.out.println(name);
    }

    String getSimbolo() {
        return simbolo;
    }

    int getCantidad() {
        return cantidad;
    }

    String getName() {
        return name;
    }

}
