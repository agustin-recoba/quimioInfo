package quimioInfo;

import java.util.ArrayList;

public class molecula {

    ArrayList<atomo> atomos;
    String name;

    molecula(ArrayList<atomo> atomosNuevos) {
        atomos = atomosNuevos;
    }

    molecula() {
        atomos = new ArrayList<>();
    }

    molecula(ArrayList<atomo> a, String n) {
        atomos = a;
        name = n;
    }

    void addAtomo(atomo i) {
        atomos.add(i);
    }
    
    
    
    
    int cantidadDeAtomosDist(){
        return this.atomos.size();
    }

    String printEveryAtom() {
        String output = "";
        for (atomo i : atomos) {
            String simbolo = i.getSimbolo();
            output += "Simbolo quimico: " + simbolo;
            int cantidad = i.getCantidad();

            output += "    Cantidad: " + cantidad;
            String nombre = i.getName();

            output += "    Nombre: " + nombre + ".\n";

        }
        return output;
    }

    String getFormula() {

        String out = "";

        for (atomo a : atomos) {
            out += a.getSimbolo();
            out += a.getCantidad();
        }
        return out;
    }

    int getCantidadDe(String elemento) {
        int out = 898;
        for (atomo a : atomos) {
            String elm = a.getSimbolo();
            if (elm.equals(elemento)) {
                out = a.getCantidad();
                break;
            }
        }
        if (out == 898) {
            out = 0;
            System.out.println("No existe tal elemento en la molecula");
        }

        return out;
    }

    int sizeMolecula() {
        return atomos.size();
    }

    atomo getAtomo(int i) {
        return atomos.get(i);
    }

}
