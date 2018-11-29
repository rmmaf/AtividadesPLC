package atividadeMarcio;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
 /*
  * O forno de uma padaria tem capacidade para assar 50 p�es simultaneamente. � medida 
que p�es ficam prontos, s�o retirados do forno. O abastecimento, que acontece apenas ap�s
o forno ser completamente esvaziado, � feito de maneira que 10 p�es s�o 
colocados no forno por vez, at� a capacidade do forno. Assuma que o primeiro lote de p�es colocados
no forno � tamb�m o primeiro a ser retirado. Utilizando uma fila bloqueante, implemente, em Java,
o comportamento dessa padaria. Considere que retirar os p�es do forno � um processo mais
lento que o abastecimento dele, devido ao tempo necess�rio para assar os p�es
  */
public class Padaria {
	private BlockingQueue<Boolean> forno;
	private boolean sinalColocar;
	private final long tempoAssar = 3000;//3 segundos
	public Padaria() {
		forno = new LinkedBlockingDeque<>(50);
		sinalColocar = true;
	}
	
	synchronized public void retirar() throws InterruptedException {
		if(forno.isEmpty()) {
			wait();
		}//nao eh necessario ja que estamos usando blockingqueue mas coloquei devido ao fato de ter de esperar assar
		long tempoEstimado = System.currentTimeMillis() + tempoAssar;
		while(System.currentTimeMillis() < tempoEstimado) {/*assando*/}
		for(int i = 0; i < 10; i++) {
			forno.take();
		}
		if(forno.size() == 0) {
			sinalColocar = true;
		}
		notifyAll();
	}
	
	synchronized public void colocar() throws InterruptedException {
		if(!sinalColocar) {
			wait();
		}
		for(int i = 0; i < 10; i++) {
			forno.put(true);;
		}
		if(forno.size() == 50) {
			sinalColocar = false;
		}
		notifyAll();
	}
	
}
