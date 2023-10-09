package com.tencent.wxcloudrun.model.impl;

import com.tencent.wxcloudrun.controller.DiceController;
import com.tencent.wxcloudrun.model.Ai;
import com.tencent.wxcloudrun.model.Dice;

import java.util.List;

/**
 * @author Lambda
 * @date 2023/10/9
 */
public class AiEasyImpl implements Ai
{

    @Override
    public DiceController.DiceReply roll(DiceController.DiceReply diceReply)
    {
        List<Dice> diceArray = diceReply.getDiceArray2();
        for(var dice: diceArray)
        {
            if(dice.getFace() >= 5)
                dice.setModifiable(false);
            dice.roll();
        }
        diceReply.setRateAdjust(0);
        return diceReply;
    }
}
