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
 * @author FABIO COSTA
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

        array.add("CREATE TABLE IF NOT EXISTS tb_Loan_status( \n"
                + "	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,\n"
                + "	description VARCHAR(100) NOT NULL);");

        array.add("INSERT INTO tb_Loan_status (id, description) VALUES (1, 'FINALIZADO');");
        array.add("INSERT INTO tb_Loan_status (id, description) VALUES (2, 'CANCELADO');");
        array.add("INSERT INTO tb_Loan_status (id, description) VALUES (3, 'DIGITADO');");

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

        array.add("CREATE TABLE IF NOT EXISTS `tb_state` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `uf` VARCHAR(45) NOT NULL,\n"
                + "  `descripition` VARCHAR(100) NOT NULL,\n"
                + "  `county` VARCHAR(100) NOT NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (1,'AC', 'Acre', 'Rio Branco');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES  (2,'AL', 'Alagoas', 'Maceio');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (3,'AP', 'Amap??', 'Macap??');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (4,'AM', 'Amazonas', 'Manaus');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (5,'BA', 'Bahia', 'Salvador');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (6,'CE', 'Cear??', 'Fortaleza');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (7,'DF', 'Distrito Federal', 'Bras??lia');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (8,'ES', 'Espirito Santo', 'Vit??ria');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (9,'GO', 'Goi??s', 'Goi??nia');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (10,'MA', 'Maranh??o', 'S??o Luiz');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (11,'MT', 'Mato Grosso', 'Cuiab??');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (12,'MS', 'Mato Grosso do Sul', 'Campo Grande');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (13,'MG', 'Minas Gerais', 'Belo Horizonte');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (14,'PA', 'Par??', 'Bel??m');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (15,'PB', 'Para??ba', 'Jo??o Pessoa');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (16,'PR', 'Paran??', 'Curitiba');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (17,'PE', 'Pernambuco', 'Recife');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (18,'PI', 'Piau??', 'Terezina');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (19,'RJ', 'Rio de Janeiro', 'Rio de Janeiro');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (20,'RN', 'Rio Grande do Norte', 'Natal');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (21,'RS', 'Rio Grande do Sul', 'Porto Alegre');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (22,'RO', 'Rond??nia', 'Porto Velho');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (23,'RR', 'Roraima', 'Boa Vista');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (24,'SC', 'Santa Catarina', 'Florian??polis');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (25,'SP', 'S??o Paulo', 'S??o Paulo');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (26,'SE', 'Sergipe', 'Aracaj??');");
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (27,'TO', 'Tocantins', 'Palma');");

        array.add("CREATE TABLE IF NOT EXISTS `tb_city` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `name` VARCHAR(500) NOT NULL,\n"
                + "  `id_state` INT NOT NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_city` \n"
                + "ADD INDEX `id_state_fk_idx` (`id_state` ASC) VISIBLE;\n"
                + ";");
        array.add("ALTER TABLE `tb_city` \n"
                + "ADD CONSTRAINT `id_state_fk`\n"
                + "  FOREIGN KEY (`id_state`)\n"
                + "  REFERENCES `tb_state` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("CREATE TABLE IF NOT EXISTS `tb_address` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `id_city` INT NOT NULL,\n"
                + "  `streetName` VARCHAR(300) NULL,\n"
                + "  `number` VARCHAR(300) NULL,\n"
                + "  `district` VARCHAR(300) NULL,\n"
                + "  `zipCode` VARCHAR(300) NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_address` \n"
                + "ADD INDEX `id_city_fk_idx` (`id_city` ASC) VISIBLE;");
        array.add("ALTER TABLE `tb_address` \n"
                + "ADD CONSTRAINT `id_city_fk`\n"
                + "  FOREIGN KEY (`id_city`)\n"
                + "  REFERENCES `tb_city` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("CREATE TABLE IF NOT EXISTS `tb_branch` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `description` VARCHAR(300) NOT NULL,\n"
                + "  `fee` DOUBLE NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("CREATE TABLE IF NOT EXISTS `tb_plan` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `description` VARCHAR(300) NULL,\n"
                + "  PRIMARY KEY (`id`));");

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
