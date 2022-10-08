
import Controller.VersionBdController;
import Model.Security.CompanyImplemetation;
import View.LoginView;


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
        CompanyImplemetation company = new CompanyImplemetation();
        company.validateLicenseImplemetation();
        
        
        VersionBdController controller = new VersionBdController();
         controller.updateBankVersion();
         
         LoginView login = new LoginView();
         login.setVisible(true);
    }
}
