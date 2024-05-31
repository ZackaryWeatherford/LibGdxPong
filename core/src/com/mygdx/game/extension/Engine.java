package com.mygdx.game.extension;

import java.util.ArrayList;


/**
 *
 */
public class Engine {

    /** */
    public static Window window;

    /** List of all game objects within game*/
    private static ArrayList<GameObject> gameObjects;

    /** Sorted list of game objects that will be drawn to screen based on their draw priority*/
    private static ArrayList<GameObject> objectsToBeDrawn;

    /** Objects with defined input*/
    private static ArrayList<GameObject> objectsWithInput;

    /** Objects with defined logic to be updated */
    private static ArrayList<GameObject> objectsWithLogic;

    /** Objects in the list will have their colliding objects list updated*/
    private static ArrayList<GameObject> objectUpdateCollisions;

    /** Objects in this list will be able to be collided with*/
    private static ArrayList<GameObject> collidableObjects;


    /* ----- CONSTRUCTOR ----- */

    /** */
    public Engine(int width, int height){

        window = new Window(width, height);

        gameObjects = new ArrayList<>();
        objectsWithInput = new ArrayList<>();
        objectsWithLogic = new ArrayList<>();
        objectsToBeDrawn = new ArrayList<>();
        objectUpdateCollisions = new ArrayList<>();
        collidableObjects = new ArrayList<>();
    }

    /* ----- ENGINE UPDATERS ----- */

    /** */
    public void update(){

        //Draw every game object onto the screen
        window.draw(objectsToBeDrawn);

        //Updates game objects colliding list
        updateCollisions();

        //Updates all game objects input
        inputUpdate();

        //Updates all game objects logic
        logicUpdate();

        //Update all timers
        Timer.incrementTimers();
    }

    /** */
    public void inputUpdate(){

        for(GameObject input : objectsWithInput)
            input.input();

    }

    /** */
    public void logicUpdate(){
        for(GameObject object : objectsWithLogic)
            object.logic();
    }


    /* ----- LIST CONTROL METHODS ----- */

    /** */
    public static void add(GameObject object){
        gameObjects.add(object);
    }

    /** */
    public static void addInput(GameObject object){
        objectsWithInput.add(object);
    }

    /** */
    public static void addLogic(GameObject object){
        objectsWithLogic.add(object);
    }

    /** */
    public static void startDrawing(GameObject object){

        if(object.sprite == null)
            return;

        objectsToBeDrawn.add(object);
        updateDrawPriority(object);

    }

    /** */
    public static void enableCollision(GameObject object){
        if(object == null)
            throw new NullPointerException("Can't add null object to engine colliders list");

        collidableObjects.add(object);
    }

    /** */
    public static void enableCollisionUpdates(GameObject object){
        if(object == null)
            throw new NullPointerException("Trying to add null game object to object colliders list");

        objectUpdateCollisions.add(object);
    }


    /** Removes game object from all Engine lists to be collected by garbage collector*/
    public static void delete(GameObject object) {
        gameObjects.remove(object);
        objectsWithInput.remove(object);
        objectsWithLogic.remove(object);
        objectsToBeDrawn.remove(object);
    }

    /** */
    public static void removeInput(GameObject object){objectsWithInput.remove(object);}
    
    /** */
    public static void removeLogic(GameObject object){objectsWithLogic.remove(object);}
    
    /** */
    public static void stopDrawing(GameObject object){
        objectsToBeDrawn.remove(object);
    }

    /** */
    public static void disableCollision(GameObject object){
        collidableObjects.remove(object);
    }

    /** */
    public static void disableCollisionUpdates(GameObject object){
        objectUpdateCollisions.remove(object);
    }

    /**
     * Reinsert object with its new draw priority back into the draw list
     * @param object the object that will be reinserted back into the objectsToBeDrawn list
     * */
    public static void updateDrawPriority(GameObject object) {

        try {
            //Remove object from drawing list and don't update
            if (!objectsToBeDrawn.remove(object))
                throw new Exception("ERROR: Can't update draw priority. Object not in the objectsToBeDrawn list");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }

        //If the list is empty
        if(objectsToBeDrawn.isEmpty())
            objectsToBeDrawn.add(object);

        int left = 0;
        int right = objectsToBeDrawn.size() - 1;
        int drawPriority = object.getDrawPriority();

        //Binary insertion
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (objectsToBeDrawn.get(mid).getDrawPriority() < drawPriority) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        objectsToBeDrawn.add(left, object);
    }


    /* ----- Update and Dispose Methods ----- */

    /**
     * Detects all collisions and informs each of the game objects with a list of all objects colliding with it.
     */
    public static void updateCollisions(){

        //Clear out colliding objects list
        for(GameObject object : objectUpdateCollisions)
            object.collidingObjects.clear();

        //Adds colliding objects to object colliders, colliding lists
        for(GameObject objectCollider : objectUpdateCollisions)

            for(GameObject collidableObject : collidableObjects)
                if (Box2D.isColliding(objectCollider, collidableObject) && objectCollider != collidableObject)
                    objectCollider.collidingObjects.add(collidableObject);

    }

    /** */
    /** Dispose of all resources*/
    public void dispose(){
        window.batch.dispose();
    }

}
