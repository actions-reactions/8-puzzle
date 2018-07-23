/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.smjx.puzzle8;

import java.util.Comparator;

/**
 *
 * @author ProttoyX
 */
public class NodePriorityComparator implements Comparator<Node> {

    @Override
    public int compare(Node x, Node y) {
        if (x.getTotalCost() < y.getTotalCost()) {
            return -1;
        }
        if (x.getTotalCost() > y.getTotalCost()) {
            return 1;
        }
        return 0;
    }
}
