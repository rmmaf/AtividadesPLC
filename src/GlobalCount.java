import java.util.Scanner;

public class GlobalCount {
	
	public static class Seq extends Thread{
		int fator;
		Contador cont;
		public Seq(int fator, Contador cont) {
			this.fator = fator;
			this.cont = cont;
		}
		
		public void run() {
			for(int i = 0; i < fator; i++) {
				cont.print();
				cont.incrementa();
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.println("Insira o Limite do valor");
		int n = reader.nextInt();
		System.out.println("Insira o Limite de Freds");
		int x = reader.nextInt();
		int fator = n/x;
		Contador cont = new Contador(0);
		for (int init = 1; init < n; init = init + fator) {
			(new Seq(fator, cont)).start();
		}
	}

}
class Contador {
	public int cont;
	public Contador (int cont) {
		this.cont = cont;
	}
	synchronized public void print() {
		synchronized(this) {
			System.out.println(this.cont);
		}
	}
	synchronized public void incrementa() {
		synchronized(this) {
			cont++;
		}
	}
}