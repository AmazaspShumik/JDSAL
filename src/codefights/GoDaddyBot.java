package codefights;


import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by amazaspshaumyan on 12/13/16.
 */
public class GoDaddyBot {

    static String[] domainType(String[] domains) {

        String[] dom   = new String[domains.length];
        for(int j = 0; j < domains.length; j++){
            String str = domains[j];
            String[] a = str.split("\\.");
            System.out.println(a[a.length-1]);
            if(a[a.length-1].equals("org")) dom[j] = "organization";
            else if(a[a.length-1].equals("info")){
                dom[j] = "information";
            } else if(a[a.length-1].equals("net")){
                dom[j] = "network";
            } else{
                dom[j] = "commercial";
            }
        }
        return dom;
    }


    static String[][] domainForwarding(String[][] redirects) {

        HashMap<String,String> redir        = new HashMap<>();
        HashMap<String,String> red          = new HashMap<>();
        HashMap<String,ArrayList<String>> r = new HashMap<>();


        for(int i = 0; i < redirects.length; i++){
            redir.put(redirects[i][0],redirects[i][1]);
        }

        for(String s : redir.keySet()){
            String curr = redir.get(s);
            while(redir.containsKey(curr)){
                curr = redir.get(curr);
            }
            red.put(s,curr);
        }

        for(String s: red.keySet()){
            String sRed = red.get(s);
            if(!r.containsKey(sRed)) r.put(sRed,new ArrayList<>());
            r.get(sRed).add(s);
        }


        String[][] out = new String[r.size()][];
        String[] finDest = new String[r.size()];
        int counter      = 0;
        for(String key: r.keySet()) finDest[counter++] = key;
        Arrays.sort(finDest);

        for(int j=0; j<finDest.length; j++){
            String key    = finDest[j];
            out[j] = new String[r.get(key).size()+1];
            int count = 1; out[j][0] = key;
            for(String str:r.get(key)) out[j][count++] = str;
            Arrays.sort(out[j]);   // sort every array lexicographically
        }

        return out;

    }


    public static void main(String[] args) {
        int x = 15, y = -4;

        System.out.println(x/y);


    }

}
