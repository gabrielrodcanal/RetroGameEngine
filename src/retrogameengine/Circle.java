/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

import java.awt.Color;

/**
 *
 * @author gabriel
 */
public class Circle extends Polygon {
    private final static int DEF_NSIDES = 15;
    
    
    public Circle(int x0, int y0, double radius, RetroGameEngine engine, Color border_colour) {
        super(DEF_NSIDES, x0, y0, createELength(radius), engine, border_colour);
    }
    
    private static int createELength(double radius) {
        double alpha = Math.PI * (1/2 - 1/DEF_NSIDES);
        double beta = Math.PI/2 - alpha;
        int e_length = (int) (2 * Math.sqrt(Math.pow(radius, 2) * (1 + Math.pow(Math.cos(beta),2))));
        
        return e_length;
    }
}
