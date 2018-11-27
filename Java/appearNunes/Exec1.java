package appearNunes;
import java.util.Scanner;

public class Exec1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Defina o numero de freds: ");
		int n = in.nextInt();
		System.out.println("Defina o numero de limite: ");
		int x = in.nextInt();
		int fator =  x/n;
		for(int i = 0; i < x; i = i + fator)
			(new Conta(i, (i + fator))).start();
	}

}
class Conta extends Thread {
	private int inicio;
	private int fim;
	public Conta(int inicio, int fim) {
		this.inicio = inicio;
		this.fim = fim;
	}
	public void run() {
		for(int i = inicio; i < fim; i++)
			System.out.println(i);
	}
}