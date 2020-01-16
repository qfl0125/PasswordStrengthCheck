package com.password;

public class CheckStrength {

    public enum LEVEL{
        EASY,MIDIUM,STRONG,VERY_STRONG, EXTREMELY_STRONG;
    }
    private static final int NUM = 1;
    private static final int SMALL_LETTER = 2;
    private static final int CAPITAL_LETTER = 3;
    private static final int OTHER_CHAR = 4;
    //private static final String[] DICTIONARY = new String[]{"password", "abc123", "iloveyou", "adobe123", "123123", "sunshine", "1314520", "a1b2c3", "123qwe", "aaa111", "qweasd", "admin", "passwd"};
    private static final String[] DICTIONARY =StringUtils.dictionary();

    /**
     * 判断字符
     * @param c
     * @return 数字1，大写3，小写2,其他特殊字符4
     */
    private static int checkCharacterType(char c) {
        return c >= 48 && c <= 57?1:(c >= 65 && c <= 90?3:(c >= 97 && c <= 122?2:4));
    }

    private static int countLetter(String passwd, int type) {
        int count = 0;
        if(null != passwd && passwd.length() > 0) {
            char[] arr$ = passwd.toCharArray();
            int len$ = arr$.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                char c = arr$[i$];
                if(checkCharacterType(c) == type) {
                    ++count;
                }
            }
        }

        return count;
    }

    public static int checkPasswordStrength(String passwd) {
        if(StringUtils.equalsNull(passwd)) {
            throw new IllegalArgumentException("password is empty");
        } else {
            int len = passwd.length();
            int level = 0;

            if(countLetter(passwd, 1) > 0) {
                ++level;//含有数字
            }

            if(countLetter(passwd, 2) > 0) {
                ++level;//含有小写字母
            }

            if(len > 4 && countLetter(passwd, 3) > 0) {
                ++level;//含有大写字母
            }

            if(len > 6 && countLetter(passwd, 4) > 0) {
                ++level;
            }

            if(len > 4 && countLetter(passwd, 1) > 0 && countLetter(passwd, 2) > 0 ||//数字和小写
                    countLetter(passwd, 1) > 0 && countLetter(passwd, 3) > 0 ||//数字和大写
                    countLetter(passwd, 1) > 0 && countLetter(passwd, 4) > 0 ||//数字和特殊字符
                    countLetter(passwd, 2) > 0 && countLetter(passwd, 3) > 0 ||//小写和大写
                    countLetter(passwd, 2) > 0 && countLetter(passwd, 4) > 0 ||//小写和特殊字符
                    countLetter(passwd, 3) > 0 && countLetter(passwd, 4) > 0) {//大写和特殊字符
                ++level;
            }

            if(len > 6 && countLetter(passwd, 1) > 0 && countLetter(passwd, 2) > 0 && countLetter(passwd, 3) > 0 ||
                    countLetter(passwd, 1) > 0 && countLetter(passwd, 2) > 0 && countLetter(passwd, 4) > 0 ||
                    countLetter(passwd, 1) > 0 && countLetter(passwd, 3) > 0 && countLetter(passwd, 4) > 0 ||
                    countLetter(passwd, 2) > 0 && countLetter(passwd, 3) > 0 && countLetter(passwd, 4) > 0) {
                ++level;
            }

            if(len > 8 && countLetter(passwd, 1) > 0 && countLetter(passwd, 2) > 0 && countLetter(passwd, 3) > 0 && countLetter(passwd, 4) > 0) {
                ++level;
            }

            if(len > 6 && countLetter(passwd, 1) >= 3 && countLetter(passwd, 2) >= 3 || countLetter(passwd, 1) >= 3 && countLetter(passwd, 3) >= 3 || countLetter(passwd, 1) >= 3 && countLetter(passwd, 4) >= 2 || countLetter(passwd, 2) >= 3 && countLetter(passwd, 3) >= 3 || countLetter(passwd, 2) >= 3 && countLetter(passwd, 4) >= 2 || countLetter(passwd, 3) >= 3 && countLetter(passwd, 4) >= 2) {
                ++level;
            }

            if(len > 8 && countLetter(passwd, 1) >= 2 && countLetter(passwd, 2) >= 2 && countLetter(passwd, 3) >= 2 || countLetter(passwd, 1) >= 2 && countLetter(passwd, 2) >= 2 && countLetter(passwd, 4) >= 2 || countLetter(passwd, 1) >= 2 && countLetter(passwd, 3) >= 2 && countLetter(passwd, 4) >= 2 || countLetter(passwd, 2) >= 2 && countLetter(passwd, 3) >= 2 && countLetter(passwd, 4) >= 2) {
                ++level;
            }

            if(len > 10 && countLetter(passwd, 1) >= 2 && countLetter(passwd, 2) >= 2 && countLetter(passwd, 3) >= 2 && countLetter(passwd, 4) >= 2) {
                ++level;
            }

            if(countLetter(passwd, 4) >= 3) {
                ++level;
            }

            if(countLetter(passwd, 4) >= 6) {
                ++level;
            }

            if(len > 12) {
                ++level;
                if(len >= 16) {
                    ++level;
                }
            }

            if("abcdefghijklmnopqrstuvwxyz".indexOf(passwd) > 0 || "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(passwd) > 0) {
                --level;
            }
            if("!@#$%^&*()_+{}:\"<>?".indexOf(passwd) > 0 || "?><\":|}{+_)(*&^%$#@!".indexOf(passwd) > 0){
                --level;
            }

            if("qwertyuiop".indexOf(passwd) > 0 || "asdfghjkl".indexOf(passwd) > 0 || "zxcvbnm".indexOf(passwd) > 0) {
                --level;
            }

            if(StringUtils.isNumeric(passwd) && ("01234567890".indexOf(passwd) > 0 || "09876543210".indexOf(passwd) > 0)) {
                --level;
            }

            if(countLetter(passwd, 1) == len || countLetter(passwd, 2) == len || countLetter(passwd, 3) == len) {
                --level;
            }

            String i;
            String size;
            if(len % 2 == 0) {
                i = passwd.substring(0, len / 2);
                size = passwd.substring(len / 2);
                if(i.equals(size)) {
                    --level;
                }

                if(StringUtils.isCharEqual(i) && StringUtils.isCharEqual(size)) {
                    --level;
                }
            }

            if(len % 3 == 0) {
                i = passwd.substring(0, len / 3);
                size = passwd.substring(len / 3, len / 3 * 2);
                String month = passwd.substring(len / 3 * 2);
                if(i.equals(size) && size.equals(month)) {
                    --level;
                }
            }

            int var;
            if(StringUtils.isNumeric(passwd) && len >= 6) {
                var = 0;
                if(len == 8 || len == 6) {
                    var = Integer.parseInt(passwd.substring(0, len - 4));
                }

                int str = StringUtils.sizeOfInt(var);
                int end = Integer.parseInt(passwd.substring(str, str + 2));
                int day = Integer.parseInt(passwd.substring(end + 2, len));
                if(var >= 1950 && var < 2050 && end >= 1 && end <= 12 && day >= 1 && day <= 31) {
                    --level;
                }
            }

            if(null != DICTIONARY && DICTIONARY.length > 0) {
                for(var = 0; var < DICTIONARY.length; ++var) {
                    if(passwd.equals(DICTIONARY[var]) || DICTIONARY[var].indexOf(passwd) >= 0) {
                        --level;
                        break;
                    }
                }
            }

            if(len <= 6) {
                --level;
                if(len <= 4) {
                    --level;
                    if(len <= 3) {
                        level = 0;
                    }
                }
            }

            if(StringUtils.isCharEqual(passwd)) {
                level = 0;
            }

            if(level < 0) {
                level = 0;
            }

            return level;
        }
    }

    public static LEVEL getPasswordLevel(String passwd) {
        int level = checkPasswordStrength(passwd);
        switch(level) {
            case 0:
            case 1:
            case 2:
            case 3:
                return LEVEL.EASY;
            case 4:
            case 5:
            case 6:
                return LEVEL.MIDIUM;
            case 7:
            case 8:
            case 9:
                return LEVEL.STRONG;
            case 10:
            case 11:
            case 12:
                return LEVEL.VERY_STRONG;
            default:
                return LEVEL.EXTREMELY_STRONG;
        }
    }
}
