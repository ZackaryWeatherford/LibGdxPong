package com.mygdx.game.extension;

import com.badlogic.gdx.math.Vector2;

public class Box2D {



    /* ----- COLLISION DETECTION ----- */


    /**
     * Finds overlap between two objects and returns if the objects are colliding
     * @param collidingObject the first object
     * @param collidableObject the second object
     * @return if the two objects are colliding
     * */
    public static boolean isColliding(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects parameters
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects parameters
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        //boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2);
        //boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2);

        boolean yOverlap = getOverlapInclusive(bottomY1, topY1, bottomY2, topY2) >= 0;
        boolean xOverlap = getOverlapInclusive(leftX1, rightX1, leftX2, rightX2) >= 0;

        return xOverlap && yOverlap;
    }

    /**
     * Determines if the colliding object is colliding with the right side of the collidable objects
     * @param collidableObject the object to determine if there's a collision on its right side
     * @param collidingObject the object to determine if it's colliding with the other objects right side
     * @return whether the colliding object is colliding on the right side of the collidable object
     * */
    public static boolean rightCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2) && (rightX1 < rightX2 && rightX1 > leftX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2);

        return xOverlap && yOverlap;
    }

    /**
     * Determines if the colliding object is colliding with the left side of the collidable objects
     * @param collidableObject the object to determine if there's a collision on its left side
     * @param collidingObject the object to determine if it's colliding with the other objects left side
     * @return whether the colliding object is colliding on the left side of the collidable object
     * */
    public static boolean leftCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2) && (leftX1 > leftX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2);

        return xOverlap && yOverlap;
    }

    /**
     * Determines if the colliding object is colliding with the bottom side of the collidable objects
     * @param collidableObject the object to determine if there's a collision on its bottom side
     * @param collidingObject the object to determine if it's colliding with the other objects bottom side
     * @return whether the colliding object is colliding on the bottom side of the collidable object
     * */
    public static boolean bottomCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get overlap
        boolean xOverlap = getOverlapInclusive(leftX1, rightX1, leftX2, rightX2) >= 0;
        boolean yOverlap = getOverlapInclusive(bottomY1, topY1, bottomY2, topY2) >= 0 && bottomY1 >= bottomY2;

        return xOverlap && yOverlap;
    }

    /**
     * Determines if the colliding object is colliding with the top side of the collidable objects
     * @param collidableObject the object to determine if there's a collision on its top side
     * @param collidingObject the object to determine if it's colliding with the other objects top side
     * @return whether the colliding object is colliding on the top side of the collidable object
     * */
    public static boolean topCollision(GameObject collidableObject, GameObject collidingObject){

        //Get collidable objects coordinates
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;

        //Get colliding objects coordinates
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        //Get Overlap
        boolean xOverlap = isOverlapping(leftX1, rightX1, leftX2, rightX2);
        boolean yOverlap = isOverlapping(bottomY1, topY1, bottomY2, topY2) && (topY1 < topY2 && topY1 > bottomY2);

        return xOverlap && yOverlap;
    }


    /* ----- Collision Resolution ------ */


    /**
     *
     */
    public static Vector2 getCollisionFreePos(GameObject collidableObject, GameObject collidingObject){

        //If objects aren't colliding then return same position
        if(!isColliding(collidableObject, collidingObject))
            return collidableObject.getPosition();

        //Direction to push this box2D out of the other one
        Movement.Direction direction = axisPushDirection(collidableObject, collidingObject);

        float pushDistance = axisPushDistance(collidableObject, collidingObject);

        float x = collidableObject.getPosition().x;
        float y = collidableObject.getPosition().y;

        if(direction == Movement.Direction.LEFT || direction == Movement.Direction.RIGHT)
            x += pushDistance;
        else
            y += pushDistance;

        return new Vector2(x, y);
    }

    /**
     * Calculate the shortest distance to move box's out of each other using the SAT method to separate block based on the shortest axis it would take
     * to move an object out of another object.
     * @param collidableObject
     * @param collidingObject
     * @return the shortest distance required to push the collidable object out of the colliding object based on its axis
     */
    public static float axisPushDistance(GameObject collidableObject, GameObject collidingObject) {

        //Calculate X Overlap
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;

        float xOverlap = getOverlap(leftX1, rightX1, leftX2, rightX2);

        //Calculate Y Overlap
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        float yOverlap = getOverlap(bottomY1, topY1, bottomY2, topY2);

        Movement.Direction pushDirection = axisPushDirection(collidableObject, collidingObject);

        switch(pushDirection){
            case UP:
                return yOverlap;
            case DOWN:
                return -1 * yOverlap;
            case LEFT:
                return -1 * xOverlap;
            case RIGHT:
                return xOverlap;
        }

        return 0;
    }

    /**
     * Find the direction for the shortest distance to move the collidable object out of the colliding object.
     * @param collidableObject
     * @param collidingObject
     * @return the direction of the minimum distance to move the objects out of each other
     */
    public static Movement.Direction axisPushDirection(GameObject collidableObject, GameObject collidingObject) {

        //Calculate X Overlap
        float leftX1 = collidableObject.getPosition().x;
        float rightX1 = collidableObject.getPosition().x + collidableObject.getDimension().width;
        float leftX2 = collidingObject.getPosition().x;
        float rightX2 = collidingObject.getPosition().x + collidingObject.getDimension().width;

        float xOverlap = getOverlap(leftX1, rightX1, leftX2, rightX2);

        //Calculate Y Overlap
        float bottomY1 = collidableObject.getPosition().y;
        float topY1 = collidableObject.getPosition().y + collidableObject.getDimension().height;
        float bottomY2 = collidingObject.getPosition().y;
        float topY2 = collidingObject.getPosition().y + collidingObject.getDimension().height;

        float yOverlap = getOverlap(bottomY1, topY1, bottomY2, topY2);


        //Compare to find which one is shortest to apply

        // If X-Overlap is shorter move horizontally out of block
        if (xOverlap <= yOverlap)
            //Move Left
            if (leftX1 <= leftX2)
                return Movement.Direction.LEFT;

            //Move Right
            else
                return Movement.Direction.RIGHT;

        //If Y-Overlap is shorter, move vertically out of block
        else if (xOverlap >= yOverlap)

            //Move Down
            if (bottomY1 <= bottomY2)
                return Movement.Direction.DOWN;
            //Move Up
            else
                return Movement.Direction.UP;


        return null;
    }

    /**
     * Takes in two lines and returns the amount of overlap or ZERO if there is no overlap
     * The min's represent the left point of the line and the max's represent the right point
     * */
    public static float getOverlap(float min1, float max1, float min2, float max2){
        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2));
    }

    public static float getOverlapInclusive(float min1, float max1, float min2, float max2){
        return Math.min(max1, max2) - Math.max(min1, min2);
    }

    /**
     * Takes in two line coordinates and returns if they are overlapping.
     * The min's represent the left point of the lines and the max's represent the right point of the lines.
     */
    public static boolean isOverlapping(float min1, float max1, float min2, float max2){
        return Math.max(0, Math.min(max1, max2) - Math.max(min1, min2)) != 0;
    }


}
