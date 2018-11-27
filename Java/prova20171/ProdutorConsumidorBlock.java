package prova20171;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProdutorConsumidorBlock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(10);
		long tempoExec = System.currentTimeMillis() + 10000; //10 segundos
		while(System.currentTimeMillis() < tempoExec) {
			(new ProdutorB(queue)).start();
			(new ConsumidorB(queue)).start();
		}
	}

}
class ProdutorB extends Thread {
	private BlockingQueue<Integer> queue;
	private Random rand;
	
	public ProdutorB(BlockingQueue<Integer> queue) {
		this.queue = queue;
		rand = new Random();
	}
	
	public void run() {
			int n = rand.nextInt(1000000) + 1;
			try {
				queue.put(n);
				System.out.println("Produtor colocou " + n);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(0);
			}
	}
}
class ConsumidorB extends Thread { 
	private BlockingQueue<Integer> queue;
	
	public ConsumidorB(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			Integer n = queue.take();
			System.out.println("Consumidor retirou " + n);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
}