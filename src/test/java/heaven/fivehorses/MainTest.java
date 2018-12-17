package heaven.fivehorses;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class MainTest extends TestCase {

    @Test
    public void test_sum() {
        Main main = new Main();
        assertEquals(3, main.sum(1, 2));
    }
    @Test
    public void test_collectionHashCode() {
        ArrayList<Integer> list1 = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();

        System.out.println(list1.hashCode());
        assertEquals(list1.hashCode(), list2.hashCode());
        assertTrue(list1.equals(list2));

        list1.add(4);
        assertNotSame(list1.hashCode(), list2.hashCode());
        assertFalse(list1.equals(list2));

        HashMap<ArrayList<Integer>, Integer> hashmap = new HashMap<ArrayList<Integer>, Integer>();
        hashmap.put(list1, 5);

        assertTrue(hashmap.containsKey(list1));

        list1.add(6);
        assertFalse(hashmap.containsKey(list1));
    }
}
