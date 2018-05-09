package net.querz.mcaselector;

import java.util.Objects;

public class Point2f implements Cloneable {
	private float x, y;

	public Point2f() {
		this.x = this.y = 0;
	}

	public Point2f(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Point2f add(float x, float y) {
		return new Point2f(this.x + x, this.y + y);
	}

	public Point2f add(Point2f p) {
		return add(p.x, p.y);
	}

	public Point2f add(float i) {
		return add(i, i);
	}

	public Point2f sub(float x, float y) {
		return new Point2f(this.x - x, this.y - y);
	}

	public Point2f sub(Point2f p) {
		return sub(p.x, p.y);
	}

	public Point2f sub(float i) {
		return sub(i, i);
	}

	public Point2f mul(float x, float y) {
		return new Point2f(this.x * x, this.y * y);
	}

	public Point2f mul(Point2f p) {
		return mul(p.x, p.y);
	}

	public Point2f mul(float i) {
		return mul(i, i);
	}

	public Point2f div(float x, float y) {
		return new Point2f(this.x / x, this.y / y);
	}

	public Point2f div(Point2f p) {
		return div(p.x, p.y);
	}

	public Point2f div(float i) {
		return div(i, i);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof Point2f
				&& Float.compare(((Point2f) other).x, x) == 0
				&& Float.compare(((Point2f) other).y, y) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "<" + x + ", " + y + ">";
	}

	@Override
	public Point2f clone() {
		try {
			return (Point2f) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
