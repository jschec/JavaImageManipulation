/**
 * Applies Emboss filter to designated 2d Pixel matrix
 * 
 * @author Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class EmbossFilter implements Filter {
	
	/**
	 * Takes Emboss weighted matrix and applies the weights and 
	 * calculates the weighted sum of the target and neighboring
	 * Pixels. Values set to range of 0 to 255.
	 * 
	 * @param	pi	2d Pixel matrix
	 */
	public void filter(PixelImage pi) {
		int[][] weightedMatrix = {
			{-2,  0,  0},
			{ 0,  1,  0},
			{ 0,  0,  2}
		};
		GridWeights gridWeights = new GridWeights(weightedMatrix);
		Pixel[][] modifiedPixelMatrix = 
				pi.getTransformedData(gridWeights, "sum");
		pi.setData(modifiedPixelMatrix);
	}
}