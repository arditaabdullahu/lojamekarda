package Pamja;

public class ShenimetEKartes {
	
	private int vlera;
	private int shenjat;
	
	public ShenimetEKartes(int vlera , int shenjat) {
		if (shenjat  > 4 ) shenjat = 4;
		if (shenjat  < 1 ) shenjat = 1;
		if (vlera > 14) vlera = 14;
		if (vlera < 2 ) vlera = 2;
		this.vlera = vlera;
		this.shenjat = shenjat;
	}
	
	public int merrShenjat() {
		return shenjat;
	}
	public int merrValue() {
		return vlera;
	}

}
