/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

/**
 *
 * @author Izaias
 */
public class InfoSis {
    
    private final static String getHDSerial() throws IOException {
        String os = System.getProperty("os.name");

        try {

            if (os.startsWith("Windows")) {
                return getHDSerialWindows("C");
            } else if (os.startsWith("Linux")) {
                return getHDSerialLinux();
            } else {
                throw new IOException("unknown operating system: " + os);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }

    public static String getOS() {
        String os = System.getProperty("os.name");
        String Sistema = "C:";
        String user = "izaias";

        try {

            if (os.startsWith("Windows")) {
                Sistema = "C:";
            } else if (os.startsWith("Linux")) {
                Sistema = "//home" + File.separator + user;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "O.S não identificado");
        }

        return Sistema;
    }

    private final static String getCPUSerial() throws IOException {
        String os = System.getProperty("os.name");

        try {

            if (os.startsWith("Windows")) {
                return getCPUSerialWindows();
            } else if (os.startsWith("Linux")) {
                return getCPUSerialLinux();
            } else {
                throw new IOException("unknown operating system: " + os);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }

    private final static String getMotherboardSerial() throws IOException {
        String os = System.getProperty("os.name");

        try {

            if (os.startsWith("Windows")) {
                return getMotherboardSerialWindows();
            } else if (os.startsWith("Linux")) {
                return getMotherboardSerialLinux();
            } else {

                if (os.startsWith("Windows 10")) {
                    return getMotherboardSerialWindows();
                }
                throw new IOException("unknown operating system: " + os);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IOException(ex.getMessage());
        }
    }
    public static String getMotherboardSerialWindows() {
        String result = "";

        try {
            File file = File.createTempFile("realhowto", ".vbs");
            file.deleteOnExit();

            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objWMIService = GetObject(\"winmgmts:\\\\.\\root\\cimv2\")\n"
                    + "Set colItems = objWMIService.ExecQuery _ \n"
                    + "   (\"Select * from Win32_BaseBoard\") \n"
                    + "For Each objItem in colItems \n"
                    + "    Wscript.Echo objItem.SerialNumber \n"
                    + "    exit for  ' do the first cpu only! \n" + "Next \n";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.trim();
    }
    public static String getMotherboardSerialLinux() {

        String result = "";

        try {
            String[] args = {"bash", "-c", "lshw -class bus | grep serial"};
            Process p = Runtime.getRuntime().exec(args);

            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;

            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();

        } catch (Exception e) {
        }

        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return filtraString(result, "serial: ");

    }
    public static String getHDSerialWindows(String drive) {
        String result = "";

        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();

            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
                    + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\""
                    + drive
                    + "\")\n"
                    + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {

        }

        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return result.trim();
    }
    public static String getHDSerialLinux() {
        String identificacaoMetodo = "Pacote: Util%n"
                + "Classe: InfoSis%n"
                + "Metodo: getHDSerialLinux()%n";

        String result = "";

        try {
            String[] args = {"bash", "-c", "lshw -class disk | grep serial"};
            Process p = Runtime.getRuntime().exec(args);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();

        } catch (Exception e) {
            LerTXT.log(identificacaoMetodo + "Erro: " + e.getMessage());
        }

        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }
        return filtraString(result, "serial: ");
    }


    public static String getCPUSerialWindows() {
        String identificacaoMetodo = "Pacote: Util%n"
                + "Classe: InfoSis%n"
                + "Metodo: getCPUSerialWindows()%n";

        String result = "";

        try {
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "On Error Resume Next \r\n\r\n"
                    + "strComputer = \".\"  \r\n"
                    + "Set objWMIService = GetObject(\"winmgmts:\" _ \r\n"
                    + "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\") \r\n"
                    + "Set colItems = objWMIService.ExecQuery(\"Select * from Win32_Processor\")  \r\n "
                    + "For Each objItem in colItems\r\n "
                    + "    Wscript.Echo objItem.ProcessorId  \r\n "
                    + "    exit for  ' do the first cpu only! \r\n"
                    + "Next                    ";

            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec(
                    "cscript //NoLogo " + file.getPath());
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {
            LerTXT.log(identificacaoMetodo + "Erro: " + e.getMessage());
        }

        if (result.trim().length() < 1 || result == null) {
            result = "NO_CPU_ID";
        }
        return result.trim();
    }

    public static String getCPUSerialLinux() {
        String identificacaoMetodo = "PROJETO: ZSICONTORLEMEI %n"
                + "PACOTE: Util / CLASSE: InfoSis%n"
                + "Metodo: getCPUSerialLinux()%n";

        String result = "";

        try {
            String[] args = {"bash", "-c", "lshw -class processor | grep serial"};
            Process p = Runtime.getRuntime().exec(args);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;

            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();

        } catch (Exception e) {
            LerTXT.log(identificacaoMetodo + "Erro: " + e.getMessage());
        }

        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";
        }

        return filtraString(result, "serial: ");
    }

    public static String filtraString(String nome, String delimitador) {
        return nome.split(delimitador)[1];
    }

    public static String retornaMacAddress() throws SocketException, UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        NetworkInterface netInter = NetworkInterface.getByInetAddress(localHost);
        byte[] macAddressBytes = netInter.getHardwareAddress();
        String macAddress = "";

        try {
            macAddress = String.format(
                    "%1$02x-%2$02x-%3$02x-%4$02x-%5$02x-%6$02x",
                    macAddressBytes[0], macAddressBytes[1], macAddressBytes[2],
                    macAddressBytes[3], macAddressBytes[4], macAddressBytes[5]).toUpperCase();
        } catch (Exception e) {
            macAddress = "";
        }

        return macAddress;
    }

    public static String[] enderecoMAC() {
        String[] retorno = new String[4];

        try {
            String placa = getMotherboardSerial();
            retorno[0] = placa;

            String CPU = getCPUSerial();
            retorno[1] = CPU;

            String HD = getHDSerial();
            retorno[2] = HD;

            String mac = retornaMacAddress();
            retorno[3] = mac;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }

}
