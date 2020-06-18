package dadm.scaffold.space;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.sound.GameEvent;

public class Eye extends Sprite {

    private final GameController gameController;

    private double speed;
    private double speedX;
    private double speedY;
    private double rotationSpeed;

    public Eye(GameController gameController, GameEngine gameEngine) {
        super(gameEngine, R.drawable.eye1);
        this.speed = 200d * pixelFactor/1000d;
        this.gameController = gameController;
    }

    public void init(GameEngine gameEngine) {
        // They initialize in a [-30, 30] degrees angle
        double angle = gameEngine.random.nextDouble()*Math.PI/3d-Math.PI/6d;
        speedX = speed * Math.sin(angle);
        speedY = speed * Math.cos(angle);
        // Asteroids initialize in the central 50% of the screen horizontally
        positionX = gameEngine.random.nextInt(gameEngine.width/2)+gameEngine.width/4;
        // They initialize outside of the screen vertically
        positionY = -height;
        rotationSpeed = angle*(180d / Math.PI)/250d; // They rotate 4 times their ange in a second.
        rotation = gameEngine.random.nextInt(360);
    }

    @Override
    public void startGame() {
    }

    public void removeObject(GameEngine gameEngine) {
        // Return to the pool
        gameEngine.removeGameObject(this);
        gameController.returnToPool(this);
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        positionX += speedX * elapsedMillis;
        positionY += speedY * elapsedMillis;
        rotation += rotationSpeed * elapsedMillis;
        if (rotation > 360) {
            rotation = 0;
        } else if (rotation < 0) {
            rotation = 360;
        }

        //notifies score object to decrease score
        if (positionY > gameEngine.height) {
            gameEngine.onGameEvent(GameEvent.AsteroidMissed);
            gameEngine.removeGameObject(this);
            gameController.returnToPool(this);
        }
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {


    }
}
