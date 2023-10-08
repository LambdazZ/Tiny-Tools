package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Random;

/**
 * Represents a dice with six faces numbered from 1 to 6.
 *
 * @author Lambda
 * @date 2023/10/4
 */

@Data
public class Dice implements Serializable
{
    public final static int FACE1 = 1;
    public final static int FACE2 = 2;
    public final static int FACE3 = 3;
    public final static int FACE4 = 4;
    public final static int FACE5 = 5;
    public final static int FACE6 = 6;

    private int face;
    private boolean modifiable = true;

    /**
     * Constructs a dice with an initial face value of 1.
     */
    public Dice()
    {
        roll();
    }

    /**
     * Constructs a dice with a specific face value.
     *
     * @param face the initial face value of the dice.
     */
    public Dice(int face)
    {
        this.face = face;
    }

    /**
     * Constructs a dice with a specific face value and modifiability.
     *
     * @param face       the initial face value of the dice.
     * @param modifiable determines if the dice's face value can be modified.
     */
    public Dice(int face, boolean modifiable)
    {
        this.face = face;
        this.modifiable = modifiable;
    }

    /**
     * Retrieves the current face value of the dice.
     *
     * @return the current face value.
     */
    public int getFace()
    {
        return face;
    }

    /**
     * Sets the face value of the dice.
     *
     * @param face the new face value to set.
     */
    public void setFace(int face)
    {
        this.face = face;
    }

    /**
     * Checks if the dice is modifiable.
     *
     * @return true if the dice's face value can be modified, false otherwise.
     */
    public boolean isModifiable()
    {
        return modifiable;
    }

    /**
     * Sets the modifiability of the dice.
     *
     * @param modifiable true to allow modification of the dice's face value, false otherwise.
     */
    public void setModifiable(boolean modifiable)
    {
        this.modifiable = modifiable;
    }

    /**
     * Rolls the dice and updates its face value if it is modifiable.
     *
     * @return true if the dice was rolled and the face value was updated, false if the dice is not modifiable.
     */
    public boolean roll()
    {
        if (modifiable)
        {
            face = new Random(System.currentTimeMillis() + new Random().nextInt()).nextInt(6) + 1;
            return true;
        }
        else
            return false;
    }
}