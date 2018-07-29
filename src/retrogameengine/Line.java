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
    private float length;
    private int dx, dy;
    
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
        draw();
    }
    
    
    @Override
    public void scale(float times) {
        
    }
    
    @Override
    public void shift(float v, float h) {
        
    }
}
