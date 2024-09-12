package controller;

import java.util.concurrent.Semaphore;

public class ThreadCozinha extends Thread{
	private int tid, tempot, porc;
	private double prog;
	private String nome1 = "Sopa da Cebola", nome2 = "Lasanha a Bolonhesa";
	private Semaphore semaforo;
	
	public ThreadCozinha(int tid, Semaphore semaforo) {
		this.tid = tid;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		cozimento();
		try {
			semaforo.acquire();
			entrega();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}finally {
			semaforo.release();
		}
	}
	
	private void cozimento() {
		if(tid % 2 == 0) {
			tempot = (int)(Math.random() * 600) + 600;
			System.out.println("O prato "+tid+": "+nome2+" se iniciou.");
			while(prog < tempot) {
				prog += 100;
				porc = (int)((prog*100) / tempot);
				if(prog > tempot) {
					System.out.println("O prato "+tid+": "+nome2+", está 100% cozido.");
				}else {
					System.out.println("O prato "+tid+": "+nome2+", está "+porc+"% cozido.");
				}
				
			}
			System.out.println("O prato "+tid+": "+nome2+" ficou pronto.");
		}else {
			tempot = (int)(Math.random() * 300) + 500;
			System.out.println("O prato "+tid+": "+nome1+" se iniciou.");
			while(prog < tempot) {
				prog += 100;
				porc = (int)((prog*100) / tempot);
				if(prog > tempot) {
					System.out.println("O prato "+tid+": "+nome1+", está 100% cozido.");
				}else {
					System.out.println("O prato "+tid+": "+nome1+", está "+porc+"% cozido.");
				}
				
			}
			System.out.println("O prato "+tid+": "+nome1+" ficou pronto.");
		}
	}
	
	private void entrega() {
		if(tid % 2 == 0) {
			System.out.println("O prato "+tid+": "+nome2+", foi levado para entrega.");
			tempot = 500;
			try {
				sleep(tempot);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("O prato "+tid+": "+nome2+", foi entregue.");
		}else {
			System.out.println("O prato "+tid+": "+nome1+", foi levado para entrega.");
			tempot = 500;
			try {
				sleep(tempot);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			System.out.println("O prato "+tid+": "+nome1+", foi entregue.");
		}
	}
}
