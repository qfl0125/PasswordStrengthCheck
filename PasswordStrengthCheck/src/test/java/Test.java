import com.password.CheckStrength;
import com.password.StringUtils;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String passwd = "!@#$QWERTa";
      //  for (int i = 0; i <100 ; i++) {
            Long start=System.currentTimeMillis();
            System.out.println(CheckStrength.getPasswordLevel(passwd));
            System.out.println("dictionary:"+Arrays.toString(StringUtils.dictionary()));
            Long end=System.currentTimeMillis();
            System.out.println("main---start------9Line:"+(end-start));
     //   }
    }
}
