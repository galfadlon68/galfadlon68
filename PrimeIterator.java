/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeIterator implements Iterator<Integer> {

    private List<Integer> primes;

    //Complete the following methods
    public PrimeIterator() {
        // task 0
        this.primes = new DynamicArray<Integer>();
    }

    public boolean hasNext() {
        // task 0
        return true;
    }


    public Integer next() {
        // task 0
        if (primes.isEmpty()) {
            primes.add(2);
            return this.primes.get(0);
        }

        // find last prime in tje array
        int last = primes.get(primes.size()-1);

        // run all over numbers
        for (int number = last+1; number <= Integer.MAX_VALUE; number = number+1){
            boolean isPrime = true;
            // check if prime
            for (int i = 0; i < primes.size() && primes.get(i) * primes.get(i)
                    <= number & isPrime; i = i + 1 )
                if (number % primes.get(i) == 0)
                    isPrime = false;
            if(isPrime) {
                primes.add(number);
                return number;
            }
        }
        //Meaningless
        return 0;
    }



}

