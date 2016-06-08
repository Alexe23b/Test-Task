import java.math.BigInteger;

/**
 * Created by alexe on 08.06.2016.
 */
public class task_3 {

    public static void main(String[] args) throws java.lang.Exception {

        BigInteger fact = BigInteger.ONE; // set value of constant 1 in the format BigInteger

        int n = 100;

        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(new BigInteger(String.valueOf(i)));  // calculation N!
        }
        System.out.println(n + "! = " + fact);

        String s = "" + fact;   // concatenation string with BigInteger automatically parse to string
        int sum = 0;
        for (int i = 0; i < s.length(); i++) {  // cycle "for" with using length of string as exit parameter
            sum += Integer.parseInt(s.substring(i, i + 1));  //   getting symbols as substring, parsing them to integer and sum
        }

        System.out.println("sum = " + sum);   //input res to console
    }
}

