package guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.*;

/**
 * Created by xiaoxuan.jin on 2016/6/1.
 */
public class CollectTest {

    @Test
    public void testRange() throws Exception {

        Range<Integer> closed = Range.closed(1, 10);
        Integer integer = closed.lowerEndpoint();
        Integer integer1 = closed.upperEndpoint();
        System.out.println(integer);
        DiscreteDomain<Integer> integers = DiscreteDomain.integers();
        ContiguousSet<Integer> sets = ContiguousSet.create(closed, integers);
        for (Integer i : sets) {
            System.out.println(i);
        }

        ContiguousSet<Integer> s = ContiguousSet.create(Range.closed(1, 100), DiscreteDomain.integers());

        final ArrayList<Integer> validStatus = Lists.newArrayList(1, 2);
        if (validStatus.contains(3)) {
            System.out.println("sucess");
        }

    }

    @Test
    public void testList() throws Exception {
        List<String> list = Lists.newArrayList();
        List<String> strings = new ArrayList();
        Map<String, List<String>> myMap = new HashMap<String, List<String>>();

        Set<String> a = Sets.newHashSet("a", "b", "c");
        Set<Set<String>> sets = Sets.powerSet(a);

        for (Set<String> set : sets) {
            System.out.println(set);
        }




    }

    @Test
    public void testMap() throws Exception {
        Map<String, String> map = Maps.newHashMap();


    }
}
