package com.fsmeeting.guava.collection;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.primitives.Ints;
import com.sun.jmx.snmp.UserAcl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 集合操作
 * @Author: yicai.liu
 * @Date: 9:03 2017/5/22
 */
public class CollectionTest {


    /**
     * Description: 集合创建
     *
     * @Author: yicai.liu
     * @Date 11:39 2017/5/22
     */
    @Test
    public void testCreate() {
        Map<Integer, Map<String, UserAcl>> amap = new HashMap<>();
        Map<Integer, Map<String, UserAcl>> bmap = Maps.newHashMap();

        List<List<Map<String, String>>> alist = new ArrayList<>();
        List<List<Map<String, String>>> blist = Lists.newArrayList();

        Set<String> testSet = Sets.newTreeSet();

        Integer[] testArrays = new Integer[10];
        Integer[] intArrays = ObjectArrays.newArray(Integer.class, 100);

        List<String> capList = Lists.newArrayListWithCapacity(100);
    }

    /**
     * Description: 集合初始化
     *
     * @Author: yicai.liu
     * @Date 11:40 2017/5/22
     */
    @Test
    public void testInitial() {
        Set<String> aset = new HashSet<>();
        aset.add("elementA");
        aset.add("elementB");
        aset.add("elementC");

        Set<String> bset = Sets.newHashSet("elementA", "elementB", "elementC");

        List<String> list = Lists.newArrayList("elementA", "elementB", "elementC");

    }

    /**
     * Description: Joiner和 Splitter的使用
     *
     * @Author: yicai.liu
     * @Date 11:40 2017/5/22
     */
    @Test
    public void testJoinerAndSplitter() {
        int[] numbers = {1, 2, 3, 4, 5};
        String separator = ",";

        String numbersAsString = Joiner.on(separator).join(Ints.asList(numbers));
        String numbersAsStringDirectly = Ints.join(separator, numbers);

        System.out.println(numbersAsString + "/" + numbersAsStringDirectly);

        List<String> list = Splitter.on(separator).splitToList(numbersAsString);
        for (String ele : list) {
            System.out.println(ele);
        }

        String testString = "foo , what,,,more,";
        Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(testString);

    }

    /**
     * Description: 集合的不可变性
     *
     * @Author: yicai.liu
     * @Date 11:41 2017/5/22
     */
    @Test
    public void testImmutable() {
        Map<String, String> aamap = new HashMap<>();
        aamap.put("ON", "TRUE");
        aamap.put("ON", "FALSE");
        aamap.put("1", "PRODUCER");
        aamap.put("0", "BROKER");
        aamap.put("-1", "CONSUMER");

        Map<String, String> bbmap = ImmutableMap.of("ON", "TRUE", "OFF", "FALSE", "1", "PRODUCER", "0", "BROKER");
        // bbmap.put("-1", "CONSUMER"); java.lang.UnsupportedOperationException
        for (String ele : bbmap.keySet()) {
            System.out.println(ele);
        }
    }

    /**
     * Description: 相当于两个key的map
     *
     * @Author: yicai.liu
     * @Date 11:52 2017/5/22
     */
    @Test
    public void testTable() {
        Table<Integer, Integer, String> personTable = HashBasedTable.create();
        personTable.put(1, 20, new String("1--20"));
        personTable.put(0, 30, new String("0--30"));
        personTable.put(0, 25, new String("0--25"));
        personTable.put(1, 50, new String("1--50"));
        personTable.put(0, 27, new String("0--27"));
        personTable.put(1, 29, new String("1--29"));
        personTable.put(0, 33, new String("0--33"));
        personTable.put(1, 66, new String("1--66"));

        // 得到行集合
        Map<Integer, String> rowMap = personTable.row(0);
        System.out.println(rowMap);// {25=0--25, 33=0--33, 27=0--27,30=0--30}
        int maxAge = Collections.max(rowMap.keySet());
        // System.out.println(maxAge);// 33
    }

    /**
     * Description: 一一映射，可以通过key得到value，也可以通过value得到key
     *
     * @Author: yicai.liu
     * @Date 11:53 2017/5/22
     */
    @Test
    public void testBiMap() {
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(1, "hello");
        biMap.put(2, "helloa");
        biMap.put(3, "world");
        biMap.put(4, "worldb");
        biMap.put(5, "my");
        biMap.put(6, "myc");
        int value = biMap.inverse().get("my");
        String key = biMap.get(value);
        System.out.println("my--" + value + ", " + value + "--" + key);
    }

    /**
     * Description: 排序器
     *
     * @Author: yicai.liu
     * @Date 11:55 2017/5/22
     */
    @Test
    public void testOrdering() {

        List<String> list = Lists.newArrayList("22", "a", "bb", "11", "ccc");
        //对可排序类型做自然排序，如数字按大小，日期按先后排序
        list = Ordering.natural().sortedCopy(list);
        System.out.println(list);// [11, 22, a, bb, ccc]

        //按对象的字符串形式做字典排序
        list = Ordering.usingToString().sortedCopy(list);
        System.out.println(list);// [11, 22, a, bb, ccc]

        //Comparator转化为排序器
        list = Ordering.from(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                return o1.hashCode() - o2.hashCode();
            }
        }).sortedCopy(list);
        System.out.println(list);// [a, 11, 22, bb, ccc]

        // 实现自定义的排序器时，除了用上面的from方法，也可以跳过实现Comparator，而直接继承Ordering：
        Ordering<String> byLenOrdering = new Ordering<String>() {
            public int compare(String left, String right) {
                return Ints.compare(left.length(), right.length());
            }
        };

        list = byLenOrdering.sortedCopy(list);
        System.out.println(list);// [a, 11, 22, bb, ccc]

        // 链式调用方法：通过链式调用，可以由给定的排序器衍生出其它排序器
        // 1.reverse()-----获取语义相反的排序器
        // 2.nullsFirst()-----使用当前排序器，但额外把null值排到最前面
        // 3.nullsLast()-----使用当前排序器，但额外把null值排到最后面
        // 4.compound(Comparator)-----合成另一个比较器，以处理当前排序器中的相等情况
        // -------用compound方法包装排序器时，就不应遵循从后往前读的原则。
        // -------为了避免理解上的混乱，请不要把compound写在一长串链式调用的中间，你可以另起一行，在链中最先或最后调用compound。
        // 5.lexicographical()-----基于处理类型T的排序器，返回该类型的可迭代对象Iterable<T>的排序器
        // 6.onResultOf(Function)-----对集合中元素调用Function，再按返回值用当前排序器排序
        list = byLenOrdering.reverse().sortedCopy(list);
        System.out.println(list);// [ccc, 11, 22, bb, a]
    }

}
