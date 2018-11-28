package ExerciciosJava;
import java.util.Random;

public class Exec1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vetor vetor = new Vetor();
		long tempoExec = System.currentTimeMillis() + 10000; //10 segundos
		while(System.currentTimeMillis() < tempoExec) {
			(new Operador(vetor)).start();
		}
		
	}

}
class Operador extends Thread {
	Vetor vetor;
	private Random rand;
	public Operador(Vetor vetor) {
		this.vetor = vetor;
		rand = new Random();
	}
	
	public void run() {
		int n = rand.nextInt(4);
		switch (n) {
		case 0: 
			int a = rand.nextInt(50);
			vetor.insert(a);
			System.out.println("Valor inserido " + a);
		case 1:
			int aux = rand.nextInt(50);
			int aux2 = rand.nextInt(vetor.getLen());
			vetor.set(aux2, aux);
			System.out.println("Valor alterado local value: " + aux2 + " " + aux);
		
		case 2:
			int local = rand.nextInt(vetor.getLen());
			System.out.println("Valor do local e valor: " + vetor.get(local) + " " + local);
		case 3:
			int local1 = rand.nextInt(vetor.getLen());
			int local2 = rand.nextInt(vetor.getLen());
			vetor.swap(local1, local2);
			System.out.println("valores trocados " + local1 + " " + local2);
		}
	}
}
class Vetor {
	private int vetor[];
	private int last;
	public Vetor () {
		vetor = new int[200];
		last = 0;
	}

	synchronized public void extend() {
		if(last >= (vetor.length - 1)) {
			int vetorAux[] = new int[2 * vetor.length];
			System.arraycopy(vetor, 0, vetorAux, 0, vetor.length);
			vetor = vetorAux;
		}
	}

	synchronized public void insert(int n) {
		synchronized (this) {
			this.extend();
		}
		vetor[last] = n;
		last++;
	}

	synchronized public void set(int local, int n) {
		if(local <= last)
			vetor[local] = n;
	}

	synchronized public int get(int local) {
		return vetor[local];
	}
	synchronized public void swap(int local1, int local2) {
		int aux = vetor[local2];
		vetor[local2] = vetor[local1];
		vetor[local1] = aux;
	}
	synchronized public int getLen() {
		return last;
	}
}