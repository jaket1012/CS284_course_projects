
package pa4;

import static org.junit.jupiter.api.Assertions.*;

public class Test {
    @org.junit.jupiter.api.Test
    void constructorTest(){
        Treap<Integer> testTreap = new Treap<Integer>();
        assertEquals(testTreap.toString(), null);
    }

    @org.junit.jupiter.api.Test
    void addTest(){
        Treap<Integer> testTreap = new Treap<Integer>();
        testTreap.add (4 ,19);
        testTreap.add (2 ,31);
        testTreap.add (6 ,70);
        testTreap.add (1 ,84);
        testTreap.add (3 ,12);
        testTreap.add (5 ,83);
        testTreap.add (7 ,26);
        assertFalse(testTreap.add(7, 50));
        assertFalse(testTreap.add(5, 70));
    }

    @org.junit.jupiter.api.Test
    void deleteTest(){
        Treap<Integer> testTreap = new Treap<Integer>();
        testTreap.add (4 ,19);
        testTreap.add (2 ,31);
        testTreap.add (6 ,70);
        testTreap.add (1 ,84);
        testTreap.add (3 ,12);
        testTreap.add (5 ,83);
        testTreap.add (7 ,26);
        assertFalse(testTreap.delete(8));
        testTreap.delete(3);
        Treap<Integer> testTreap2 = new Treap<Integer>();
        testTreap2.add (4 ,19);
        testTreap2.add (2 ,31);
        testTreap2.add (6 ,70);
        testTreap2.add (1 ,84);
        testTreap2.add (5 ,83);
        testTreap2.add (7 ,26);
        assertEquals(testTreap.toString(),testTreap2.toString());
    }

    @org.junit.jupiter.api.Test
    void testFind(){
        Treap<Integer> testTreap = new Treap<Integer>();
        testTreap.add (4 ,19);
        testTreap.add (2 ,31);
        testTreap.add (6 ,70);
        testTreap.add (1 ,84);
        testTreap.add (3 ,12);
        testTreap.add (5 ,83);
        testTreap.add (7 ,26);
        assertTrue(testTreap.find(2));
        assertTrue(testTreap.find(6));
        assertFalse(testTreap.find(18));
    }

    @org.junit.jupiter.api.Test
    void testToString(){
        Treap<Integer> testTreap = new Treap<Integer>();
        testTreap.add (4 ,19);
        testTreap.add (2 ,31);
        testTreap.add (6 ,70);
        testTreap.add (1 ,84);
        testTreap.add (3 ,12);
        testTreap.add (5 ,83);
        testTreap.add (7 ,26);
        assertEquals(testTreap.toString(),
                """
            (key =1 , priority =84)
            null
        ( key =5 , priority =83)
        ( key =2 , priority =31)
        null
        ( key =4 , priority =19)
        ( key =3 , priority =12)
        null
        null
        null
        (key =6, priority =70)
            null
        (key =7, priority =26)
            null
            null""");
    }
}
