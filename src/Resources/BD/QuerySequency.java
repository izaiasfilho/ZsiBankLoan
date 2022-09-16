/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources.BD;

import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public class QuerySequency {
    
   
    public static ArrayList<String> listQueryVersion(){
        ArrayList<String> array = new ArrayList<>();
        
        array.add("CREATE TABLE IF NOT EXISTS tb_en_typetransactionssql( \n" +
"	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
"	description VARCHAR(100) NOT NULL);");
        
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (1, 'DELETE');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (2, 'UPDATE');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (3, 'INSERT');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (4, 'ALTER');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (5, 'CREATER');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (6, 'DROP');");
        
        array.add("CREATE TABLE IF NOT EXISTS tb_version( \n" +
"	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
"	version INT,\n" +
    "	typetransactionssql VARCHAR(100) NOT NULL,\n" +
"	description VARCHAR(500) NOT NULL);");
        
        
        
        return array;
    }
    
    public static String registerVersionBd(int ver, String descryption ){
         return "INSERT INTO tb_version (version, typetransactionssql, description) VALUES ("+ver+","+"'"+descryption.substring(0, 7)+"'"+","+"'"+descryption.substring(0, 50)+"');";
    }
   
}
