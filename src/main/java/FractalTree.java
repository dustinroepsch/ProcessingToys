import processing.core.PApplet;

/**
 * Created by dustin on 12/27/16.
 */
public class FractalTree extends PApplet {
    @Override
    public void settings() {
        size(500, 500);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        clear();
        background(0);
        stroke(255);
        //get our angle by the mouse position on the screen
        float angle = map(mouseX, 0, width, 0, PI / 2);
        //get our dampening factor by the mouseY
        float dampFactor = map(mouseY, 0, height, 0.7f, 0);
        drawTree(100, angle, dampFactor);
    }

    private void drawTree(float baseLength, float radians, float dampeningFactor) {
        //we're going to change the matrix a lot so we store it
        pushMatrix();
        //move to the bottom center of the screen
        translate(width / 2, height);
        //draw a line straight up
        line(0, 0, 0, -baseLength);
        //move to the tip of that linestroke(255);
        translate(0, -baseLength);
        //draw the rest of the tree and dampen!
        drawTreeRecursive(baseLength, radians, dampeningFactor);
        popMatrix();
        //undo our matrix changes :)
    }

    private void drawTreeRecursive(float baseLength, float radians, float dampeningFactor) {
        //check our base case
        if (baseLength < 1) {
            return; //we are done drawing branches once they are this small
        }
        //draw the positive angle branches
        pushMatrix();
        rotate(radians);
        line(0, 0, 0, -baseLength);
        translate(0, -baseLength);
        drawTreeRecursive(baseLength * dampeningFactor, radians, dampeningFactor);
        popMatrix();
        //draw the negative angle branches
        pushMatrix();
        rotate(-radians);
        line(0, 0, 0, -baseLength);
        translate(0, -baseLength);
        drawTreeRecursive(baseLength * dampeningFactor, radians, dampeningFactor);
        popMatrix();
    }

    public static void main(String[] args) {
        PApplet.main("FractalTree");
    }
}
