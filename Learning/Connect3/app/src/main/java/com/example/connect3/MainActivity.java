package com.example.connect3;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    enum Player {Yellow,Red}
    enum gameState {notPlayed,yellowPlayed, redPlayed}
    Player activePlayer = Player.Yellow;
    ArrayList<gameState> gameStateArray;
    int [][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},{1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    LinearLayout linearLayout;
    boolean gameRunning = true;
    public void dropIn(View view){
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameStateArray.get(tappedCounter) == gameState.notPlayed && gameRunning){
            counter.setTranslationY(-1000f);

            if (activePlayer == Player.Yellow){
                gameStateArray.set(tappedCounter,gameState.yellowPlayed);
                counter.setImageResource(R.drawable.yellow);
                checkWinner();
                activePlayer = Player.Red;
            }
            else {
                gameStateArray.set(tappedCounter,gameState.redPlayed);
                counter.setImageResource(R.drawable.red);
                checkWinner();
                activePlayer = Player.Yellow;
            }
            counter.animate().translationYBy(1000f).rotation(200).setDuration(900);
        }
        else if( gameRunning) {
            Toast.makeText(MainActivity.this,"Can't go here",Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("SetTextI18n")
    public void checkWinner(){
        for (int[] winningPositions:winningPositions){
            if (gameStateArray.get(winningPositions[0]) == gameStateArray.get(winningPositions[1])
                    && gameStateArray.get(winningPositions[1]) == gameStateArray.get(winningPositions[2])
                    && gameStateArray.get(winningPositions[0]) != gameState.notPlayed ){

                String text = activePlayer.toString()+ " Won !!";
                resetGame(text);
            }
            else {
                boolean gameIsOver = true;
                for (int i = 0; i < gameStateArray.size();i++){
                   if(gameStateArray.get(i) == gameState.notPlayed ){
                       gameIsOver = false;
                   }
                }
                if (gameIsOver){
                    String text = "It's a Draw ";
                    resetGame(text);
                }
            }
        }
    }
    private void resetGame(String text) {
        linearLayout = findViewById(R.id.playAgainLayout);
        linearLayout.setAlpha(1f);
        TextView textView = findViewById(R.id.textView);
        textView.setText(text);
        gameRunning = false;
    }
    public void playAgain(View view){
        linearLayout.setAlpha(0f);
        activePlayer = Player.Yellow;
        for (int i = 0; i < gameStateArray.size();i++){
            gameStateArray.set(i,gameState.notPlayed);
        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i<gridLayout.getChildCount();i++){
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
        gameRunning = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameStateArray = new ArrayList<>();
        for (int i = 0; i < 9;i++){
            gameStateArray.add(gameState.notPlayed);
        }
        setContentView(R.layout.activity_main);
    }
}