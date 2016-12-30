import processing.core.PApplet;

import java.util.ArrayList;
import java.util.Iterator;


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
        movePoints();
        loadPixels();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                pixels[i * width + j] = getClosestColor(i, j);
            }
        }
        updatePixels();
//        for (Point point : points) {
//            ellipseMode(CENTER);
//            fill(color(0));
//            ellipse(point.x, point.y, 15, 15);
//        }
        Iterator<Point> iterator = points.iterator();
        while (iterator.hasNext()) {
            Point current = iterator.next();
            if (current.x < 0 || current.x > width || current.y < 0 || current.y > height) {
                iterator.remove();
            }
        }
    }

    private void movePoints() {
        for (Point point : points) {
            point.move();
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
        points.add(
                new Point(
                        mouseX,
                        mouseY,
                        color(
                                random(255),
                                random(255),
                                random(255)
                        ),
                        random(0, 2 * PI)
                ));
    }

    public static void main(String[] args) {
        PApplet.main("Voronoi");
    }
}

class Point {
    public int x, y, color;
    float direction;

    public Point(int x, int y, int color, float direction) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.direction = direction;
    }

    public double distanceTo(int x, int y) {
        return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
    }

    public void move() {
        this.x = (int) (this.x + 2 * Math.cos(direction));
        this.y = (int) (this.y + 2 * Math.sin(direction));
    }
}