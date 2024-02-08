package com.cat.mandelbrotrender.complex;

import com.cat.mandelbrotrender.graphics.Screen;

public class Fractal {
	
	public enum FractalType {
		MANDELBROT
	}
	
	private int width, height;
	
	private int[] pixels;
	
	public Fractal(int width, int height, FractalType type, double scale) {
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		
		switch (type) {
		case MANDELBROT:
			initMandlebrot(scale);
			break;
		}
	}

	private void initMandlebrot(double scale) {
		int originX = width * 3 / 4;
		int originY = height / 2;
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Complex result = Complex.ZERO;
				int count = 0;
				while (result.real() <= 2 && count < 20) {
					result = result.mul(result).add(new Complex((x - originX) / scale, (y - originY) / scale));
					
					count++;
				}
				
				pixels[x + y * width] = 20 - count;
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public int getPixel(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height)
			return 0;
		
		return pixels[x + y * width];
	}

	public int getWidth() {
		return width;
	}

	public void render(Screen screen, int i, int j) {
		screen.renderFractal(i, j, this);
	}
}
