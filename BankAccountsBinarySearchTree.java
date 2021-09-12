/*---------------------------------------
 Genuine author: <name>, I.D.: <id number>
 Date: xx-xx-2020 
---------------------------------------*/
import java.util.Comparator;
import java.util.Iterator;

public class BankAccountsBinarySearchTree extends BinarySearchTree<BankAccount>{

	public BankAccountsBinarySearchTree(Comparator<BankAccount> myComparator) {
		super(myComparator);
	}
	
	// Complete the following methods

	// task 5b
	public void balance(){
		// create a sorted list
		List<BankAccount> list = new DynamicArray<BankAccount>();
		BinaryTreeInOrderIterator<BankAccount> iter = new BinaryTreeInOrderIterator<>(root);
		while (iter.hasNext())
			list.add(iter.next());

		root = null; // change to empty tree
		buildBalancedTree(list, 0, list.size()-1);// balanced tree recursively
	}

	// task 5b
	private void buildBalancedTree(List<BankAccount> list, int low, int high){

		// Get the middle of the list and insert him to the tree
		int mid = (low + high) / 2;
		this.insert(list.get(mid));

		// create left and right subtrees and go on to balance each
		if (low < high) { // similar to binary search in array
			buildBalancedTree(list, mid + 1, high);
			buildBalancedTree(list, low, mid - 1);
		}
	}
	
}
