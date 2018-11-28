package provas;

import java.util.ArrayList;
import java.util.Random;

public class BlockingList {
	// uma blocking list so deixa outra ação tomar se o negocio estiver liberado para uso.
	public static void main(String[] args) {
		Vetor lista= new Vetor();
		for (int i = 0; i < 5; i++) {
			(new aThread(lista)).start();
			
		}

	}

}

class aThread extends Thread{
	private Vetor lista ;
	private Random rand;
	public  aThread(Vetor lista) {
		this.lista = lista;
		this.rand =new Random();
	}
	public void run() {
		for (int i = 0; i < 10; i++) {
			boolean randomico = rand.nextBoolean();
			if (randomico == true) {
				int valor = rand.nextInt(1000)+1;
				lista.add(valor);
			}else {
				lista.rem();
			}
			
		}
		
	}
}
class Vetor{
	private ArrayList<Integer> vetor;
	private Semaforo semaforo;
	
	public Vetor() {
		Semaforo sema = new Semaforo();
		ArrayList<Integer> lista = new ArrayList<Integer>();
		this.vetor = lista ;
		this.semaforo = sema;
	}
	
	public void add(int valor) {
		semaforo.fechar();
		vetor.add(valor);
		System.out.println("Adicionado: "+ valor);
		semaforo.liberar();
		
	}
	public void rem() {
		semaforo.fechar();
		if( vetor.size() > 0 ) {
			System.out.println("Removido: "+ vetor.remove(0));
		}
		semaforo.liberar();
	}
}
class Semaforo{
	private boolean lock;
	public Semaforo() {
		this.lock=false;
	}
	synchronized public void liberar() {
		if(lock == true){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			liberar();
		}else {
			lock = true;
		}
	}
	
	synchronized public void fechar() {
		lock= false;
		notify();
	}
}
