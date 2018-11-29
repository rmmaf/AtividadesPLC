package atividadeMarcio;

public class FilaBloqueante {
	private int fila[];
	private int size;
	
	public FilaBloqueante(int size) {
		fila = new int[size];
		size = 0;
	}
	
	synchronized public void push(int a) throws InterruptedException {
		if(size == fila.length) {
			wait();
		}
		fila[size] = a;
		size++;
		notifyAll();
	}
	
	synchronized public void pop() throws InterruptedException {
		if(size == 0) {
			wait();
		}
		for (int i = 0; i < this.size - 1; i++) {
			this.fila[i] = this.fila[i + 1];
		}//caso o size seja igual a 1, ele nao remove, mas libera para sobrescrever
		size--;
		notifyAll();
	}
	
}
