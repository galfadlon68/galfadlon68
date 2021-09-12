
class Part1_Change{
    public static void main(String[] args){

//        int [] Coins = {1,5,10};  // n = 7  T
//        System.out.println( change(Coins,7) + " - neet to be true");
//        printChangeLimited(Coins , 7 ,3);
//
//        int [] Coins1 = {2,10,20,100}; // n = 15 F
//        System.out.println( change(Coins1,15) + " - neet to be false");
//        printChangeLimited(Coins1 ,15 , 4);
//
//        int [] Coins3 = {5,7,8}; // n = 14 T
//        System.out.println( change(Coins3,14) + " - neet to be true");
//        printChangeLimited(Coins3 ,14 , 2);
//
//        int [] Coins4 = {2,20,25}; // n = 26 T
//        System.out.println( change(Coins4,26) + " - neet to be true");
//
//        int [] Coins5 = {2,3,8}; // n = 9 T
//        System.out.println( change(Coins5,9) + " - neet to be true");
//
//        int [] Coins6 = {1}; // n = 9 F
//        System.out.println( change(Coins6,5) + " - neet to be false");
//
//        int [] Coins7 = {2,10,20,100}; // n = 25 F
//        System.out.println( change(Coins7,25) + " - neet to be false");
//
//        int [] Coins8 ={1,7,19,12};
//        System.out.println( changeLimited(Coins8,20 , 2) + " - neet to be true");
//
//        int [] Coins9 = {5,7,12};
//        System.out.println( changeLimited(Coins9,8 , 2) + " - neet to be false");
//
//        int [] Coins10 = {1,7,10,12};
//        System.out.println( changeLimited(Coins10,10 , 5) + " - neet to be false");
//        int [] Coins11 = {1};
//        System.out.println( changeLimited(Coins10,1 , 1) + " - neet to be true");
//        int [] Coins12 = {};
//        System.out.println(changeLimited(Coins12 , 0, 0));
//        printChangeLimited(Coins12 ,0,0);

        int [] Coins14 ={1,2,3};
    printChangeLimited(Coins14 ,4 , 2);

        int [] Coins13 = {1,5,10,20};
        printChangeLimited(Coins13 ,13 ,2);
    }


    // Task 2.1 check if the change can be taken from coins, starting at index i.
    public static boolean change(int[] coins, int n, int i) {
        boolean ans;
        // stop when we got the change we wanted
        if (n == 0) {
            ans = true;
        }

        // condition for check that we didn't exceed of boundaries
        else if (n < 0 | i >= coins.length)
            ans = false;

            // Recursive call
        else {
            ans = change(coins, n - coins[i], i) ||
                    change(coins, n, i + 1);
        }

        return ans;
    }


    // Task 2.1 check if the sum can be taken from coins.
    public static boolean change(int [] coins, int n){
        return change(coins , n , 0);
    }

    //Task 2.2 check if the change can be taken from exact number of coins, starting at index i.
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse, int i) {
        boolean ans;
        // stop only if we got the change we wanted that  not exceed boundaries of numOfCoinsToUse
        if (n == 0 && numOfCoinsToUse == 0) {
            ans = true;
        }

        // condition for check that we didn't exceed of boundaries
        else if (n < 0 | i >= coins.length)
            ans = false;

        // Recursive call
        else {
            ans = changeLimited(coins, n - coins[i], numOfCoinsToUse-1 , i) ||
                    changeLimited(coins, n, numOfCoinsToUse , i + 1);
        }

        return ans;
    }

    //Task 2.2 check if the sum can be taken from exact number of coins.
    public static boolean changeLimited(int[] coins, int n, int numOfCoinsToUse){

        return changeLimited(coins , n , numOfCoinsToUse ,0);
    }


    //Task 2.3 check if the change can be taken from exact number of coins, starting at index i . and print one of the option to do that
    public static boolean printChangeLimited(int[] coins, int n, int numOfCoinsToUse, int i ,String acc) {
        boolean ans;
        // stop only if we got the change we wanted that not exceed boundaries of numOfCoinsToUse and print one of the options
        if (n == 0 && numOfCoinsToUse == 0) {
            ans = true;
            if (acc.length()!=0)
            System.out.println(acc.substring(0 ,acc.length()-1));
        }

        // condition for check that we didn't exceed of boundaries
        else if (n < 0 | i >= coins.length)
            ans = false;

        // Recursive call
        else {
            ans = printChangeLimited(coins, n - coins[i], numOfCoinsToUse-1 , i , acc + coins[i] + "," ) ||
                    printChangeLimited(coins, n, numOfCoinsToUse , i + 1 , acc);
        }

        return ans;
    }

    //Task 2.3 call to the function above
    public static void printChangeLimited(int[] coins, int n, int numOfCoinsToUse){
        printChangeLimited(coins , n , numOfCoinsToUse ,0 ,"");
    }

}
