import java.util.Scanner;
import java.util.Vector;

public class Main {

	Scanner scan = new Scanner(System.in);
	int num=0; int menu;
	Vector <String> namaVector = new Vector<>();
	Vector <String> kodeVector = new Vector<>();
	Vector <String> genderVector = new Vector<>();
	Vector <String> jabatanVector = new Vector<>();
	Vector <Double> gajiVector = new Vector<>();
	Vector <String> code = new Vector<>();
	Vector <String> kodetemp = new Vector<>();
	
	
	public Main () {
		mainMenu();
	}
	
	
	public static void main(String[] args) {
		new Main();

	}
	
	
	private void mainMenu() {
		do {
			System.out.println("Data Karyawan PT Musang");
			System.out.println("=======================");
			System.out.println("1. Insert");
			System.out.println("2. View");
			System.out.println("3. Update");
			System.out.println("4. Delete");
			System.out.println("5. Exit");
			
			do {
				System.out.print("Input: ");
				menu=scan.nextInt();
			} while (menu!=1 && menu!=2 && menu!=3 && menu!=4 && menu!=5);
			scan.nextLine();
			
			switch (menu) {
			case 1:
				insert();
				break;
			case 2:
				view();
				break;
			case 3:
				view();
				update();
				break;
			case 4:
				view();
				delete();
				break;
			}
			System.out.println("ENTER to return");
			scan.nextLine();
			System.out.println();
		} while (menu!=5);
	}
	
	
	
	private void insert () {
		String nama; String gender; String jabatan;
		do {
			System.out.print("Input nama karyawan: [>= 3]: ");
			nama = scan.nextLine();
		} while (nama.length()<3);
		namaVector.add(nama);
		
		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			gender = scan.nextLine();
		} while (gender.compareTo("Laki-laki")!=0 && gender.compareTo("Perempuan")!=0);
		genderVector.add(gender);
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.next(); scan.nextLine();
		} while (jabatan.compareTo("Manager")!=0 && jabatan.compareTo("Supervisor")!=0 && jabatan.compareTo("Admin")!=0);
		jabatanVector.add(jabatan);
		
		String string = random();
		for (int i=0; i<num; i++) {
			while (kodeVector.get(i)==string) {
				string = random();
			}
		}
		kodeVector.add(string);
		code.add(string);
		System.out.println("Berhasil menambahkan karyawan dengan id " + string);
		num++;
		
		if (jabatan.equals("Manager")) {
			add(8000000, 110, "Manager", num);
		}
		else if (jabatan.equals("Supervisor")) {
			add(6000000, 107.5, "Supervisor", num);
		}
		else if (jabatan.equals("Admin")) {
			add(4000000, 105, "Admin", num);
		}
	}
	
	
	
	private void view () {
		if (num==0) {
			System.out.println("No data found");
		}
		else {
			sort();
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan       |Gaji Karyawan|");
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
			for (int i=0; i<num; i++) {
				System.out.printf("|%4d|", (i+1));
				System.out.printf("%17s|", kodeVector.get(i));
				System.out.printf("%30s|", namaVector.get(i));
				System.out.printf("%15s|", genderVector.get(i));
				System.out.printf("%14s|", jabatanVector.get(i));
				System.out.printf("%13.0f|\n", gajiVector.get(i));
			}
			System.out.println("|----|-----------------|------------------------------|---------------|--------------|-------------|");
		}
	}
	
	
	
	private void update() {
		int idx; String nama; String gender; String jabatan;
		if (num==0) {
			System.out.println("There are no data to be updated");
			return;
		}
		do {
			System.out.print("Input nomor karyawan yang ingin diupdate: ");
			idx = scan.nextInt(); scan.nextLine();
		} while (idx<1 || idx>num);
		
			do {
				System.out.print("Input nama karyawan: [>= 3]: ");
				nama = scan.nextLine();
			} while (nama.length()<3 && nama.compareTo("0")!=0);
			if (nama.compareTo("0")!=0) {
				namaVector.set(idx-1, nama);
			}
			
			do {
				System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
				gender = scan.nextLine();
			} while (gender.compareTo("Laki-laki")!=0 && gender.compareTo("Perempuan")!=0 && gender.compareTo("0")!=0);
			if (gender.compareTo("0")!=0) {
				genderVector.set(idx-1, gender);
			}
			
			do {
				System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
				jabatan = scan.nextLine();
			} while (jabatan.compareTo("Manager")!=0 && jabatan.compareTo("Supervisor")!=0 && jabatan.compareTo("Admin")!=0 && jabatan.compareTo("0")!=0);
			if (jabatan.compareTo("0")!=0) {
				jabatanVector.set(idx-1, jabatan);
				if (jabatan.equals("Manager")) {
					add(8000000, 110, "Manager", idx);
				}
				else if (jabatan.equals("Supervisor")) {
					add(6000000, 107.5, "Supervisor", idx);
				}
				else if (jabatan.equals("Admin")) {
					add(4000000, 105, "Admin", idx);
				}
			}
			
			System.out.println("Berhasil mengupdate karyawan dengan id " + kodeVector.get(idx-1));
	}
	
	
	
	private void delete() {
		int idx; String kode;
		if (num==0) {
			System.out.println("There are no data to be deleted");
			return;
		}
		do {
			System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
			idx = scan.nextInt();
		} while (idx<1 || idx>num);
		
		kode = kodeVector.get(idx-1);
		kodeVector.remove(idx-1);
		namaVector.remove(idx-1);
		genderVector.remove(idx-1);
		jabatanVector.remove(idx-1);
		gajiVector.remove(idx-1);
		code.remove(idx-1);
		num--;
		System.out.println("Karyawan dengan kode " + kode + " berhasil dihapus");
	}
	
	
	private String random () {
		char [] arr = new char [7]; 
		for (int i=0; i<2; i++) {
			arr[i] = (char)(('A')+(Math.random()*('Z'-'A'+1)));
		}
		arr[2]= '-'; 
		for (int i=3; i<7; i++) {
			arr[i] = (char) (('0')+(Math.random()*('9'-'0'+1)));
		}
		String kode = new String (arr);
		return kode;
	}
	
	
	
	private void add(double value, double bonus, String jabatan, int idx) {
		int jumlah = 0;
		if (menu==1) {
			gajiVector.add(value);
		}
		else {
			gajiVector.set(idx-1, value);
		}
		for (int i=0; i<num; i++) {
				for (int j=0; j<num; j++) {
					if (code.get(i)==kodeVector.get(j)) {
						if (jabatanVector.get(j).equals(jabatan)) {
							jumlah++;
							kodetemp.add(code.get(i));
							break;
						}
					}
				}
		}
		
		if (menu==1) {
			if (jumlah%3==1 && jumlah>3) {
				System.out.print("Bonus sebesar " + (bonus-100) + "% telah diberikan kepada karyawan dengan id ");
				for (int i=0; i<jumlah-1; i++) {
					if (i==jumlah-2) {
						System.out.println(kodetemp.get(i));
					}
					else {
						System.out.print(kodetemp.get(i) + ", ");
					}

					for (int j=0; j<num; j++) {
						if (kodeVector.get(j)==kodetemp.get(i)) {
							gajiVector.set(j, gajiVector.get(j)*bonus/100);
							break;
						}
					}
				}
			}
		}
		kodetemp.removeAllElements();
	}
	
	
	
	private void sort() {
		for (int i=0; i<num; i++) {
			for (int j=0; j<num-i-1; j++) {
				if (namaVector.get(j).compareTo(namaVector.get(j+1))>0) {
					String temp = kodeVector.get(j);
					kodeVector.set(j, kodeVector.get(j+1));
					kodeVector.set(j+1, temp);
					
					temp = namaVector.get(j);
					namaVector.set(j, namaVector.get(j+1));
					namaVector.set(j+1, temp);
					
					temp = genderVector.get(j);
					genderVector.set(j, genderVector.get(j+1));
					genderVector.set(j+1, temp);
					
					temp = jabatanVector.get(j);
					jabatanVector.set(j, jabatanVector.get(j+1));
					jabatanVector.set(j+1, temp);
					
					double tempnum = gajiVector.get(j);
					gajiVector.set(j, gajiVector.get(j+1));
					gajiVector.set(j+1, tempnum);
					
				}
			}
		}
	}

}
