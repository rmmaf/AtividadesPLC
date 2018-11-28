package prova20172;

import java.util.Random;

public class Questao1 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Conta conta = new Conta(0);
		for(int i = 0; i < 6; i++) {
			(new Pessoa(conta)).start();
		}
		
	}

}
class Conta {
	private int saldo;
	
	public Conta(int saldoInicio) {
		saldo = saldoInicio;
	}
	
	synchronized public boolean sacar(int n) {
		if((saldo - n) >= 0) {
			saldo = saldo - n;
			return true;
		} else {
			return false;
		}
	}

	synchronized public void depositar(int n) {
		saldo = saldo + n;
	}
}
class Pessoa extends Thread {
	private Conta conta;
	private Random rand;
	
	public Pessoa(Conta conta) {
		this.conta = conta;
		rand = new Random();
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			boolean b = rand.nextBoolean();
			if(b) {
				int n = rand.nextInt(1000) + 1;
				conta.depositar(n);
				System.out.println("Depositado " + n);
			} else {
				int n = rand.nextInt(1000) + 1;
				if(conta.sacar(n)) {
					System.out.println("Sacado " + n);
				} else {
					System.out.println("Saldo insulficiente");
				}
			}
		}
	}
}