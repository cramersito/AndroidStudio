package dadm.scaffold.space;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.R;
import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.ScreenGameObject;
import dadm.scaffold.engine.Sprite;
import dadm.scaffold.input.InputController;
import dadm.scaffold.sound.GameEvent;

public class LemonPlayer extends Sprite {

    private static final int INITIAL_BULLET_POOL_AMOUNT = 6;
    private static final long TIME_BETWEEN_BULLETS = 250;
    List<Lemonade> lemonades = new ArrayList<Lemonade>();
    private long timeSinceLastFire;

    private int maxX;
    private int maxY;
    private double speedFactor;

    private int life;


    public LemonPlayer(GameEngine gameEngine){
        super(gameEngine, R.drawable.ship1);
        speedFactor = pixelFactor * 100d / 1000d; // We want to move at 100px per second on a 400px tall screen
        maxX = gameEngine.width - width;
        maxY = gameEngine.height - height;
        life = 100;

        initBulletPool(gameEngine);
    }

    private void initBulletPool(GameEngine gameEngine) {
        for (int i=0; i<INITIAL_BULLET_POOL_AMOUNT; i++) {
            lemonades.add(new Lemonade(gameEngine));
        }
    }

    private Lemonade getBullet() {
        if (lemonades.isEmpty()) {
            return null;
        }
        return lemonades.remove(0);
    }

    void releaseBullet(Lemonade lemonade) {
        lemonades.add(lemonade);
    }


    @Override
    public void startGame() {
        positionX = maxX / 2;
        positionY = maxY / 2;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        // Get the info from the inputController
        updatePosition(elapsedMillis, gameEngine.theInputController);
        checkFiring(elapsedMillis, gameEngine);
    }

    private void updatePosition(long elapsedMillis, InputController inputController) {
        positionX += speedFactor * inputController.horizontalFactor * elapsedMillis;
        if (positionX < 0) {
            positionX = 0;
        }
        if (positionX > maxX) {
            positionX = maxX;
        }
        positionY += speedFactor * inputController.verticalFactor * elapsedMillis;
        if (positionY < 0) {
            positionY = 0;
        }
        if (positionY > maxY) {
            positionY = maxY;
        }
    }

    private void checkFiring(long elapsedMillis, GameEngine gameEngine) {
        if (gameEngine.theInputController.isFiring && timeSinceLastFire > TIME_BETWEEN_BULLETS) {
            Lemonade lemonade = getBullet();
            if (lemonade == null) {
                return;
            }
            lemonade.init(this, positionX + width/2, positionY);
            gameEngine.addGameObject(lemonade);
            timeSinceLastFire = 0;
            gameEngine.onGameEvent(GameEvent.LaserFired);
        }
        else {
            timeSinceLastFire += elapsedMillis;
        }
    }

    @Override
    public void onCollision(GameEngine gameEngine, ScreenGameObject otherObject) {

        //we finish de game if life asteroid gets last life from player
        if (otherObject instanceof Eye && this.life <= 0) {
            gameEngine.removeGameObject(this);
            Eye a = (Eye) otherObject;
            a.removeObject(gameEngine);
            gameEngine.onGameEvent(GameEvent.Death);

            //Else we just update with a hit event
        } else if (otherObject instanceof Eye){
            Eye a = (Eye) otherObject;
            a.removeObject(gameEngine);
            gameEngine.onGameEvent(GameEvent.SpaceshipHit);
            this.life-=10;
        }
    }
}
