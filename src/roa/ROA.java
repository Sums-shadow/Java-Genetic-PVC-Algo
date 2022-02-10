/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author USER
 */
public class ROA {

    static ArrayList minimum = new ArrayList();

    static ArrayList regre = new ArrayList();
    static ArrayList arc = new ArrayList();
    static int minL=0;
    static int minC=0;
    static ArrayList B = new ArrayList();
    static int BB = 0;
    static ArrayList BR = new ArrayList();
    static ArrayList R = new ArrayList();
    static ArrayList RB = new ArrayList();
    static ArrayList RP = new ArrayList();

    static ArrayList ROOT = new ArrayList();

    static Scanner input = new Scanner(System.in);

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
        afficherTableau(T);
        String r = input.nextLine();
        reductionTableau(T, true);

    }

    static void reductionLigne(int T[][], boolean calculTetra) {
//         System.err.println("********************");
//         afficherTableau(T);
//                  System.err.println("********************");
           int minL = 0;
     int minC = 0;
        ArrayList minColon = new ArrayList();
        ArrayList colMin = new ArrayList();
        ArrayList ligneMin = new ArrayList();
        System.out.println("PHASE I");
        String r = input.nextLine();
        afficherTableau(T);
        for (int i = 0; i < T.length; i++) {
            int min = 10000;
            int indexl = 0;
            int indexc = 0;
//            int min2 = 10000;

            for (int j = 0; j < T.length; j++) {
                if (T[i][j] != -1 && T[i][j] < min && T[i][j] != -9) {
                    min = T[i][j];

                    indexl = i;
                    indexc = j;
                }
              
//                if (T[j][i] != -1 && T[j][i] < min2 && T[j][i] != -9) {
//                    min2 = T[j][i];
//
//            }
            
            }
//            minColon.add(min2);
            minimum.add(min);
            colMin.add(indexc);
            ligneMin.add(indexl);

            //Reduction tableau et soustraction avec le minimum
            //T[indexl][indexc] = 0;
            for (int k = 0; k < T.length; k++) {
                if (T[i][k] != 0 && T[i][k] != -1 && T[i][k] != -9) {
                    T[i][k] -= min;
                }
            }
            
            
//             int min2 = 10000;
//
//            for (int j = 0; j < T.length; j++) {
//                
//              
//                if (T[j][i] != -1 && T[j][i] < min2 && T[j][i] != -9) {
//                    min2 = T[j][i];
//
//            }
//            
//            }
//            minColon.add(min2);

        }
        
        
          for (int i = 0; i < T.length; i++) {
            int min2 = 10000;

            for (int j = 0; j < T.length; j++) {
              
                if (T[j][i] != -1 && T[j][i] < min2 && T[j][i] != -9) {
                    min2 = T[j][i];

                }
            }
            minColon.add(min2);
         
        }

        System.out.println("TABLEAU REDUIT "+minColon.toString());
        afficherTableau(T);
        String rr = input.nextLine();
        int somVectMin = sommeVecteur(minimum);
        System.out.println("B=" + somVectMin);
        rr = input.nextLine();
        R.add(somVectMin);
        int tetra = 0;
        if (calculTetra) {
            tetra = calculTetra(T, ligneMin, colMin);
            RB.add(tetra + sommeVecteur(minimum));
            //  System.out.println(tetra);
        } else {

        }

        int TT[][] = new int[T.length][T.length];

        for (int i = 0; i < T.length; i++) {

            for (int j = 0; j < T.length; j++) {
                if (i != minL && j != minC) {
                    TT[i][j] = T[i][j];

                } else {
                    TT[i][j] = -8;
                }

            }

        }
//             afficherTableau(TT);
        reductionTableau2(TT);

    }

    static void reductionTableau2(int T[][]) {
//        System.err.println(T[minC][minL]);
//        afficherTableau(T);
        System.out.println("minC "+minC+" minL "+minL);
        String rr=input.nextLine();
        T[minC][minL] = -9;
        int[][] TTMP = new int[T.length - 1][T.length - 1];
        ArrayList ttm = new ArrayList<Integer>();

        int n = 0;
        for (int i = 0; i < T.length; i++) {
            if (i != minL) {
                ArrayList ttk = new ArrayList<>();
                for (int j = 0; j < T.length; j++) {
                    if (j != minC) {
                        ttk.add(T[i][j]);
                    }
//                TTMP[n++][j]=T[i][j];

//                    System.out.print(T[n][j]+"\t");
                }
                ttm.add(ttk);
                n++;

            }
            //   System.out.println("");
        }
        int j = 0;
//        System.out.println(ttm.get(0));
        for (Object ff : ttm) {
            //System.err.println(ff);
//            int[] d=ff.toString().substring(1, ff.toString().length()-1).split(",");

            for (int i = 0; i < ((ArrayList) ff).toArray().length; i++) {
                //   System.err.print(((ArrayList) ff).toArray()[i]+"\t");
                TTMP[j][i] = (Integer) ((ArrayList) ff).toArray()[i];
            }
            j++;
            // System.out.println("");

        }

        reduireTableau3(TTMP);

    }

    static void reduireTableau3(int T[][]) {
//     afficherTableau(T);
        //tableau temporaire pour recuperer le min 
        int min = 10000;
        for (int i = 0; i < T.length; i++) {
            if (T[minL][i] < min && T[minL][i] != -9) {
                min = T[minL][i];
            }

        }

        ArrayList Lmin = new ArrayList();
        ArrayList Cmin = new ArrayList();

        //calcule de minimum de chaque ligne et colonne et addition
        for (int i = 0; i < T.length; i++) {
            int minli = 10000;
            int minco = 10000;

            for (int j = 0; j < T.length; j++) {
                if (T[i][j] != -1 && T[i][j] != -9) {
                    if (T[i][j] < minli) {
                        minli = T[i][j];
                    }
                }

                if (T[j][i] != -1 && T[j][i] != -9) {
                    if (T[j][i] < minco) {
                        minco = T[i][j];
                    }
                }

            }
            Lmin.add(minli);
            Cmin.add(minco);

        }
//        afficherVecteur(Lmin);
//        afficherVecteur(Cmin);
        int res = sommeVecteur(Lmin) + sommeVecteur(Cmin);

        res = (Integer) R.get(R.size() - 1) + res;
        // System.err.println("RES "+res);
        RP.add(res);

        
        int[][] TCOPY=new int[T.length][T.length];
        //copy tableau
        for (int i = 0; i < T.length; i++) {

            for (int j = 0; j < T.length; j++) {
                 
                    TCOPY[i][j] = T[i][j];
              

            }
        }
        
        
        //Soustraction avec le minimum
        for (int i = 0; i < T.length; i++) {

            for (int j = 0; j < T.length; j++) {
                if (T[i][j] != -1 && T[i][j] != 0 && T[i][j] != -9) {
                    T[i][j] -= min;
                }

            }
        }
        ROOT.add(Math.min((Integer) RB.get(RB.size() - 1), (Integer) RP.get(RP.size() - 1)));

        System.out.println("B BARRE " + RB.get(RB.size() - 1));
        System.out.println("B SIMPLE " + RP.get(RP.size() - 1));
        System.err.println("CHEMIN PRISE " + ROOT.get(ROOT.size() - 1));
        String rr = input.nextLine();

        if (T.length > 1) {
            //verifie si RB<R
//           if(((Integer)RB.get(RB.size()-1))<((Integer)R.get(R.size())-1)){
            reductionLigne(T, true);
            //         }
        } else {
            System.out.println("FIN");
        }
//        afficherTableau(T);

    }

    static int calculTetra(int T[][], ArrayList ligneMin, ArrayList colMin) {
        int kk = -100;
         minL=0;
         minC=0;
        System.out.println("Calcul Tetra " + T.length + " " + ligneMin.toString() + " " + colMin.toString());
        String rr = input.nextLine();
        afficherTableau(T);
        rr = input.nextLine();
        for (int i = 0; i < T.length; i++) {
            int ml = 10000;
            int mc = 10000;
            for (int j = 0; j < T.length; j++) {
                if (T[Integer.parseInt(ligneMin.get(i).toString())][j] < ml && T[Integer.parseInt(ligneMin.get(i).toString())][j] != -1 && T[Integer.parseInt(ligneMin.get(i).toString())][j] != 0 && T[Integer.parseInt(ligneMin.get(i).toString())][j] != -9) {
                    ml = T[Integer.parseInt(ligneMin.get(i).toString())][j];
                    minL = j;
                }
                if (T[j][Integer.parseInt(colMin.get(i).toString())] < mc && T[j][Integer.parseInt(colMin.get(i).toString())] != -1 && T[j][Integer.parseInt(colMin.get(i).toString())] != 0 && T[j][Integer.parseInt(colMin.get(i).toString())] != -9) {
                    mc = T[j][Integer.parseInt(colMin.get(i).toString())];
                    minC = j;
                }
//                if(ml==10000 && T[Integer.parseInt(ligneMin.get(i).toString())][j] != -1 && T[Integer.parseInt(ligneMin.get(i).toString())][j] != -9)ml=0;
//                if(mc==10000 && T[j][Integer.parseInt(colMin.get(i).toString())] != -1 && T[j][Integer.parseInt(colMin.get(i).toString())] != -9)mc=0;
            }
             if(mc==10000)mc=0;
              if(ml==10000)ml=0;
            int res_regre = ml + mc;
            System.out.println("min ligne " + ml + " + min colonne " + mc + "= regre " + res_regre);
            rr = input.nextLine();
            regre.add(ml + mc);
            if ((ml + mc) > kk) {
                kk = ml + mc;
            }

        }
        System.err.println("Regre=" + kk);
        rr = input.nextLine();
        return kk;
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

    private static void reductionTableau(int T[][], boolean enLigne) {
//        System.out.println(T.length);
        if (T.length != 1) {
            //Verification de reduction en ligne ou en colonne
            if (enLigne) {
                reductionLigne(T, true);
            } else {
                reductionColone(T);
            }

            //Verification si toute les colonnes ont 0
            for (int i = 0; i < T.length; i++) {
                boolean hasNull = false;
                for (int j = 0; j < T.length; j++) {
                    if (T[j][i] == 0) {
                        hasNull = true;
                    }
                }
                if (!hasNull) {
                    //  reductionTableau(T, false);
                }
            }
        }
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

    static int sommeVecteur(ArrayList V) {
        int res = 0;

        for (int i = 0; i < V.size(); i++) {
            res += Integer.parseInt(V.get(i).toString());
        }
        return res;
    }

}
