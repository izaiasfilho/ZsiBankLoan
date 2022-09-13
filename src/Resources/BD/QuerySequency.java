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
        
        array.add("CREATE TABLE IF NOT EXISTS tb_en_typedml( \n" +
"	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
"	description VARCHAR(100) NOT NULL);");
        
        array.add("INSERT INTO tb_en_typedml (id, description) VALUES (1, 'DELETE');");
        array.add("INSERT INTO tb_en_typedml (id, description) VALUES (2, 'UPDATE');");
        array.add("INSERT INTO tb_en_typedml (id, description) VALUES (3, 'INSERT');");

        
        array.add("CREATE TABLE IF NOT EXISTS tb_version( \n" +
"	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n" +
"	version INT,\n" +
    "	id_typedml INT NOT NULL,\n" +
"	description VARCHAR(100) NOT NULL);");
        
        array.add("ALTER TABLE tb_version ADD CONSTRAINT `id_typedml` FOREIGN KEY (`id_typedml`) REFERENCES tb_en_typedml(id)");
        
        array.add("INSERT INTO tb_version (version, id_typedml, description) VALUES (0100, 3, 'Instalação Inicial');");
     
        return array;
    }
    
    public static String registerVersionBd(){
        // verifica a versao atual --> SELECT max(version) FROM u323187073_zsibl.tb_version;
            //se nao existir = 1
            //se existir = recebe atual +1
        return "INSERT INTO `tb_version` (`version`, `description`) VALUES ('1', 'teste');";
    }
    
    public static void getVersionBd(){
        
    }
   
}
