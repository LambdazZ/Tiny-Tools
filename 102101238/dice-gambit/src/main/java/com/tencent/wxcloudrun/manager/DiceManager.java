package com.tencent.wxcloudrun.manager;


import com.tencent.wxcloudrun.model.Dice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * This class evaluates the score for a given set of dice based on specific rules.
 * It provides a method to calculate the score based on the dice rules.
 *
 * @author Lambda
 * @date 2023/10/4
 */
public class DiceManager
{
    public static String getPattern(Dice[] dices)
    {
        if (dices == null || dices.length != 5)
            return "参数错误";

        // Array to store the number of occurrences of each face value
        int[] faces = new int[7];

        // Check for specific combinations and add corresponding bonus points to the basic score
        for (var dice : dices)
            ++faces[dice.getFace() & 7];

        // Five of a kind: all faces are the same
        if (Arrays.stream(faces).filter(cnt -> cnt == 5).count() == 1L)
            return "五连牌";

        // Four of a kind: four faces are the same
        if (Arrays.stream(faces).filter(cnt -> cnt == 4).count() == 1L)
            return "四连牌";

        // Three of a kind and a pair
        long cnt2 = Arrays.stream(faces).filter(cnt -> cnt == 2).count();
        long cnt3 = Arrays.stream(faces).filter(cnt -> cnt == 3).count();
        if (cnt3 == 1 && cnt2 == 1)
            return "葫芦牌";

        // Two pairs or three of a kind
        if (cnt2 == 2)
            return "双对牌";

        if (cnt3 == 1)
            return "三连牌";

        // Sequential combination: 1, 2, 3, 4, or 2, 3, 4, 5, or 3, 4, 5, 6
        boolean b1 = Arrays.stream(faces, 1, 5).allMatch(cnt -> cnt >= 1);
        boolean b2 = Arrays.stream(faces, 2, 6).allMatch(cnt -> cnt >= 1);
        boolean b3 = Arrays.stream(faces, 3, 7).allMatch(cnt -> cnt >= 1);

        if (b2 && (b1 || b3))
            return "大顺子";

        // Any one pair, any two pairs, or any three of a kind
        if (b1 || b2 || b3)
            return "小顺子";

        // No special combinations, return the basic score
        return "普通牌";
    }

    public static int getScore(Dice[] dices)
    {
        String pattern = getPattern(dices);
        if (dices == null || dices.length != 5)
            return -1;

        // Array to store the number of occurrences of each face value
        int[] faces = new int[7];

        // Count the occurrences of each face value in the dice set
        for (var dice : dices)
            ++faces[dice.getFace() & 7];

        // Calculate the basic score by multiplying the face value with its occurrence and summing them up
        int basic = IntStream.range(1, 7).map(i -> faces[i] * i).sum();

        // Check for specific combinations and add corresponding bonus points to the basic score

        switch (pattern)
        {
            case "大顺子" -> {return 60 + basic;}
            case "小顺子" -> {return 30 + basic;}
            case "五连牌" -> {return 100 + basic;}
            case "四连牌" -> {return 40 + basic;}
            case "葫芦牌" -> {return 20 + basic;}
            case "三连牌", "双对牌" -> {return 10 + basic;}
            default -> {return basic;}
        }
    }

    public static int getScore(Dice[] dices, String pattern)
    {
        if (dices == null || dices.length != 5)
            return -1;

        // Array to store the number of occurrences of each face value
        int[] faces = new int[7];

        // Count the occurrences of each face value in the dice set
        for (var dice : dices)
            ++faces[dice.getFace() & 7];

        // Calculate the basic score by multiplying the face value with its occurrence and summing them up
        int basic = IntStream.range(1, 7).map(i -> faces[i] * i).sum();

        // Check for specific combinations and add corresponding bonus points to the basic score

        switch (pattern)
        {
            case "大顺子" -> {return 60 + basic;}
            case "小顺子" -> {return 30 + basic;}
            case "五连牌" -> {return 100 + basic;}
            case "四连牌" -> {return 40 + basic;}
            case "葫芦牌" -> {return 20 + basic;}
            case "三连牌", "双对牌" -> {return 10 + basic;}
            default -> {return basic;}
        }
    }

    public static int getScore(List<Dice> diceArray)
    {
        Dice[] dices = new Dice[5];
        for(int i = 0; i < 5; ++i)
            dices[i] = diceArray.get(i);
        return getScore(dices);
    }
}