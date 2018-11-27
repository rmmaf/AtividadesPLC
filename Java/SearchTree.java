import java.util.Random;

//Random rand = new Random();
//int n = rand.nextInt(50) + 1; maixmo 50 e minimo 1
public class SearchTree {
	public static class Fred extends Thread {
		Tree tree;
		public Fred(Tree tree) {
			this.tree = tree;
		}
		public void run() {
			Random rand = new Random();
			for(int i = 0; i < 2000; i++) {
				tree.add(rand.nextInt(1000000000) + 1);
			}
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree tree = new Tree();
		for(int i = 0; i < 50; i++) {
			Fred fred = (new Fred(tree));
			fred.start();
			try{
				fred.join();
			}catch(Exception e){
			}
		}//o join "segura" o programa, esperando as freds terminarem para entao prosseguir com o codigo
		tree.printInit();

	}

}
class Node {
	int value;
	Node left;
	Node right;

	public Node(int value) {
		this.value = value;
		right = null;
		left = null;
	}
}
class Tree {
	Node root;
	int init;
	public Tree(){
		root = null;
		init = 0;
	}
	synchronized private Node addRecursive(Node current, int value) {
		if (current == null) {
			synchronized(this) {
				init++;
			}
			return new Node(value);
		}

		if (value < current.value) {
			current.left = addRecursive(current.left, value);
		} else if (value > current.value) {
			current.right = addRecursive(current.right, value);
		} else {
			// value already exists
			return current;
		}

		return current;
	}
	synchronized public void add(int value) {
		root = addRecursive(root, value);
	}
	public void printInit() {
		System.out.println(init);
	}
}