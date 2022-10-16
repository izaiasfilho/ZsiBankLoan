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
        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (14,'PA', 'Pará', 'Belém');");
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

        array.add("INSERT INTO `tb_state` (`id`, `uf`, `descripition`, `county`) VALUES (28,'DF', 'lula', 'Palma');");

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

        array.add("CREATE TABLE IF NOT EXISTS `tb_institution` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `description` VARCHAR(300) NOT NULL,\n"
                + "  `fee` DOUBLE NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("CREATE TABLE IF NOT EXISTS `tb_plan` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `description` VARCHAR(300) NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("CREATE TABLE IF NOT EXISTS `tb_genre` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `description` VARCHAR(300) NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("CREATE TABLE IF NOT EXISTS `tb_user` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `physicalPersonRegistration` VARCHAR(300) NOT NULL,\n"
                + "  `Registration` VARCHAR(300) NULL,\n"
                + "  `name` VARCHAR(300) NULL,\n"
                + "  `spouse` VARCHAR(300) NULL,\n"
                + "  `issuingBody` VARCHAR(300) NULL,\n"
                + "  `issuer` VARCHAR(300) NULL,\n"
                + "  `birthDate` VARCHAR(300) NULL,\n"
                + "  `naturalness` VARCHAR(300) NULL,\n"
                + "  `email` VARCHAR(300) NULL,\n"
                + "  `dad` VARCHAR(300) NULL,\n"
                + "  `mother` VARCHAR(300) NULL,\n"
                + "  `id_address` int NOT NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_user` \n"
                + "ADD INDEX `id_address_fk_idx` (`id_address` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_user` \n"
                + "ADD CONSTRAINT `id_address_fk`\n"
                + "  FOREIGN KEY (`id_address`)\n"
                + "  REFERENCES `tb_address` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("CREATE TABLE IF NOT EXISTS `tb_phone` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `numberType` VARCHAR(300) NULL,\n"
                + "  `id_user` INT NOT NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_phone` \n"
                + "ADD INDEX `id_user_fk_idx` (`id_user` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_phone` \n"
                + "ADD CONSTRAINT `id_user_fk`\n"
                + "  FOREIGN KEY (`id_user`)\n"
                + "  REFERENCES `tb_user` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("CREATE TABLE IF NOT EXISTS `tb_user_role` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `id_user` INT NOT NULL,\n"
                + "  `id_role` INT NOT NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_user_role` \n"
                + "ADD INDEX `id_user_role_fk_idx` (`id_user` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_user_role` \n"
                + "ADD CONSTRAINT `id_userEn_fk`\n"
                + "  FOREIGN KEY (`id_user`)\n"
                + "  REFERENCES `tb_user` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("ALTER TABLE `tb_user_role` \n"
                + "ADD INDEX `idr_user_role_fk_idx` (`id_role` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_user_role` \n"
                + "ADD CONSTRAINT `idr_user_role_fk`\n"
                + "  FOREIGN KEY (`id_role`)\n"
                + "  REFERENCES `tb_role` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("CREATE TABLE `tb_user_institution` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `id_user` INT NOT NULL,\n"
                + "  `id_institution` INT NOT NULL,\n"
                + "  `agency` VARCHAR(200) NULL,\n"
                + "  `account_number` VARCHAR(200) NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_user_institution` \n"
                + "ADD INDEX `id_userB_fk_idx` (`id_user` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_user_institution` \n"
                + "ADD CONSTRAINT `id_userB_fk`\n"
                + "  FOREIGN KEY (`id_user`)\n"
                + "  REFERENCES `tb_user` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("ALTER TABLE `tb_user_institution` \n"
                + "ADD INDEX `id_institutionB_fk_idx` (`id_institution` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_user_institution` \n"
                + "ADD CONSTRAINT `id_institution_fk`\n"
                + "  FOREIGN KEY (`id_institution`)\n"
                + "  REFERENCES `tb_institution` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

        array.add("CREATE TABLE `tb_loan` (\n"
                + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                + "  `id_user` INT NOT NULL,\n"
                + "  `id_institution` INT NOT NULL,\n"
                + "  `id_plan` INT NOT NULL,\n"
                + "  `id_transaction` INT NOT NULL,\n"
                + "  `id_user_institution` INT NOT NULL,\n"
                + "  `id_loan_status` INT NOT NULL,\n"
                + "  `broker` VARCHAR(405) NULL,\n"
                + "  `issueDate` DATETIME NULL,\n"
                + "  `commission` VARCHAR(400) NULL,\n"
                + "  `grossValue` DOUBLE NULL,\n"
                + "  `netValue` DOUBLE NULL,\n"
                + "  `amountOfInstallments` DOUBLE NULL,\n"
                + "  `valueInstallments` DOUBLE NULL,\n"
                + "  `contactNumber` VARCHAR(400) NULL,\n"
                + "  `operator` VARCHAR(405) NULL,\n"
                + "  `note` VARCHAR(405) NULL,\n"
                + "  `files` VARCHAR(405) NULL,\n"
                + "  `numberADE` VARCHAR(405) NULL,\n"
                + "  `benefitNumber` VARCHAR(405) NULL,\n"
                + "  `speciesCode` VARCHAR(405) NULL,\n"
                + "  PRIMARY KEY (`id`));");

        array.add("ALTER TABLE `tb_loan` \n"
                + "ADD INDEX `id_loan_user_fk_idx` (`id_user` ASC) VISIBLE,\n"
                + "ADD INDEX `id_loan_insti_fk_idx` (`id_institution` ASC) VISIBLE,\n"
                + "ADD INDEX `id_loan_plan_fk_idx` (`id_plan` ASC) VISIBLE,\n"
                + "ADD INDEX `id_loan_transaction_fk_idx` (`id_transaction` ASC) VISIBLE,\n"
                + "ADD INDEX `id_loan_user_inst_fk_idx` (`id_user_institution` ASC) VISIBLE,\n"
                + "ADD INDEX `id_loan_loan_status_fk_idx` (`id_loan_status` ASC) VISIBLE;");

        array.add("ALTER TABLE `tb_loan` \n"
                + "ADD CONSTRAINT `id_loan_user_fk`\n"
                + "  FOREIGN KEY (`id_user`)\n"
                + "  REFERENCES `tb_user` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION,\n"
                + "ADD CONSTRAINT `id_loan_insti_fk`\n"
                + "  FOREIGN KEY (`id_institution`)\n"
                + "  REFERENCES `tb_institution` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION,\n"
                + "ADD CONSTRAINT `id_loan_plan_fk`\n"
                + "  FOREIGN KEY (`id_plan`)\n"
                + "  REFERENCES `tb_plan` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION,\n"
                + "ADD CONSTRAINT `id_loan_transaction_fk`\n"
                + "  FOREIGN KEY (`id_transaction`)\n"
                + "  REFERENCES `tb_transaction` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION,\n"
                + "ADD CONSTRAINT `id_loan_user_inst_fk`\n"
                + "  FOREIGN KEY (`id_user_institution`)\n"
                + "  REFERENCES `tb_user_institution` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION,\n"
                + "ADD CONSTRAINT `id_loan_loan_status_fk`\n"
                + "  FOREIGN KEY (`id_loan_status`)\n"
                + "  REFERENCES `tb_loan_status` (`id`)\n"
                + "  ON DELETE NO ACTION\n"
                + "  ON UPDATE NO ACTION;");

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
