package atividadeMarcio;

public class Vetor {
	private int array[];
	
	public Vetor(int size) {
		array = new int[size];
	}
	
	synchronized public void set (int num, int local) {
		array[local] = num;
	}
	
	synchronized public int get(int local) {
		return array[local];
	}
	synchronized public void swap(int local1, int local2) {
		int aux = array[local1];
		array[local1] = array[local2];
		array[local2] = aux;
	}
}
