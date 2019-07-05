package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Iterator;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {


    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 99;
        g = 0;
        b = 76;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = 63;
        g = (int) (g + energy * 96);
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.15;
        if (energy < 0) {
            energy = 0;
        }
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy = energy + 0.2;
        if (energy > 2) {
            energy = 2;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip newPlip = new Plip(this.energy / 2);
        this.energy = this.energy / 2;
        return newPlip;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     * @author wnc
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        boolean isEmpty = false;
        for (Direction D : neighbors.keySet()) {
            if (neighbors.get(D).name().equals("empty")) {
                isEmpty = true;
                emptyNeighbors.addFirst(D);
            }
            if (neighbors.get(D).name().equals("Clorus")) {
                anyClorus = true;
            }
        }
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}

        if (!isEmpty) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (energy >= 1) {
            Direction repD = selectDirection(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, repD);
        }

        // Rule 3
        if (anyClorus) {
            double probability = Math.random();
            if (probability >= 0.5) {
                Direction repD = selectDirection(emptyNeighbors);
                return new Action(Action.ActionType.MOVE, repD);
            }
        }
        // Rule 4
        return new Action(Action.ActionType.STAY);
    }


    /**
     * selectDirection select the random direction and return it.
     * @author wnc
     * @param D The Deque of direction in which the creature can move
     * @return the random selected direction
     */
    private Direction selectDirection(Deque<Direction> D) {
        int step = (int) (Math.random() * D.size());
        Iterator<Direction> neiIter = D.iterator();
        Direction repD = D.getFirst();
        for (int i = 0; i < step; i++) {
            repD = neiIter.next();
        }
        return repD;
    }
}
