package philosoph;

public class Philosoph extends Thread {

	private String name;

	private Table table;

	/**
	 * Constructor
	 */
	public Philosoph(String philosophName, Table table) {
		name = philosophName;
		this.table = table;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void run() {
		while (true) {

			Fork leftFork = null;
			Fork rightFork = null;

			synchronized (table) {
				// acquire resources
				leftFork = table.tryGetLeftFork(this);
				if (leftFork != null) {
					rightFork = table.tryGetRightFork(this);

					if (rightFork == null) {
						table.releaseLeftFork(this, leftFork);
					}
				}
			}

			if (leftFork != null && rightFork != null) {
				System.out.println(name + " is eating...");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				System.out.println(name + " is full");

				synchronized (table) {
					table.releaseLeftFork(this, leftFork);
					table.releaseRightFork(this, rightFork);
				}
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}

		}
	}

}
