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

    private static boolean decider(double x, double y, ArrayList result) {
        Object delay_press=result.get(0);
        String str = delay_press.toString();
        double press_delay = Double.parseDouble(str);
        Object delay_change=result.get(1);
        String str1 = delay_change.toString();
        double change_delay = Double.parseDouble(str1);         
        
        if (Math.abs(press_delay-x)<10 ){
            if(Math.abs(change_delay-y)<20){
                return true;
            }
        }
       
        System.out.println("delay_press "+ press_delay+" "+ x);
        System.out.println("delay_change " + change_delay + " "+ y);
        return false;
    }
    
    public double press_time;
    public double release_time;
    public double full_time;
    public static ArrayList timers1=new ArrayList();
    public static ArrayList timers2=new ArrayList();
    public static ArrayList members=new ArrayList();
    public static ArrayList registered_usernames=new ArrayList();
    public static ArrayList users=new ArrayList();
    public static String user_name;
    public static boolean stateUser=false;
    public static InsertData app;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    SQLiteJDBCDriverConnection.createNewDatabase("test.db");
                    SQLiteJDBCDriverConnection.createNewTable();
                    app = new InsertData();
                    app.selectAll();
                    new StatupPage().setVisible(true);
                }
            });
     }
    void press(KeyEvent evt) {
       
        press_time= System.currentTimeMillis();
        timers2.add(press_time-release_time);
        
    }
    void declare(){
        timers1=new ArrayList();
        timers2=new ArrayList();
        
    }
    void release(KeyEvent evt) {
        release_time= System.currentTimeMillis();
        timers1.add(release_time-press_time);
        
        //System.out.println("Release time "+timers1);
    }
    static boolean calculate(String form) {
        
        double pressingtime = 0;
        double changingtime = 0;
        
        for (int j=2; j<timers1.size();j++){
            pressingtime+=(double) timers1.get(j);
        }
        double parameter1=  pressingtime/(timers1.size()-2);
        System.out.println("Release Average = " + parameter1);
        
        for (int i=2; i<timers2.size();i++){
            changingtime+=(double) timers2.get(i);
        }
        double parameter2 = changingtime/(timers2.size()-2);
        System.out.println("Changing Average = "+ parameter2);
        
        ArrayList registering= new ArrayList();

        
        if (form=="register"){
            users.add(registering);
            timers1=new ArrayList();
            timers2=new ArrayList();
            ArrayList check_avalability = app.getUser(user_name);
            if (check_avalability.isEmpty()){
                app.insert(user_name, parameter1,parameter2);
                return true;
            }
            else{
                System.out.println("user avalable");
                return false;
            }
            
        }else if(form=="login"){
            ArrayList result = app.getUser(user_name);
            if(!result.isEmpty()){
                System.out.println("there is a user");
                double avg_pressingtime = parameter1;
                double avg_changingtime = parameter2;
               
                timers1=new ArrayList();
                timers2=new ArrayList();
               
                stateUser=true;
                return decider(avg_pressingtime,avg_changingtime,result);  
            }else{
                System.out.println("there is no user");
                timers1=new ArrayList();
                timers2=new ArrayList();
                System.out.println("Users = "+users);
                return false;
            }    
        }    
        return false;
    }
    
    static void setter(String name){
        user_name=name;
        System.out.println(user_name);
    }
}

