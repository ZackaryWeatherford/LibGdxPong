package com.mygdx.game.extension;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GameObject {

    /** The x and y coordinates*/
    protected Vector2 position;

    /** The width and height*/
    protected Dimension<Float> dimension;

    /** The identification of the game object*/
    public ArrayList<ID> idList;

    /** Determines if sprite should be drawn onto screen*/
    private boolean visible = false;

    /** Higher draw priority means it will be drawn in front of other sprites*/
    private int drawPriority = 0;

    /** The sprite to be drawn*/
    public Sprite sprite;

    /** */
    ArrayList<GameObject> collidingObjects;

    /** Determines if the colliding objects list gets updated*/
    private boolean updateCollisions = false;

    /** The boolean for whether this block is collidable or not*/
    private boolean collidable = false;


    /* ----- CONSTRUCTORS ----- */

    /** */
    private GameObject(){

        idList = new ArrayList<ID>();
        collidingObjects = new ArrayList<GameObject>();

        Engine.add(this);
    }

    /** */
    public GameObject(Vector2 position, Sprite sprite) {

        this();

        setPosition(position);
        this.sprite = sprite;
        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());

        setVisibility(true);
    }

    /** */
    public GameObject(Vector2 position, Sprite sprite, ID id) {
        this();

        idList.add(id);

        this.sprite = sprite;

        setPosition(position);
        dimension = new Dimension<Float>(sprite.getWidth(), sprite.getHeight());

        setVisibility(true);
    }

    /** */
    public GameObject(Vector2 position, Dimension<Float> dimension, ID id) {
        this();

        idList.add(id);

        setPosition(position);
        this.dimension = dimension;
    }

    /** */
    public GameObject(Vector2 position, Dimension<Float> dimension) {
        this();

        setPosition(position);
        this.dimension = dimension;
    }


    /* ----- CHILD DEFINED CLASSES ----- */

    /**
     * Where the game objects logic is defined by overriding this function in child classes.
     * Must call {@code enableLogic()} for function to start being updated
     */
    public void logic(){}

    /**
     * Where the game objects input is defined by overriding this function in child classes.
     * Must call {@code enableInput()} for function to start being updated
     */
    public void input(){}

    /* ----- MUTATORS ----- */

    /** */
    public void setCollidable(boolean collidable){
        if(!collidable) {
            this.collidable = true;
            Engine.enableCollision(this);
        }

        if (collidable) {
            this.collidable = false;
            Engine.disableCollision(this);
        }
    }

    /** */
    public void setUpdateCollisions(boolean updateCollisions){
        if(updateCollisions == this.updateCollisions) {
            System.out.println("Error: Can't set update collision because provided boolean is already set to same value");
            return;
        }

        if(updateCollisions){
            this.updateCollisions = true;
            Engine.enableCollisionUpdates(this);
        }

        if(!updateCollisions) {
            this.updateCollisions = false;
            Engine.disableCollisionUpdates(this);
        }
    }

    /** */
    public void setVisibility(boolean visible){

        if(visible && sprite != null) {
            this.visible = true;
            Engine.startDrawing(this);
        }

        if(!visible) {
            this.visible = false;
            Engine.stopDrawing(this);
        }
    }

    /** */
    public void setDrawPriority(int newPriority){

        drawPriority = newPriority;

        Engine.updateDrawPriority(this);
    }

    /** Set focus of camera on game object? */
    public void setFocus(){

    }

    /**
     * Sets a new position for the block and sets a new position for sprite
     * @param newPosition new Vector2 position
     */
    public void setPosition(Vector2 newPosition){
        position = newPosition;

        if(sprite != null)
            sprite.setPosition(position.x, position.y);
    }

    /** */
    public void setDimension(Dimension<Float> newDimension){
        if(dimension != null)
            dimension = newDimension;
    }

    /* ----- ID METHODS ----- */

    /** */
    public boolean hasMatchingID(ID id){

        for(ID idElement : idList)
            if(idElement.equals(id))
                return true;

        return false;
    }

    /* ----- ACCESSORS ----- */

    /** */
    public Vector2 getPosition(){
        return position;
    }

    /** */
    public boolean isVisible(){
        return visible;
    }

    /** */
    public Dimension<Float> getDimension(){
        return dimension;
    }

    /** */
    public int getDrawPriority(){
        return drawPriority;
    }

    /** */
    public boolean isCollidable(){
        return collidable;
    }

    /* ----- EVENTS ----- */

    public boolean justClicked(){
        System.out.println(Gdx.input.getX());
        System.out.println(Gdx.input.getY());
        System.out.println(Window.camera.position.x);

        return false;
    }
}
