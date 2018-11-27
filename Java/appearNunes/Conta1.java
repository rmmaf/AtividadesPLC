package appearNunes;
import java.util.Random;
public class Conta1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conta2 conta = new Conta2();
		for(int i = 0; i < 6; i++) {
			(new Pessoa(conta)).start();
		}
	}

}
class Pessoa extends Thread {
	private Random rand;
	private Conta2 conta;
	
	public Pessoa(Conta2 cont) {
		this.conta = cont;
		rand = new Random();
	}
	public void run() {
		for(int i = 0; i < 20; i++) {
			boolean b = rand.nextBoolean();
			int n = rand.nextInt(1000) + 1;
			if(b) {
				conta.depositar(n);
				System.out.println("Depositado " + n);
			} else {
				if(conta.sacar(n)) {
					System.out.println("Sacado " + n);
				} else {
					System.out.println("Saldo insulficiente");
				}
			}
		}
	}
}

class Conta2 {
	private int saldo;
	
	public Conta2() {
		this.saldo = 0;
	}
	synchronized public void depositar(int valor) {
		saldo = saldo + valor;
	}
	synchronized public boolean sacar(int valor) {
		if(valor > saldo) {
			return false;
		} else {
			saldo = saldo - valor;
			return true;
		}
	}
}