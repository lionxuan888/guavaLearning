package guava;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by xiaoxuan.jin on 2016/5/3.
 */
public class CharMatcherTest {

    @Test
    public void testCharMatcher() throws Exception {

        String string = "99hello0394 56 java BCBC ||";

        String noControl = CharMatcher.JAVA_ISO_CONTROL.removeFrom(string); // remove control characters
        String theDigits = CharMatcher.DIGIT.retainFrom(string); // only the digits
        String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(string, ' ');
        // trim whitespace at ends, and replace/collapse whitespace into single spaces
        String noDigits = CharMatcher.JAVA_DIGIT.replaceFrom(string, "*"); // star out all digits
        String lowerAndDigit = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom(string);
        // eliminate all characters that aren't digits or lowercase


        String str = "account_id";
        String s = CharMatcher.is('_').retainFrom(str);
        System.out.println(s);


        String to = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "account_id");
        System.out.println(to);
        int i = CharMatcher.is('_').indexIn("accountId");
        int j = CharMatcher.is('_').indexIn("account_id_jfef");
        //String s1 = CharMatcher.("@qunar.com").replaceFrom("xiaoxuan.jin@qunar.com", "");

        long elapsed = 5019;
        long seconds = elapsed / 1000;
        long milliseconds = elapsed % 1000;
        System.out.println("seconds: " + seconds);
        System.out.println("milliseconds: " + milliseconds);

        Stopwatch stopwatch = Stopwatch.createStarted();
        //Thread.sleep(1000 * 3 + 500);
        formatTime("first: ", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
        stopwatch.reset().start();
       // Thread.sleep(1000 * 5 + 500);
        formatTime("second: ", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));

        String monthParam = "201601";
        String pp = CharMatcher.is('-').replaceFrom(monthParam, "");
        System.out.println(pp);
        List<String> list = Lists.newArrayList();
        list.add("a");
        list.add("b");
        list.add("c");

        Iterable<Integer> a1 = Iterables.filter(Lists.transform(list, new Function<String, Integer>() {
            public Integer apply(String input) {
                if (input.equals("a")) {
                    return 1;
                }
                return null;
            }
        }), Predicates.<Integer>notNull());
        System.out.println("a的集合：" + a1);


        IntRange intRange = new IntRange(0, 0);
        System.out.println(intRange.getMinimumInteger());
        System.out.println(intRange.getMaximumInteger());
    }


    private void formatTime(String action, long elapsed) {
        long seconds = elapsed / 1000;
        long milliseconds = elapsed % 1000;
        System.out.println(action + "耗时: " + seconds + " 秒， " + milliseconds + " 毫秒 {}");
    }
}
