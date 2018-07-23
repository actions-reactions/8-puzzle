/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro.smjx.puzzle8;

/**
 *
 * @author Alim
 */
public class State {
    String file;

    /**
     *
     * @param filename
     */
    public static String FromFile(String filename ){
        String state = "";
        In in = new In(filename);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                state += in.readInt();
        return state;
    }
}
