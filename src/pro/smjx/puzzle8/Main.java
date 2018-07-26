/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.smjx.puzzle8;

import java.util.Random;

/**
 *
 * @author ProttoyX
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    final static private String INIT_STATE = "135702468";
    final static private String GOAL_STATE = "123456780";
    static String state;
    static Heuristic heuristic;

    public static void main(String[] args) {
        // TODO code application logic here
        if (args.length == 0) {
//            Random rand = new Random();
//            int n = rand.nextInt(29) + 1;
//
            args = new String[2];
            args[0] = "1";
//            args[1] = "" + n;

            System.out.println("");
            System.out.println("8 Puzzle require at least 1 arguments.");
            System.out.println("First argument is for Heuristic type in integer (1-3).");
            System.out.println("For example: java -jar 8-puzzle.jar 1");
            System.out.println("");

            //return;
        }

        heuristic = Heuristic.H_ONE;
        if (args[0].equals("1")) {
            heuristic = Heuristic.H_ONE;
        } else if (args[0].equals("2")) {
            heuristic = Heuristic.H_TWO;
        } else if (args[0].equals("3")) {
            heuristic = Heuristic.H_THREE;
        } else {
            heuristic = Heuristic.H_TWO;
        }

        for (int i = 0; i < 1000; i++) {
            try {

                state = "" + i;

                String ffn = "puzzle3x3/puzzle3x3-" + i + ".txt";
                String rootState = State.FromFile(ffn);
                System.out.println("\n\n\n");
                System.out.println(ffn);
                System.out.println(heuristic);
                System.out.println("\n\n\n");

                long startTime = System.currentTimeMillis();
                NodeUtil.startTime = startTime;

                SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
                search.aStar(heuristic);

                long finishTime = System.currentTimeMillis();
                long totalTime = finishTime - startTime;
                System.out.println("Time  :" + totalTime);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
