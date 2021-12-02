import java.util.ArrayList;

public class CarQueue {
	ArrayList<Integer> q = new ArrayList<>();
	int randomNum;
	
	public CarQueue() {
		for(int i = 0; i < 5; i++) {
			randomNum = (int) (Math.random() * 4);
			q.add(randomNum);
		}
	}
	
	public void add(int i) {
		q.add(i);
	}
	
	public void addToQueue() {
		carRunnable cr = new carRunnable(this);
		Thread t = new Thread(cr);
		t.start();
	}
	
	public int deleteQueue() {
		int result = q.get(0);
		q.remove(0);
		return result;
	}
	
	public class carRunnable implements Runnable {
		CarQueue cq;
		int randomNum;
		public carRunnable(CarQueue cq) {
			this.cq = cq;
		}
		@Override
		public void run() {
			try {
				while(!Thread.interrupted()) {
					randomNum = (int) (Math.random() * 4);
					cq.add(randomNum);
					Thread.sleep(randomNum);
				}
			} catch(InterruptedException e) {
				
			}
		}
		
	}
}
