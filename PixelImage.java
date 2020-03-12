import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage {
	private BufferedImage myImage;
	private int width;
	private int height;

	/**
	 * Map this PixelImage to a real image
	 * 
	 * @param bi
	 *            The image
	 */
	public PixelImage(BufferedImage bi) {
		// initialise instance variables
		this.myImage = bi;
		this.width = bi.getWidth();
		this.height = bi.getHeight();
	}

	/**
	 * Return the width of the image
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Return the height of the image
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Return the BufferedImage of this PixelImage
	 */
	public BufferedImage getImage() {
		return this.myImage;
	}

	/**
	 * Return the image's pixel data as an array of Pixels. The first coordinate
	 * is the x-coordinate, so the size of the array is [width][height], where
	 * width and height are the dimensions of the array
	 * 
	 * @return The array of pixels
	 */
	public Pixel[][] getData() {
		Raster r = this.myImage.getRaster();
		Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
		int[] samples = new int[3];

		for (int row = 0; row < r.getHeight(); row++) {
			for (int col = 0; col < r.getWidth(); col++) {
				samples = r.getPixel(col, row, samples);
				Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
				data[row][col] = newPixel;
			}
		}

		return data;
	}

	/**
	 * Set the image's pixel data from an array. This array matches that
	 * returned by getData(). It is an error to pass in an array that does not
	 * match the image's dimensions or that has pixels with invalid values (not
	 * 0-255)
	 * 
	 * @param data
	 *            The array to pull from
	 */
	public void setData(Pixel[][] data) {
		int[] pixelValues = new int[3]; // a temporary array to hold r,g,b
										// values
		WritableRaster wr = this.myImage.getRaster();

		if (data.length != wr.getHeight()) {
			throw new IllegalArgumentException("Array size does not match");
		} else if (data[0].length != wr.getWidth()) {
			throw new IllegalArgumentException("Array size does not match");
		}

		for (int row = 0; row < wr.getHeight(); row++) {
			for (int col = 0; col < wr.getWidth(); col++) {
				pixelValues[0] = data[row][col].red;
				pixelValues[1] = data[row][col].green;
				pixelValues[2] = data[row][col].blue;
				wr.setPixel(col, row, pixelValues);
			}
		}
	}

	/**
	 * Applies 2d matrix of weights to 2d pixel matrix and returns
	 * the resulting modified image
	 * 
	 * @author Joshua Scheck
	 * @param 	weights		class containing 2d matrix of weights
	 * to apply 
	 * @param 	operation	apply weighted sum or average operation
	 * to target pixel
	 * @return	return modified 2d pixel matrix
	 */
	public Pixel[][] getTransformedData(
			GridWeights weights, String operation) {
		int gridHeight = weights.getHeight();
		int gridWidth = weights.getWidth();
		int gridHeightMargin = (gridHeight - 1) / 2; 
		int gridWidthMargin = (gridWidth - 1) / 2;
		int gridTotalWeight = weights.getTotalWeight();
		
		// Retrieve initial Pixel matrix
		Pixel[][] data = this.getData();
		
		// Declare new 2d Pixel matrix to add new Pixels
		Pixel[][] modifiedData = 
				new Pixel[this.height][this.width];
		
		int lastRow = this.height - 1;
		int lastCol = this.width - 1;
		
		// Add unmodified top and bottom rows missed by filter
		for (int i = 0; i < gridHeightMargin; i++) {
			modifiedData[i] = data[i];
			modifiedData[lastRow - i] = data[lastRow - i];
		}
		
		// iterate through rows available after subtracting margins
		for (int row = gridHeightMargin; 
				row < this.height - gridHeightMargin; row++) {
			
			// Add unmodified pixels missed by filter
			for (int i = 0; i < gridWidthMargin; i++) {
				modifiedData[row][i] = data[row][i];
				modifiedData[row][lastCol - i] = 
						data[row][lastCol - i];
			}
			
			
			/*
			 * iterate through columns available after subtracting
			 * margins
			 */
			for (int col = gridWidthMargin; col < this.width - gridWidthMargin; col++) {
				
				int redPixelWeightedSum = 0;
				int greenPixelWeightedSum = 0;
				int bluePixelWeightedSum = 0;
				
				int startGridRow = row - gridHeightMargin;
				int startGridCol = col - gridWidthMargin;
				
				/*
				 * calculate weighted sum for target pixel after
				 * applying grid to target pixel and neighboring
				 * pixels
				 */
				for (int gridRow = 0; gridRow < gridHeight; gridRow++) {
					for ( int gridColumn = 0; gridColumn < gridWidth; gridColumn++) {
						Pixel pixel = data[gridRow+startGridRow][gridColumn+startGridCol];
						redPixelWeightedSum += 
								pixel.red * weights.getPositionWeight(gridRow, gridColumn);
						greenPixelWeightedSum += 
								pixel.green * weights.getPositionWeight(gridRow, gridColumn);
						bluePixelWeightedSum += 
								pixel.blue * weights.getPositionWeight(gridRow, gridColumn);
					}
				}
				
				if ("average".equals(operation)) {
					/*
					 *  takes weighted sum for each rgb pixel and divides by total 
					 *  weight of supplied weighted grid
					 */
					int redPixelWeightedAvg = redPixelWeightedSum / gridTotalWeight;
					int greenPixelWeightedAvg = greenPixelWeightedSum / gridTotalWeight;
					int bluePixelWeightedAvg = bluePixelWeightedSum / gridTotalWeight;
					
					/* 
					 * rgb int values above 255 are set to 255 and values below 0 are 
					 * set to 0
					 */
					redPixelWeightedAvg = Math.min(255, Math.max(0, redPixelWeightedAvg));
					greenPixelWeightedAvg = Math.min(255, Math.max(0, greenPixelWeightedAvg));
					bluePixelWeightedAvg = Math.min(255, Math.max(0, bluePixelWeightedAvg));
					
					/* 
					 * Adds new pixel to modified 2d pixel matrix at specific row and 
					 * column coordinates
					 */
					Pixel newPixel = 
							new Pixel(redPixelWeightedAvg, greenPixelWeightedAvg, bluePixelWeightedAvg);
					modifiedData[row][col] = newPixel;
					
				} else if ("sum".equals(operation)) {
					// only takes weighted sum and does NOT divide by any weight
					
					// rgb int values above 255 are set to 255 and values below 0 are set to 0
					redPixelWeightedSum = Math.min(255, Math.max(0, redPixelWeightedSum));
					greenPixelWeightedSum = Math.min(255, Math.max(0, greenPixelWeightedSum));
					bluePixelWeightedSum = Math.min(255, Math.max(0, bluePixelWeightedSum));
					
					/*
					 *  Adds new pixel to modified 2d pixel matrix at specific row and column 
					 *  coordinates
					 */
					Pixel newPixel = 
							new Pixel(redPixelWeightedSum, greenPixelWeightedSum, bluePixelWeightedSum);
					modifiedData[row][col] = newPixel;
					
				} else {
					System.out.println("Not a valid operation... returning initial 2d Pixel matrix");
					return data;
				}
			}
			
		}
		return modifiedData;
	}
}
