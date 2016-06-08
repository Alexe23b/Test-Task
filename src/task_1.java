import java.util.Scanner;

/**
 * Created by alexe on 08.06.2016.
 */
public class task_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);  //  creation new scanner for reading data from screen
        System.out.println("Input the amount of pairs of brackets");
        double amount = in.nextInt();  //reading integer from console

        double rez = factorial(2 * amount) / (factorial(amount) * factorial(amount + 1));  // calculation with binomial coefficients
        long result = Math.round(rez);  // rounding

        System.out.println("Amount of pairs of brackets " + (long) amount + ";");  // input data to console

        System.out.println("Amount of the Regular Bracket sequences " + result + ";");
    }

    public static double factorial(double n) {  //  method of calculation of factorials
        double res = 1;
        for (double i = 2; i <= n; i = i + 1) res *= i;
        return res;
    }
}
