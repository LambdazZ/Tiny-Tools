package com.tencent.wxcloudrun.model;

import com.tencent.wxcloudrun.controller.DiceController;
import org.apache.ibatis.javassist.compiler.ast.Pair;

import java.util.List;

/**
 * @author Lambda
 * @date 2023/10/9
 */
public interface Ai
{
    DiceController.DiceReply roll(DiceController.DiceReply diceReply);
}
