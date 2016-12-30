import processing.core.PApplet;

import java.util.ArrayList;

/**
 * Created by dustin on 12/29/16.
 */
public class Voronoi extends PApplet {
    ArrayList<Point> points;

    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {
        points = new ArrayList<Point>();
    }

    @Override
    public void draw() {
        clear();
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i * width + j] = getClosestColor(i, j);
            }
        }
        updatePixels();
        for (Point point : points) {
            ellipseMode(CENTER);
            fill(color(0));
            ellipse(point.x, point.y, 15, 15);
        }
    }

    private int getClosestColor(int y, int x) {
        if (points.size() == 0) {
            return color(0);
        }
        Point closest = points.get(0);
        for (Point point : points) {
            if (point.distanceTo(x, y) < closest.distanceTo(x, y)) {
                closest = point;
            }
        }
        return closest.color;
    }

    @Override
    public void mouseClicked() {
        points.add(new Point(mouseX, mouseY, color(random(255), random(255), random(255))));
    }

    public static void main(String[] args) {
        PApplet.main("Voronoi");
    }
}

class Point {
    public int x, y, color;

    public Point(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public double distanceTo(int x, int y) {
        return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
    }
}