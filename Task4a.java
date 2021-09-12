
import java.util.Scanner;

public class Task4a {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans = true; // ans=isprime

        //---------------write your code BELOW this line only!--------------
        int num = scanner.nextInt();
        if (num%2 == 0 & num != 2) {// if the number is odd he have to be not prime, 2 is prime even though is odd
            ans = false;
        }
        else {
            for (int p = 3; p * p <= num & ans & p < 46341; p=p+2) //  the number 2,147,483,647 (2^31) is the highest number in the range of int,the number 46341 is the  square root of this number
            {
                if (num % p == 0)
                    ans = false;
            }
        }
        //---------------write your code ABOVE this line only!--------------

        System.out.println(ans);
    }
}