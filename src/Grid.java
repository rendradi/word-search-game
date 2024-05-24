import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Grid {
	private char[][] grid;
	private Set<String> words;
	private Set<String> foundWords;

	public Grid(DifficultyLevel level) {
		this.grid = new char[level.getSize()][level.getSize()];
		this.words = new HashSet<>();
		this.foundWords = new HashSet<>();
		initializeGrid();
		loadWords(level.getWords());
		placeWordsInGrid();
	}

	private void initializeGrid() {
		Random random = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = (char) ('A' + random.nextInt(26));
			}
		}
	}

	private void loadWords(String[] wordsArray) {
		for (String word : wordsArray) {
			words.add(word.toUpperCase());
		}
	}

	private void placeWordsInGrid() {
		// Implementasi logika untuk meletakkan kata di grid
		for (String word : words) {
			// Contoh sederhana, Anda bisa mengembangkan logika penempatan kata yang lebih baik
			int row = new Random().nextInt(grid.length);
			int col = new Random().nextInt(grid.length - word.length());
			for (int i = 0; i < word.length(); i++) {
				grid[row][col + i] = word.charAt(i);
			}
		}
	}

	public boolean checkWord(String word) {
		word = word.toUpperCase();
		if (foundWords.contains(word)) {
			System.out.println("Kata sudah ditemukan.");
			return false;
		}

		if (words.contains(word) && isWordInGrid(word)) {
			foundWords.add(word);
			return true;
		}

		return false;
	}

	private boolean isWordInGrid(String word) {
		// Implementasi logika untuk memeriksa apakah kata ada di grid
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (checkHorizontal(i, j, word) || checkVertical(i, j, word)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkHorizontal(int row, int col, String word) {
		if (col + word.length() > grid[row].length) return false;
		for (int i = 0; i < word.length(); i++) {
			if (grid[row][col + i] != word.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private boolean checkVertical(int row, int col, String word) {
		if (row + word.length() > grid.length) return false;
		for (int i = 0; i < word.length(); i++) {
			if (grid[row + i][col] != word.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	public void displayGrid() {
		for (char[] row : grid) {
			for (char c : row) {
				System.out.print(c + " ");
			}
			System.out.println();
		}
	}

	public boolean allWordsFound() {
		return foundWords.equals(words);
	}

	public int getFoundWordsCount() {
		return foundWords.size();
	}
}
