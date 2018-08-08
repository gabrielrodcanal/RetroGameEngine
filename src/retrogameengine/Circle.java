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
    private double radius;
    private int x0, y0;
    private int e_length;
    private double alpha, beta;
    
    public Circle(int x0, int y0, double radius, RetroGameEngine engine, Color border_colour) {
        super(DEF_NSIDES, x0, y0, (int)createELength(radius)[0], engine, border_colour);
        this.radius = radius;
        this.x0 = x0;
        this.y0 = y0;
        double[] ang_length = createELength(radius);
        this.e_length = (int)ang_length[0];
        this.alpha = ang_length[1];
        this.beta = ang_length[2];
    }
    
    private static double[] createELength(double radius) {
        double alpha = Math.PI * (1/2 - 1/DEF_NSIDES);
        double beta = Math.PI/2 - alpha;
        double e_length = (2 * Math.sqrt(Math.pow(radius, 2) * (1 + Math.pow(Math.cos(beta),2))));
        
        return new double[] {e_length,alpha,beta};
    }
    
    public int[] getCenter() {
        int[] x0_y0 = getEdge(0).getStart();
        return new int[] {(int)(x0_y0[0] + e_length/2 + radius), (int)(x0_y0[1] + radius * Math.sin(alpha))};
    }
    
    public void setCenter(int x_center, int y_center) {
        this.getEdge(0).setStart(new int[] {(int)(x_center - (e_length/2 +radius)),
        (int)(y_center - radius * Math.sin(alpha))});
    }
}
