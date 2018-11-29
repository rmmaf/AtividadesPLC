package atividadeMarcio;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
 /*
  * O forno de uma padaria tem capacidade para assar 50 pães simultaneamente. À medida 
que pães ficam prontos, são retirados do forno. O abastecimento, que acontece apenas após
o forno ser completamente esvaziado, é feito de maneira que 10 pães são 
colocados no forno por vez, até a capacidade do forno. Assuma que o primeiro lote de pães colocados
no forno é também o primeiro a ser retirado. Utilizando uma fila bloqueante, implemente, em Java,
o comportamento dessa padaria. Considere que retirar os pães do forno é um processo mais
lento que o abastecimento dele, devido ao tempo necessário para assar os pães
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
