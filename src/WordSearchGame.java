import java.util.Scanner;

public class WordSearchGame {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DifficultyLevel level = chooseDifficulty(scanner);

		Grid grid = new Grid(level);
		grid.displayGrid();

		while (!grid.allWordsFound()) {
			System.out.print("Masukkan kata: ");
			String word = scanner.next();
			if (grid.checkWord(word)) {
				System.out.println("Kata ditemukan! Skor: " + (grid.getFoundWordsCount() * 10));
			} else {
				System.out.println("Kata tidak ditemukan.");
			}
			grid.displayGrid();
		}

		System.out.println("Selamat! Anda telah menemukan semua kata!");
		scanner.close();
	}

	private static DifficultyLevel chooseDifficulty(Scanner scanner) {
		DifficultyLevel level = null;
		boolean validChoice = false;

		do {
			System.out.println("Pilih tingkat kesulitan: 1. Mudah 2. Sedang 3. Sulit");
			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				switch (choice) {
					case 1:
						level = new EasyLevel();
						validChoice = true;
						break;
					case 2:
						level = new MediumLevel();
						validChoice = true;
						break;
					case 3:
						level = new HardLevel();
						validChoice = true;
						break;
					default:
						System.out.println("Pilihan tidak valid. Coba lagi.");
				}
			} else {
				System.out.println("Input tidak valid. Masukkan angka 1, 2, atau 3.");
				scanner.next(); // membersihkan input yang tidak valid
			}
		} while (!validChoice);

		return level;
	}
}
