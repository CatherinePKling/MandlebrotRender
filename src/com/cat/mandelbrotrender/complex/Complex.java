package com.cat.mandelbrotrender.complex;

public class Complex {
	public static final Complex ZERO = new Complex(0, 0);
	
	private double a, b;
	
	public Complex (double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public double real() {
		return a;
	}
	
	public double imag() {
		return b;
	}
	
	public Complex add(Complex n) {
		return new Complex(a + n.real(), b + n.imag());
	}
	
	public Complex sub(Complex n) {
		return new Complex(a - n.real(), b - n.imag());
	}

	public Complex mul(Complex n) {
		return new Complex(a * n.real() - b * n.imag(), a * n.imag() + b * n.real());
	}
	
	public Complex div(Complex n) {
		double delta = n.real() * n.real() - n.imag() * n.imag();
		return new Complex((a * n.real() + b * n.imag()) / delta, (b * n.real() + a * n.imag()) / delta);
	}
}
