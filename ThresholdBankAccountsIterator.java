import java.util.Iterator;
import java.util.NoSuchElementException;


public class ThresholdBankAccountsIterator implements Iterator<BankAccount> {

    private BinaryTreeInOrderIterator<BankAccount> iterator;
    private BankAccount current;
    private int balanceThreshold;

    // task 5c
    public ThresholdBankAccountsIterator(BankAccountsBinarySearchTree bankAccountsTree, int balanceThreshold) {
        this.balanceThreshold = balanceThreshold;
        bankAccountsTree.balance();
        current = null; // if none of the bank accounts meets the condition or bankAccountsTree is empty

        //runs all over bankAccountsTree
        iterator = new BinaryTreeInOrderIterator<BankAccount>(bankAccountsTree.root);
        while (iterator.hasNext() & current == null) {
            BankAccount tmp = iterator.next();
            if (tmp.getBalance() >= balanceThreshold) //Account with sufficient balance
                current = tmp;
        }
    }

    //Complete the following method
    @Override
    // task 5c
    public boolean hasNext() {
        return current != null;
    }

    //Complete the following method
    @Override
    // task 5c
    public BankAccount next() {
        if (!hasNext())
            throw new NoSuchElementException();
        BankAccount ans = current;
        // runs all over accounts
        while (iterator.hasNext()) {
            current = iterator.next();
            if (current.getBalance() >= balanceThreshold) //Account with sufficient balance
                return ans;
        }
        current = null; // no bank account with sufficient balance
        return ans;
    }
}
