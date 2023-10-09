package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.manager.DiceManager;
import com.tencent.wxcloudrun.manager.ScoreManager;
import com.tencent.wxcloudrun.model.Ai;
import com.tencent.wxcloudrun.model.Dice;
import com.tencent.wxcloudrun.model.Player;
import com.tencent.wxcloudrun.model.impl.AiEasyImpl;
import com.tencent.wxcloudrun.model.impl.AiHardImpl;
import com.tencent.wxcloudrun.model.impl.AiMediumImpl;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lambda
 * @date 2023/10/4
 */

@RestController
public class DiceController
{
    @GetMapping(value = "/dice/local/init")
    List<Player> getPlayersL()
    {
        return getPlayers(2);
    }

    @GetMapping(value = "/dice/robot/init")
    List<Player> getPlayersR()
    {
        return getPlayers(2);
    }

    @Getter
    public static class DiceRequest
    {
        private List<Dice> diceArray1;
        private List<Dice> diceArray2;
        private Integer chip1;
        private Integer chip2;
        private Integer rate1;
        private Integer rate2;
        private String token1;
        private String token2;
        private Integer robotMode;

        // Getters and setters
        // ...
    }

    @Data
    public static class DiceReply
    {
        private List<Dice> diceArray1;
        private List<Dice> diceArray2;
        private Integer chip1;
        private Integer chip2;
        private Integer rate1;
        private Integer rate2;
        private String token1;
        private String token2;
        private Integer rateAdjust;

        // Getters and setters
        // ...
    }

    @PostMapping("/dice/local/roll")
    List<List<Dice>> receiveDiceArraysL(@RequestBody DiceRequest diceRequest)
    {
        List<Dice> diceArray1 = diceRequest.getDiceArray1();
        List<Dice> diceArray2 = diceRequest.getDiceArray2();

        for (Dice dice : diceArray1)
            dice.roll();

        for (Dice dice : diceArray2)
            dice.roll();

        List<List<Dice>> combinedDiceArrays = new ArrayList<>();
        combinedDiceArrays.add(diceArray1);
        combinedDiceArrays.add(diceArray2);

        return combinedDiceArrays;
    }

    @PostMapping("/dice/robot/roll")
    DiceReply receiveDiceArraysR(@RequestBody DiceRequest diceRequest)
    {
        List<Dice> diceArray1 = diceRequest.getDiceArray1();
        List<Dice> diceArray2 = diceRequest.getDiceArray2();

        for (Dice dice : diceArray1)
            dice.roll();

        DiceReply diceReply = new DiceReply();
        diceReply.setDiceArray1(diceArray1);
        diceReply.setDiceArray2(diceArray2);
        Ai ai = switch (diceRequest.getRobotMode())
        {
            case 1 -> new AiEasyImpl();
            case 2 -> new AiMediumImpl();
            case 4 -> new AiHardImpl();
            default -> throw new IllegalArgumentException("Invalid robot mode: " + diceRequest.getRobotMode());
        };
        diceReply = ai.roll(diceReply);

        return diceReply;
    }

    @PostMapping(value = "/dice/pattern")
    List<String> getPattern(@RequestBody DiceRequest diceRequest)
    {
        List<String> res = new ArrayList<>();
        List<Dice> diceArray1 = diceRequest.getDiceArray1();
        List<Dice> diceArray2 = diceRequest.getDiceArray2();
        Dice[] diceArrays = new Dice[5]; // 创建长度为 5 的 Dice 数组
        diceArray1.toArray(diceArrays);
        res.add(DiceManager.getPattern(diceArrays));
        diceArray2.toArray(diceArrays);
        res.add(DiceManager.getPattern(diceArrays));
        return res;
    }

    @PostMapping(value = "/dice/updateChip")
    List<Integer> updateChip(@RequestBody DiceRequest diceRequest)
    {
        List<Integer> res = new ArrayList<>();
        List<Dice> diceArray1 = diceRequest.getDiceArray1();
        List<Dice> diceArray2 = diceRequest.getDiceArray2();
        Dice[] diceArrays = new Dice[5]; // 创建长度为 5 的 Dice 数组
        diceArray1.toArray(diceArrays);
        Player player1 = new Player(diceArrays, diceRequest.getChip1(), diceRequest.getRate1());
        diceArray2.toArray(diceArrays);
        Player player2 = new Player(diceArrays, diceRequest.getChip2(), diceRequest.getRate1());
        System.out.println(List.of(player1, player2).toString());
        ScoreManager.updateScore(List.of(player1, player2));
        res.add(player1.getChip());
        res.add(player2.getChip());
        return res;
    }




    public static List<Player> getPlayers(int num)
    {
        List<Player> players = new ArrayList<>();
        while(num-- != 0)
            players.add(new Player());
        return players;
    }
}
