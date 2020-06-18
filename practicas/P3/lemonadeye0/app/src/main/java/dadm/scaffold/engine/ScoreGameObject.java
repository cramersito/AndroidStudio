package dadm.scaffold.engine;

import android.graphics.Canvas;
import android.view.View;
import android.widget.TextView;

import dadm.scaffold.sound.GameEvent;

public class ScoreGameObject extends GameObject {

    private final TextView textField;
    private int scorePoints;
    private boolean update;
    private static final int MISSED_POINTS = 1;
    private static final int GAINED_POINTS = 50;

    public ScoreGameObject(View view, int viewResId) {
        textField = (TextView) view.findViewById(viewResId);
    }

    @Override
    public void onUpdate(long elapsedMillis, GameEngine gameEngine) {}

    @Override
    public void startGame() {
        scorePoints = 0;
        textField.post(textRunnable);
    }


    @Override
    public void onGameEvent(GameEvent gameEvent) {

        //on hit we win 50 points
        if (gameEvent == GameEvent.AsteroidHit) {
            scorePoints += GAINED_POINTS;
            update = true;
        }

        //on miss we lose a point
        else if (gameEvent == GameEvent.AsteroidMissed) {
            if (scorePoints > 0) {
                scorePoints -= MISSED_POINTS;
                update = true;

                //no negative values
            } else if (scorePoints <= 0){
                scorePoints = 0;
            }
        }
    }

    //RUNNABLE to update screen score
    private Runnable textRunnable = new Runnable() {

        @Override
        public void run() {
            String text = String.format("%06d", scorePoints);
            textField.setText(text);
        }
    };

    //only updates on lost or won point
    @Override
    public void onDraw(Canvas canvas) {
        if (update) {
            textField.post(textRunnable);
            update = false;
        }
    }

    public String getScore(){
        return Integer.toString(scorePoints);
    }
}
