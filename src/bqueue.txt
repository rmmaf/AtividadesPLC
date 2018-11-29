class BQueue {
	private int[] fila;
	private int size;

	public BQueue(int size) {
		this.fila = new int[size];
		this.size = 0;
	}

	public synchronized void push(int val) throws InterruptedException {
		if (this.size == this.fila.length) {
			wait();
		}
		this.fila[this.size++] = val;
		notifyAll();
	}

	public synchronized int pop() throws InterruptedException {
		if (this.size == 0) {
			wait();
		}
		int ret = this.fila[0];
		for (int i = 0; i < this.size - 1; i++) {
			this.fila[i] = this.fila[i + 1];
		}
		this.size--;
		notifyAll();
		return ret;
	}

	public synchronized int getSize() {
		return this.size;
	}
}