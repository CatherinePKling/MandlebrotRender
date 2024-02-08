package com.cat.mandelbrotrender;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import javax.swing.JFrame;

import com.cat.mandelbrotrender.complex.Fractal;
import com.cat.mandelbrotrender.complex.Fractal.FractalType;
import com.cat.mandelbrotrender.graphics.Screen;

public class Main extends Canvas implements Runnable {
	private static int scale = 1;
	private static int width = 1300 / scale;
	private static int height = 700 / scale;
	private static String title = "Fractal Renderer";
	
	private boolean running = false;
	
	private Thread thread;
	private JFrame frame;
	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	private Random rand = new Random();
	
	private Fractal fractal;
	
	double centerMultiplier = 0.5;
	
	public Main() {
		setPreferredSize(new Dimension(width * scale, height * scale));
		
		frame = new JFrame();
		screen = new Screen(width, height);
		
		fractal = new Fractal(width, height, FractalType.MANDELBROT, 300);
	}
	
	public static void main(String[] args) {		
		Main main = new Main();
		main.frame.setResizable(false);
		main.frame.setTitle(title);
		main.frame.add(main);
		main.frame.pack();
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.setLocationRelativeTo(null);
		main.frame.setVisible(true);
		
		main.start();
	}

	public void run() {
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60;
		double delta = 0;
		int frames = 0, updates = 0;
		requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				delta--;
				updates++;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer +=1000;
				frame.setTitle(title + "  |  " + updates + " ups, "+ frames + " fps");
				frames = 0; 
				updates = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		
		fractal.render(screen, 0, 0);
		
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	private void update() {

	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
