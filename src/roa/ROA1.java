/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roa;

import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class ROA1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int T[][] = {
            {-1, 18, 9, 8},
            {13, -1, 4, 11},
            {10, 8, -1, 10},
            {5, 7, 9, -1}
        };

        //afficherTableau(T);
        //verificationSymetrique(T);
//        calculNbreCircuit(T);
        reductionTableau(T, true);

    }

    static void afficherTableau(int T[][]) {
        for (int i = 0; i < T.length; i++) {
            for (int j = 0; j < T.length; j++) {
                System.out.print(T[i][j] + "\t");
            }
            System.out.println("");
        }
    }

    static void afficherVecteur(int T[]) {
        for (int i = 0; i < T.length; i++) {
            System.out.print(T[i] + "\t");
        }
        System.out.println("");
    }

    static void afficherVecteur(ArrayList T) {
        for (int i = 0; i < T.size(); i++) {
            System.out.print(T.get(i) + "\t");
        }
        System.out.println("");
    }

    static boolean verificationSymetrique(int T[][]) {
        ArrayList L = new ArrayList<>();
        ArrayList C = new ArrayList<>();

        for (int i = 0; i < T.length; i++) {

            for (int j = 0; j < T.length; j++) {
                if (i == 0 && T[i][j] != -1) {
                    L.add(T[i][j]);
                }

                if (j == 0 && T[i][j] != -1) {
                    C.add(T[i][j]);
                }

            }

        }

        boolean isSym = L.equals(C);
        if (isSym) {
            System.out.println("SYMETRIQUE");
        } else {
            System.out.println("NON SYMETRIQUE");
        }

        return isSym;
    }

    private static void calculNbreCircuit(int[][] T) {
        int result = getFactorial(T.length - 1);
        if (verificationSymetrique(T)) {
            result = result / 2;
        }
        System.out.println("Nombre de circuit possible: " + result);
    }

    public static int getFactorial(int f) {
        int result = 1;
        for (int i = 1; i <= f; i++) {
            result = result * i; // trouver la factorielle du nombre avec des boucles
        }
        return result;
    }

    private static void reductionTableau(int T[][], boolean enLigne) {

        if (enLigne) {
            reductionLigne(T);
        } else {
            reductionColone(T);
        }

        for (int i = 0; i < T.length; i++) {
            boolean hasNull = false;
            for (int j = 0; j < T.length; j++) {
                if (T[j][i] == 0) {
                    hasNull = true;
                }

            }
//            System.out.println(" A null "+hasNull);
            if (!hasNull) {
                reductionTableau(T, false);
            }
        }
//        afficherTableau(T);
    }

    static void reductionLigne(int T[][]) {
        ArrayList minimum = new ArrayList();
        ArrayList colMin = new ArrayList();
        ArrayList ligneMin = new ArrayList();
        ArrayList regre = new ArrayList();
        ArrayList arc=new ArrayList();
        int minL=0;
        int minC=0;

        for (int i = 0; i < T.length; i++) {
            int min = 10000;
            int indexl = 0;
            int indexc = 0;

            for (int j = 0; j < T.length; j++) {
                if (T[i][j] != -1 && T[i][j] < min) {
                    min = T[i][j];

                    indexl = i;
                    indexc = j;
                }

            }

            minimum.add(min);
            colMin.add(indexc);
            ligneMin.add(indexl);

//            System.out.println("MIN "+min);
            T[indexl][indexc] = 0;
            for (int k = 0; k < T.length; k++) {
                if (T[i][k] != 0 && T[i][k] != -1) {
                    T[i][k] -= min;
                }
            }
            System.out.println("");
        }

        afficherTableau(T);
        int kk=-100;
        for (int i = 0; i < T.length; i++) {
//            System.out.println(ligneMin.get(i)+"X"+colMin.get(i));
            int ml = 10000;
            int mc = 10000;
            for (int j = 0; j < T.length; j++) {
                if (T[Integer.parseInt(ligneMin.get(i).toString())][j] < ml && T[Integer.parseInt(ligneMin.get(i).toString())][j] != -1 && T[Integer.parseInt(ligneMin.get(i).toString())][j] != 0) {
                    ml = T[Integer.parseInt(ligneMin.get(i).toString())][j];
                    minL=j;
                }
                if (T[j][Integer.parseInt(colMin.get(i).toString())] < mc && T[j][Integer.parseInt(colMin.get(i).toString())] != -1 && T[j][Integer.parseInt(colMin.get(i).toString())] != 0) {
                    mc = T[j][Integer.parseInt(colMin.get(i).toString())];
                    minC=j;
                }
            }
            int res_regre = ml + mc;
            System.out.println(ml + " + " + mc + "=" + res_regre);
            regre.add(ml + mc);
            if((ml+mc)>kk){
                kk=ml+mc;
                
            }

//            System.out.print(mc);
        }
        System.err.println("KKKK "+kk);

        System.out.println("MAX REGRE " + maxVecteur(regre)+ " ML "+minL+" minC "+minC);
//        System.out.println("Ligne "+ml+ " Colonne "+mc);

        afficherVecteur(regre);
//        System.out.println("");
//        afficherVecteur(ligneMin);
//        afficherTableau(T);

//        for(int i=0;i<T.length;i++){
//            ArrayList RL=new ArrayList();
//            ArrayList RC=new ArrayList();
//            for (int j = 0; j < T.length; j++) {
//                System.out.print(T[j][i]);
//                RL.add(T[j][Integer.parseInt(ligneMin.get(j).toString())]);
//                RC.add(T[Integer.parseInt(colMin.get(j).toString())][j]);
//            }
//            System.out.println("");
//          //  regre.add((Integer.parseInt(RL.get(i).toString())-Integer.parseInt(RC.get(i).toString())));
//        }
//        for (int i = 0; i < regre.size(); i++) {
//            System.out.println(regre.get(i));
//        }
//        calculBorneInferieurCircuit(minimum);
    }

    static void reductionColone(int T[][]) {
        for (int i = 0; i < T.length; i++) {
            int min = 10000;
            int indexl = 0;
            int indexc = 0;
            for (int j = 0; j < T.length; j++) {
                if (T[j][i] != -1 && T[j][i] < min) {
                    min = T[j][i];
                    indexl = i;
                    indexc = j;
                }

            }
//            System.out.println("MIN "+min);
            T[indexl][indexc] = 0;
            for (int k = 0; k < T.length; k++) {
                if (T[k][i] != 0 && T[k][i] != -1) {
                    T[k][i] -= min;
                }
            }
            System.out.println("");
        }
    }

//     ETAPE 2
    static int calculBorneInferieurCircuit(ArrayList minimum) {
        int res = 0;
        String text = "";

        for (int i = 0; i < minimum.size(); i++) {
            res += Integer.parseInt(minimum.get(i).toString());
            text = minimum.get(i) + "+";
            System.out.print(minimum.get(i) + "+");
        }
//         System.out.println(text.substring(0, text.length()-1)+"="+res);
        return res;
    }

    static int minVecteur(int V[]) {
        int res = 10000;

        for (int i = 0; i < V.length; i++) {
            if (V[i] < res) {
                res = V[i];
            }
        }
        return res;
    }

    static int maxVecteur(int V[]) {
        int res = -10000;

        for (int i = 0; i < V.length; i++) {
            if (V[i] > res) {
                res = V[i];
            }
        }
        return res;
    }

    static int maxVecteur(ArrayList V) {
        int res = -10000;

        for (int i = 0; i < V.size(); i++) {
            if (Integer.parseInt(V.get(i).toString()) > res) {
                res = Integer.parseInt(V.get(i).toString());
            }
        }
        return res;
    }
}
