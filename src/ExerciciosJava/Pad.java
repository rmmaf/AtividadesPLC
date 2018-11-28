package ExerciciosJava;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/*
 * O forno de uma padaria tem capacidade para assar 50 p�es simultaneamente. � medida 
que p�es ficam prontos, s�o retirados do forno. O abastecimento, que acontece apenas ap�s
o forno ser completamente esvaziado, � feito de maneira que 10 p�es s�o 
colocados no forno por vez, at� a capacidade do forno. Assuma que o primeiro lote de p�es colocados
no forno � tamb�m o primeiro a ser retirado. Utilizando uma fila bloqueante, implemente, em Java,
o comportamento dessa padaria. Considere que retirar os p�es do forno � um processo mais
lento que o abastecimento dele, devido ao tempo necess�rio para assar os p�es.
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
			System.out.println("Retirados os p�es");
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
			System.out.println("Colocados os p�es");
		}
	}
}