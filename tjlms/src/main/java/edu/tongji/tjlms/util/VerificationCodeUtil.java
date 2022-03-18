package edu.tongji.tjlms.util;

import java.util.Random;

/**
 * @author Charles Gao
 * @description verification code utility
 * @date 2021/10/11
 */
public class VerificationCodeUtil {
    /**
     * generate a 6-digit verification code
     * @return verification code
     */
    public static String generateCode()
    {
        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for(int i = 0;i < 6;i++)
        {
            code.append(r.nextInt(10));
        }
        return code.toString();
    }
}
