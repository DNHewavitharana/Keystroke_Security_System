/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package security_system;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Dilshan
 */
public class Security_System {

    private static boolean decider(String user_name, double x, double y, Object get) {
        //get.getClass()
        System.out.println("object of"+get);
        return true;
    }

 
    
    public double press_time;
    public double release_time;
    public double full_time;
    public static ArrayList timers1=new ArrayList();
    public static ArrayList timers2=new ArrayList();
    public static ArrayList members=new ArrayList();
    public static ArrayList registered_usernames=new ArrayList();
    public static ArrayList<ArrayList> users=new ArrayList<ArrayList>();
    public static String user_name;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new StatupPage().setVisible(true);
                }
            });
        
     }
    void press(KeyEvent evt) {
        press_time= System.currentTimeMillis();
        timers2.add(press_time-release_time);
        
        System.out.println("GapTime "+timers2);
    }
    void declare(){
        timers1=new ArrayList();
        timers2=new ArrayList();
    }
    void release(KeyEvent evt) {
        release_time= System.currentTimeMillis();
        timers1.add(release_time-press_time);
        
        System.out.println("Release "+timers1);
    }
    static boolean calculate(int x, String form) {
        double pressingtime = 0;
        double changingtime = 0;
        System.out.println("hvhsjhs"+timers1.size());
        for (int j=2; j<timers1.size();j++){
            pressingtime+=(double) timers1.get(j);
        }
        System.out.println("Release = " + pressingtime/(timers1.size()-2));
        
        for (int i=2; i<timers2.size();i++){
            changingtime+=(double) timers2.get(i);
            System.out.println("sds"+(double) timers2.get(i));
        }
        System.out.println("Changing Average = "+changingtime/(timers2.size()-2));
        
        ArrayList registering= new ArrayList();

        
        if (form=="register"){
            registering.add(user_name);
            registered_usernames.add(user_name);
            users.add(registering);
            System.out.println("Users = "+users);
            registering.add(pressingtime/timers1.size()-2);
            registering.add(changingtime/(timers2.size()-2));
            timers1=new ArrayList();
            timers2=new ArrayList();
             System.out.println("Users = "+users);
            return true;
        }else if(form=="login"){
            registering.add(user_name);
            for (int m=0; m<users.size();m++){
                if (registering.get(0).equals(registered_usernames.get(m))){
                    System.out.println("there is a user");
                    double avg_pressingtime = pressingtime/timers1.size()-2;
                    double avg_changingtime = changingtime/timers2.size()-2;
                    registering.add(avg_pressingtime);
                    registering.add(avg_changingtime);
                    timers1=new ArrayList();
                    timers2=new ArrayList();
                    System.out.println("Users = "+users);
                    return decider(user_name,avg_pressingtime,avg_changingtime,users.get(m));
                    
                }else{
                    System.out.println("there is no user");
                    registering.add(pressingtime/timers1.size()-2);
                    registering.add(changingtime/(timers2.size()-2));
                    timers1=new ArrayList();
                    timers2=new ArrayList();
                    System.out.println("Users = "+users);
                    return false;
                    }
                }
            }
            return false;
        }
    
    static void setter(String name){
        user_name=name;
        System.out.println(user_name);
    }
}
