package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MakingScreen extends ScreenAdapter{
    
    private Main game;
    private Pizza pizza;
    private Texture red = new Texture("red.jpg");
    private Texture cuttingBoard = new Texture("tmp_background.jpg");
    private Texture pepperoni = new Texture("pepperoni.png");
    private Topping holding;
    
    public MakingScreen(Main game){
        this.game = game;
        this.pizza = new Pizza();
    }
    
    @Override
    public void show(){
        Topping pepperoni = new Topping("pepperoni");
        
        
        //Code below gotten from https://happycoding.io/tutorials/libgdx/game-screens
        //This code sets up an input processor that when the space key is pressed it
        //will advance to the next screen which is the OrderScreen
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new TitleScreen(game));
                }
                return true;
            }
        });
        //end code from link
    }
    
    @Override
    public void render(float delta){
        
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        //Mouse logic
        //Will use a string to check if anything is being currently held
        //If nothing is being held then the player can pick up the pizza or topping
        //If something is being held, then it can be "dropped" onto the pizza or put back
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            int mouseX = Gdx.input.getX();
            int mouseY = Gdx.input.getY();
            //pizza
            if (mouseX >= pizza.getX() & mouseX <= pizza.getX() + pizza.getW() & 
                game.screenH - mouseY >= pizza.getY() & game.screenH - mouseY <= pizza.getY() + pizza.getW()){
                pizza.setX(mouseX - pizza.getW()/2);
                pizza.setY(game.screenH - mouseY - pizza.getH()/2);
            //pepperoni
            } else if (mouseX >= game.screenW * .4f & mouseX <= game.screenW * .6f &
                        game.screenH - mouseY >= 0 & game.screenH - mouseY >= game.screenH * .75f) {
                
            }
        }
        
        game.batch.begin();
        game.font.draw(game.batch, "Making Screen!", game.screenW * .25f, game.screenH * .75f);
        game.batch.draw(cuttingBoard, game.screenW * .2f, 0, game.screenW * .8f, game.screenH * 0.75f);
        //draws temporary red boxes to mark layout of cooking screen
        game.batch.draw(red, 0, game.screenH * .75f, game.screenW * 0.2f, game.screenH* 0.25f);
        game.batch.draw(red, game.screenW * .4f, game.screenH * .75f, game.screenW * .2f, game.screenH * .25f);
        game.batch.draw(red, game.screenW * .8f, game.screenH * .75f, game.screenW * .2f, game.screenH * .25f);
        
        game.font.draw(game.batch, "Pepperoni", game.screenW * .5f, game.screenH * 0.95f);
        game.batch.draw(pepperoni, game.screenW * .5f, game.screenH * .8f, 100, 100);
        
        game.batch.draw(pizza.getImg(), pizza.getX(), pizza.getY(), pizza.getW(), pizza.getH());
        game.batch.end();
    }
    
    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
