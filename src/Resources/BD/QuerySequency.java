/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources.BD;

import Model.Enuns.TypetransactionsSqlEnuns;
import java.util.ArrayList;

/**
 *
 * @author Izaias
 */
public class QuerySequency {

    public static ArrayList<String> listQueryVersion() {
        ArrayList<String> array = new ArrayList<>();

        array.add("CREATE TABLE IF NOT EXISTS tb_en_typetransactionssql( \n"
                + "	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                + "	description VARCHAR(100) NOT NULL);");

        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (1, 'DELETE');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (2, 'UPDATE');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (3, 'INSERT');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (4, 'ALTER');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (5, 'CREATE');");
        array.add("INSERT INTO tb_en_typetransactionssql (id, description) VALUES (6, 'DROP');");

        array.add("CREATE TABLE IF NOT EXISTS tb_version( \n"
                + "	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                + "	version INT,\n"
                + "	id_typetransactionssql INT NOT NULL,\n"
                + "	description VARCHAR(500) NOT NULL);");

        array.add("ALTER TABLE tb_version ADD CONSTRAINT `id_typetransactionssql` FOREIGN KEY (`id_typetransactionssql`) REFERENCES tb_en_typetransactionssql(id)");

        array.add("CREATE TABLE IF NOT EXISTS tb_role( \n"
                + "	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                + "	description VARCHAR(100) NOT NULL);");

        array.add("INSERT INTO tb_role (id, description) VALUES (1, 'ADMIN');");
        array.add("INSERT INTO tb_role (id, description) VALUES (2, 'GR');");
        array.add("INSERT INTO tb_role (id, description) VALUES (3, 'FUNCIONARIO');");
        array.add("INSERT INTO tb_role (id, description) VALUES (4, 'CONSULTOR');");
        array.add("INSERT INTO tb_role (id, description) VALUES (5, 'CLIENTE');");

        array.add("CREATE TABLE IF NOT EXISTS tb_transaction( \n"
                + "	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                + "	description VARCHAR(100) NOT NULL);");

        array.add("INSERT INTO tb_transaction (id, description) VALUES (1, 'PORTABILIDADE');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (2, 'REFINANCIAMENTO');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (3, 'MARGEM_LIVRE');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (4, 'CARTAO_RMC');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (5, 'EMPRESTIMO_CARTAO');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (6, 'CARTAO BENEFICIARIO');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (7, 'FGTS');");
        
        
         array.add("CREATE TABLE IF NOT EXISTS tb_State( \n"
                + "	ID INT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                + "	county VARCHAR(100) NOT NULL);");
        
        array.add("INSERT INTO tb_State (UF, county) VALUES ('AC, Acre, Rio Branco');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('AL, Alagoas, Macieió');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('AP, Amapá, Macapá');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('AM, Amazonas, Manaus');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('BA, Bahia, Salvador');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('CE, Ceará, Fortaleza');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('DF, Distrito Federal, Brasília');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('ES, Espirito Santo, Vitória');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('GO, Goiás, Goiânia');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('MA, Maranhão, São Luiz');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('MT, Mato Grosso, Cuiabá');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('MS, Mato Grosso do Sul, Campo Grande');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('MG, Minas Gerais, Belo Horizonte');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('PA, Pará, Belém');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('PB, Paraíba, João Pessoa');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('PR, Paraná, Curitiba');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('PE, Pernambuco, Recife');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('PI, Piauí, Terezina');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('RJ, Rio de Janeiro, Rio de Janeiro');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('RN, Rio Grande do Norte, Natal');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('RS, Rio Grande do Sul, Porto Alegre');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('RO, Rondônia, Porto Velho');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('RR, Roraima, Boa Vista');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('SC, Santa Catarina, Florianópolis');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('SP, São Paulo, São Paulo');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('SE, Sergipe, Aracajú');");
        array.add("INSERT INTO tb_State (UF, county) VALUES ('TO, Tocantins, Palma');");
        
        
        return array;

    }

    public static String registerVersionBd(int version, TypetransactionsSqlEnuns typetransactionsSql, String descryption) {
        return "INSERT INTO tb_version (version, id_typetransactionssql, description) "
                + "VALUES "
                + "(" + version + ","
                + "" + "'" + typetransactionsSql.getId() + "'" + ","
                + "" + "'" + "  " + "');";
    }

}
