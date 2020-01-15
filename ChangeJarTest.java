package Project1;

import org.junit.Test;

import static org.junit.Assert.*;

public class ChangeJarTest {

    /**
     *  Your assignment is to write many more test cases to
     *  have a robust testing, more than just 100% coverage.
     *
     *  Some examples are provided to help you get started
     */


    // Testing valid constructors with wide range of values
    @Test
    public void testConstructor() {
        ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);

        assertEquals (6, jar1.getQuarters());
        assertEquals (5, jar1.getDimes());
        assertEquals (4, jar1.getNickels());
        assertEquals (2, jar1.getPennies());

        ChangeJar jar2 = new ChangeJar();
        assertEquals (0, jar2.getQuarters());
        assertEquals (0, jar2.getDimes());
        assertEquals (0, jar2.getNickels());
        assertEquals (0, jar2.getPennies());

        ChangeJar jar3 = new ChangeJar(jar1);
        assertEquals (6, jar3.getQuarters());
        assertEquals (5, jar3.getDimes());
        assertEquals (4, jar3.getNickels());
        assertEquals (2, jar3.getPennies());
    }

    // testing valid takeOut with wide range of
    // quarters, dimes, nickels, pennies
    @Test
    public void testTakeOut1() {
        ChangeJar jar = new ChangeJar(3,3,2,2);
        jar.takeOut(1,1,1,1);
        assertEquals (2, jar.getQuarters());
        assertEquals (2, jar.getDimes());
        assertEquals (1, jar.getNickels());
        assertEquals (1, jar.getPennies());
    }

    // testing valid takeOut with wide range of amounts
    @Test
    public void testTakeOut2() {
        ChangeJar jar1 = new ChangeJar(5,3,4,3);
        ChangeJar jar2 = jar1.takeOut(1.22);

        assertEquals (1, jar1.getQuarters());
        assertEquals (1, jar1.getDimes());
        assertEquals (4, jar1.getNickels());
        assertEquals (1, jar1.getPennies());

        assertEquals (4, jar2.getQuarters());
        assertEquals (2, jar2.getDimes());
        assertEquals (0, jar2.getNickels());
        assertEquals (2, jar2.getPennies());
    }

    // testing putIn for valid low numbers
    @Test
    public void testPutIn() {
        ChangeJar jar = new ChangeJar();
        // jar.putIn(2,3,4,5);  // this line was commented because no putin method yet.
        assertEquals (2, jar.getQuarters());
        assertEquals (3, jar.getDimes());
        assertEquals (4, jar.getNickels());
        assertEquals (5, jar.getPennies());
    }

    // Testing equals for valid numbers
    @Test
    public void testEqual () {
        ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
        ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
        ChangeJar jar3 = new ChangeJar(2, 5, 4, 2);

        assertFalse(jar1.equals(jar2));
        assertTrue(jar1.equals(jar3));
    }

    // testing compareTo all returns
    @Test
    public void testCompareTo () {
        ChangeJar jar1 = new ChangeJar(2, 5, 4, 2);
        ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);
        ChangeJar jar3 = new ChangeJar(2, 3, 4, 2);
        ChangeJar jar4 = new ChangeJar(2, 5, 4, 2);

        assertTrue(jar2.compareTo(jar1) > 0);
        assertTrue(jar3.compareTo(jar1) < 0);
        assertTrue(jar1.compareTo(jar4) == 0);
    }

    // load and save combined.
    @Test
    public void testLoadSave() {
        ChangeJar jar1 = new ChangeJar(6, 5, 4, 2);
        ChangeJar jar2 = new ChangeJar(6, 5, 4, 2);

        jar1.save("file1");
        jar1 = new ChangeJar();  // resets to zero

        jar1.load("file1");
        assertTrue(jar1.equals(jar2));
    }

    // IMPORTANT: only one test per exception!!!
    // testing negative number for nickels in takeOut
    @Test
            (expected = IllegalArgumentException.class)
    public void testTakeOutNegNickels() {
        ChangeJar jar1 = new ChangeJar(2,2,2,2);
        jar1.takeOut(1,1,-1,1);
    }

    // testing negative number for dimes in takeOut
    @Test
            (expected = IllegalArgumentException.class)
    public void testTakeOutNegDimes() {
        ChangeJar jar1 = new ChangeJar(2,2,2,2);
        jar1.takeOut(1,-1,1,1);
    }

    // testing negative number quarters for the constructor
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegQuarters() {
        new ChangeJar(-300, 0, 0, 0);
    }

}
