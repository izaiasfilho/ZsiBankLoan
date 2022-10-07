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
        array.add("INSERT INTO tb_transaction (id, description) VALUES (7, 'FGTS');");
        array.add("INSERT INTO tb_transaction (id, description) VALUES (6, 'CARTAO BENEFICIARIO');");
        

        array.add("CREATE TABLE `tb_state` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `uf` VARCHAR(45) NOT NULL,\n"
                + "  `descripition` VARCHAR(100) NOT NULL,\n"
                + "  `county` VARCHAR(100) NOT NULL,\n"
                + "  PRIMARY KEY (`id`));");

       
                
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (1,'AC', 'Acre', 'Rio Branco');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES  (2,'AL', 'Alagoas', 'Macieió');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (3,'AP', 'Amapá', 'Macapá');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (4,'AM', 'Amazonas', 'Manaus');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (5,'BA', 'Bahia', 'Salvador');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (6,'CE', 'Ceará', 'Fortaleza');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (7,'DF', 'Distrito Federal', 'Brasília');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (8,'ES', 'Espirito Santo', 'Vitória');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (9,'GO', 'Goiás', 'Goiânia');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (10,'MA', 'Maranhão', 'São Luiz');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (11,'MT', 'Mato Grosso', 'Cuiabá');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (12,'MS', 'Mato Grosso do Sul', 'Campo Grande');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (13,'MG', 'Minas Gerais', 'Belo Horizonte');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (14,'PA', 'Pará', Belém');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (15,'PB', 'Paraíba', 'João Pessoa');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (16,'PR', 'Paraná', 'Curitiba');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (17,'PE', 'Pernambuco', 'Recife');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (18,'PI', 'Piauí', 'Terezina');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (19,'RJ', 'Rio de Janeiro', 'Rio de Janeiro');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (20,'RN', 'Rio Grande do Norte', 'Natal');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (21,'RS', 'Rio Grande do Sul', 'Porto Alegre');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (22,'RO', 'Rondônia', 'Porto Velho');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (23,'RR', 'Roraima', 'Boa Vista');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (24,'SC', 'Santa Catarina', 'Florianópolis');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (25,'SP', 'São Paulo', 'São Paulo');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (26,'SE', 'Sergipe', 'Aracajú');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (27,'TO', 'Tocantins, 'Palma');");
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
