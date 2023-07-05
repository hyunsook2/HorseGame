package project;

import java.util.List;
import java.util.Random;

public class Horse extends Thread implements Comparable<Horse> {
	public static int currentRank;
	private String hName;
	private int rank;
	private int position;
	private Random random = new Random();
	private float rate;

	public Horse(String name, float rate) {
		super();
		this.hName = name;
		this.setRate(rate);
	}

	public String getHName() {
		return hName;
	}

	public void setHName(String hName) {
		this.hName = hName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public int compareTo(Horse h) {
		return Integer.compare(this.getRank(), h.getRank());
	}

	@Override
	public String toString() {
		return String.format("%s번마 (승률 : %.3f)", hName, rate);
	}

	@Override
	public void run() {
		int s = random.nextInt(100) + 10;
		for (int i = 1; i <= 50; i++) {
			setPosition(i);

			try {
				Thread.sleep(s + 100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		setRank(++currentRank);
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

}

class Position extends Thread {
	public List<Horse> hs;

	public Position(List<Horse> hs) {
		super();
		this.hs = hs;
	}

	@Override
	public void run() {
		for (Horse clr : hs) {
			clr.setRank(0);
			clr.setPosition(0);
			Horse.currentRank = 0;
		}
		while (true) {

			if (Horse.currentRank == hs.size()) {
				break;
			}

			for (int i = 1; i <= 10; i++) {
				System.out.println();
			}

			for (Horse h : hs) {
				System.out.print(h.getHName() + "번마" + " : ");
				for (int i = 1; i <= 50; i++) {
					if (h.getPosition() == i) {
						System.out.print("🐎");
					} else if (h.getPosition() >= i) {
						System.out.print("→");
					} else {
						System.out.print("=");
					}
				}
				if (h.getPosition() == 50) {
					System.out.print("골인!!");
				}
				System.out.println();
			}

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
