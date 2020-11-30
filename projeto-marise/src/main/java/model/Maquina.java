/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author guilh
 */
public class Maquina {
    private Integer idMaquina;
    private String hostName;
    
    
    public String getHostNameMaquinaOshi(){
      try {
            return InetAddress.getLocalHost().getHostName();
      } catch (UnknownHostException e) {
            e.printStackTrace();
            return "falha";
      }
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    
}
