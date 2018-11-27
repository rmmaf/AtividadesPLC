package prova20171;
import java.util.Random;


public class ProdutorConsumidor {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContadorPC cont = new ContadorPC(100);
		long tempoExec = System.currentTimeMillis() + 10000; //10 segundos
		while(System.currentTimeMillis() < tempoExec) {
			
			Consumidor c = new Consumidor(cont);
			Produtor p = new Produtor(cont);
			p.start();
			c.start();
		}
		
	}

}
class Produtor extends Thread {
	private ContadorPC cont;
	private Random rand;
	public Produtor (ContadorPC cont) {
		this.cont = cont;
		this.rand = new Random();
	}
	public void run() {
		int n = rand.nextInt(1000000) + 1;
		System.out.println("Produtor colocou " + cont.adiciona(n));
	}
}
class Consumidor extends Thread {
	private ContadorPC cont;
	public Consumidor (ContadorPC cont) {
		this.cont = cont;
		
	}
	public void run() {
		try {
			System.out.println("Consumidor retirou " + cont.remove());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class ContadorPC {
	private int buffer[];
	private int last;
	public ContadorPC(int tamanho) {
		this.buffer = new int[tamanho];
		this.last = 0;
	}
	synchronized public int adiciona(int numero) {
		try {
			buffer[last] = numero;
			last++;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array fora do índice ao adicionar: "+e.getMessage());
            System.exit(0);
        }
		return numero;
	}
	synchronized public int remove() throws InterruptedException {
		int n = -1;
		try {
			if(last > 0) {
				n = buffer[last];
				buffer[last] = 0;
				last--;
			}
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Array fora do índice ao remover: "+e.getMessage());
            System.exit(0);
        }
		return n;
	}
}