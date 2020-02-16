package com.maxdunsmore.coinrunnermain.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class CoinRunner extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private Texture[] character;
    private Texture[] characterDead;
    private Texture[] explosion;
    private Texture[] coin;
    private Texture[] backgroundMove;
    private Texture idleCharacter;
    private Texture bomb;
    private int backgroundPause = 0;
    private int backgroundState = 0;
    private int characterDeadState = 0;
    private int characterState = 0;
    private int explosionState = 0;
    private int pauseExplosion;
    private int pause = 0;
    private int score = 0;
    private int coinState = 0;
    private int characterPause = 0;
    private int gameState = 0;
    private int characterY;
    private int coinCount;
    private int bombCount;
    private int coinPause = 0;
    private int difficulty = 0;
    private int speed;
    private float velocity = 0;
    private BitmapFont scoreFont;
    private BitmapFont freeFontGameOver;
    private BitmapFont freeFontScore;
    private ArrayList<Integer> coinXs = new ArrayList<>();
    private ArrayList<Integer> coinYs = new ArrayList<>();
    private ArrayList<Rectangle> coinRectangles = new ArrayList<>();
    private ArrayList<Integer> bombXs = new ArrayList<>();
    private ArrayList<Integer> bombYs = new ArrayList<>();
    private ArrayList<Rectangle> bombRectangles = new ArrayList<>();
    private Random random;

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        characterY = Gdx.graphics.getHeight() / 2; // character y location
        idleCharacter = new Texture("idle01.png");
        character = new Texture[10]; // list of character images
        character[0] = new Texture("run01.png");
        character[1] = new Texture("run02.png");
        character[2] = new Texture("run03.png");
        character[3] = new Texture("run04.png");
        character[4] = new Texture("run05.png");
        character[5] = new Texture("run06.png");
        character[6] = new Texture("run07.png");
        character[7] = new Texture("run08.png");
        character[8] = new Texture("run09.png");
        character[9] = new Texture("run10.png");

        backgroundMove = new Texture[25];
        backgroundMove[0] = new Texture("bg01.png");
        backgroundMove[1] = new Texture("bg02.png");
        backgroundMove[2] = new Texture("bg03.png");
        backgroundMove[3] = new Texture("bg04.png");
        backgroundMove[4] = new Texture("bg05.png");
        backgroundMove[5] = new Texture("bg06.png");
        backgroundMove[6] = new Texture("bg07.png");
        backgroundMove[7] = new Texture("bg08.png");
        backgroundMove[8] = new Texture("bg09.png");
        backgroundMove[9] = new Texture("bg10.png");
        backgroundMove[10] = new Texture("bg11.png");
        backgroundMove[11] = new Texture("bg12.png");
        backgroundMove[12] = new Texture("bg13.png");
        backgroundMove[13] = new Texture("bg14.png");
        backgroundMove[14] = new Texture("bg15.png");
        backgroundMove[15] = new Texture("bg16.png");
        backgroundMove[16] = new Texture("bg17.png");
        backgroundMove[17] = new Texture("bg18.png");
        backgroundMove[18] = new Texture("bg19.png");
        backgroundMove[19] = new Texture("bg20.png");
        backgroundMove[20] = new Texture("bg21.png");
        backgroundMove[21] = new Texture("bg22.png");
        backgroundMove[22] = new Texture("bg23.png");
        backgroundMove[23] = new Texture("bg24.png");
        backgroundMove[24] = new Texture("bg25.png");

        coin = new Texture[10];
        coin[0] = new Texture("coin01.png");
        coin[1] = new Texture("coin02.png");
        coin[2] = new Texture("coin03.png");
        coin[3] = new Texture("coin04.png");
        coin[4] = new Texture("coin05.png");
        coin[5] = new Texture("coin06.png");
        coin[6] = new Texture("coin07.png");
        coin[7] = new Texture("coin08.png");
        coin[8] = new Texture("coin09.png");
        coin[9] = new Texture("coin10.png");
        explosion = new Texture[7];
        explosion[0] = new Texture("explosion01.png");
        explosion[1] = new Texture("explosion02.png");
        explosion[2] = new Texture("explosion03.png");
        explosion[3] = new Texture("explosion04.png");
        explosion[4] = new Texture("explosion05.png");
        explosion[5] = new Texture("explosion06.png");
        explosion[6] = new Texture("explosion07.png");
        characterDead = new Texture[10];
        characterDead[0] = new Texture("dead01.png");
        characterDead[1] = new Texture( "dead02.png");
        characterDead[2] = new Texture( "dead03.png");
        characterDead[3] = new Texture( "dead04.png");
        characterDead[4] = new Texture( "dead05.png");
        characterDead[5] = new Texture( "dead06.png");
        characterDead[6] = new Texture( "dead07.png");
        characterDead[7] = new Texture( "dead08.png");
        characterDead[8] = new Texture("dead09.png");
        characterDead[9] = new Texture("dead10.png");
        bomb = new Texture("bomb.png");
        random = new Random();
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Semibold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 120;
        fontParameter.borderWidth = 5;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.borderColor = Color.WHITE;
        freeFontGameOver = fontGenerator.generateFont(fontParameter);
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans-Regular.ttf"));
        fontParameter.size = 100;
        freeFontScore = fontGenerator.generateFont(fontParameter);
        scoreFont = new BitmapFont();
        scoreFont.setColor(Color.WHITE);
        scoreFont.getData().setScale(10);
        BitmapFont gameOverFont = new BitmapFont();
        gameOverFont.setColor(Color.WHITE);
        gameOverFont.getData().setScale(10);
    }

    private void makeCoin() {
        float height = random.nextFloat() * Gdx.graphics.getHeight(); // set height of singleCoin
        coinYs.add((int) height);
        coinXs.add(Gdx.graphics.getWidth());
    }

    private void makeBomb() {
        float height = random.nextFloat() * Gdx.graphics.getHeight();
        bombYs.add((int) height);
        bombXs.add(Gdx.graphics.getWidth());
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(backgroundMove[backgroundState], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (gameState == 1) { // Game is live
            if (backgroundPause < 5) {
                backgroundPause++;
            } else {
                backgroundPause = 0;
                if (backgroundState < 24) { // change images
                    backgroundState++;
                } else {
                    backgroundState = 0;
                }
            }
            batch.draw(backgroundMove[backgroundState], 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

            if(difficulty > 1000){
                speed+=1;
                difficulty=0;
            }else {
                difficulty++;
            }
            moveCoins();
            moveBombs();
            moveMan();
        } else if (gameState == 0) { // Waiting to start
            batch.draw(idleCharacter, Gdx.graphics.getWidth() / 2 - idleCharacter.getWidth() / 2, characterY);
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {// Game is over
            freeFontGameOver.draw(batch, "Game Over !!", Gdx.graphics.getWidth() / 2 - 400, Gdx.graphics.getHeight() - 150);
            freeFontScore.draw(batch, "You Scored  " + score, Gdx.graphics.getWidth() / 2 - 370, Gdx.graphics.getHeight() - 300);
            if (pauseExplosion < 5) { // slow down character
                pauseExplosion++;
            } else {
                pauseExplosion = 0;
                if (explosionState < 6) { // change images
                    batch.draw(explosion[explosionState], Gdx.graphics.getWidth() / 2 + 250, characterY + 100);
                    explosionState++;
                }
            }
            if (Gdx.input.justTouched()) {
                gameState = 1;
                characterY = Gdx.graphics.getHeight() / 2;
                score = 0;
                velocity = 0;

                coinXs.clear();
                coinYs.clear();
                coinRectangles.clear();
                coinCount = 0;

                bombXs.clear();
                bombYs.clear();
                bombRectangles.clear();
                bombCount = 0;
            }
        }
        if (gameState == 2) {
            if (pause < 3) { // slow down character
                pause++;
            } else {
                pause = 0;

                if (characterDeadState < 9) { // change images
                    characterDeadState++;
                }
            }
            manFall();
            batch.draw(characterDead[characterDeadState], Gdx.graphics.getWidth() / 2 - characterDead[characterDeadState].getWidth() / 2, characterY); // draw character dizzy
        } else if (gameState == 1) {
            batch.draw(character[characterState], Gdx.graphics.getWidth() / 2 - character[characterState].getWidth() / 2, characterY); // draw character
        }
        // set up character rectangle
        Rectangle manRectangle = new Rectangle(Gdx.graphics.getWidth() / 2 - character[characterState].getWidth() / 2, characterY, character[characterState].getWidth(), character[characterState].getHeight());
        for (int i = 0; i < coinRectangles.size(); i++) { // check if character overlaps with singleCoin
            if (Intersector.overlaps(manRectangle, coinRectangles.get(i))) {
                score++;
                coinRectangles.remove(i);
                coinXs.remove(i);
                coinYs.remove(i);
                break;
            }
        }
        for (int i = 0; i < bombRectangles.size(); i++) { // check if character overlaps with bomb
            if (Intersector.overlaps(manRectangle, bombRectangles.get(i))) {
                gameState = 2;
                bombRectangles.remove(i);
                bombXs.remove(i);
                bombYs.remove(i);
                break;
            }
        }
        String scoreText = String.valueOf(score);
        scoreFont.draw(batch, scoreText, 100, 200);
        batch.end();
    }

    private void moveMan() {
        if (Gdx.input.justTouched()) { // on touch jump character
            velocity = -15;
        }
        if (characterPause < 3) {
            characterPause++;
        } else {
            characterPause = 0;
            if (characterState < 9) { // change images
                characterState++;
            } else {
                characterState = 0;
            }
        }
        //change gravity (make character fall)
        manFall();
    }

    private void manFall() {
        float gravity = 0.3f;
        velocity += gravity;
        characterY -= velocity;
        if (characterY <= 0) {// stops character on floor
            characterY = 0;
        }
        if (characterY > Gdx.graphics.getHeight() - character[characterState].getHeight()/2){
            characterY = Gdx.graphics.getHeight() - character[characterState].getHeight()/2;
        }
    }

    private void moveBombs() {
        //move bombs
        if (bombCount < 130) {
            bombCount++;
        } else {
            bombCount = 0;
            makeBomb();
        }
        bombRectangles.clear();
        for (int i = 0; i < bombXs.size(); i++) { // |||||||| POSSIBLY REMOVE CODE add code to stop coins and bombs overlapping
            batch.draw(bomb, bombXs.get(i), bombYs.get(i));
            bombXs.set(i, bombXs.get(i) - 11 - speed);
            bombRectangles.add(new Rectangle(bombXs.get(i), bombYs.get(i), bomb.getWidth(), bomb.getHeight()));
        }
    }

    private void moveCoins() {
        //move coins
        if (coinCount < 90) {
            coinCount++;
        } else {
            coinCount = 0;
            makeCoin();
        }
        coinRectangles.clear();
        if (coinPause < 3) { // slow down character
            coinPause++;
        } else {
            coinPause = 0;
            if (coinState < 9) { // change images
                coinState++;
            } else {
                coinState = 0;
            }
        }
        for (int i = 0; i < coinXs.size(); i++) {
            batch.draw(coin[coinState], coinXs.get(i), coinYs.get(i));
            coinXs.set(i, coinXs.get(i) - 9 - speed);
            coinRectangles.add(new Rectangle(coinXs.get(i), coinYs.get(i), coin[coinState].getWidth(), coin[coinState].getHeight()));
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
