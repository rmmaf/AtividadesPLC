package ExerciciosJava;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * O forno de uma padaria tem capacidade para assar 50 pães simultaneamente. À medida 
que pães ficam prontos, são retirados do forno. O abastecimento, que acontece apenas após
o forno ser completamente esvaziado, é feito de maneira que 10 pães são 
colocados no forno por vez, até a capacidade do forno. Assuma que o primeiro lote de pães colocados
no forno é também o primeiro a ser retirado. Utilizando uma fila bloqueante, implemente, em Java,
o comportamento dessa padaria. Considere que retirar os pães do forno é um processo mais
lento que o abastecimento dele, devido ao tempo necessário para assar os pães.
 */
public class Pad {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Forno forno = new Forno();
		long tempoExec = System.currentTimeMillis() + 10000; //10 segundos
		while(System.currentTimeMillis() < tempoExec) {
			(new Colocar(forno)).start();
			(new Assar(forno)).start();
		}
	}

}
class Assar extends Thread {
	private Forno forno;
	
	public Assar(Forno forno) {
		this.forno = forno;
	}
	
	public void run() {
		try {
			Thread.sleep(1000);
			forno.retirar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
class Colocar extends Thread {
	private Forno forno;
	
	public Colocar(Forno forno) {
		this.forno = forno;
	}
	
	public void run() {
		forno.colocar();
	}
}
class Forno {
	private BlockingQueue<Boolean> queue;
	
	public Forno() {
		queue = new LinkedBlockingDeque<>(50);
	}
	
	public synchronized void retirar() {
		if(queue.size() == 50) {
			while(!queue.isEmpty()) {
				try {
					queue.take();queue.take();queue.take();queue.take();queue.take();queue.take();queue.take();queue.take();queue.take();queue.take();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Retirados os pães");
		}
	}
	
	public synchronized void colocar() {
		if(queue.isEmpty()) {
			while(queue.size() < 50) {
				try {
					queue.put(true);queue.put(true);queue.put(true);queue.put(true);queue.put(true);queue.put(true);queue.put(true);queue.put(true);queue.put(true);queue.put(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("Colocados os pães");
		}
	}
}