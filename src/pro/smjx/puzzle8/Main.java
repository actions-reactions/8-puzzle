/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.smjx.puzzle8;

/**
 *
 * @author ProttoyX
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    final static private String MEDIUM = "135702468";
    final static private String GOAL_STATE = "123456780";

    public static void main(String[] args) {
        // TODO code application logic here
        String rootState = MEDIUM;
        long startTime = System.currentTimeMillis();

        SearchTree search = new SearchTree(new Node(rootState), GOAL_STATE);
        search.aStar(Heuristic.H_ONE);

        long finishTime = System.currentTimeMillis();
        long totalTime = finishTime - startTime;
        System.out.println("Time  :" + totalTime);
    }

}
