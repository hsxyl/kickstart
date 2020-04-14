package year2014.roundA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author xushenbao
 * @desc 添加描述
 * @create 2020/4/13
 */
public class A {

    private static Map<Integer,String> map = new HashMap<>();
    private static Map<Integer,String> numberEnglish = new HashMap<>();

    static {
        map.put(2, "double");
        map.put(3, "triple");
        map.put(4, "quadruple");
        map.put(5, "quintuple");
        map.put(6, "sextuple");
        map.put(7, "septuple");
        map.put(8, "octuple");
        map.put(9, "nonuple");
        map.put(10, "decuple");
        numberEnglish.put(0,"zero");
        numberEnglish.put(1,"one");
        numberEnglish.put(2,"two");
        numberEnglish.put(3,"three");
        numberEnglish.put(4,"four");
        numberEnglish.put(5,"five");
        numberEnglish.put(6,"six");
        numberEnglish.put(7,"seven");
        numberEnglish.put(8,"eight");
        numberEnglish.put(9,"nine");

    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        int t = in.nextInt();  // Scanner has functions to read ints, longs, strings, chars, etc.
        in.nextLine();

        for (int i = 1; i <= t; ++i) {
            String[] input = in.nextLine().split(" ");
            String number = input[0];
            int[] len = Arrays.stream(input[1].split("-")).mapToInt(Integer::parseInt).toArray();

            StringBuilder stringBuilder = new StringBuilder();
            int sum = 0;
            for(int j = 0 ;j <len.length;j++) {
                if(j!=0) {
                    stringBuilder.append(' ');
                }
            stringBuilder.append(buildPhoneNumber(number.substring(sum,sum+len[j])));
            sum+=len[j];
            }
            System.out.println(String.format("Case #%d: %s", i,stringBuilder.toString().trim()));
        }
    }

    private static String buildPhoneNumber(String s) {
        StringBuilder sb = new StringBuilder();
        int l=0,r=1;
        for(;r<s.length();r++) {
            if(s.charAt(r)!=s.charAt(r-1)) {
                sb.append(foldNumber(r - l, s.charAt(r - 1))).append(' ');
                l = r;
            }
        }
        sb.append(foldNumber(r-l,s.charAt(r-1)));
        return sb.toString();
    }
    private static String foldNumber(int len,char num) {
        String number = numberEnglish.get(num-'0');
        if(len==1) {
            return number;
        } else if(len>10) {
            return IntStream.range(0,len).mapToObj(i->number).collect(Collectors.joining(" " ));
        }
        else {
            return map.get(len)+' '+number;
        }

    }
}
