
import java.math.BigInteger;
import java.util.Random;

class Part1_BigInteger{
    public static void main(String[] args) {

    }

    //Task 1.1
    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum =  new BigInteger("0");
        BigInteger idx = new BigInteger("1");
        BigInteger bigOne = BigInteger.ONE;

        // Loop for sum all numbers from 1 to n
        while (idx.compareTo (n) < 0) {
            sum = sum.add(idx);
            idx = idx.add(bigOne);
        }
        return sum;
    }


    //Task 1.2
    public static void printRandoms(int n){
        Random num = new Random();

        // Loop to print n random numbers in the range of int
        for (int i = 0; i < n ; i++) {
            System.out.println(num.nextInt());
        }
    }

    //Task 1.3
    public static   boolean isPrime(BigInteger n){
        boolean ans= true;

        // Condition for checking if n is odd, and if n is not 1 or 2
        if (n.remainder(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0 & n.compareTo(BigInteger.TWO) != 0
                | n.compareTo(BigInteger.ONE) == 0){
            ans =false;
        }

        // Loop for checking if number is Prime
        for (BigInteger p =  new BigInteger("3");  p.compareTo(n.sqrt()) <= 0 & ans; p = p.add(BigInteger.TWO)) {
            if (n.remainder(p).compareTo(BigInteger.ZERO) == 0)
                ans=false;
        }

        return ans;
    }
    //Task 1.4
    public static BigInteger randomPrime(int n){
        Random num = new Random();
        BigInteger randBig = new BigInteger(n , num);
        while (!isPrime(randBig))
            randBig = new BigInteger(n , num);
        return randBig;
    }


}