package lock;

import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by logan on 2019/12/31.
 */
public class TestWeight {

    public static Random rand = new Random(System.currentTimeMillis());
    /**
     * 根据随机数组返回某个区间 比如probability = [20,30,50] 则返回0/1/2，0、1、2的概率分别是20%、30%、50%
     *
     * @param probability
     * @return
     */
    public static int roll(Integer[] probability) {
        int sum = 0;
        for (Integer rd : probability) {
            sum += rd;
        }
        return roll(probability, sum);
    }

    public static int roll(Integer[] probability, int base) {
        int roll = getRandNumber(base);
        for (int i = 0; i < probability.length; i++) {
            if (roll < probability[i]) {
                return i;
            }
            roll -= probability[i];
        }
        return -1;
    }

    public static int getRandNumber(int maxNumber) {
        return rand.nextInt(maxNumber);
    }

    public static void main(String[] args) {
        Integer channelA = 20;
        Integer channelB = 30;
        Integer channelC = 50;
        int countA  = 0; int countB = 0; int countC = 0;
        int loopCount = 10000;
        for (int i = 0; i < loopCount; i++) {
            int roll = roll(new Integer[]{channelC, channelB, channelA});
            if(roll == 0) {
                countA++;
                System.out.println("渠道A");
            } else if (roll == 1) {
                countB++;
                System.out.println("渠道B");
            } else if (roll == 2){
                countC++;
                System.out.println("渠道C");
            } else {
                System.out.println("未知渠道");
            }
        }
        System.out.println("渠道A:" + countA);
        System.out.println("渠道B:" + countB);
        System.out.println("渠道C:" + countC);

        NumberFormat numberFormat = NumberFormat.getInstance();

        System.out.println("渠道A:" + numberFormat.format((float)countA/(float)loopCount*100));
        System.out.println("渠道B:" + numberFormat.format((float)countB/(float)loopCount*100));
        System.out.println("渠道C:" + numberFormat.format((float)countC/(float)loopCount*100));




    }

}
