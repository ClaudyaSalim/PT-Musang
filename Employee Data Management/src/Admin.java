import java.util.Vector;

public class Admin extends Manager{ //sama fungsinya kayak class manager cuma beda detail
	
	public Admin() {
		
	}
	
	public void add(int idx, Vector <Double> gajiVector, int menu, int num, Vector <String> kodeVector, Vector <String> code, Vector <String> jabatanVector) {
		super.value = 4000000; //gaji
		super.bonus = 105;
		super.posisi = "Admin";
		super.add(idx, gajiVector, menu, num, kodeVector, code, jabatanVector);
	}

}