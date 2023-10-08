package com.tencent.wxcloudrun.manager;

import com.tencent.wxcloudrun.model.Player;

/**
 * @author Lambda
 * @date 2023/10/5
 */
public class PlayerManager
{
    public static boolean updateChip(Player player1, Player player2, int chipCountAdjustment)
    {
        if (player1 == null || player2 == null)
            return false;
        try
        {
            if (player2.getChip() < chipCountAdjustment)
                chipCountAdjustment = player2.getChip();
            player1.setChip(player1.getChip() + chipCountAdjustment);
            player2.setChip(player2.getChip() - chipCountAdjustment);
        }
        catch (Exception e)
        {
            return false;
        }
        return true;
    }
}
