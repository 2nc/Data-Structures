package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/**
 * This class test the clorus
 * @author wnc
 */

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus c = new Clorus(2);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(1.97, c.energy(), 0.01);
        c.move();
        assertEquals(1.94, c.energy(), 0.01);
        c.stay();
        assertEquals(1.93, c.energy(), 0.01);
        c.stay();
        assertEquals(1.92, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus m = new Clorus(1);
        Clorus n = m.replicate();
        assertNotEquals(m, n);
        assertEquals(0.5, m.energy(), 0.01);
        assertEquals(0.5, n.energy(), 0.01);
    }

    @Test
    public void testAttack() {
        Clorus c = new Clorus(1);
        Plip p = new Plip(2);
        c.attack(p);
        assertEquals(3, c.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Plip(2));
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Has Plip besides Clorus, attack
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> attackPlip = new HashMap<Direction, Occupant>();
        attackPlip.put(Direction.TOP, new Empty());
        attackPlip.put(Direction.BOTTOM, new Impassible());
        attackPlip.put(Direction.LEFT, new Plip(2));
        attackPlip.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(attackPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.LEFT);

        assertEquals(actual, expected);

        // Energy >= 1; replicate towards an empty space.
        c = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = c.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);


        // Energy < 1; move towards an empty space.
        c = new Clorus(0.9);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = c.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


    }
}
