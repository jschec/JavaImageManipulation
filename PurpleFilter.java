/**
 * Applies Purple filter to designated 2d Pixel matrix which will
 * make image purple.
 * 
 * @author Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class PurpleFilter implements Filter {
	
	/**
	 * Each initial green value in Pixel class is replaced with
	 * 0 which will turn 2d Pixel matrix purple since purple is
	 * derived from the primary colors blue and red
	 * 
	 * @param	pi	2d Pixel matrix
	 */
	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();
		
		for (int row = 0; row < pi.getHeight(); row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				Pixel pixel = data[row][col];
				pixel.green = 0;
			}
		}
		
		pi.setData(data);
	}
}