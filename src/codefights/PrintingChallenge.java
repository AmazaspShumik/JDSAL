package codefights;

import java.util.Arrays;

/**
 * Created by amazaspshaumyan on 12/13/16.
 */
public class PrintingChallenge {

    static String[] CodeFight(int n) {

        String[] str    = new String[n];
        String current;
        boolean mod5, mod7;
        for(int j = 1; j <= n; j++){
            current   = "";
            mod5      = j%5==0;
            mod7      = j%7==0;
            if(mod5) current = "Code";
            if(mod7) current += "Fight";
            if(!(mod5||mod7)) current = ((Integer)j).toString();
            str[j-1]  = current;
        }
        return str;
    }


    public static void main(String[] args){
        String[] x = CodeFight(15);
        for(int j=0; j< x.length; j++){
            System.out.println(x[j]);
        }

        String s = "en.wiki.org";
        String[] str = s.split(".");
        System.out.println(Arrays.toString(s.split("\\.")));
    }


}
