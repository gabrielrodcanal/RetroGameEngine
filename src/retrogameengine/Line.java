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
public class Line implements Drawable {
    private int x0, y0, x1, y1;
    private int[] gfx;
    private RetroGameEngine engine;
    private int scr_width, scr_height;
    private double length;
    private int dx, dy;
    private double theta;
    
    public Line(int x0, int y0, int x1, int y1, RetroGameEngine engine) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.engine = engine;
        gfx = engine.getGfx();
        this.scr_width = engine.getScrSize()[0];
        this.scr_height = engine.getScrSize()[1];
        
        dx = x1 - x0;
        dy = y1 - y0;
        length = (float)Math.sqrt((Math.pow((double)dx,2) + Math.pow((double)dy,2)));
        theta = Math.atan(dy/(double)dx);
        
    }
    
    @Override
    public void draw() {
        int steps = Math.abs(dy) > Math.abs(dx) ? Math.abs(dy) : Math.abs(dx);

        float xInc = dx / (float)steps;
        float yInc = dy / (float)steps;

        float x = x0;
        float y = y0;

        for(int i = 0; i < steps; i++) {
            gfx[Math.round(x) + Math.round(y) * scr_width] = 1;
            x += xInc;
            y += yInc;
        }
    }
    
    @Override
    public void rotate(double theta) {
        x1 = x0 + (int)(length * Math.cos(theta));
        y1 = y0 + (int)(length * Math.sin(theta));
        dx = x1 - x0;
        dy = y1 - y0;
        this.theta = Math.atan(dy/(double)dx);
    }
    
    
    @Override
    public void scale(float times) {
        double s_length = times * length;
        int sign = s_length < length ? 1 : -1;
        
        x0 += sign*(s_length/2) * Math.cos(theta);
        y0 += sign*(s_length/2) * Math.sin(theta);
        x1 -= sign*(s_length/2) * Math.cos(theta);
        y1 -= sign*(s_length/2) * Math.sin(theta);
        dx = x1 - x0;
        dy = y1 - y0;
    }
    
    @Override
    public void shift(float h, float v) {
        x0 += h;
        x1 += h;
        y0 += v;
        y1 += v;        
    }
    
    public double getLength() {
        return length;
    }
    
    public int[] getStart() {
        return new int[] {x0,y0};
    }
    
    public int[] getEnd() {
        return new int[] {x1,y1};
    }
    
    public void setStart(int[] start) {
        x1 += start[0] - x0;
        x0 = start[0];
        y1 += start[1] - y0;
        y0 = start[1];
        draw();
    }
    
    public void setEnd(int[] end) {
        x0 += end[0] - x1;
        x1 = end[0];
        y0 += end[1] - y1;
        y1 = end[1];
        draw();
    }
}
