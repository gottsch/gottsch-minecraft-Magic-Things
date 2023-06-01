package mod.gottsch.forge.gealdorcraft.core.size;

import mod.gottsch.forge.gottschcore.random.RandomHelper;

import java.util.Objects;
import java.util.Random;

/**
 * Created by Mark Gottschling on 6/1/2023
 */
public class IntegerRange {
    public static final IntegerRange EMPTY = new IntegerRange(0, 0);

    private int min;
    private int max;

    public IntegerRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getInRange() {
        return getInRange(new Random());
    }

    public int getInRange(Random random) {
        return RandomHelper.randomInt(random, getMin(), getMax());
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerRange that = (IntegerRange) o;
        return min == that.min && max == that.max;
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return "IntegerRange{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
