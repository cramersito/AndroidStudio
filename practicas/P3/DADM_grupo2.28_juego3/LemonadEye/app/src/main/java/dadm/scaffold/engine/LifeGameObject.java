package dadm.scaffold.engine;

import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import dadm.scaffold.sound.GameEvent;

public class LifeGameObject extends GameObject {

    private final TextView textField;
    private int lives;
    private boolean update;

    public LifeGameObject(View view, int viewResId) {
        textField = (TextView) view.findViewById(viewResId);
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {}

    @Override
    public void startGame() {
        lives = 10;
        textField.post(textRunnable);
    }

    //on hit we lose a life
    @Override
    public void onGameEvent(GameEvent gameEvent) {
        if (gameEvent == GameEvent.SpaceshipHit) {
            lives -=1;
            update = true;
        }
    }

    //RUNNABLE to update life in game fragment
    private Runnable textRunnable = new Runnable() {

        @Override
        public void run() {
            String text = String.format("%06d", lives);
            textField.setText(text);
        }
    };

    //only updates when life lost
    @Override
    public void onDraw(Canvas canvas) {
        if (update) {
            textField.post(textRunnable);
            update = false;
        }
    }
}
