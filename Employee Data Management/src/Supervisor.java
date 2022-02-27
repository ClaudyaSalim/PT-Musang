import java.util.Vector;

public class Supervisor extends Manager{ //sama fungsinya kayak class manager cuma beda detail

	public Supervisor() {
		
	}
	
	public void add(int idx, Vector <Double> gajiVector, int menu, int num, Vector <String> kodeVector, Vector <String> code, Vector <String> jabatanVector) {
		super.value = 6000000; //gaji
		super.bonus = 107.5;
		super.posisi = "Supervisor";
		super.add(idx, gajiVector, menu, num, kodeVector, code, jabatanVector);
	}

}
