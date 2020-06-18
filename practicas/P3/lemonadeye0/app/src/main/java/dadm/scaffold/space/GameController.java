package dadm.scaffold.space;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import dadm.scaffold.engine.GameEngine;
import dadm.scaffold.engine.GameObject;

public class GameController extends GameObject {

    private static final int TIME_BETWEEN_ENEMIES = 500;
    private long currentMillis;
    private List<Eye> eyePool = new ArrayList<Eye>();
    private int enemiesSpawned;

    public GameController(GameEngine gameEngine) {
        // We initialize the pool of items now
        for (int i=0; i<10; i++) {
            eyePool.add(new Eye(this, gameEngine));
        }
    }

    @Override
    public void startGame() {
        currentMillis = 0;
        enemiesSpawned = 0;
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {
        currentMillis += elapsedMillis;

        long waveTimestamp = enemiesSpawned*TIME_BETWEEN_ENEMIES;
        if (currentMillis > waveTimestamp) {
            // Spawn a new enemy
            Eye a = eyePool.remove(0);
            a.init(gameEngine);
            gameEngine.addGameObject(a);
            enemiesSpawned++;
            return;
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
    }

    public void returnToPool(Eye eye) {
        eyePool.add(eye);
    }
}
