/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

/**
 *
 * @author gabriel
 */
public class Polygon implements Drawable {
    private int n_sides;
    private double theta;
    private Line[] edges;
    private RetroGameEngine engine;
    
    public Polygon(int n_sides, int x0, int y0, int e_length, RetroGameEngine engine) {
        this.n_sides = n_sides;
        theta =  Math.PI - (n_sides - 2) * Math.PI / (n_sides);
        this.engine = engine;
        edges = new Line[n_sides];
        edges[0] = new Line(x0, y0, x0 + e_length, y0, engine);
        double angle = theta;
        for(int i = 1; i < n_sides; i++) {
            try {
                edges[i] = (Line)edges[i-1].clone();
                edges[i].setStart(edges[i-1].getEnd());
                edges[i].rotate(angle);
                angle += theta;
            }
            catch(CloneNotSupportedException e) {}
        }
    }

    @Override
    public void draw() {
        for(Line edge : edges)
            edge.draw();
    }

    @Override
    public void rotate(double alpha) {
        edges[0].rotate(alpha);
        for(int i = 1; i < n_sides; i++) {
            edges[i].setStart(edges[i-1].getEnd());
            edges[i].rotate(edges[i].getTheta() + alpha);
        }
    }

    @Override
    public void scale(float times) {
        edges[0].scale(times);
        for(int i = 1; i < n_sides; i++) {
            edges[i].scale(times);
            edges[i].setStart(edges[i-1].getEnd());
        }
    }

    @Override
    public void shift(float h, float v) {
        edges[0].shift(h, v);
        for(int i = 1; i < n_sides; i++) {
            edges[i].setStart(edges[i-1].getEnd());
        }
    }
      
}
