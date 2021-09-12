
import java.util.Iterator;
import java.util.LinkedList;

public class BitList extends LinkedList<Bit> {
    public static void main(String[] args) {
    }


    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================
    //Adds element to the end of this list

    public void addLast(Bit element) {
        if (element == null)
            throw new IllegalArgumentException("You cant insert null to the list");
        super.addLast(element);
        if (element == Bit.ONE)
            numberOfOnes = numberOfOnes + 1;
    }

    //Adds element to the beginning of this list
    public void addFirst(Bit element) {
        if (element == null)
            throw new IllegalArgumentException("You cant insert null to the list");
        super.addFirst(element);
        if (element == Bit.ONE)
            numberOfOnes = numberOfOnes + 1;
    }

    //Remove the last Bit int the list tnd return him to the user
    public Bit removeLast() {
        Bit removeLast = super.removeLast();
        if (removeLast.equals(Bit.ONE))
            numberOfOnes = numberOfOnes - 1;
        return removeLast;

    }

    //Remove the first Bit int the list tnd return him to the user
    public Bit removeFirst() {
        Bit removeFirst = super.removeFirst();
        if (removeFirst.equals(Bit.ONE))
            numberOfOnes = numberOfOnes - 1;
        return removeFirst;
    }

//    =========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================

    //Returns a String representing this BitList
    public String toString() {
        String output = "";
        Iterator<Bit> bitIterator = this.iterator();
        while (bitIterator.hasNext())
            output = bitIterator.next().toString() + output;
        output = "<" + output + ">";
        return output;
    }

    //    Bit newBit = new Bit(bitIterator.next().toInt());
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
        this();
        if (other == null)
            throw new IllegalArgumentException("You cant copy null list");
        Iterator<Bit> bitIterator = other.iterator();
        // run all over other Bit's
        while (bitIterator.hasNext()) {
            addLast(bitIterator.next());
        }
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
        Iterator<Bit> bitIterator = this.iterator();
        if (!bitIterator.hasNext()) // empty list
            return false;
        if (this.getLast().equals(Bit.ZERO)) // positive number
            return true;
        while (bitIterator.hasNext()) {
            if (bitIterator.next().toInt() == 1) // more than one show of 1
                while (bitIterator.hasNext())
                    if (bitIterator.next().toInt() == 1) // more than one show of 1
                        return true;
        }
        return false; // neither of the conditions
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
        //check if legal
        if (!this.isNumber())
            return false;
        // check condition 2(a)
        if (this.size() == 1 | (this.size() == 2 & this.getLast().toInt() != 0))
            return true;
        Iterator<Bit> bitIterator = this.descendingIterator();
        int last = bitIterator.next().toInt();
        int secondLast = bitIterator.next().toInt();
        // check condition 2(b) , at least three bit already checked above
        if ((last == 1 & secondLast == 0) | (last == 0 & secondLast == 1))
            return true;
        // check condition 2(c)
        if (last == 1 & secondLast == 1) {
            while (bitIterator.hasNext()) {
                if (bitIterator.next().toInt() == 1)
                    return false;
            }
            return true;
        }
        return false; // neither of the conditions
    }

    //reduced by removing the leftmost as long as the list is legal
    public void reduce() {
        if (!this.isNumber())
            throw new IllegalArgumentException("number isn't legal");
        while (!isReduced())
            removeLast();

    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    // Returns a new list of bits, in which each verb verb is replaced by its own complement
    public BitList complement() {
        BitList newList = new BitList();
        Iterator<Bit> bitIterator = this.iterator();
        while (bitIterator.hasNext()) {
            Bit b = bitIterator.next().negate();
            newList.addLast(b);
        }
        return newList;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    //Remove the first (far right) bit
    public Bit shiftRight() {
        if (size() == 0)
            return null;
        else
            return removeFirst();
    }

    //Adding bit 0 at the beginning of the list (in the rightmost place)
    public void shiftLeft() {
        this.addFirst(Bit.ZERO);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    // Changes the object that activates it by adding the last bit of the list to end
    // until the length of the list reaches the value of the newLength parameter
    public void padding(int newLength) {
        if (isEmpty())
            throw new IllegalArgumentException("number isn't legal");
        // If the parameter is small or equal throughout the list, the method does nothing
        if (this.size() < newLength) {
            Bit last = this.getLast();
            while (this.size() < newLength)
                this.addLast(last);
        }
    }

    public static boolean satisfies(int[] mu, int[][] phi) {
        boolean ans = false;
        for (int i = 0; i < mu.length; i++) {
            phi = substitute(mu[i], phi);
        }
        if (phi[0].length == 0)
            ans = true;
        return ans;
    }
    

    //----------------------------------------------------------------------------------------------------------
// The following overriding methods must not be changed.
//----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
