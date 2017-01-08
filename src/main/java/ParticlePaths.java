import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by dustin on 1/8/17.
 */
public class ParticlePaths extends PApplet {
    private static final int ROOT_NUM_TILES = 10;
    private static final int TILE_SIZE = 50;

    private HashSet<Block> blocks = new HashSet<Block>();

    @Override
    public void settings() {
        size(ROOT_NUM_TILES * TILE_SIZE, ROOT_NUM_TILES * TILE_SIZE);
    }

    @Override
    public void setup() {

    }

    @Override
    public void draw() {
        background(color(0, 0, 0));
        drawLines();
        drawBlocks();
    }

    private void drawBlocks() {
        fill(color(0, 255, 0));
        rectMode(CORNERS);
        for (Block block : blocks) {
            rect(
                    block.col * TILE_SIZE,
                    block.row * TILE_SIZE,
                    block.col * TILE_SIZE + TILE_SIZE,
                    block.row * TILE_SIZE + TILE_SIZE
            );
        }
    }

    @Override
    public void mouseClicked() {
        toggleBlock(mouseX, mouseY);
    }

    private void toggleBlock(int mouseX, int mouseY) {
        int row = mouseY / TILE_SIZE;
        int col = mouseX / TILE_SIZE;
        Block block = new Block(row, col);
        if (blocks.contains(block)) {
            blocks.remove(block);
        } else {
            blocks.add(block);
        }
    }

    private void drawLines() {
        stroke(color(255, 255, 255));
        //draw row lines
        for (int i = TILE_SIZE; i < height; i += TILE_SIZE) {
            line(0, i, width, i);
        }
        //draw column lines
        for (int i = TILE_SIZE; i < width; i += TILE_SIZE) {
            line(i, 0, i, height);
        }
    }

    public static void main(String[] args) {
        PApplet.main("ParticlePaths");
    }
}

class Block {
    public final int row, col;

    public Block(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Block block = (Block) o;

        return row == block.row && col == block.col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }
}