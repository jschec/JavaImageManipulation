/**
 * Flips image vertically.
 * 
 * @author 	Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class FlipVerticalFilter implements Filter {
	
	/**
	 * Takes Pixels from opposite ends of x axis and maps the
	 * opposite rgb Pixel values to each other.
	 *
	 * @param	pi	2d Pixel matrix
	 */
	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();
		
		for (int row = 0; row < pi.getHeight() / 2; row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				Pixel temp = data[row][col];
				data[row][col] = data[pi.getHeight() - row - 1][col];
				data[pi.getHeight() - row - 1][col] = temp;
			}
		}
		
		pi.setData(data);
	}
	
}
