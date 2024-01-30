package map;

import java.util.Random;

/**
 *
 * @author https://stackoverflow.com/questions/5531019/perlin-noise-in-java
 */
public class Noise {
    /** Source of entropy */
    private Random rand;

    /** Amount of roughness */
    private float roughness;

    /** Plasma fractal grid */
    private float[][] grid;

    public float getNoiseAt(int x, int y) {
        return this.grid[x][y];
    }

    /**
     * Generate a noise source based upon the midpoint displacement fractal.
     * 
     * @param rand      The random number generator
     * @param roughness a roughness parameter
     * @param width     the width of the grid
     * @param height    the height of the grid
     */
    public Noise(Random rand, float roughness, int width, int height) {
        this.roughness = roughness / width;
        this.grid = new float[width][height];
        this.rand = (rand == null) ? new Random() : rand;
    }

    public void initialise() {
        int xh = this.grid.length - 1;
        int yh = this.grid[0].length - 1;

        // set the corner points
        this.grid[0][0] = this.rand.nextFloat() - 0.5f;
        this.grid[0][yh] = this.rand.nextFloat() - 0.5f;
        this.grid[xh][0] = this.rand.nextFloat() - 0.5f;
        this.grid[xh][yh] = this.rand.nextFloat() - 0.5f;

        // generate the fractal
        generate(0, 0, xh, yh);
    }

    // Add a suitable amount of random displacement to a point
    private float roughen(float v, int l, int h) {
        return v + this.roughness * (float) (this.rand.nextGaussian() * (h - l));
    }

    // generate the fractal
    private void generate(int xl, int yl, int xh, int yh) {
        int xm = (xl + xh) / 2;
        int ym = (yl + yh) / 2;
        if ((xl == xm) && (yl == ym))
            return;

        this.grid[xm][yl] = 0.5f * (this.grid[xl][yl] + this.grid[xh][yl]);
        this.grid[xm][yh] = 0.5f * (this.grid[xl][yh] + this.grid[xh][yh]);
        this.grid[xl][ym] = 0.5f * (this.grid[xl][yl] + this.grid[xl][yh]);
        this.grid[xh][ym] = 0.5f * (this.grid[xh][yl] + this.grid[xh][yh]);

        float v = roughen(0.5f * (this.grid[xm][yl] + this.grid[xm][yh]), xl + yl, yh + xh);
        this.grid[xm][ym] = v;
        this.grid[xm][yl] = roughen(this.grid[xm][yl], xl, xh);
        this.grid[xm][yh] = roughen(this.grid[xm][yh], xl, xh);
        this.grid[xl][ym] = roughen(this.grid[xl][ym], yl, yh);
        this.grid[xh][ym] = roughen(this.grid[xh][ym], yl, yh);

        generate(xl, yl, xm, ym);
        generate(xm, yl, xh, ym);
        generate(xl, ym, xm, yh);
        generate(xm, ym, xh, yh);
    }
}
