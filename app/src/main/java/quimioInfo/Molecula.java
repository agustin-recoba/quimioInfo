package quimioInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Molecula {

    private ArrayList<Atomo> atomos;
    private String name;

    public Molecula(ArrayList<Atomo> AtomosNuevos) {
        atomos = (ArrayList<Atomo>) AtomosNuevos.clone();
    }

    public Molecula() {
        this.atomos = new ArrayList<>();
    }

    public Molecula(ArrayList<Atomo> a, String n) {
        this.atomos = a;
        this.name = n;
    }

    public Molecula(String formula) {
        Map<String, String> element_map = Quimioinformatica.getSymbolMap();
        Map<String, String> name_map = Quimioinformatica.getNameMap();
        Map<String, Integer> element_counts = new HashMap<>();
        int len = formula.length();
        for (int i = 0; i < len;) {
            boolean is_group = false;
            if (formula.charAt(i) == '(') {
                i++;
                is_group = true;
            }
            int repeat_count = 1;
            Map<String, Integer> atoms_in_group = new HashMap<>();
            do {
                int start = i;
                int restore_i = 0;
                String element = null;
                String restore_element = null;
                while (i < len && Character.isLetter(formula.charAt(i))) {
                    i++;
                    element = formula.substring(start, i);
                    String element_from_map = element_map.get(element);
                    if (element_from_map == null) {
                        element_from_map = element_map.get(element.toUpperCase());
                    }
                    if (element_from_map != null) {
                        restore_i = i;
                        restore_element = element_from_map;
                    }
                }
                if (restore_element != null) {
                    i = restore_i;
                    element = restore_element;
                }

                if (element == null || "".equals(element)) {
                    System.out.println("Error de parseo, no se detecto una formula donde se esperaba.");
                    System.exit(1);
                }

                start = i;
                while (i < len && Character.isDigit(formula.charAt(i))) {
                    i++;
                }
                int count;
                try {
                    count = Integer.parseInt(formula.substring(start, i));
                } catch (NumberFormatException e) {
                    count = 1;
                }
                /* System.out.println("element: "+element); */
                atoms_in_group.put(element, count);
                if (i < len && formula.charAt(i) == ')') {
                    if (!is_group) {
                        System.out.println("Error de parseo: cantidad de paréntesis desigual");
                    }

                    i++;
                    is_group = false;

                    start = i;
                    while (i < len && Character.isDigit(formula.charAt(i))) {
                        i++;
                    }

                    try {
                        repeat_count = Integer.parseInt(formula.substring(start, i));
                    } catch (NumberFormatException e) {
                        repeat_count = 1;
                    }
                }
            } while (is_group == true);
            for (String atom_type : atoms_in_group.keySet()) {
                int current_value = 0;
                if (element_counts.containsKey(atom_type)) {
                    current_value = element_counts.get(atom_type);
                }
                element_counts.put(atom_type, current_value + atoms_in_group.get(atom_type) * repeat_count);
            }
        }

        ArrayList<Atomo> atoms = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : element_counts.entrySet()) {
            Atomo i = new Atomo(name_map.get(entry.getKey()), entry.getValue(), entry.getKey());
            atoms.add(i);
        }
        this.atomos = atoms;
    }
    
    public Molecula(Molecula m) {
        this.atomos = new ArrayList<>();
        for(Atomo a : m.atomos) {
            this.atomos.add(a.clonado());
        }
    }
    
    public void addAtomo(Atomo i) {
        atomos.add(i);
    }
    
    @Override
    public String toString() {
        String output = "";
        for (Atomo i : this.atomos) {
            String element = i.getSimbolo();
            output += element;
            int cantidad = i.getCantidad();
            output += cantidad;
        }
        return output;
    }
    
    public int cantidadDeAtomosDist(){
        return this.atomos.size();
    }

    public void multMoles(int mul) {
        for (int i = 0; i < this.atomos.size(); i++) {
            Atomo at = this.atomos.get(i);
            int cant = at.getCantidad();
            cant *= mul;
            at.setCantidad(cant);
        }
    }
    
    public String printEveryAtom() {
        String output = "";
        for (Atomo i : atomos) {
            String simbolo = i.getSimbolo();
            output += "Simbolo quimico: " + simbolo;
            int cantidad = i.getCantidad();

            output += "    Cantidad: " + cantidad;
            String nombre = i.getName();

            output += "    Nombre: " + nombre + ".\n";

        }
        return output;
    }

    public String getFormula() {

        String out = "";

        for (Atomo a : atomos) {
            out += a.getSimbolo();
            out += a.getCantidad();
        }
        return out;
    }

    public int getCantidadDe(String elemento) {
        int out = 898;
        for (Atomo a : atomos) {
            String elm = a.getSimbolo();
            if (elm.equals(elemento)) {
                out = a.getCantidad();
                break;
            }
        }
        if (out == 898) {
            out = 0;
            System.out.println("No existe tal elemento en la Molecula");
        }

        return out;
    }

    public int sizeMolecula() {
        return atomos.size();
    }

    public Atomo getAtomo(int i) {
        return atomos.get(i);
    }

}
