package data_structures;


import java.util.*;
import java.util.Queue;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Created by amazaspshaumyan on 12/26/16.
 */
public class Person{

    public static String smartAssigning(String[] names, boolean[] statuses, int[] projects, int[] tasks) {
        Queue<Worker> q = new ArrayDeque<>();
        for(int j = 0; j<statuses.length; j++){
            if(statuses[j]==false) q.add(new Worker(names[j],tasks[j],projects[j]));
        }
        Worker min = q.poll();
        while(!q.isEmpty()){
            if(min.compareTo(q.peek())>0) min = q.poll();
            else                          q.poll();
        }
        return min.name;

    }


    public static class Worker implements Comparable<Worker>{

        String name;
        int tasks;
        int projects;

        public Worker(String name, int tasks, int projects){
            this.name = name; this.tasks = tasks; this.projects = projects;
        }

        public int compareTo(Worker b){
            if(tasks < b.tasks)      return -1;
            else if(tasks > b.tasks) return 1;
            else{
                if(projects<b.projects)      return -1;
                else if(projects>b.projects) return 1;
                else                           return 0;
            }
        }
    }


    public static String[] recurringTask(String firstDate, int k, String[] daysOfTheWeek, int n){
        String[] ans           = new String[n];
        SimpleDateFormat sdf   = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal           = new GregorianCalendar();
        try {
            cal.setTime(sdf.parse(firstDate));
        } catch (ParseException e){}


        // handle days of the week
        Map<String,Integer> hm = constructWeekDays();
        Integer[] days         = new Integer[daysOfTheWeek.length];
        for(int j=0; j<days.length; j++) days[j] = getDayOfWeek(hm,daysOfTheWeek[j]);
        Arrays.sort(days);
        int firstDay = cal.get(Calendar.DAY_OF_WEEK);
        int delta    = 0, startDayIndex = 0;

        Set<Integer> s = new HashSet<>();
        for(int j=0; j<days.length; j++) s.add(days[j]);
        while(!s.contains(cal.get(Calendar.DAY_OF_WEEK))){
            cal.add(Calendar.DAY_OF_YEAR,1);
        }
        startDayIndex = Arrays.binarySearch(days,cal.get(Calendar.DAY_OF_WEEK));

        System.out.println(Arrays.toString(days));



        int j = 0;

        while(j<n){
            int count = 0;
            Calendar prevCal = new GregorianCalendar();
            prevCal.setTime(cal.getTime());
            while(j<n && count<days.length){
                ans[j] = sdf.format(cal.getTime());
                delta = days[(startDayIndex+j+1)%days.length]-days[(startDayIndex+j)%days.length]+1;
                System.out.println(delta);
                if( delta <= 0 ) delta = 7 - days[days.length-1]+days[0];
                System.out.println(sdf.format(cal.getTime()));
                cal.add(Calendar.DATE,delta);
                j++;
                count++;
            }
            prevCal.add(Calendar.DATE,7*k);
            cal = prevCal;
        }
        return ans;
    }



    public static Map<String,Integer> constructWeekDays(){
        Map<String,Integer> hm = new HashMap<>();
        hm.put("Monday",Calendar.MONDAY);
        hm.put("Tuesday",Calendar.TUESDAY);
        hm.put("Wednesday",Calendar.WEDNESDAY);
        hm.put("Thursday",Calendar.THURSDAY);
        hm.put("Friday",Calendar.FRIDAY);
        hm.put("Saturday",Calendar.SATURDAY);
        hm.put("Sunday",Calendar.SUNDAY);
        return hm;
    }

    public static int getDayOfWeek(Map<String,Integer> hm, String dw){
        return hm.get(dw);
    }





    public static void main(String[] args){
        String firstDate = "01/02/2100";
        int k            =  4;
        String[] daysOfTheWeek = {"Sunday","Monday"};
        int n            =  4;
        System.out.println(Arrays.toString(recurringTask(firstDate,k,daysOfTheWeek,n)));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = new GregorianCalendar();
        try{
            cal.setTime(sdf.parse(firstDate));
        } catch(ParseException e){

        }

        List<Integer> l = new ArrayList<>();
        l.add(2); l.add(3);l.add(4);
        int[] x = new int[3];
        for(int j =0; j<l.size(); j++)  x[j] = l.get(j);

        /*

        //String dt = "1970-12-31";  // Start date
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dt = "31/12/1970";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        try{
            c.setTime(sdf.parse(dt));
        } catch(ParseException e){}
        c.add(Calendar.DATE, 1);  // number of days to add
        dt = sdf.format(c.getTime());  // dt is now the new date
        System.out.println(dt);
        */


    }



}
