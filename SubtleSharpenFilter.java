/**
 * Applies Subtle Sharpen filter to designated 2d Pixel matrix.
 * 
 * @author Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class SubtleSharpenFilter implements Filter {

	/**
	 * Takes Subtle Sharpen weighted matrix and applies the weights
	 * and calculates the weighted average of the target and 
	 * neighboring Pixels. Values set to range of 0 to 255.
	 * 
	 * @param	pi	2d Pixel matrix
	 */
	public void filter(PixelImage pi) {
		int[][] weightMatrix = {
			{-1, -1, -1, -1, -1},
			{-1,  2,  2,  2, -1},
			{-1,  2,  8,  2, -1},
			{-1,  2,  2,  2, -1},
			{-1, -1, -1, -1, -1}
		};
		
		GridWeights gridWeights = new GridWeights(weightMatrix);
		Pixel[][] modifiedPixelMatrix = 
				pi.getTransformedData(gridWeights, "average");
		pi.setData(modifiedPixelMatrix);
	}
}
