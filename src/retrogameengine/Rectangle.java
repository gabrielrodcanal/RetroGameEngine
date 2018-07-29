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
public class Rectangle implements Drawable {
    private int x0, y0, x1, y1;
    private Line[] edges;
    private RetroGameEngine engine;
    
    public Rectangle(int x0, int y0, int x1, int y1, RetroGameEngine engine) {
        //top left vertex
        this.x0 = x0;
        this.y0 = y0;
        //bottom right vertex
        this.x1 = x1;
        this.y1 = y1;
        this.engine = engine;
        
        edges = new Line[4];
        edges[0] = new Line(x0,y0,x1,y0,engine);
        edges[1] = new Line(x1,y0,x1,y1,engine);
        edges[2] = new Line(x0,y1,x1,y1,engine);
        edges[3] = new Line(x0,y0,x0,y1,engine);
    }

    @Override
    public void draw() {
        for(int i = 0; i < 4; i++) 
            edges[i].draw();
    }

    @Override
    public void rotate(double theta) {
        edges[0].rotate(theta);
        edges[0].draw();
        
        edges[1].rotate(theta + Math.PI / 2);
        edges[1].setStart(edges[0].getEnd());
        
        edges[2].rotate(theta);
        edges[2].setEnd(edges[1].getEnd());
        
        edges[3].rotate(theta + Math.PI / 2);
        edges[3].setEnd(edges[2].getStart());
    }

    @Override
    public void scale(float times) {
        edges[0].scale(times);
        edges[0].draw();
        
        edges[1].scale(times);
        edges[1].setStart(edges[0].getEnd());
        
        edges[2].scale(times);
        edges[2].setEnd(edges[1].getEnd());
        
        edges[3].scale(times);
        edges[3].setEnd(edges[2].getStart());
    }

    @Override
    public void shift(float h, float v) {
        x0 += h;
        x1 += h;
        y0 += v;
        y1 += v;
        for(int i = 0; i < 4; i++) {
            edges[i].shift(h,v);
            edges[i].draw();
        }
    }
}
