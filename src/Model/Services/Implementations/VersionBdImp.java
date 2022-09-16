/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.VersionBdEntity;
import Model.Persistence.VersionBdPersistence;
import static Model.Services.Implementations.ResultsetConvert.VersionBdResultsetConvert.checkExistTbVersionResultsetConvert;
import static Model.Services.Implementations.ResultsetConvert.VersionBdResultsetConvert.getListVersionBdResultsetConvert;
import Model.Services.Interfaces.VersionBdInterface;
import Resources.BD.QuerySequency;
import static Resources.BD.QuerySequency.listQueryVersion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Izaias
 */
public class VersionBdImp implements VersionBdInterface {

    @Override
    public void updateBankVersionImp() {

        if (!checkExistTbVersionImp()) {
            listQueryVersion().stream().forEach(query -> {
                try {
                    VersionBdPersistence.updateBankVersionPersistence(query);
                } catch (SQLException ex) {
                    Logger.getLogger(VersionBdImp.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            for (int i = 0; i < listQueryVersion().size(); i++) {
                updateVersion(i);
            }
        } else {
            versionControl();
        }
    }

    @Override
    public ArrayList<VersionBdEntity> getListVersionBdImp() {
        return getListVersionBdResultsetConvert();
    }

    @Override
    public boolean checkExistTbVersionImp() {
        return checkExistTbVersionResultsetConvert();
    }

    @Override
    public int lastVersionBd() {
        return getListVersionBdImp().get(getListVersionBdImp().size() - 1).getVersion();
    }

    @Override
    public void versionControl() {
        for (int x = lastVersionBd(); x < listQueryVersion().size(); x++) {
            try {
                VersionBdPersistence.updateBankVersionPersistence(listQueryVersion().get(x));
                
                updateVersion(x);
            } catch (SQLException ex) {
                Logger.getLogger(VersionBdImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void updateVersion(int i) {
        try {
            VersionBdPersistence.updateBankVersionPersistence(
                    QuerySequency.registerVersionBd(i + 1, listQueryVersion().get(i)));
        } catch (SQLException ex) {
            Logger.getLogger(VersionBdImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
