package appearNunes;

import java.util.Scanner;

public class Exec2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Defina o numero de freds: ");
		int n = in.nextInt();
		System.out.println("Defina o numero de limite: ");
		int x = in.nextInt();
		int fator =  x/n;
		Contador cont = new Contador(0);
		for(int i = 0; i < x; i = i + fator)
			(new Fred(cont, (fator))).start();
	}

}
class Fred extends Thread {
	private Contador cont;
	private int fator;
	
	public Fred(Contador cont, int fator) {
		this.cont = cont;
		this.fator = fator;
	}
	public void run() {
		for(int i = 0; i < fator; i++) {
			cont.incrementa();
			cont.print();
		}
	}
}
class Contador {
	private int cont;
	
	public Contador(int cont) {
		this.cont = cont;
	}
	synchronized public void incrementa() {
		cont++;
	}
	synchronized public void print() {
		System.out.println(cont);
	}
}