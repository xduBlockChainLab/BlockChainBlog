package com.bc208.blog.utils;

import java.util.Random;

/**
 * @author QingheLi
 */
public class RandomCaptcha {
    public String createCode() {
        String code = "";
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int type = rand.nextInt(3);//0 1 2
            switch (type) {
                case 0:
                    char ch = (char) (rand.nextInt(26) + 65);
                    code += ch;
                    break;
                case 1:
                    char ch1 = (char) (rand.nextInt(26) + 97);
                    code += ch1;
                    break;
                case 2:
                    code += rand.nextInt(10);
                    break;
            }
        }
        return code;
    }
}
