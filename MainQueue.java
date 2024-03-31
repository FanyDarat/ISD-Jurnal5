import java.util.*;

public class MainQueue {
    public static void main(String[] args) {
        QueueData<String> tugas = new QueueData<>();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("Pilih Opsi Dibawah Ini:\n1. Input Tugas\n2. Hapus Tugas\n3. Cetak Tugas\n4. Keluar");
            System.out.print("Pilihan Anda: ");
            int pilihan = s.nextInt();
            s.nextLine();

            if (pilihan == 1) {
                System.out.print("Masukkan Nama Tugas: ");
                String namaTugas = s.nextLine();
                tugas.masukTugas(namaTugas);
            } else if (pilihan == 2) {
                tugas.hapusTugas();
            } else if (pilihan == 3) {
                tugas.printQueue();
            } else if (pilihan == 4) {
                break;
            } else {
                System.out.println("Pilihan Tidak Tersedia");
            }
        }
    }
}
