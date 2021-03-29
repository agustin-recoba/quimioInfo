package quimioInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


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

    static Map<String, String> getSymbolMap() {
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

    static Map<String, String> getNameMap() {

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

    public static int[] igualadorOrdn(Molecula a, Molecula b, Molecula c, Molecula d) {
        int[] resultado = new int[5];

        resultado[4] = 0;

        int i = 1;
        int j = 1;
        int k = 1;
        int l = 1;

        Molecula aX = new Molecula(a);
        Molecula bX = new Molecula(b);
        Molecula cX = new Molecula(c);
        Molecula dX = new Molecula(d);

        Molecula reactivosTotal =  new Molecula(a.getFormula() + b.getFormula());
        Molecula productosTotal =  new Molecula(c.getFormula() + d.getFormula());

        if (reactivosTotal.cantidadDeAtomosDist() != productosTotal.cantidadDeAtomosDist()) {
            resultado[4] = 1;

            resultado[0] = 0;
            resultado[1] = 0;
            resultado[2] = 0;
            resultado[3] = 0;
            return resultado;
        }

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

                            aX.multMoles(i);
                            bX.multMoles(j);
                            cX.multMoles(k);
                            dX.multMoles(l);

                            if (estaIgualada(aX, bX, cX, dX)) {
                                resultado[0] = i;
                                resultado[1] = j;
                                resultado[2] = k;
                                resultado[3] = l;
                                return resultado;
                            }
                            aX = new Molecula(a);
                            bX = new Molecula(b);
                            cX = new Molecula(c);
                            dX = new Molecula(d);
                        }

                        aX.multMoles(i);
                        bX.multMoles(j);
                        cX.multMoles(k);
                        dX.multMoles(l);

                        if (estaIgualada(aX, bX, cX, dX)) {
                            resultado[0] = i;
                            resultado[1] = j;
                            resultado[2] = k;
                            resultado[3] = l;
                            return resultado;
                        }
                        aX = new Molecula(a);
                        bX = new Molecula(b);
                        cX = new Molecula(c);
                        dX = new Molecula(d);
                    }

                    aX.multMoles(i);
                    bX.multMoles(j);
                    cX.multMoles(k);
                    dX.multMoles(l);

                    if (estaIgualada(aX, bX, cX, dX)) {
                        resultado[0] = i;
                        resultado[1] = j;
                        resultado[2] = k;
                        resultado[3] = l;
                        return resultado;
                    }
                    aX = new Molecula(a);
                    bX = new Molecula(b);
                    cX = new Molecula(c);
                    dX = new Molecula(d);
                }

                aX.multMoles(i);
                bX.multMoles(j);
                cX.multMoles(k);
                dX.multMoles(l);

                if (estaIgualada(aX, bX, cX, dX)) {
                    resultado[0] = i;
                    resultado[1] = j;
                    resultado[2] = k;
                    resultado[3] = l;
                    return resultado;
                }
                aX = new Molecula(a);
                bX = new Molecula(b);
                cX = new Molecula(c);
                dX = new Molecula(d);
            }

            contador++;
        }
    }

    public static boolean estaIgualada(Molecula a, Molecula b, Molecula c, Molecula d) {

        String reactivos = "";
        reactivos += a.getFormula();
        reactivos += b.getFormula();
        Molecula reactivosTotal = new Molecula(reactivos);

        String productos = "";
        productos += c.getFormula();
        productos += d.getFormula();
        Molecula productosTotal = new Molecula(productos);

        boolean igualada = true;

        if (reactivosTotal.cantidadDeAtomosDist() != productosTotal.cantidadDeAtomosDist()) {
            return false;
        }

        if (productosTotal.sizeMolecula() == reactivosTotal.sizeMolecula()) {
            
            for (int i = 0; i < reactivosTotal.sizeMolecula(); i++) {
                Atomo react = reactivosTotal.getAtomo(i);
                String simbReact = react.getSimbolo();
                int cantReact = react.getCantidad();

                for (int j = 0; j < productosTotal.sizeMolecula(); j++) {
                    Atomo prod = productosTotal.getAtomo(j);
                    String simbProd = prod.getSimbolo();
                    int cantProd = prod.getCantidad();

                    if (simbProd.equals(simbReact) && cantProd != cantReact) {
                        igualada = false;
                    }

                }

            }
        }
        return igualada;
    }
}
