package com.tencent.wxcloudrun.model.impl;

import com.tencent.wxcloudrun.controller.DiceController;
import com.tencent.wxcloudrun.manager.DiceManager;
import com.tencent.wxcloudrun.model.Ai;
import com.tencent.wxcloudrun.model.Dice;

import java.util.*;

/**
 * @author Lambda
 * @date 2023/10/9
 */
public class AiMediumImpl implements Ai
{
    @Override
    public DiceController.DiceReply roll(DiceController.DiceReply diceReply)
    {
        List<Dice> diceArray = diceReply.getDiceArray2();

        Dice[] dices = new Dice[5];
        for(int i = 0; i < 5; ++i)
            dices[i] = diceArray.get(i);

        String pattern = DiceManager.getPattern(dices);
        if(DiceManager.getScore(dices, pattern) >= 40)
            diceReply.setRateAdjust(new Random().nextInt(4));
        if(pattern.equals("五连排") || pattern.equals("大顺子"))
            for(var dice: diceArray)
                dice.setModifiable(false);
        if(pattern.equals("小顺子"))
        {
            Arrays.sort(dices, Comparator.comparing(Dice::getFace));
            if(dices[0].getFace() == dices[1].getFace())
                dices[0].setModifiable(false);
            else
                dices[4].setModifiable(false);
            for(int i = 1; i < 4; ++i)
                dices[i].setModifiable(false);
        }
        if(pattern.equals("三连牌") || pattern.equals("四连牌"))
        {
            Map<Integer, Integer> map = new HashMap<>();
            for(int i = 0; i < 5; ++i)
                map.put(dices[i].getFace(), map.getOrDefault(dices[i].getFace(), 0) + 1);
            for(int i = 0; i < 5; ++i)
                if(map.get(dices[i].getFace()) >= 3)
                    dices[i].setModifiable(false);
        }

        for(var dice: diceArray)
        {
            if(dice.getFace() >= 5)
                dice.setModifiable(false);
            dice.roll();
        }
        return diceReply;
    }
}
