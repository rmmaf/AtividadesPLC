import java.util.List;
public class PrintaSeq {
	public static class Print extends Thread{
		long init;
		long ultim;
		Integer fred;
		public Print(long init, long ultim, Integer fred) {
			this.init = init;
			this.ultim = ultim;
			this.fred = fred;
		}
		public void run() {
			System.out.println("inicio " + init);
			for (long i = init; i < ultim; i++) {
				
			}
			System.out.println("Final " + ultim);
			System.out.println("Finalizada fred " + fred);
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long i = 0;
		long f = 2000000000;
		long fator = 40000000;
		Integer freds = 0;
		for (long init = i; init < f; init = init + fator, freds++) {
			System.out.println("Iniciada Fred " + freds);
			(new Print(init, (init + fator), freds)).start();
		}
	}

}
