package com.tencent.wxcloudrun.model;

import com.tencent.wxcloudrun.manager.DiceManager;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Lambda
 * @date 2023/10/4
 */
@Data
public class Player implements Serializable
{
    private String token;
    private Integer rate;
    private Integer chip;
    private Dice[] dice;
    private Integer diceScore;
    private String pattern;

    public Player()
    {
        this.token = null;
        this.dice = new Dice[]{ new Dice(), new Dice(), new Dice(), new Dice(), new Dice() };
        this.chip = 1000;
        this.pattern = DiceManager.getPattern(dice);
        this.diceScore = DiceManager.getScore(dice, pattern);
    }

    public Player(Dice[] dice, Integer chip, Integer rate)
    {
        this.token = null;
        this.dice = dice;
        this.chip = chip;
        this.rate = rate;
        this.diceScore = DiceManager.getScore(dice);
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public Integer getRate()
    {
        return rate;
    }

    public void setRate(Integer rate)
    {
        this.rate = rate;
    }

    public Integer getChip()
    {
        return chip;
    }

    public void setChip(Integer chip)
    {
        this.chip = chip;
    }

    public Dice[] getDice()
    {
        return dice;
    }

    public void setDice(Dice[] dice)
    {
        this.dice = dice;
    }

    public Integer getDiceScore()
    {
        return diceScore;
    }

    public void setDiceScore(Integer diceScore)
    {
        this.diceScore = diceScore;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }
}
