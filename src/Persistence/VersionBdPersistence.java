/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import static Resources.BD.Conection.Checks;
import static Resources.BD.Conection.closeConect;
import static Resources.BD.Conection.conect;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class VersionBdPersistence {

    public  static void updateBankVersionPersistence(ArrayList<String> array) throws SQLException {

        array.stream().forEach( scrypt -> {
            PreparedStatement preparedStatement = null;
            if (Checks()) {closeConect();}
            try {
                preparedStatement = conect().prepareStatement(scrypt);
                preparedStatement.executeUpdate();
                preparedStatement.close();
            } catch (SQLException ex) {
                Logger.getLogger(VersionBdPersistence.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                closeConect();
            }
        });
    }

}
