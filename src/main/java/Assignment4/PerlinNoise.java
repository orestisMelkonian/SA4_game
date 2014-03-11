package Assignment4;

import java.util.Random;


public class PerlinNoise
{
    private final long seed;
    private final Random rand;
    private final int octave;
     
    public PerlinNoise(final long seed, final int octave)
    {
        this.seed = seed;
        this.octave = octave;
        rand = new Random();
    }
     
    public double getNoiseLevelAt(final int x, final int z)
    {
        final int xmin = (int)((double)x / octave);
        final int xmax = xmin + 1;
        final int zmin = (int)((double)z / octave);
        final int zmax = zmin + 1;
         
        final double ra = getRandomAt(xmin, zmin);
        final double rb = getRandomAt(xmax, zmin);
        final double rc = getRandomAt(xmax, zmax);
        final double rd = getRandomAt(xmin, zmax);
         
        final double ret = cosineInterpolate(
                cosineInterpolate((float)ra, (float)rb, (float)(x - xmin * octave) / octave),
                cosineInterpolate((float)rd, (float)rc, (float)(x - xmin * octave) / octave),
                ((float)z - (float)zmin * (float)octave) / (float)octave);
        return ret;
    }
     
    private final double getRandomAt(final int x, final int z)
    {
        final double s = 10000 * (Math.sin(x) + Math.cos(z) + Math.tan(seed));
        rand.setSeed((long)s);
        final double ret = rand.nextDouble();
        return ret;
    }
     
    private final float cosineInterpolate(final float a, final float b, final float x)
    {
        final float ft = (float)(x * Math.PI);
        final float f = (float)((1f - Math.cos(ft)) * .5f);
        final float ret = a * (1f - f) + b * f;
        return ret;
    }
}
