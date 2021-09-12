
import java.util.Scanner;

public class Task4e {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean ans1 = true; // the condition * is not happening
        int ans2 = 0;

        //---------------write your code BELOW this line only!--------------


        int n = scanner.nextInt();
        int b = scanner.nextInt();
        int s = scanner.nextInt();
        int d = scanner.nextInt();

        ans2=-1;
        int powerbd= 1;                     // b^d

        // loop for finding (b^d)%n
        for (int j = 0; j <d ; j=j+1) {
            powerbd = (powerbd*b)%n;
        }

        double power2i= 0.5;                //2^i
        //loop for finding b^((2^i)*d) and for running over the condition * from i=0 to i=s-1
        for (int i = 0; i <= s-1; i=i+1) {
            power2i = power2i * 2;         //2^i
            int power2id = (int) power2i * d;      //(2^i)*d
            int powerb2id = 1;
            for (int j = 0; j <power2id ; j++) {
                powerb2id = (powerb2id*b) % n; //(b^(2^i)*d)%n
            }
                if (((powerbd % n) != 1) && ((powerb2id % n) != (n-1))) { // the * condition. im working here by the mathematics fact that (a*b)%k= ((a%k)*(b%k))%k
                    ans1 = false;
                    ans2 = b;
                }
                else {
                    ans1=true;   // its important to do ans=true again because if the program will gat the * condition true even once so ans1 will be false
                    i = s; //if we found that the condition * is not happening even once. we can stop and go out from the loop
                }
        }
        //---------------write your code ABOVE this line only!--------------
        System.out.println(ans1);
        System.out.println(ans2);
    }
}