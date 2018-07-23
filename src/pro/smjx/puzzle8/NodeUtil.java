/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.smjx.puzzle8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ProttoyX
 */
public class NodeUtil {
    
    static long startTime;

    public static List<String> getSuccessors(String state) {
        List<String> successors = new ArrayList<String>();

        switch (state.indexOf("0")) {
            case 0: {
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(1), state.charAt(0)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(0), '*').replace(state.charAt(3), state.charAt(0)).replace('*', state.charAt(3)));
                break;
            }
            case 1: {
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(0), state.charAt(1)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(2), state.charAt(1)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(1), '*').replace(state.charAt(4), state.charAt(1)).replace('*', state.charAt(4)));
                break;
            }
            case 2: {

                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(1), state.charAt(2)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(2), '*').replace(state.charAt(5), state.charAt(2)).replace('*', state.charAt(5)));
                break;
            }
            case 3: {
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(0), state.charAt(3)).replace('*', state.charAt(0)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(4), state.charAt(3)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(3), '*').replace(state.charAt(6), state.charAt(3)).replace('*', state.charAt(6)));
                break;
            }
            case 4: {
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(1), state.charAt(4)).replace('*', state.charAt(1)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(3), state.charAt(4)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(5), state.charAt(4)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(4), '*').replace(state.charAt(7), state.charAt(4)).replace('*', state.charAt(7)));
                break;
            }
            case 5: {
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(2), state.charAt(5)).replace('*', state.charAt(2)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(4), state.charAt(5)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(5), '*').replace(state.charAt(8), state.charAt(5)).replace('*', state.charAt(8)));
                break;
            }
            case 6: {
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(3), state.charAt(6)).replace('*', state.charAt(3)));
                successors.add(state.replace(state.charAt(6), '*').replace(state.charAt(7), state.charAt(6)).replace('*', state.charAt(7)));
                break;

            }
            case 7: {
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(4), state.charAt(7)).replace('*', state.charAt(4)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(6), state.charAt(7)).replace('*', state.charAt(6)));
                successors.add(state.replace(state.charAt(7), '*').replace(state.charAt(8), state.charAt(7)).replace('*', state.charAt(8)));
                break;
            }
            case 8: {
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(5), state.charAt(8)).replace('*', state.charAt(5)));
                successors.add(state.replace(state.charAt(8), '*').replace(state.charAt(7), state.charAt(8)).replace('*', state.charAt(7)));
                break;
            }
        }

        return successors;

    }

    /**
     *
     * prints the transitions along with the states starting from the initial
     * states to the goal state.
     *
     * @author Himan Abdollahpouri
     *
     */
    public static void printSolution(Node goalNode, Set<String> visitedNodes, Node root, int time) {

        int totalCost = 0;

        Stack<Node> solutionStack = new Stack<Node>();
        solutionStack.push(goalNode);
        while (!goalNode.getState().equals(root.getState())) {
            solutionStack.push(goalNode.getParent());
            goalNode = goalNode.getParent();
        }
        String sourceState = root.getState();
        String destinationState;
        int cost = 0;
        for (int i = solutionStack.size() - 1; i >= 0; i--) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            destinationState = solutionStack.get(i).getState();
            if (!sourceState.equals(destinationState)) {
                System.out.println("Move " + destinationState.charAt(sourceState.indexOf('0')) + " " + findTransition(sourceState, destinationState));
                cost = Character.getNumericValue(destinationState.charAt(sourceState.indexOf('0')));
                totalCost += cost;
            }

            sourceState = destinationState;
            System.out.println("Cost of the movement: " + cost);
            System.out.println("*******");
            System.out.println("* " + solutionStack.get(i).getState().substring(0, 3) + " *");
            System.out.println("* " + solutionStack.get(i).getState().substring(3, 6) + " *");
            System.out.println("* " + solutionStack.get(i).getState().substring(6, 9) + " *");
            System.out.println("*******");

        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("** Number of transitions to get to the goal state from the initial state:  " + (solutionStack.size() - 1));
        System.out.println("** Number of visited states:  " + (visitedNodes.size()));
        System.out.println("** Total cost for this solution: " + totalCost);
        System.out.println("** Number of Nodes poped out of the queue: " + time);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        
        PrintWriter pw;
        try {
            StringBuilder sb = new StringBuilder();

            try(BufferedReader br = new BufferedReader(new FileReader("test.csv"))) {
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();
            } catch (IOException ex) {
                sb.append("State");
                sb.append(',');
                sb.append("Huristic");
                sb.append(',');
                sb.append("No. of State");
                sb.append(',');
                sb.append("visited states");
                sb.append(',');
                sb.append("Total cost");
                sb.append(',');
                sb.append("Number of Nodes poped out of the queue");
                sb.append(',');
                sb.append("Total Time");
                sb.append('\n');
            }
            System.out.println(Paths.get("test.csv").toAbsolutePath());
            pw = new PrintWriter(new File("test.csv"));

            sb.append(Main.state);
            sb.append(',');
            sb.append(Main.heuristic);
            sb.append(',');
            sb.append(solutionStack.size() - 1);
            sb.append(',');
            sb.append(visitedNodes.size());
            sb.append(',');
            sb.append(totalCost);
            sb.append(',');
            sb.append(time);
            sb.append(',');
            sb.append(System.currentTimeMillis() - startTime);
            sb.append('\n');
            
            pw.write(sb.toString());
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(NodeUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//*******************************************************************************************
    /**
     *
     * @return returns the transition between two states.
     *
     * @author Himan Abdollahpouri
     *
     */
    public static MovementType findTransition(String source, String destination) {
        int zero_position_difference = destination.indexOf('0') - source.indexOf('0');
        switch (zero_position_difference) {
            case -3:
                return MovementType.DOWN;
            case 3:
                return MovementType.UP;
            case 1:
                return MovementType.LEFT;
            case -1:
                return MovementType.RIGHT;
        }
        return null;
    }
}
