
import java.util.Scanner;

public class Task4d {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int ans1 = 0;
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------
        int n=scanner.nextInt();

        int k=n-1; //n should be odd
        // by this loop im getting the value of s. moreover, my "final" k is my d
        while (k%2==0){
            ans1=ans1+1;
            k=k/2;
        }
        ans2=k;

            //---------------write your code ABOVE this line only!--------------

            System.out.println(ans1);
            System.out.println(ans2);

    }
}

