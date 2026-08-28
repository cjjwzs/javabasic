package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Learn Java from https://www.liaoxuefeng.com/
 * 
 * @author liaoxuefeng
 */
public class Main {

	static final Object LOCK_A = new Object();
	static final Object LOCK_B = new Object();

	public static void main(String[] args) {

		ExecutorService es = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 6; i++) {
			es.submit(new Task("" + i));
		}
		es.shutdown();

		//new Thread1().start();
		new Thread2().start();

		int [][]x;
		int y[][];
		int []z[];

		String[][] rooms=new String[3][];
		rooms[0]=new String[]{"Tom","Mike","Jack",null}; //第一层楼的客人
		rooms[1]=new String[]{"Mary",null,"Linda"}; //第二层楼的客人
		rooms[2]=new String[]{null,"Jane",null,null,"Rose"}; //第三层楼的客人

	}

	static void sleep1s() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Thread1 extends Thread {

	public void run() {
		System.out.println("Thread-1: try get lock A...");
		synchronized (Main.LOCK_A) {
			System.out.println("Thread-1: lock A got.");
			Main.sleep1s();
			System.out.println("Thread-1: try get lock B...");
			synchronized (Main.LOCK_B) {
				System.out.println("Thread-1: lock B got.");
				Main.sleep1s();
			}
			System.out.println("Thread-1: lock B released.");
		}
		System.out.println("Thread-1: lock A released.");
	}
}

class Thread2 extends Thread {

	public void run() {
		System.out.println("Thread-2: try get lock B...");
		synchronized (Main.LOCK_B) {
			System.out.println("Thread-2: lock B got.");
			Main.sleep1s();
			System.out.println("Thread-2: try get lock A...");
			synchronized (Main.LOCK_A) {
				System.out.println("Thread-2: lock A got.");
				Main.sleep1s();
			}
			System.out.println("Thread-2: lock A released.");
		}
		System.out.println("Thread-2: lock B released.");
	}
}

class Task implements Runnable {

	private final String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.println("start task " + name);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		System.out.println("end task " + name);
	}
}
