# PasswordStrengthCheck
#### 关于使用：
```
String passwd = "!@#$QWERTa";
CheckStrength.getPasswordLevel(passwd)

```
关于强度划分：
```
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
```

#### 关于增强：
更新配置文件中的数据密码字典




