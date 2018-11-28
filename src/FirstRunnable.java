
public class FirstRunnable implements Runnable {
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Hi (from a runnable fred)");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		(new Thread(new FirstRunnable())).start();
	}


}
