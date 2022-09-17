
import Controller.VersionBdController;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Izaias
 */
public class ZsiBankLoanApiApplication {
    public static void main(String[] args) {
        VersionBdController controller = new VersionBdController();
         controller.updateBankVersion();
    }
}
