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
        
        
        array.add("CREATE TABLE IF NOT EXISTS tb_version( \n" +
"	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
"	version INT,\n" +
"	description VARCHAR(100) NOT NULL);");
        
        array.add("INSERT INTO `tb_version` (`version`, `description`) VALUES ('1', 'teste');");
        
        return array;
    }
   
}
