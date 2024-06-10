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
		fillEmptySpaces();
	}

	private void initializeGrid() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = '.';
			}
		}
	}

	private void loadWords(String[] wordsArray) {
		for (String word : wordsArray) {
			words.add(word.toUpperCase());
		}
	}

	private void placeWordsInGrid() {
		for (String word : words) {
			placeWord(word);
		}
	}

	private void placeWord(String word) {
		Random random = new Random();
		boolean placed = false;
		while (!placed) {
			int direction = random.nextInt(2); // 0 horizontal, 1 vertical
			int row = random.nextInt(grid.length);
			int col = random.nextInt(grid[0].length);

			if (direction == 0 && col + word.length() <= grid[0].length) {
				if (canPlaceHorizontally(row, col, word)) {
					for (int i = 0; i < word.length(); i++) {
						grid[row][col + i] = word.charAt(i);
					}
					placed = true;
				}
			} else if (direction == 1 && row + word.length() <= grid.length) {
				if (canPlaceVertically(row, col, word)) {
					for (int i = 0; i < word.length(); i++) {
						grid[row + i][col] = word.charAt(i);
					}
					placed = true;
				}
			}
		}
	}

	private boolean canPlaceHorizontally(int row, int col, String word) {
		for (int i = 0; i < word.length(); i++) {
			if (grid[row][col + i] != '.' && grid[row][col + i] != word.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private boolean canPlaceVertically(int row, int col, String word) {
		for (int i = 0; i < word.length(); i++) {
			if (grid[row + i][col] != '.' && grid[row + i][col] != word.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	private void fillEmptySpaces() {
		Random random = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '.') {
					grid[i][j] = (char) ('A' + random.nextInt(26));
				}
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
