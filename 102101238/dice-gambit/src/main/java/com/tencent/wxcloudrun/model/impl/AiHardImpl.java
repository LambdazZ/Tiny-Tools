package com.tencent.wxcloudrun.model.impl;

import com.tencent.wxcloudrun.controller.DiceController;
import com.tencent.wxcloudrun.manager.DiceManager;
import com.tencent.wxcloudrun.model.Ai;
import com.tencent.wxcloudrun.model.Dice;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Lambda
 * @date 2023/10/9
 */
public class AiHardImpl implements Ai
{
    private static final int L0 = 1;
    private static final int L1 = 6;
    private static final int L2 = 36;
    private static final int L3 = 216;
    private static final int L4 = 1296;
    private static final int[] L = {L0, L1, L2, L3, L4};

    private static double[]expectations;

    static
    {
        expectations = new double[9331];
        for(int i = 1; i <= 6; ++i)
        {
            int l0 = i * L0;
            for(int j = 1; j <= 6; ++j)
            {
                int l1 = i * L0 + j * L1;
                for(int k = 1; k <= 6; ++k)
                {
                    int l2 = i * L0 + j * L1 + k * L2;
                    for(int l = 1; l <= 6; ++l)
                    {
                        int l3 = i * L0 + j * L1 + k * L2 + l * L3;
                        for(int m = 1; m <= 6; ++m)
                        {
                            int score = DiceManager.getScore(new Dice[]{new Dice(i), new Dice(j), new Dice(k), new Dice(l), new Dice(m)});
                            expectations[l0] += score;
                            expectations[l1] += score;
                            expectations[l2] += score;
                            expectations[l3] += score;
                            expectations[i * L0 + j * L1 + k * L2 + l * L3 + m * L4] = score;
                        }
                        expectations[l3] /= 6;
                    }
                    expectations[l2] /= 36;
                }
                expectations[l1] /= 216;
            }
            expectations[l0] /= 1296;
        }
//        System.out.println(Arrays.toString(expectations));
//        System.out.println(expectations[6 * L0]);
//        System.out.println(expectations[6 * L0 + 6 * L1]);
//        System.out.println(expectations[6 * L0 + 6 * L1 + 6 * L2]);
//        System.out.println(expectations[6 * L0 + 6 * L1 + 6 * L2 + 6 * L3]);
//        System.out.println(expectations[6 * L0 + 6 * L1 + 6 * L2 + 6 * L3 + 6 * L4]);
//        Arrays.sort(expectations);
//        System.out.println(expectations[7777]);
//        System.out.println(expectations[6666]);
//        System.out.println(expectations[5555]);
//        System.out.println(expectations[3333]);
//        System.out.println(expectations[2222]);
//        System.out.println(expectations[1111]);
    }
    @Override
    public DiceController.DiceReply roll(DiceController.DiceReply diceReply)
    {
        List<Dice> diceArray = diceReply.getDiceArray2();

        Dice[] dices = new Dice[5];
        for(int i = 0; i < 5; ++i)
            dices[i] = diceArray.get(i);
        Arrays.sort(dices, Comparator.comparing(Dice::isModifiable));
//        if(DiceManager.getScore(dices) >= 41)
//            diceReply.setRateAdjust(3);
//        else if(DiceManager.getScore(dices) >= 31.5)
//            diceReply.setRateAdjust(2);
//        else if(DiceManager.getScore(dices) >= 28)
//            diceReply.setRateAdjust(1);
//        else if(DiceManager.getScore(dices) <= 17)
//            diceReply.setRateAdjust(-3);
//        else if(DiceManager.getScore(dices) <= 19)
//            diceReply.setRateAdjust(-2);
//        else if(DiceManager.getScore(dices) <= 21)
//            diceReply.setRateAdjust(-1);

        double userScore = DiceManager.getScore(diceReply.getDiceArray1());
        double maximum = DiceManager.getScore(dices);
        double diff = maximum - userScore;
        if(diff >= 20)
            diceReply.setRateAdjust(3);
        else if(diff >= 12)
            diceReply.setRateAdjust(2);
        else if(diff >= 8)
            diceReply.setRateAdjust(1);
        else if(diff <= -20)
            diceReply.setRateAdjust(-3);
        else if(diff <= -12)
            diceReply.setRateAdjust(-2);
        else if(diff <= -8)
            diceReply.setRateAdjust(-1);

        int index = 0;
        for(int i = 0; i < 5; ++i)
            if(dices[index].isModifiable())
                break;
            else
                ++index;
        //System.out.println(index);
        boolean[] modifiable = new boolean[5];
        int base = 0;
        for(int i = 0; i < index; ++i)
            base += dices[i].getFace() * L[i];

        //System.out.println(maximum);
        switch (index)
        {
            case 0 ->
            {
                //System.out.println("case 0");
                //System.out.println(Arrays.toString(modifiable));
                if(maximum <= expectations[base])
                    modifiable[0] = modifiable[1] = modifiable[2] = modifiable[3] = modifiable[4] = true;

                for(int i = index; i < 5; ++i)
                    if(maximum <= expectations[base + dices[i].getFace() * L[index]])
                    {
                        maximum = expectations[base + dices[i].getFace() * L[index]];
                        for(int j = index; j < 5; ++j)
                            modifiable[j] = true;
                        modifiable[i] = false;
                    }

                for(int i = index; i < 4; ++ i)
                    for(int j = i + 1; j < 5; ++j)
                    {
                        if(maximum <= expectations[base + dices[i].getFace() * L[index] + dices[j].getFace() * L[index + 1]])
                        {
                            maximum = expectations[base + dices[i].getFace() * L[index] + dices[j].getFace() * L[index + 1]];
                            for(int k = index; k < 5; ++k)
                                modifiable[k] = true;
                            modifiable[i] = modifiable[j] = false;
                        }
                    }

                for(int i = index; i < 4; ++ i)
                    for(int j = i + 1; j < 5; ++j)
                    {
                        int tmpIndex = base;
                        int cnt = 0;
                        for(int k = index; k < 5; ++k)
                            if(k != i && k != j)
                                tmpIndex += dices[k].getFace() * L[index + cnt++];
                        if(maximum <= expectations[tmpIndex])
                        {
                            maximum = expectations[tmpIndex];
                            for(int k = index; k < 5; ++k)
                                modifiable[k] = false;
                            modifiable[i] = modifiable[j] = true;
                        }
                    }

                //System.out.println(Arrays.toString(modifiable));

                for(int i = index; i < 5; ++i)
                {
                    int tmpIndex = base;
                    int cnt = 0;
                    for(int j = index; j < 5; ++j)
                        if(i != j)
                            tmpIndex += dices[j].getFace() * L[index + cnt++];
                    if(maximum <= expectations[tmpIndex])
                    {
                        maximum = expectations[tmpIndex];
                        for(int j = index; j < 5; ++j)
                            modifiable[j] = false;
                        modifiable[i] = true;
                    }
                }

                //System.out.println(Arrays.toString(modifiable));
            }
            case 1 ->
            {
                if(maximum <= expectations[base])
                    modifiable[1] = modifiable[2] = modifiable[3] = modifiable[4] = true;
                for(int i = index; i < 5; ++i)
                    if(maximum <= expectations[base + dices[i].getFace() * L[index]])
                    {
                        maximum = expectations[base + dices[i].getFace() * L[index]];
                        for(int j = index; j < 5; ++j)
                            modifiable[j] = true;
                        modifiable[i] = false;
                    }

                for(int i = index; i < 4; ++ i)
                    for(int j = i + 1; j < 5; ++j)
                    {
                        if(maximum <= expectations[base + dices[i].getFace() * L[index] + dices[j].getFace() * L[index + 1]])
                        {
                            maximum = expectations[base + dices[i].getFace() * L[index] + dices[j].getFace() * L[index + 1]];
                            for(int k = index; k < 5; ++k)
                                modifiable[k] = true;
                            modifiable[i] = modifiable[j] = false;
                        }
                    }

                for(int i = index; i < 5; ++i)
                {
                    int tmpIndex = base;
                    int cnt = 0;
                    for(int j = index; j < 5; ++j)
                        if(i != j)
                            tmpIndex += dices[j].getFace() * L[index + cnt++];
                    if(maximum <= expectations[tmpIndex])
                    {
                        maximum = expectations[tmpIndex];
                        for(int j = index; j < 5; ++j)
                            modifiable[j] = false;
                        modifiable[i] = true;
                    }
                }
            }
            case 2 ->
            {
                if(maximum <= expectations[base])
                    modifiable[2] = modifiable[3] = modifiable[4] = true;
                for(int i = index; i < 5; ++i)
                    if(maximum <= expectations[base + dices[i].getFace() * L[index]])
                    {
                        maximum = expectations[base + dices[i].getFace() * L[index]];
                        for(int j = index; j < 5; ++j)
                            modifiable[j] = true;
                        modifiable[i] = false;
                    }
                for(int i = index; i < 5; ++i)
                {
                    int tmpIndex = base;
                    int cnt = 0;
                    for(int j = index; j < 5; ++j)
                        if(i != j)
                            tmpIndex += dices[j].getFace() * L[index + cnt++];
                    if(maximum <= expectations[tmpIndex])
                    {
                        maximum = expectations[tmpIndex];
                        for(int j = index; j < 5; ++j)
                            modifiable[j] = false;
                        modifiable[i] = true;
                    }
                }
            }
            case 3 ->
            {
                if(maximum <= expectations[base])
                    modifiable[3] = modifiable[4] = true;
                for(int i = index; i < 5; ++i)
                    if(maximum <= expectations[base + dices[i].getFace() * L[index]])
                    {
                        maximum = expectations[base + dices[i].getFace() * L[index]];
                        modifiable[i] = false;
                        modifiable[7 - i] = true;
                    }
            }
            case 4 ->
            {
                if(maximum <= expectations[base])
                    modifiable[4] = true;
            }
            default ->
            {
                ;
            }
        }

        for(int i = index; i < modifiable.length; ++i)
            dices[i].setModifiable(modifiable[i]);

        for(var dice: dices)
            dice.roll();

        return diceReply;
    }

    public static void main(String[] args)
    {
        DiceController.DiceReply diceReply = new DiceController.DiceReply();
        diceReply.setDiceArray2(List.of(new Dice(1), new Dice(1), new Dice(1), new Dice(1), new Dice(2)));
        new AiHardImpl().roll(diceReply);
        System.out.println(diceReply.getDiceArray2());
        //System.out.println(expectations[2 * L0 + 1 * L1 + 1 * L2 + 1 * L3 + 1 * L4]);
        //System.out.println(expectations[2]);
    }
}
