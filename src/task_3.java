import java.math.BigInteger;

/**
 * Created by alexe on 08.06.2016.
 */
public class task_3 {

    public static void main(String[] args) throws java.lang.Exception {

        BigInteger fact = BigInteger.ONE;

        int n = 100;

        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(new BigInteger(String.valueOf(i)));
        }
        System.out.println(n + "! = " + fact);

        String s = "" + fact;
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {
            sum += Integer.parseInt(s.substring(i, i + 1));
        }

        System.out.println("sum = " + sum);
    }
}

