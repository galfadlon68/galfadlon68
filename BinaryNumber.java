

import java.util.Iterator;

public class BinaryNumber implements Comparable<BinaryNumber> {
    public static void main(String[] args) {
    }

    private static final BinaryNumber ZERO = new BinaryNumber(0);
    private static final BinaryNumber ONE = new BinaryNumber(1);


    private BitList bits;

    // Copy constructor
    //Do not chainge this constructor
    public BinaryNumber(BinaryNumber number) {
        bits = new BitList(number.bits);
    }

    //A constructor who builds an empty BinaryNumber
    private BinaryNumber() {
        bits = new BitList();
    }


    //Do not change this constructor
    private BinaryNumber(int i) {
        bits = new BitList();
        bits.addFirst(Bit.ZERO);
        if (i == 1)
            bits.addFirst(Bit.ONE);
        else if (i != 0)
            throw new IllegalArgumentException("This Constructor may only get either zero or one.");
    }

    //Do not change this method
    public int length() {
        return bits.size();
    }

    //Do not change this method
    public boolean isLegal() {
        return bits.isNumber() & bits.isReduced();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.1 ================================================
    public BinaryNumber(char c) {
        this();
        if (c < '0' | c > '9')
            throw new IllegalArgumentException("char is not between 0 to 9");
        String bin = Integer.toBinaryString(toInt(c)); // convert decimal to binary string
        // creator of the new object
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '0')
                bits.addFirst(Bit.ZERO);
            else
                bits.addFirst(Bit.ONE);
        }
        // check if char is '0'
        if (!(bits.size() == 1 & bits.getFirst().equals(Bit.ZERO)))
            bits.addLast(Bit.ZERO);
    }

    // Converts a character to a number
    private int toInt(char c) {
        return "0123456789".indexOf(c);
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.2 ================================================
    //Returns a string in which the series of bits is from right to left
    public String toString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line
        String output = "";
        Iterator<Bit> bitIterator = this.bits.iterator();
        while (bitIterator.hasNext())
            output = bitIterator.next().toString() + output;
        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.3 ================================================
    //Returns the value true if and only if the parameter is
    //An object in the BinaryNumber class that represents the same number
    public boolean equals(Object other) {
        // Check instance type
        if (!(other instanceof BinaryNumber))
            return false;
        BinaryNumber otherBin = (BinaryNumber) other;
        if (!otherBin.isLegal())
            throw new IllegalArgumentException("number isn't legal");

        if (this.length() != otherBin.length())
            return false;

        Iterator<Bit> thisBitIter = this.bits.iterator();
        Iterator<Bit> otherBitIter = otherBin.bits.iterator();
        // check each single bit
        while (thisBitIter.hasNext() && otherBitIter.hasNext()) {
            if (!(thisBitIter.next().equals(otherBitIter.next())))
                return false;
        }
        return true;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.4 ================================================
    //The method adds (sum operation) the value of the parameter to "this"
    public BinaryNumber add(BinaryNumber addMe) {
        if (addMe == null)
            throw new IllegalArgumentException("addMe is null");
        if (!addMe.isLegal())
            throw new IllegalArgumentException("number isn't legal");

        // temporarily copied of to return "this" to its original state after the changes
        BinaryNumber curr = new BinaryNumber(this);

        // conditions for padding
        if (addMe.length() > this.length()) {
            int padding = addMe.length();
            this.bits.padding(padding);
        }
        if (addMe.length() < this.length()) {
            int padding = this.length();
            addMe.bits.padding(padding);
        }

        BinaryNumber ans = new BinaryNumber();


        Iterator<Bit> addToIter = this.bits.iterator();
        Iterator<Bit> addMeIter = addMe.bits.iterator();

        Bit carry = Bit.ZERO;
        //calculate the sum
        while (addMeIter.hasNext()) {
            Bit b1 = addToIter.next();
            Bit b2 = addMeIter.next();
            Bit sum = Bit.fullAdderSum(b1, b2, carry);
            carry = Bit.fullAdderCarry(b1, b2, carry);
            ans.bits.addLast(sum);
        }
        //Add the last digit
        if (this.bits.getLast().equals(addMe.bits.getLast()))
            ans.bits.addLast(carry);
        //Make the numbers legal again
        ans.bits.reduce();
        this.bits = curr.bits;
        addMe.bits.reduce();

        return ans;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.5 ================================================
    //The method returns an BinaryNumber that represents the negate of the original number
    public BinaryNumber negate() {
        BinaryNumber one = new BinaryNumber('1');
        BinaryNumber ans = new BinaryNumber();
        ans.bits = this.bits.complement();
        return ans.add(one);
    }


    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.6 ================================================
    //method for subtracting the two BinaryNumbers
    public BinaryNumber subtract(BinaryNumber subtractMe) {
        if (subtractMe == null)
            throw new IllegalArgumentException("null not allowed");
        if (!subtractMe.isLegal())
            throw new IllegalArgumentException("number isn't legal");
        return this.add(subtractMe.negate());
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.7 ================================================
    // The method "returns" the signum of the BinaryNumber by returning 0/1/-1
    public int signum() {
        if (this.length() > 1) {
            if (this.bits.getLast().equals(Bit.ZERO))
                return 1;//positive

            else
                return -1; // negative
        } else
            return 0; // zero
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.8 ================================================
    public int compareTo(BinaryNumber other) {
        if (other == null)
            throw new IllegalArgumentException("null not allowed");
        if (!other.isLegal())
            throw new IllegalArgumentException("number isn't legal");
        return this.subtract(other).signum();
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.9 ================================================
    public int toInt() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line

        int output = 0;
        if (this.signum() == 0)
            return output;

        // toInt for Min Value
        Iterator<Bit> minValueIter = this.bits.descendingIterator();
        boolean thisIsMinValue = true;
        if (this.length() == 33 && minValueIter.next().equals(Bit.ONE) && minValueIter.next().equals(Bit.ONE)) {
            while (minValueIter.hasNext() && thisIsMinValue) {
                if (!minValueIter.next().equals(Bit.ZERO))
                    thisIsMinValue = false;
            }
            if (thisIsMinValue)
                return -2147483648;
        }

        // copy of the original BinaryNumber
        BinaryNumber curr = new BinaryNumber(this);

        // if the number is negative
        boolean isNegate = false;
        if (this.signum() < 0) {
            curr = curr.negate();
            isNegate = true;
        }
        //toInt
        Iterator<Bit> bitIterator = curr.bits.iterator();
        int pow = 0;
        while (bitIterator.hasNext()) {
            output = output + (bitIterator.next().toInt()) * ((int) Math.pow(2, pow));
            pow = pow + 1;
            // Exceeds the MAXVALUE
            if (pow > 32)
                throw new RuntimeException();
        }

        if (isNegate)
            output = output * -1;

        return output;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.10 ================================================
    // Do not change this method
    public BinaryNumber multiply(BinaryNumber multiplyMe) {
        if (multiplyMe == null || !multiplyMe.bits.isNumber())
            throw new IllegalArgumentException("illegal input");

        // we don't to change the "object" we got and "this"
        BinaryNumber thisCopy = new BinaryNumber(this);
        BinaryNumber multiplyMeCopy = new BinaryNumber(multiplyMe);

        BinaryNumber output = new BinaryNumber();

        if (thisCopy.signum() == 0 | multiplyMeCopy.signum() == 0)
            return ZERO;

        // same signum
        if (thisCopy.signum() == multiplyMeCopy.signum()) {
            // both negative
            if (thisCopy.signum() < 0)
                output = thisCopy.negate().multiplyPositive(multiplyMeCopy.negate());
            else
                output = thisCopy.multiplyPositive(multiplyMeCopy);
        }

        // different signum
        if (thisCopy.signum() != multiplyMeCopy.signum()) {
            // "this" is positive multiplyMe is negative
            if (thisCopy.signum() > 0)
                output = thisCopy.multiplyPositive(multiplyMeCopy.negate());

            else
                output = thisCopy.negate().multiplyPositive(multiplyMeCopy);

            output = output.negate();
        }

        return output;
    }

    // Returns a multiplication of two positive binary numbers
    private BinaryNumber multiplyPositive(BinaryNumber multiplyMe) {
        if (multiplyMe == null || !multiplyMe.bits.isNumber())
            throw new IllegalArgumentException("your input isn't legal!");

        BinaryNumber multiplyPositive = new BinaryNumber(0);
        BinaryNumber multiplyMeCopy = new BinaryNumber(multiplyMe); // we don't to change the "object" we got

        Iterator<Bit> iter = this.bits.iterator();
        // Acts according to the principles of long multiplication
        while (iter.hasNext()) {
            Bit tmp = iter.next();
            if (tmp.equals(Bit.ONE))
                multiplyPositive = multiplyPositive.add(multiplyMeCopy);
            multiplyMeCopy = multiplyMeCopy.multBy2(); // in each iteration, zero is added to the right
        }

        return multiplyPositive;
    }

    //=========================== Intro2CS 2020, ASSIGNMENT 4, TASK 3.11 ================================================
    // Do not change this method
    public BinaryNumber divide(BinaryNumber divisor) {
        if (divisor == null || !divisor.isLegal()) {
            throw new IllegalArgumentException("not legal!");
        }

        if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
        //

        // we don't to change the "object" we got and "this"
        BinaryNumber thisCopy = new BinaryNumber(this);
        BinaryNumber divisorCopy = new BinaryNumber(divisor);

        BinaryNumber output = new BinaryNumber();

        if (thisCopy.signum() == 0)
            return ZERO;

        // same signum
        if (thisCopy.signum() == divisorCopy.signum()) {
            // both negative
            if (thisCopy.signum() < 0)
                output = thisCopy.negate().dividePositive(divisorCopy.negate());
            else
                output = thisCopy.dividePositive(divisorCopy);
        }

        // different signum
        if (thisCopy.signum() != divisorCopy.signum()) {
            // "this" is positive multiplyMe is negative
            if (thisCopy.signum() > 0)
                output = thisCopy.dividePositive(divisorCopy.negate());

            else
                output = thisCopy.negate().dividePositive(divisorCopy);

            output = output.negate();
        }

        return output;
    }

    private BinaryNumber dividePositive(BinaryNumber divisor) {
        if (divisor.equals(ZERO)) // Do not change this line
            throw new RuntimeException("Cannot divide by zero."); // Do not change this line
        //

        BinaryNumber output = new BinaryNumber();
        BinaryNumber carry = new BinaryNumber();
        BinaryNumber thisCopy = new BinaryNumber(this); // we don't to change the "object" we got

        // loop for long division
        for (int i = 0; i < this.length(); i++) {
            carry.bits.addFirst(thisCopy.bits.removeLast()); // increase ans by the last number of this
            carry.bits.reduce();

            //not divide
            if (carry.compareTo(divisor) < 0)
                output.bits.addFirst(Bit.ZERO);
            else {
                output.bits.addFirst(Bit.ONE);//is divide
                carry = carry.subtract(divisor);
            }
        }
        output.bits.reduce();
        return output;
    }


    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.12 ================================================
    public BinaryNumber(String s) {
        if (s == null || s.length() == 0 || (s.charAt(0) == '-' & s.length() == 1))
            throw new IllegalArgumentException("your input isn't legal!");

        BinaryNumber ans = new BinaryNumber(0);
        BinaryNumber tenNum = new BinaryNumber('5').multBy2(); //represent ten in BinaryNumber
        BinaryNumber tenPow = new BinaryNumber(1); // in each iteration in the loop we will power ten

        boolean isPositiveNum = true;
        if (s.charAt(0) == '-') {
            s = s.substring(1);
            isPositiveNum = false;
        }

        // loop for creating the BinaryNumber
        for (int i = s.length() - 1; i >= 0; i--) {
            BinaryNumber temp = new BinaryNumber(s.charAt(i));
            ans = ans.add(temp.multiply(tenPow));
            tenPow = tenPow.multiply(tenNum);
        }
        if (!isPositiveNum)
            ans = ans.negate();

        this.bits = ans.bits;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 3.13 ================================================
    // Returns a string representing the BinaryNumber that acts as a decimal number
    public String toIntString() {
        // Do not remove or change the next two lines
        if (!isLegal()) // Do not change this line
            throw new RuntimeException("I am illegal.");// Do not change this line

        if (this.signum() == 0)
            return "0";

        BinaryNumber thisCopy = new BinaryNumber(this);
        String str = "";
        String output = "";

        //the number is negative
        if (thisCopy.signum() < 0) {
            thisCopy = thisCopy.negate();
            output = '-' + output;
        }

        // In each iteration we divide the number by 10 and subtract the copy number from it
        BinaryNumber ten = new BinaryNumber("10");
        while (!thisCopy.equals(ZERO)) {
            BinaryNumber div1 = thisCopy.divide(ten);
            BinaryNumber multy1 = div1.multiply(ten);
            int digit = thisCopy.subtract(multy1).toInt();
            str = str + digit;
            thisCopy = thisCopy.divide(ten); //divide the string by 10
        }

        for (int i = str.length() - 1; i >= 0; i--) {
            output = output + str.charAt(i);
        }

        return output;
    }


    // Returns this * 2
    public BinaryNumber multBy2() {
        BinaryNumber output = new BinaryNumber(this);
        output.bits.shiftLeft();
        output.bits.reduce();
        return output;
    }

    // Returns this / 2;
    public BinaryNumber divBy2() {
        BinaryNumber output = new BinaryNumber(this);
        if (!equals(ZERO)) {
            if (signum() == -1) {
                output.negate();
                output.bits.shiftRight();
                output.negate();
            } else output.bits.shiftRight();
        }
        return output;
    }
}


