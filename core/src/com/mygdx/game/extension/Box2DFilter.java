package com.mygdx.game.extension;

import java.util.ArrayList;

public class Box2DFilter {

    public static ArrayList<GameObject> getLeftCollisions(ArrayList<GameObject> objects, GameObject collidableObject){

        ArrayList<GameObject> leftCollisions = new ArrayList<GameObject>();

        for(GameObject object : objects)
            if(Box2D.leftCollision(collidableObject, object))
                leftCollisions.add(object);

        return leftCollisions;
    }

    public static ArrayList<GameObject> getRightCollisions(ArrayList<GameObject> objects, GameObject collidableObject){

        ArrayList<GameObject> rightCollisions = new ArrayList<GameObject>();

        for(GameObject object : objects)
            if(Box2D.rightCollision(collidableObject, object))
                rightCollisions.add(object);

        return rightCollisions;
    }

    public static ArrayList<GameObject> getBottomCollisions(ArrayList<GameObject> objects, GameObject collidableObject){

        ArrayList<GameObject> bottomCollisions = new ArrayList<GameObject>();

        for(GameObject object : objects)
            if(Box2D.bottomCollision(collidableObject, object))
                bottomCollisions.add(object);

        return bottomCollisions;
    }



}
