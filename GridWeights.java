/**
 * Takes in 2d int matrix of any size and calculates the height,
 * width, and sum of all values, which allows for easier downstream
 * image filtering.
 * 
 * @author Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class GridWeights {
	private int[][] weightMatrix;
	private int height;
	private int width;
	private int totalWeight;
	
	/**
	 * Constructor for GridWeights class
	 * 
	 * @param	weightMatrix 	2d int matrix of any size
	 */
	public GridWeights(int[][] weightMatrix) {
		this.weightMatrix = weightMatrix;
		this.height = weightMatrix.length;
		this.width = weightMatrix[0].length;
		this.totalWeight = this.determineTotalWeight();
		
	}
	
	/**
	 * Sums up all integer weights for each row and column coordinate
	 * in provided 2d matrix
	 * 
	 * @return	sum of all 2d matrix weights
	 */
	private int determineTotalWeight() {
		int totalWeight = 0;
		for (int row = 0; row < this.height; row++) {
			for (int col = 0; col < this.width; col++) {
				totalWeight += this.weightMatrix[row][col];
			}
		}
		return totalWeight;
	}
	
	/**
	 * Access height (or number rows) available in provided 2d matrix
	 * 
	 * @return 	length of matrix
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Access width (or number of columns) available in provided 2d
	 * matrix
	 * 
	 * @return 	length of first row in matrix
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Access weight of provided 2d matrix for calculating weighted
	 * averages
	 * 
	 * @return 	sum of all integers in 2d matrix
	 */
	public int getTotalWeight() {
		return this.totalWeight;
	}
	
	/**
	 * Access specific weight at specific coordinate in provided 2d
	 * matrix
	 * 
	 * @param 	rowIndex	target row position in 2d matrix
	 * @param 	columnIndex	target column position in 2d matrix
	 * @return	weight of target row and column in 2d matrix
	 */
	public int getPositionWeight(int rowIndex, int columnIndex) {
		int posWeight = this.weightMatrix[rowIndex][columnIndex];
		return posWeight;
	}
	
}
