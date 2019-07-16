package creatures;


import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

/**
 * An implementation of Clorus
 * @author wnc
 */
public class Clorus extends Creature {
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
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * Should return a color with red = 34, blue = 231, and green = 0 .
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * Attack creature c, gain the energy of creature c
     */
    public void attack(Creature c) {
        this.energy = this.energy + c.energy();

    }

    /**
     * Clorus should lose 0.03 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.03;
    }


    /**
     * Clorus lose 0.01 energy when staying.
     */
    public void stay() {
        energy = energy - 0.01;
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        Clorus newClorus = new Clorus(this.energy / 2);
        this.energy = this.energy / 2;
        return newClorus;
    }

    /**
     * Clorus take exactly the following actions based on NEIGHBORS:
     * 1.If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip squares
     * do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3.Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     * @author wnc
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        boolean isEmpty = false;
        for (Direction D : neighbors.keySet()) {
            if (neighbors.get(D).name().equals("empty")) {
                isEmpty = true;
                emptyNeighbors.addFirst(D);
            }
            if (neighbors.get(D).name().equals("plip")) {
                anyPlip = true;
                plipNeighbors.addFirst(D);
            }
        }
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        // for () {...}

        if (!isEmpty) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlip) {
            Direction attackD = selectDirection(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, attackD);
        }


        // Rule 3
        if (energy >= 1) {
            Direction repD = selectDirection(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, repD);
        }

        // Rule 4
        Direction moveD = selectDirection(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, moveD);
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

