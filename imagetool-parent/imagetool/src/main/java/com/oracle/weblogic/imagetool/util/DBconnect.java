package com.oracle.weblogic.imagetool.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.regex.Pattern;


public class DBconnect {

	public static Connection connect() {
        String driverName=Constant.driverName;  
        String url=Constant.url;
        String user=Constant.user;  
        String password=Constant.password; 
         
        Connection conn = null;  
        
        try {    
            Class.forName(driverName);  
              
 
            conn = DriverManager.getConnection(url, user, password); 
        }catch (Exception e) {  
            e.printStackTrace();  
        }     
       return  conn;    
	}

 private static String reg = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|"
             + "(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";

 private static Pattern sqlPattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
 
 public static boolean isValid(String str)
    {
        if (sqlPattern.matcher(str).find())
        {
            return false;
        }
        return true;

    }
	

}
