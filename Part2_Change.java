
class Part2_Change{
    public static void main(String[] args){


    }

    //Task 2.4
    public static int countChangeLimited(int[] coins , int n , int numOfCoinsToUse, int i) {
        // stop only if we got the change we wanted that not exceed boundaries of numOfCoinsToUse and set ans to be 1
        int ans;
        if (n == 0 && numOfCoinsToUse == 0)
            ans = 1;

        // condition for check that we didn't exceed of boundaries
        else if (n < 0 | i >= coins.length)
            ans = 0;

            // Recursive call for adding 1 to ans when we found another solution
        else {
            ans = countChangeLimited(coins , n - coins[i], numOfCoinsToUse - 1 , i) +
                    countChangeLimited(coins , n , numOfCoinsToUse , i + 1);
        }

        return ans;
    }

    //Task 2.4
    public static int countChangeLimited(int[] coins , int n , int numOfCoinsToUse){

        return countChangeLimited(coins , n , numOfCoinsToUse , 0);
    }

    //Task 2.5
    public static int printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse, int i , String acc) {
        int ans;
        // stop only if we got the change we wanted that not exceed boundaries of numOfCoinsToUse and print all options
        if (n == 0 && numOfCoinsToUse == 0) {
            ans = 1;
            if (acc.length()!=0)
                System.out.println(acc.substring(0 ,acc.length()-1));
        }

        // condition for check that we didn't exceed of boundaries
        else if (n < 0 | i >= coins.length)
            ans = 0;

            // Recursive call
        else {
            ans = printAllChangeLimited(coins, n - coins[i], numOfCoinsToUse-1 , i , acc + coins[i] + "," ) +
                    printAllChangeLimited(coins, n, numOfCoinsToUse , i + 1 , acc);
        }

        return ans;
    }

    //Task 2.5
    public static void printAllChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        printAllChangeLimited(coins , n , numOfCoinsToUse ,0 ,"");
    }

    //Task 2.6
    public static int countChangeUnlimited(int[] coins, int n, int i) {
        int ans;
        // stop when we found solution
        if (n == 0)
            ans = 1;

            // condition for check that we didn't exceed of boundaries
        else if (n < 0 | i >= coins.length)
            ans = 0;

            // Recursive call for adding 1 to ans when we found another solution
        else {
            ans = countChangeUnlimited(coins, n - coins[i], i) +
                    countChangeUnlimited(coins, n, i + 1);
        }

        return ans;
    }

    //Task 2.6
    public static int changeInCuba(int cuc){
            int [] cucCup = {1,3,3,5,9,10,15,20,30,50,60,100,150,300};
        return countChangeUnlimited(cucCup , 3*cuc , 0) ;

    }

}
