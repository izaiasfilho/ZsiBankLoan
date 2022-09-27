/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Services.Implementations;

import Model.Entities.VersionBdEntity;
import Model.Enuns.TypetransactionsSqlEnuns;
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
public class VersionBdImplemetatiion implements VersionBdInterface {

    @Override
    public void updateBankVersionImp() {

        if (!checkExistTbVersionImp()) {
            listQueryVersion().stream().forEach(query -> {
                try {
                    VersionBdPersistence.updateBankVersionPersistence(query);
                } catch (SQLException ex) {
                    Logger.getLogger(VersionBdImplemetatiion.class.getName()).log(Level.SEVERE, null, ex);
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
                if (VersionBdPersistence.updateBankVersionPersistence(listQueryVersion().get(x))) {
                    updateVersion(x);
                }else{
                    x = listQueryVersion().size();
                }
            } catch (SQLException ex) {
                Logger.getLogger(VersionBdImplemetatiion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateVersion(int i) {
        try {
            VersionBdPersistence.updateBankVersionPersistence(
                    QuerySequency.registerVersionBd(i + 1,
                            convertStringToTypeTransactions(listQueryVersion().get(i).substring(0, 7)),
                            listQueryVersion().get(i)));
        } catch (SQLException ex) {
            Logger.getLogger(VersionBdImplemetatiion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public TypetransactionsSqlEnuns convertStringToTypeTransactions(String type) {
        if (type.contains("DELETE")) {
            return TypetransactionsSqlEnuns.DELETE;
        } else {
            if (type.contains("UPDATE")) {
                return TypetransactionsSqlEnuns.UPDATE;
            } else {
                if (type.contains("INSERT")) {
                    return TypetransactionsSqlEnuns.INSERT;
                } else {
                    if (type.contains("ALTER")) {
                        return TypetransactionsSqlEnuns.ALTER;
                    } else {
                        if (type.contains("CREATE")) {
                            return TypetransactionsSqlEnuns.CREATE;
                        } else {
                            if (type.contains("DROP")) {
                                return TypetransactionsSqlEnuns.DROP;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}
