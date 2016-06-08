import java.util.Scanner;

/**
 * Created by alexe on 08.06.2016.
 */
public class task_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input the amount of pairs of brackets");
        long amount = in.nextInt();

        long rez = factorial(2*amount)/(factorial(amount)*factorial(amount+1));
        //long rez = factorial(amount);

        System.out.println("Amount of pairs of brackets " + amount + ";");

        System.out.println("Amount of the Regular Bracket sequences " + rez + ";");
    }
    public static long factorial(long n) {
        long a = 1;
        for (long i = 1; i <= n; i = i + 1) {
            a = a * i;
        }
        return a;
    }
}
