import java.util.Scanner;

public class ImprimePrimos {
	public static class Primo extends Thread{
		int inicio;
		int fina;
		public Primo(int inicio, int fina) {
			this.inicio = inicio;
			this.fina = fina;
		}
		public static boolean ehPrimo(int num) {
			boolean flag = false;
			for(int i = 2; i <= num/2; ++i)
	        {	
	            if(num % i == 0)
	            {
	                flag = true;
	                break;
	            }
	        }
			if(num == 1) {
				return false;
			}
			if (!flag)
	            return true;
	        else
	            return false;
		}
		
		public void run() {
			for(int i = inicio; i < fina; i++) {
				if (ehPrimo(i)) {
					System.out.println(i);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner reader = new Scanner(System.in);
		System.out.println("Insira o N");
		int n = reader.nextInt();
		System.out.println("Insira o X");
		int x = reader.nextInt();
		int fator = n/x;
		for (int init = 1; init < n; init = init + fator) {
			(new Primo(init, (init + fator))).start();
		}
	}

}
