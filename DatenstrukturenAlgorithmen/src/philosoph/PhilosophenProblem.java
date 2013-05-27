package philosoph;

public class PhilosophenProblem {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Table t = new Table();
		
		Philosoph[] philosophArray = new Philosoph[5];
		philosophArray[0]= new Philosoph("Sokrates", t);
		philosophArray[1]= new Philosoph("Archimedes", t);
		philosophArray[2]= new Philosoph("Brutus", t);
		philosophArray[3]= new Philosoph("Cicero", t);
		philosophArray[4]= new Philosoph("Konfuzius", t);

		Fork[] forkArray = new Fork[5];
		forkArray[0] = new Fork();
		forkArray[1] = new Fork();
		forkArray[2] = new Fork();
		forkArray[3] = new Fork();
		forkArray[4] = new Fork();
		
		t.seat(philosophArray);
		t.place(forkArray);
		
		philosophArray[0].setPriority(Thread.MAX_PRIORITY);
		for(int i = 0; i< philosophArray.length;i++){
			philosophArray[i].start();
		}
	}
	
	
}
