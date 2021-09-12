
import java.util.Scanner;

public class Task4b {

    public static void main(String[] args) {

        int ans=0;
        Scanner scanner = new Scanner(System.in);


        //---------------write your code BELOW this line only!--------------
        int num = scanner.nextInt();

        for (int i = 2; i <=num ; i++) {
            boolean isPrime = true;
            if (i%2==0 & i!=2) {// if the number is odd he have to be not prime, 2 is prime even though is odd
                isPrime = false;
            }
            else {
                for (int p = 3; p * p <= i & isPrime & p < 46341; p=p+2)  //  the number 2,147,483,647 (2^31) is the highest number in the range of int,the number 46341 is the  square root of this number
                {
                    if (i % p == 0)
                        isPrime = false;
                }
            }
            if (isPrime) // this  routin count the number of primes number until num (including num)
                ans=ans+1;
        }

        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}