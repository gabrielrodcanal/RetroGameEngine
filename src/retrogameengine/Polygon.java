/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.Color;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author gabriel
 */
public class Polygon implements Drawable {
    private int n_sides;
    private double theta;
    private Line[] edges;
    private RetroGameEngine engine;
    private int[] gfx;
    private HashSet<Integer> poly_pixels;
    
    public Polygon(int n_sides, int x0, int y0, int e_length, RetroGameEngine engine, Color e_colour) {
        this.n_sides = n_sides;
        theta =  Math.PI - (n_sides - 2) * Math.PI / (n_sides);
        this.engine = engine;
        edges = new Line[n_sides];
        edges[0] = new Line(x0, y0, x0 + e_length, y0, engine,e_colour);
        double angle = theta;
        gfx = engine.getGfx();
        poly_pixels = new HashSet<Integer>();        
        for(int i = 1; i < n_sides; i++) {
            try {
                edges[i] = (Line)edges[i-1].clone();
                edges[i].setStart(edges[i-1].getEnd());
                edges[i].rotate(angle);
                edges[i].setInitialTheta();
                angle += theta;
            }
            catch(CloneNotSupportedException e) {}
        }
        getPixels();
    }

    @Override
    public void draw() {
        for(Line edge : edges)
            edge.draw();
    }

    @Override
    public void rotate(double alpha) {
        
        //System.out.println("Theta: " + edges[0].getInitialTheta() + " alpha: " + alpha);
        edges[0].rotate(edges[0].getInitialTheta() + alpha);
        for(int i = 1; i < n_sides; i++) {
            edges[i].setStart(edges[i-1].getEnd());
            edges[i].rotate(edges[i].getInitialTheta() + alpha);
        }
        getPixels();
    }

    @Override
    public void scale(float timesX, float timesY) {
        edges[0].scale(timesX,timesY);
        for(int i = 1; i < n_sides; i++) {
            edges[i].scale(timesX,timesY);
            edges[i].setStart(edges[i-1].getEnd());
        }
        getPixels();
    }

    @Override
    public void shift(float h, float v) {
        edges[0].shift(h, v);
        for(int i = 1; i < n_sides; i++) {
            edges[i].setStart(edges[i-1].getEnd());
        }
        getPixels();
    }    
    
    @Override
    public HashSet<Integer> getPixels() {
        poly_pixels.clear();
        List<int[]> pixels = edges[0].getPixelsXY();
        
        for(int i = 1; i < n_sides; i++)
            pixels.addAll(edges[i].getPixelsXY());
        
        pixels.sort((int[] p0, int[] p1) -> Integer.compare(p0[1],p1[1]));
        
        int x0, x1, y;
        int[] elem;
        
        //x coordinates grouped by y coordinate
        ArrayList<Integer> group = new ArrayList<Integer>();
        group.add(pixels.get(0)[0]);
        y = pixels.get(0)[1];
        
        for(int i = 1; i < pixels.size(); i++) {
            elem = pixels.get(i);
            
            if(elem[1] != pixels.get(i-1)[1]) {
                x0 = Collections.min(group);
                x1 = Collections.max(group);
                
                for(int j = x0; j < x1; j++)
                    poly_pixels.add(j + y);
                
                group.clear();
                y = elem[1];
            }
            
            group.add(elem[0]);
        }
        
        return poly_pixels;
    }
    
    /**
     * Fills the inner polygon space with the chosen colour. This method should be applied before draw to avoid
     * undesired overlapping.
     * @param colour 
     */    
    public void fill(Color colour) {
        int int_colour = colour.getRGB();
        Iterator<Integer> it = poly_pixels.iterator();
        while(it.hasNext())
            gfx[it.next()] = int_colour;
    }
    
    public boolean hasCollided(Drawable other) {
        if(other != null) {
            Iterator<Integer> other_it = other.getPixels().iterator();
            while(other_it.hasNext())
                if(poly_pixels.contains(other_it.next()))
                    return true;
        }
        
        return false;
    }
    
    public RetroGameEngine getEngine() {
        return engine;
    }
    
    public Line getEdge(int n) {
        return edges[n];
    }
}
