package com.maxdunsmore.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture background;
    private Texture topTube;
    private Texture bottomTube;
    private Texture gameOver;
    private Texture coin;
    private Texture[] birds;
    private int flapState = 0;
    private int pauseFlapState = 0;
    private int gameState = 0;
    private int numberOfTubes = 4;
    private int height;
    private int width;
    private int score = 0;
    private int scoringTube = 0;
    private int highScore = 0;
    private float birdY = 0;
    private float velocity = 0;
    private float gravity = 2;
    private float gap = 450;
    private float maxTubeOffset;
    private float distanceBetweenTubes;
    private float tubeVelocity = 4;
    private float[] tubeX = new float[numberOfTubes];
    private float[] tubeOffset = new float[numberOfTubes];
    private Random randomGenerator;
    private Circle birdCircle;
    private Rectangle[] topTubeRectangles;
    private Rectangle[] bottomTubeRectangles;
    private BitmapFont font;
    private BitmapFont fontScore;
    private BitmapFont fontHighScore;
    private Stage stage;
    private Preferences preferences;

    //ShapeRenderer shapeRenderer;

    @Override
    public void create () {
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        height = Gdx.graphics.getHeight() / 2;
        width = Gdx.graphics.getWidth() / 2;

        gameOver = new Texture("gameOver2.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");

        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        maxTubeOffset = height - gap / 2 - 100;
        randomGenerator = new Random();
        distanceBetweenTubes = Gdx.graphics.getWidth() * 3 / 4;
        birdCircle = new Circle();
        topTubeRectangles = new Rectangle[numberOfTubes];
        bottomTubeRectangles = new Rectangle[numberOfTubes];
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

        fontScore = new BitmapFont();
        fontScore.setColor(Color.WHITE);
        fontScore.getData().setScale(7);

        fontHighScore = new BitmapFont();
        fontHighScore.setColor(Color.WHITE);
        fontHighScore.getData().setScale(7);

        stage = new Stage(new StretchViewport(width, height));
        startGame();
        //shapeRenderer = new ShapeRenderer();

    }
    public void resize(int width, int height) {
        // use true here to center the camera
        // that's what you probably want in case of a UI
        stage.getViewport().update(width, height, true);
    }

    private void startGame(){
        birdY = height - birds[0].getHeight() / 2;
        for (int i = 0; i < numberOfTubes; i++) {
            tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
            tubeX[i] = height - topTube.getWidth() / 2 + Gdx.graphics.getWidth() + i * distanceBetweenTubes;
            topTubeRectangles[i] = new Rectangle();
            bottomTubeRectangles[i] = new Rectangle();
        }
    }
    @Override
    public void render () {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        if (gameState == 1) {
            if (tubeX[scoringTube] < Gdx.graphics.getWidth()){
                score++;
                if (scoringTube < numberOfTubes - 1){
                    scoringTube++;
                }else {
                    scoringTube = 0;
                }
            }
            if (Gdx.input.justTouched()) {
                velocity = -30;
            }
            for (int i = 0; i < numberOfTubes; i++) {
                if (tubeX[i] < - topTube.getWidth()) {
                    tubeX[i] += numberOfTubes * distanceBetweenTubes;
                    tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
                } else {
                    tubeX[i] = tubeX[i] - tubeVelocity;
                }
                batch.draw(topTube, tubeX[i], height + gap / 2 + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], height - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

                topTubeRectangles[i] = new Rectangle(tubeX[i],height + gap / 2 + tubeOffset[i],topTube.getWidth(),topTube.getHeight());
                bottomTubeRectangles[i] = new Rectangle(tubeX[i],height - gap / 2 - bottomTube.getHeight() + tubeOffset[i],bottomTube.getWidth(),bottomTube.getHeight());
            }
            if (birdY > 0 ) {
                velocity = velocity + gravity;
                birdY -= velocity;
            }else {
                gameState = 2;
            }
        } else if ( gameState == 0){
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        }
        else if( gameState == 2){
            if (score < 9){
                coin = new Texture("coin1.png");
            }else if(score > 9 && score < 25){
                coin = new Texture("coin2.png");
            }else if(score > 24 && score < 50){
                coin = new Texture("coin3.png");
            }else if(score > 49){
                coin = new Texture("coin4.png");
            }
            velocity = velocity + gravity;
            birdY -= velocity;
            preferences = Gdx.app.getPreferences("flappyBird");
            highScore = preferences.getInteger("highScore",0);
            if (score > highScore){
                preferences.putInteger("highScore",score);
                preferences.flush();
            }
            batch.draw(gameOver,50,height - gameOver.getHeight()/2, Gdx.graphics.getWidth()-100,Gdx.graphics.getHeight()/2 );
            batch.draw(coin,145,Gdx.graphics.getWidth()/2 + 595, Gdx.graphics.getWidth()/4,Gdx.graphics.getHeight()/10);
            fontScore.draw(batch,String.valueOf(score),Gdx.graphics.getWidth()/2 + 295,Gdx.graphics.getHeight()/2 + 365);
            fontHighScore.draw(batch,String.valueOf(preferences.getInteger("highScore",0)),Gdx.graphics.getWidth()/2 + 295,Gdx.graphics.getHeight()/2 + 160);
            if (Gdx.input.justTouched()) {
                gameState = 1;
                startGame();
                score = 0;
                scoringTube = 0;
                velocity = 0;
            }
        }
        if (pauseFlapState < 4){
            pauseFlapState++;
        }else{
            pauseFlapState =0;
            if (flapState == 0) {
                flapState = 1;
            } else {
                flapState = 0;
            }
        }
        batch.draw(birds[flapState], width - birds[flapState].getWidth() / 2, birdY);
// move code with breaks to other break
        font.draw(batch,String.valueOf(score),100,200);
        birdCircle.set(width,birdY+birds[flapState].getHeight()/2,birds[flapState].getWidth()/2);
        //shapeRenderer.setColor(Color.RED);
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //shapeRenderer.circle(birdCircle.x,birdCircle.y,birdCircle.radius);
        batch.end();
        for (int i = 0; i < numberOfTubes; i++) {
            //shapeRenderer.rect(tubeX[i],height + gap / 2 + tubeOffset[i],topTube.getWidth(),topTube.getHeight());
            //shapeRenderer.rect(tubeX[i],height - gap / 2 - bottomTube.getHeight() + tubeOffset[i],bottomTube.getWidth(),bottomTube.getHeight());
            if (Intersector.overlaps(birdCircle,topTubeRectangles[i]) || Intersector.overlaps(birdCircle,bottomTubeRectangles[i])){
                gameState = 2;
            }
        }
        //shapeRenderer.end();
    }
}
