package com.cat.mandelbrotrender.graphics;

import com.cat.mandelbrotrender.complex.Fractal;

public class Screen {
	private int width, height;
	public int[] pixels;
	
	public Screen (int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	private int getPixel(int x, int y) {
		if (x < 0 || y < 0 || y >= height || x >= width) return 0;
		return pixels[x + y * width];
	}
	
	public void renderFractal(int xa, int ya, Fractal fractal) {
		for (int y = 0; y < fractal.getHeight(); y++) {
			int yb = y + ya;
			for (int x = 0; x < fractal.getWidth(); x++) {
				int xb = x + xa;
				if (xb < 0 || yb < 0 || xb > width || yb >= height) continue;
				switch (fractal.getPixel(x, y)) {
				case 0:
					pixels[xb + yb * width] = 0;
					break;
				case 1:
					pixels[xb + yb * width] = 0x66600c;
					break;
				case 2:
					pixels[xb + yb * width] = 0xd8cc1c;
					break;
				case 3:
					pixels[xb + yb * width] = 0xf7c531;
					break;
				case 4:
					pixels[xb + yb * width] = 0xefa13b;
					break;
				case 5:
					pixels[xb + yb * width] = 0xed814b;
					break;
				case 6:
					pixels[xb + yb * width] = 0xf27559;
					break;
				case 7:
					pixels[xb + yb * width] = 0xed6d6d;
					break;
				case 8:
					pixels[xb + yb * width] = 0xed8989;
					break;
				case 9:
					pixels[xb + yb * width] = 0xe590c9;
					break;
				case 10:
					pixels[xb + yb * width] = 0xe5a9de;
					break;
				case 11:
					pixels[xb + yb * width] = 0xcd77dd;
					break;
				case 12:
					pixels[xb + yb * width] = 0x9b5fd3;
					break;
				case 13:
					pixels[xb + yb * width] = 0x6c53d1;
					break;
				case 14:
					pixels[xb + yb * width] = 0x3a3cc1;
					break;
				case 15:
					pixels[xb + yb * width] = 0x3264ba;
					break;
				case 16:
					pixels[xb + yb * width] = 0x1d74a3;
					break;
				case 17:
					pixels[xb + yb * width] = 0x2cb0ba;
					break;
				case 18:
					pixels[xb + yb * width] = 0x43c1a2;
					break;
				case 19:
					pixels[xb + yb * width] = 0x5bbf8f;
					break;
				case 20:
					pixels[xb + yb * width] = 0;
					break;				}
				//pixels[xb + yb * width] = fractal.getPixel(x, y);
			}
		}
	}
}
