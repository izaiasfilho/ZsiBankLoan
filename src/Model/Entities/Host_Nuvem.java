/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Entities;

/**
 *
 * @author Izaias
 */
public class Host_Nuvem {
   
    private int id_host;
    private int id_empresa;

    private String host;
    private String CPU;
    private String HD;
    private String mac;

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public String getHD() {
        return HD;
    }

    public void setHD(String HD) {
        this.HD = HD;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getId_host() {
        return id_host;
    }

    public void setId_host(int id_host) {
        this.id_host = id_host;
    }

    public int getId_empresa() {
        return id_empresa;
    }

    public void setId_empresa(int id_empresa) {
        this.id_empresa = id_empresa;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    } 
}
