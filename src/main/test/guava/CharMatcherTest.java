package guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import org.junit.Test;

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
    }
}
