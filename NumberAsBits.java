
public class NumberAsBits {
    public static void main(String[] args) {

    }

    private Bit[] bits;

    //Task 3.4
    public NumberAsBits(Bit[] bits) {
        if (bits == null)
            throw new IllegalArgumentException("Array can not be null");
        Bit [] bits1 = new Bit[bits.length];
        //Copy all cells of bit array to bit1 array
        for (int i = 0; i <bits.length ; i++) {
            if (bits [i] == null)
                throw new IllegalArgumentException("Array cells can not be null");
            bits1 [i] = bits [i];
        }
        this.bits = bits1;
    }

    //Task 3.5
    public String toString() {
        //A string containing the decimal representation of the number
        String  decimal;
        String output = "";
        String power = "1";
        if (bits.length > 0) {
            if (bits[bits.length-1].toInt() == 1)
                decimal = "1";
            else
                decimal = "0";

            for (int i = 1; i < bits.length ; i = i + 1) {
                int curr = bits[bits.length -1 - i].toInt();
                power = SumIntString(power , power);
                if (curr == 1) {
                    decimal = SumIntString(decimal , power);
                }
            }
            output = decimal;
        }

        return output;
    }

    // Auxiliary function for char to int
    private static int toInt(char c) {
        return "0123456789".indexOf(c) ;
    }

    // Auxiliary function for summing the strings of numbers
    private String SumIntString(String s1, String s2) {
        String sum = "" , currSum= "";
        int length, digitSum;

        //Condition for checking if s2 length longer then s1 length
        if (s1.length() < s2.length()) {
            String curr = s1;
            s1 = s2;
            s2 = curr;
        }

        length = s1.length();
        //Condition for checking if s1 length longer then s2 length
        if (s1.length() > s2.length()) {
            currSum = s1.substring(0 , s1.length() - s2.length());
            s1 = s1.substring(s1.length() - s2.length());
            length = s1.length();
        }

        int left = 0;
        // This loop summarizes all the digits in a long essay
        for (int i = length-1; i >= 0; i = i - 1) {
            digitSum = toInt(s1.charAt(i)) + toInt(s2.charAt(i));
            digitSum = digitSum + left;
            left = 0;
            if (digitSum > 9) {
                left = 1;
                digitSum -= 10;
            }
            sum = digitSum + sum;
        }
        // Condition for checking the remainder of the sum is 1
        if (left == 1) {
            if (!currSum.equals("")) {
                    sum = SumIntString(currSum, "1") + sum;
            }
            else
                sum = "1" + sum;
        }
        else
            sum = currSum + sum;

        return sum;
    }

    //Task 3.6
    public String base2() {
        String ans ="";
        // Loop  for a string containing the binary representation of the number
        for (int i = 0; i < bits.length ; i++) {
            if (bits[i].toInt() == 0)
                ans = ans + "0";
            else
                ans = ans + "1";
        }
        return ans;
    }
}


