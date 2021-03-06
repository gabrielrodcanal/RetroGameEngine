/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retrogameengine;

import java.util.HashSet;

/**
 *
 * @author gabriel
 */
public interface Drawable {
    public void draw();
    
    public void rotate(double theta);
    
    public void scale(float timesX, float timesY);
    
    public void shift(float h, float v);
    
    public HashSet<Integer> getPixels();
}
