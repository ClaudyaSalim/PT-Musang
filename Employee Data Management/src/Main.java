import java.util.Scanner;
import java.util.Vector;
//pake scanner sama vector

public class Main {

	//deklarasi
	Scanner scan = new Scanner(System.in);
	int num=0; int menu;
	Vector <String> namaVector = new Vector<>();
	Vector <String> kodeVector = new Vector<>(); //buat nampung kode di-sort yg bakal di view
	Vector <String> code = new Vector<>(); //nampung kode berdasarkan yg pertama datang
	Vector <String> genderVector = new Vector<>();
	Vector <String> jabatanVector = new Vector<>();
	Vector <Double> gajiVector = new Vector<>();
	Karyawan k = new Karyawan ();
	
	public Main () {
		mainMenu(); //ke method mainMenu
	}
	
	
	public static void main(String[] args) {
		new Main(); //jalan di constructor main

	}
	
	//menu utama buat pilih"
	public void mainMenu() {
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
			} while (menu!=1 && menu!=2 && menu!=3 && menu!=4 && menu!=5); //validasi
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
			scan.nextLine(); //biar bisa scan enternya
			System.out.println();
		} while (menu!=5); //kalo pencet 5, sistem selesai
	}
	
	
	
	private void insert () {
		//input nama, gender sama jabatan
		String nama; String gender; String jabatan;
		do {
			nama = k.scanNama();
		} while (nama.length()<3);
		
		namaVector.add(nama);
		
		do {
			gender = k.scanGender();
		} while (gender.compareTo("Laki-laki")!=0 && gender.compareTo("Perempuan")!=0);
		
		genderVector.add(gender);
		
		do {
			jabatan = k.scanJabatan();
		} while (jabatan.compareTo("Manager")!=0 && jabatan.compareTo("Supervisor")!=0 && jabatan.compareTo("Admin")!=0);
		
		jabatanVector.add(jabatan);
		
		//cari kode ke method random()
		String string = random();
		for (int i=0; i<num; i++) {
			while (kodeVector.get(i)==string) { //validasi kalo kode harus unik
				string = random();
			}
		}
		kodeVector.add(string);
		code.add(string);
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + string);
		num++;
		
		k.checkJabatan(num, gajiVector, menu, num, kodeVector, code, jabatanVector); //tambah gaji
	}
	
	
	
	private void view () {
		if (num==0) { //kalau ga ada karyawan
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
		int idx; 
		String nama; String gender; String jabatan;
		if (num==0) { //kalau ga ada karyawan
			System.out.println("There are no data to be updated");
			return;
		}
		//kalau ada karyawan, input nomor, nama, gender, jabatan, dan di-update
		do {
			System.out.print("Input nomor karyawan yang ingin diupdate: ");
			idx = scan.nextInt(); scan.nextLine();
		} while (idx<1 || idx>num);
		
		do {
			nama = k.scanNama();
		} while (nama.length()<3 && nama.compareTo("0")!=0);
		
		if (nama.compareTo("0")!=0) {
			namaVector.set(idx-1, nama);
		}
		
		do {
			gender = k.scanGender();
		} while (gender.compareTo("Laki-laki")!=0 && gender.compareTo("Perempuan")!=0 && gender.compareTo("0")!=0);
		
		if (gender.compareTo("0")!=0) {
			genderVector.set(idx-1, gender);
		}
		
		do {
			jabatan = k.scanJabatan();
		} while (jabatan.compareTo("Manager")!=0 && jabatan.compareTo("Supervisor")!=0 && jabatan.compareTo("Admin")!=0 && jabatan.compareTo("0")!=0);
		
		
		if (jabatan.compareTo("0")!=0) {
			jabatanVector.set(idx-1, jabatan);
			k.checkJabatan(idx, gajiVector, menu, num, kodeVector, code, jabatanVector); //update gaji
		}
		
		System.out.println("Berhasil mengupdate karyawan dengan id " + kodeVector.get(idx-1));
	}
	
	
	
	private void delete() {
		int idx; String kode;
		if (num==0) { //kalau ga ada karyawan
			System.out.println("There are no data to be deleted");
			return;
		}
		//kalau ada karyawan, hapus data indexnya di vector
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
	
	
	private String random () { //method random dari insert() buat cari kode random
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
	
	
	
	private void sort() { //method sort dari view() buat urutin berdasarkan abjad
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
