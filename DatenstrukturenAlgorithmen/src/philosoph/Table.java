package philosoph;

public class Table {

	private Philosoph[] philosophArray;
	private Fork[] forkArray;

	public void seat(Philosoph[] philosophArray) {
		this.philosophArray = philosophArray;
	}

	public void place(Fork[] forkArray) {
		this.forkArray = forkArray;
	}

	public Fork tryGetLeftFork(Philosoph philosoph) {
		int chairNr = getChairNumber(philosoph);
		int forkNr = chairNr;

		Fork f = forkArray[forkNr];
		forkArray[forkNr] = null;

		//System.out.println(philosoph + " get Fork " + forkNr);
		return f;
	}

	public Fork tryGetRightFork(Philosoph philosoph) {
		int chairNr = getChairNumber(philosoph);
		int forkNr = (chairNr + 1) % forkArray.length;

		Fork f = forkArray[forkNr];
		forkArray[forkNr] = null;

		//System.out.println( philosoph + " gets Fork " + forkNr);
		return f;
	}

	public void releaseLeftFork(Philosoph philosoph, Fork leftFork) {
		int chairNr = getChairNumber(philosoph);
		int forkNr = chairNr;

		if (forkArray[forkNr] != null) {
			throw new RuntimeException("Something went wrong with Fork " + forkNr);
		}
		
		//System.out.println(philosoph + " release Fork " + forkNr);
		forkArray[forkNr] = leftFork;
	}

	public void releaseRightFork(Philosoph philosoph, Fork rightFork) {
		int chairNr = getChairNumber(philosoph);
		int forkNr = (chairNr + 1) % forkArray.length;

		if (forkArray[forkNr] != null) {
			throw new RuntimeException("Something went wrong with Fork " + forkNr);
		}
		//System.out.println(philosoph + " releases Fork " + forkNr);
		forkArray[forkNr] = rightFork;
	}

	private int getChairNumber(Philosoph philosoph) {
		for (int i = 0; i < philosophArray.length; i++) {
			if (philosophArray[i] == philosoph) {
				return i;
			}
		}
	
		throw new RuntimeException("Unknown Philosoph" + philosoph);
	}

}
