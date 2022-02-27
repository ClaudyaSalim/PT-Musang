import java.util.Vector;

public class Manager {

	public double value = 8000000; //gaji
	public double bonus = 110;
	public String posisi = "Manager";
	Vector <String> kodetemp = new Vector<>(); //buat tampung kode karyawan yg khusus punya jabatan tersebut
	
	public Manager() {
		
	}
	
	public void add(int idx, Vector <Double> gajiVector, int menu, int num, Vector <String> kodeVector, Vector <String> code, Vector <String> jabatanVector) {
		int jumlah;
		if (menu==1) { //kalau milih insert di main
			gajiVector.add(value);
		}
		else { //kalau milih update di main
			gajiVector.set(idx-1, value);
		}
		
			jumlah = cekJumlah(num, kodeVector, code, jabatanVector); //cari brp orang yg punya jabatan manager
			if (jumlah%3==1 && jumlah>3) { //kalau ada 4 manager & berlaku kelipatan + 3
				if (menu==1) { //print bonus yg didapat cuman pas insert karyawan baru aja
				System.out.print("Bonus sebesar " + (bonus-100) + "% telah diberikan kepada karyawan dengan id ");
				}
				for (int i=0; i<jumlah-1; i++) {
					if (menu==1) { //print bonus yg didapat cuman pas insert karyawan baru aja
						if (i==jumlah-2) {
							System.out.println(kodetemp.get(i));
						}
						else {
							System.out.print(kodetemp.get(i) + ", ");
						}
					}

					for (int j=0; j<num; j++) {
						if (kodeVector.get(j)==kodetemp.get(i)) {
							gajiVector.set(j, gajiVector.get(j)*bonus/100); //gaji di-update
							break;
						}
					}
				}
			}
		kodetemp.removeAllElements(); //hapus yg tampung kode karyawan khusus jabatan manager
	}
	
	//method buat cek jumlah orang yg jabatannya manager
	public int cekJumlah(int num, Vector <String> kodeVector, Vector <String> code, Vector <String> jabatanVector) {
		int jumlah = 0;
		for (int i=0; i<num; i++) {
			for (int j=0; j<num; j++) {
				if (code.get(i)==kodeVector.get(j)) {
					if (jabatanVector.get(j).equals(posisi)) {
						jumlah++;
						kodetemp.add(code.get(i));
						break;
					}
				}
			}
		}
		return jumlah;
	}
}
