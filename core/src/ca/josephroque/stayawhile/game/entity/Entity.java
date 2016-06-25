package ca.josephroque.stayawhile.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

import ca.josephroque.stayawhile.graphics.Textures;
import ca.josephroque.stayawhile.input.GameInput;
import ca.josephroque.stayawhile.screen.GameScreen;

public abstract class Entity {

    protected Rectangle boundingBox;
    float xVelocity;
    float yVelocity;

    public abstract void tick(float delta);

    public abstract void draw(Textures textures, SpriteBatch spriteBatch);

    public abstract void handleInput(GameInput gameInput);

    public Entity(float x, float y, float width, float height) {
        boundingBox = new Rectangle(x, y, width, height);
    }

    public float getX() {
        return boundingBox.getX();
    }

    public float getY() {
        return boundingBox.getY();
    }

    public float getCenterX() {
        return getX() + getWidth() / 2;
    }

    public float getCenterY() {
        return getY() + getHeight() / 2;
    }

    public float getWidth() {
        return boundingBox.getWidth();
    }

    public float getHeight() {
        return boundingBox.getHeight();
    }

    public Shape2D getBounds() {
        return boundingBox;
    }

    public void updatePosition(float delta) {
        boundingBox.setPosition(getX() + getXVelocity() * delta, getY() + getYVelocity() * delta);
    }

    public float getXVelocity() {
        return xVelocity;
    }

    public float getYVelocity() {
        return yVelocity;
    }

    public void setXVelocity(float xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(float yVelocity) {
        this.yVelocity = yVelocity;
    }

    public void snapToCell() {
        int xRemainder = ((int) getX()) % GameScreen.BLOCK_SIZE;
        int yRemainder = ((int) getY()) % GameScreen.BLOCK_SIZE;

        int xOffset = 0;
        int yOffset = 0;

        if (xRemainder < GameScreen.BLOCK_SIZE / 2) {
            xOffset -= xRemainder;
        } else {
            xOffset += GameScreen.BLOCK_SIZE - xRemainder;
        }

        if (yRemainder < GameScreen.BLOCK_SIZE / 2) {
            yOffset -= yRemainder;
        } else {
            yOffset += GameScreen.BLOCK_SIZE - yRemainder;
        }

        boundingBox.setPosition(getX() + xOffset, getY() + yOffset);
    }
}