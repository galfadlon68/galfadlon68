
public class Bit {

    private boolean value;

    //Task 3.1
    public Bit(boolean value){
        this.value = value;
    }

    //Task 3.2
    public int toInt(){ 
        int ans = 0;
        if (this.value)
            ans = 1;
        else
            ans = 0;

        return ans;
    }

    //Task 3.3
    public String toString(){
        String ans = "";
        if (this.value)
            ans = "1";
        else
            ans = "0";

        return ans;
    }
}

