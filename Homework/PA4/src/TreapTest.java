package pa4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

    @Test
    void testTreap() {
        Treap<Character> test1 = new Treap<Character>();
        assertEquals("null\n", test1.toString());
    }

    @Test
    void testTreapLong() {
        Treap<Character> test1 = new Treap<Character>(1);
        test1.add('A');
        test1.add('B');
        test1.add('C');
        Treap<Character> test2 = new Treap<Character>(1);
        test2.add('A');
        test2.add('B');
        test2.add('C');
        assertEquals(test1.toString(), test2.toString());
    }

    @Test
    void testAddE() {
        Treap<Integer> test1 = new Treap<Integer>(2);
        test1.add(4);
        test1.add(2);
        test1.add(6);
        test1.add(1);
        test1.add(3);
        test1.add(5);
        test1.add(7);
        assertEquals(false, test1.add(7)); //false as treap already has this key in a node
    }

    @Test
    void testAddEInt() {
        Treap<Integer> test1 = new Treap<Integer>();
        test1.add(4, 19);
        test1.add(2, 31);
        test1.add(6, 70);
        test1.add(1, 84);
        test1.add(3, 12);
        test1.add(5, 83);
        test1.add(7, 26);
        assertEquals(false, test1.add(7, 26)); //false as treap already has this key
    }

    @Test
    void testDelete() {
        Treap<Character> test2 = new Treap<Character>(2);
        test2.add('p', 99);
        test2.add('g', 80);
        test2.add('a', 60);
        test2.add('j', 65);
        test2.add('u', 75);
        test2.add('r', 40);
        test2.add('w', 32);
        test2.add('v', 21);
        test2.add('x', 25);
        test2.add('z', 47);
        assertEquals(true, test2.delete('p')); //test with root for most possible problems
        Treap<Character> test3 = new Treap<Character>(2);
        test3.add('g', 80);
        test3.add('a', 60);
        test3.add('j', 65);
        test3.add('u', 75);
        test3.add('r', 40);
        test3.add('w', 32);
        test3.add('v', 21);
        test3.add('x', 25);
        test3.add('z', 47); //Create same treap without root
        assertEquals(test2.toString(), test3.toString());

        Treap<Character> test4 = new Treap<Character>(2);
        test4.add('p', 99);
        test4.add('g', 80);
        test4.add('a', 60);
        test4.add('j', 65);
        test4.add('u', 75);
        test4.add('r', 40);
        test4.add('w', 32);
        test4.add('v', 21);
        test4.add('x', 25);
        test4.add('z', 47);
        assertEquals(true, test4.delete('z')); //test with root for most possible problems
        Treap<Character> test5 = new Treap<Character>(2);
        test5.add('p', 99);
        test5.add('g', 80);
        test5.add('a', 60);
        test5.add('j', 65);
        test5.add('u', 75);
        test5.add('r', 40);
        test5.add('w', 32);
        test5.add('v', 21);
        test5.add('x', 25);//Create same treap without root
        assertEquals(test2.toString(), test3.toString());

    }

    @Test
    void testFind() {
        Treap<Integer> test1 = new Treap<Integer>();
        test1.add(4, 19);
        test1.add(2, 31);
        test1.add(6, 70);
        test1.add(1, 84);
        test1.add(3, 12);
        test1.add(5, 83);
        test1.add(7, 26);
        assertEquals(true, test1.find(6));
    }

    @Test
    void testToString() {
        Treap<Integer> test1 = new Treap<Integer>();
        test1.add(4, 19);
        test1.add(2, 31);
        test1.add(6, 70);
        test1.add(1, 84);
        test1.add(3, 12);
        test1.add(5, 83);
        test1.add(7, 26);
        System.out.println(test1.toString()); //same toString as example test
    }

}

