import java.util.Scanner;
import java.util.Vector;
//pake scanner sama vector

public class Karyawan {
	
	//deklarasi
	private String nama;
	private String gender;
	private String jabatan;
	Scanner scan = new Scanner(System.in);
	Admin a = new Admin();
	Supervisor s = new Supervisor();
	Manager m = new Manager();

	public Karyawan () {
		
	}
	
	//method buat scanning nama, gender, jabatan
	public String scanNama () {
		System.out.print("Input nama karyawan: [>= 3]: ");
		nama = scan.nextLine();
		return nama;
	}
	
	public String scanGender () {
		System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
		gender = scan.nextLine();
		return gender;
	}
	
	public String scanJabatan () {
		System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
		jabatan = scan.next(); scan.nextLine();
		return jabatan;
	}
	
	//method buat nambah / update gaji -> cek jabatan dulu
	public void checkJabatan (int idx, Vector <Double> gajiVector, int menu, int num, Vector <String> kodeVector, Vector <String> code, Vector <String> jabatanVector) {
		if (jabatan.equals("Manager")) {
			m.add(idx, gajiVector, menu, num, kodeVector, code, jabatanVector); //pass ke class manager
		}
		else if (jabatan.equals("Supervisor")) {
			s.add(idx, gajiVector, menu, num, kodeVector, code, jabatanVector); //pass ke class supervisor
		}
		else if (jabatan.equals("Admin")) {
			a.add(idx, gajiVector, menu, num, kodeVector, code, jabatanVector); //pass ke class admin
		}
	}
	
}
