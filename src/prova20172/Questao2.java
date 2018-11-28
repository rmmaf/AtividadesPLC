package prova20172;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Questao2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AtomicInteger saldo = new AtomicInteger(); 
		for(int i = 0; i < 6; i++) {
			(new PessoaAt(saldo)).start();
		}
	}

}
class PessoaAt extends Thread {
	 private AtomicInteger saldo;
	 private Random rand;
	 
	 public PessoaAt (AtomicInteger saldo) {
		 this.saldo = saldo;
		 rand = new Random();
	 }
	 public void run() {
			for(int i = 0; i < 10; i++) {
				boolean b = rand.nextBoolean();
				if(b) {
					int n = rand.nextInt(1000) + 1;
					saldo.getAndAdd(n);
					System.out.println("Depositado " + n);
				} else {
					int n = rand.nextInt(1000) + 1;
					if(n <= saldo.get()) {
						saldo.getAndAdd(-n);
						System.out.println("Sacado " + n);
					} else {
						System.out.println("Saldo insulficiente");
					}
				}
			}
		}
}