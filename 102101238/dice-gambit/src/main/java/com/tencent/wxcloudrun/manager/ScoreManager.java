package com.tencent.wxcloudrun.manager;


import com.tencent.wxcloudrun.model.Player;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.List;
import java.util.Optional;

/**
 * The ScoreManager class is responsible for updating the scores of the players based on their chip counts.
 * The updateScore() method calculates the score differences between the players and updates them accordingly.
 *
 * @author Lambda
 * @date 2023/10/4
 */
public class ScoreManager
{
    public static boolean updateScore(List<Player> players)
    {
        if (players == null)
            return false;

        // Find the maximum dice score
        Optional<Integer> maximum = players.stream().map(Player::getDiceScore).max(Integer::compareTo);

        if (maximum.isPresent())
        {
            try
            {
                int maxDiceScore = maximum.get();

                // Get the list of winners with the maximum dice score
                List<Player> winners = players.stream().filter(chip -> chip.getDiceScore() == maxDiceScore).toList();

                // Calculate the total rate of the winners
                int winnersTotalRate = winners.stream().mapToInt(Player::getRate).sum();
                //System.out.println(winnersTotalRate);

                for (var player : players)
                    if (player.getDiceScore() != maxDiceScore)
                    {
                        boolean flag = false;
                        int chipRate = player.getRate();
                        int rateSum = winnersTotalRate + chipRate * winners.size();
                        int chipCountDifference = Math.abs(maxDiceScore - player.getDiceScore());
                        int totalAdjustment = chipCountDifference * rateSum;
                        //System.out.println("totalAdjustment = " + totalAdjustment);
                        //System.out.println("chipCountDifference = " + chipCountDifference);


                        // Ensure total adjustment does not exceed chip count
                        if (player.getChip() < totalAdjustment)
                        {
                            totalAdjustment = player.getChip();
                            flag = true;
                        }


                        // Calculate quantity per unit
                        double quantityPerUnit = 1.0 * totalAdjustment / rateSum;
                        System.out.println(quantityPerUnit);

                        // Update chip counts for winners based on quantity per unit
                        winners.forEach(winner -> PlayerManager.updateChip(winner, player, (int) (quantityPerUnit * (winner.getRate() + chipRate))));
                        // Distribute remaining chip counts evenly among winners
                        if(flag)
                            while (player.getChip() != 0)
                                for (var winner : winners)
                                {
                                    if (player.getChip() == 0)
                                        break;
                                    PlayerManager.updateChip(winner, player, 1);
                                }
                }
                //System.out.println(players);
            }
            catch (Exception e)
            {
                return false;
            }
            return true;
        }
        else
            return false;
    }
}
