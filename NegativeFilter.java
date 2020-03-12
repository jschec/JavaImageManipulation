/**
 * Applies negative filter to designated 2d Pixel matrix.
 * 
 * @author 	Joshua Scheck
 * class	CSC 142
 * section	HY1
 * @since	2020/03/06
 */
public class NegativeFilter implements Filter {
	
	/**
	 * Each initial rgb value in Pixel class is replaced with
	 * calculated value where 255 is subtracted by the respective
	 * rgb value.
	 * 
	 * @param	pi	2d Pixel matrix
	 */
	public void filter(PixelImage pi) {
		Pixel[][] data = pi.getData();
		
		for (int row = 0; row < pi.getHeight(); row++) {
			for (int col = 0; col < pi.getWidth(); col++) {
				Pixel pixel = data[row][col];
				
				pixel.red = 255 - pixel.red;
				pixel.green = 255 - pixel.green;
				pixel.blue = 255 - pixel.blue;
			}
		}
		
		pi.setData(data);
	}
}
