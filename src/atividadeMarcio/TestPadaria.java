package atividadeMarcio;
import java.util.Random;

import atividadeMarcio.Padaria;
public class TestPadaria {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Padaria pad = new Padaria();
		Random rand = new Random();
		boolean val;
		for (int i = 0; i < 50; i++) {
			val = rand.nextBoolean();
			if(val) {
				(new Coloca(pad)).start();
			} else {
				(new Retira(pad)).start();
			}
		}

	}
}
class Coloca extends Thread {
	private  Padaria pad;

	public Coloca(Padaria pad) {
		this.pad = pad;
	}

	public void run() {
		try {
			pad.colocar();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
class Retira extends Thread {
	private  Padaria pad;

	public Retira(Padaria pad) {
		this.pad = pad;
	}

	public void run() {
		try {
			pad.retirar();;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}