package quimioInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

// Format of formulas: [(](shorthand|longhand)[count][)][count]
//
// shorthand is case sensitive and is the symbol notation (e.g. C for Carbon, Co for Cobalt and Hg for Lead)
// longhand is the full element name and is not case sensitive (e.g. Carbon, CARBON, CaRBoN, lead)
// If a count is left out, it's implicitly '1', else you can put a count after a shorthand or longhand element
// You can group element with parenthesis and apply counts to the groups. (e.g. C2(HO)2)
// Any other symbols are stripped from the formula (e.g. -, +). Leaving them in does no harm
//
// The output is a map from the capitalized element name as a string, to it's count
public class Quimioinformatica {

    private final static String symbols_map
            = "H Hidrógeno\n"
            + "He Helio\n"
            + "Li Litio\n"
            + "Be Berilio\n"
            + "B Boro\n"
            + "C Carbono\n"
            + "N Nitrógeno\n"
            + "O Oxígeno\n"
            + "F Flúor\n"
            + "Ne Neón\n"
            + "Na Sodio\n"
            + "Mg Magnesio\n"
            + "Al Aluminio\n"
            + "Si Silicio\n"
            + "P Fósforo\n"
            + "S Azufre\n"
            + "Cl Cloro\n"
            + "Ar Argón\n"
            + "K Potasio\n"
            + "Ca Calcio\n"
            + "Sc Escandio\n"
            + "Ti Titanio\n"
            + "V Vanadio\n"
            + "Cr Cromo\n"
            + "Mn Manganeso\n"
            + "Fe Hierro\n"
            + "Co Cobalto\n"
            + "Ni Niquel\n"
            + "Cu Cobre\n"
            + "Zn Zinc\n"
            + "Ga Galio\n"
            + "Ge Germanio\n"
            + "As Arsénico\n"
            + "Se Selenio\n"
            + "Br Bromo\n"
            + "Kr Kriptón\n"
            + "Rb Rubidio\n"
            + "Sr Estroncio\n"
            + "Y Itrio\n"
            + "Zr Zirconio\n"
            + "Nb Niobio\n"
            + "Mo Molibdeno\n"
            + "Tc Tecnecio\n"
            + "Ru Rutenio\n"
            + "Rh Rodio\n"
            + "Pd Paladio\n"
            + "Ag Plata\n"
            + "Cd Cadmio\n"
            + "In Indio\n"
            + "Sn Estaño\n"
            + "Sb Antimonio\n"
            + "Te Teluro\n"
            + "I Yodo\n"
            + "Xe Xenon\n"
            + "Cs Cesio\n"
            + "Ba Bario\n"
            + "La Lantano\n"
            + "Ce Cerio\n"
            + "Pr Praseodimio\n"
            + "Nd Neodimio\n"
            + "Pm Prometio\n"
            + "Sm Samario\n"
            + "Eu Europio\n"
            + "Gd Gadolinio\n"
            + "Tb Terbio\n"
            + "Dy Disprosio\n"
            + "Ho Holmio\n"
            + "Er Erbio\n"
            + "Tm Tulio\n"
            + "Yb Iterbio\n"
            + "Lu Lutecio\n"
            + "Hf Hafnio\n"
            + "Ta Tantalio\n"
            + "W Wolframio\n"
            + "Re Renio\n"
            + "Os Osmio\n"
            + "Ir Iridio\n"
            + "Pt Platino\n"
            + "Au Oro\n"
            + "Hg Mercurio\n"
            + "Tl Talio\n"
            + "Pb Plomo\n"
            + "Bi Bismuto\n"
            + "Po Polonio\n"
            + "At Astato\n"
            + "Rn Radón\n"
            + "Fr Francio\n"
            + "Ra Radio\n"
            + "Ac Actinio\n"
            + "Th Torio\n"
            + "Pa Protactinio\n"
            + "U Uranio\n"
            + "Np Neptunio\n"
            + "Pu Plutonio\n"
            + "Am Americio\n"
            + "Cm Curio\n"
            + "Bk Berkelio\n"
            + "Cf Californio\n"
            + "Es Einsteinio\n"
            + "Fm Fermio\n"
            + "Md Mendelevio\n"
            + "No Nobelio\n"
            + "Lr Lawrencio\n"
            + "Rf Rutherfordio\n"
            + "Db Dubnio\n"
            + "Sg Seaborgio\n"
            + "Bh Bohrio\n"
            + "Hs Hassio\n"
            + "Mt Meitnerio\n"
            + "Ds Darmstadtio\n"
            + "Rg Roentgenio\n"
            + "Cn Copernicio\n"
            + "Nh Nihonio\n"
            + "Fl Flerovio\n"
            + "Mc Moscovio\n"
            + "Lv Livermorio\n"
            + "Ts Teneso\n"
            + "Og Oganesón";

    public static String printMolecula(molecula a) {
        String output = "";
        for (atomo i : a.atomos) {
            String element = i.getSimbolo();
            output += element;
            int cantidad = i.getCantidad();
            output += cantidad;
        }
        return output;
    }

    private static Map<String, String> getSymbolMap() {
        Map<String, String> element_map = new HashMap<>();

        Scanner in = new Scanner(symbols_map);

        while (in.hasNext()) {
            String shortform = in.next();
            String longform = in.next();
            element_map.put(shortform, longform);
            element_map.put(longform.toUpperCase(), longform);
        }
        return element_map;
    }

    private static <K, V> Map<V, K> inverseMap(Map<K, V> sourceMap) {
        return sourceMap.entrySet().stream().collect(
                Collectors.toMap(Entry::getValue, Entry::getKey,
                        (a, b) -> a) //if sourceMap has duplicate values, keep only first
        );
    }

    private static Map<String, String> getNameMap() {

        Map<String, String> element_map = new HashMap<>();

        Scanner in = new Scanner(symbols_map);

        while (in.hasNext()) {
            String shortform = in.next();
            String longform = in.next();

            element_map.put(longform, shortform);
            element_map.put(longform.toUpperCase(), longform);
        }
        return element_map;
    }

    public static molecula inputToMolecula(String formula) {

        Map<String, String> element_map = getSymbolMap();
//        System.out.println(element_map);

        Map<String, String> name_map = getNameMap();
//        System.out.println(name_map);

        Map<String, Integer> element_counts = new HashMap<>();
        /* formula = formula.toUpperCase(); */
        //System.out.println(formula);
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
                    /* System.out.println( element); */
                    String element_from_map = element_map.get(element);
                    if (element_from_map == null) {
                        element_from_map = element_map.get(element.toUpperCase());
                    }
                    if (element_from_map != null) {
                        /* System.out.println("element from map: " + element_from_map); */
                        restore_i = i;
                        restore_element = element_from_map;
                    }
                }
                if (restore_element != null) {
                    i = restore_i;
                    element = restore_element;
                }

                if (element == null || "".equals(element)) {
                    System.out.println("Parse error: could not detect an element where one was expected in formula string.");
                    System.out.println("Remaining formula to parse: " + formula.substring(i));
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
                        System.out.println("Parse error: unmatched parenthesis detected...");
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

        molecula returned = new molecula(new ArrayList<>());

        for (Map.Entry<String, Integer> entry : element_counts.entrySet()) {
//            atomo(String simbolo, int cantidad, entry.getKey() )
            atomo i = new atomo(name_map.get(entry.getKey()), entry.getValue(), entry.getKey());

//            System.out.println("nombre " + entry.getKey());
//            System.out.println("cant " + entry.getValue());
//            System.out.println("simbolo " + name_map.get(entry.getKey()));
            returned.addAtomo(i);

            //System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return returned;
    }

    public static molecula multMoles(molecula mol, int mul) {
        for (int i = 0; i < mol.atomos.size(); i++) {
            atomo at = mol.atomos.get(i);
            at.cantidad *= mul;
            mol.atomos.set(i, at);
        }
        return mol;
    }

    public static molecula copyMol(molecula original) {
        molecula nueva = inputToMolecula(original.getFormula());
        return nueva;
    }

    public static int[] igualadorOrdn(molecula a, molecula b, molecula c, molecula d) {
        int[] resultado = new int[5];

        resultado[4] = 0;

        boolean igualado = true;
        int i = 1;
        int j = 1;
        int k = 1;
        int l = 1;

        molecula aX = copyMol(a);
        molecula bX = copyMol(b);
        molecula cX = copyMol(c);
        molecula dX = copyMol(d);

        molecula reactivosTotal = inputToMolecula(a.getFormula() + b.getFormula());
        molecula productosTotal = inputToMolecula(c.getFormula() + d.getFormula());

        if (reactivosTotal.cantidadDeAtomosDist() != productosTotal.cantidadDeAtomosDist()) {
            resultado[4] = 1;

            resultado[0] = 0;
            resultado[1] = 0;
            resultado[2] = 0;
            resultado[3] = 0;
            return resultado;
        }

        Random r;

        int contador = 0;
        while (true) {

            if (estaIgualada(aX, bX, cX, dX)) {
                resultado[0] = i;
                resultado[1] = j;
                resultado[2] = k;
                resultado[3] = l;
                return resultado;
            }

            for (i = 1; i < 11; i++) {
                for (j = 1; j < 11; j++) {
                    for (k = 1; k < 11; k++) {
                        for (l = 1; l < 11; l++) {

                            aX = multMoles(aX, i);
                            bX = multMoles(bX, j);
                            cX = multMoles(cX, k);
                            dX = multMoles(dX, l);

                            if (estaIgualada(aX, bX, cX, dX)) {
                                resultado[0] = i;
                                resultado[1] = j;
                                resultado[2] = k;
                                resultado[3] = l;
                                return resultado;
                            }
                            aX = copyMol(a);
                            bX = copyMol(b);
                            cX = copyMol(c);
                            dX = copyMol(d);
                        }

                        aX = multMoles(aX, i);
                        bX = multMoles(bX, j);
                        cX = multMoles(cX, k);
                        dX = multMoles(dX, l);

                        if (estaIgualada(aX, bX, cX, dX)) {
                            resultado[0] = i;
                            resultado[1] = j;
                            resultado[2] = k;
                            resultado[3] = l;
                            return resultado;
                        }
                        aX = copyMol(a);
                        bX = copyMol(b);
                        cX = copyMol(c);
                        dX = copyMol(d);
                    }

                    aX = multMoles(aX, i);
                    bX = multMoles(bX, j);
                    cX = multMoles(cX, k);
                    dX = multMoles(dX, l);

                    if (estaIgualada(aX, bX, cX, dX)) {
                        resultado[0] = i;
                        resultado[1] = j;
                        resultado[2] = k;
                        resultado[3] = l;
                        return resultado;
                    }
                    aX = copyMol(a);
                    bX = copyMol(b);
                    cX = copyMol(c);
                    dX = copyMol(d);
                }

                aX = multMoles(aX, i);
                bX = multMoles(bX, j);
                cX = multMoles(cX, k);
                dX = multMoles(dX, l);

                if (estaIgualada(aX, bX, cX, dX)) {
                    resultado[0] = i;
                    resultado[1] = j;
                    resultado[2] = k;
                    resultado[3] = l;
                    return resultado;
                }
                aX = copyMol(a);
                bX = copyMol(b);
                cX = copyMol(c);
                dX = copyMol(d);
            }

            contador++;

            System.out.println("Contador: " + contador);
            System.out.println("Formula aX:  " + aX.getFormula());
            System.out.println("Formula a:  " + a.getFormula());
            System.out.println("Formula bX:  " + bX.getFormula());
            System.out.println("Formula b:  " + b.getFormula());
        }
    }

    public static boolean estaIgualada(molecula a, molecula b, molecula c, molecula d) {

//        System.out.println(printMolecula(a));
//        System.out.println(printMolecula(b));
//        System.out.println(printMolecula(c));
//        System.out.println(printMolecula(d));
        String reactivos = "";
        reactivos += a.getFormula();
        reactivos += b.getFormula();
        molecula reactivosTotal = inputToMolecula(reactivos);

        String productos = "";
        productos += c.getFormula();
        productos += d.getFormula();
        molecula productosTotal = inputToMolecula(productos);

        boolean igualada = true;

        if (reactivosTotal.cantidadDeAtomosDist() != productosTotal.cantidadDeAtomosDist()) {
            return false;
        }

        if (productosTotal.sizeMolecula() == reactivosTotal.sizeMolecula()) {
//            System.out.println("A) productosTotal.sizeMolecula == reactivosTotal.sizeMolecula");
//            System.out.println("A) " + productosTotal.sizeMolecula() + " = " + reactivosTotal.sizeMolecula());
            for (int i = 0; i < reactivosTotal.sizeMolecula(); i++) {
                atomo react = reactivosTotal.getAtomo(i);
                String simbReact = react.getSimbolo();
                int cantReact = react.getCantidad();

                for (int j = 0; j < productosTotal.sizeMolecula(); j++) {
                    atomo prod = productosTotal.getAtomo(j);
                    String simbProd = prod.getSimbolo();
                    int cantProd = prod.getCantidad();

                    if (simbProd.equals(simbReact)) {
//                        System.out.println("B"+j+") simbProd.equals(simbReact)");
//                        System.out.println("B"+j+")" + simbProd + " = " + simbReact);
                        if (cantProd != cantReact) {
//                            System.out.println("C) cantProd != cantReact");
//                            System.out.println("C) " + cantProd + " != " + simbReact);
                            igualada = false;
                        }
                    }

                }

            }
        }
        return igualada;
    }
}
