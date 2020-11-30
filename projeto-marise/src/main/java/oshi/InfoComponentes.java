/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oshi;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Date;
import java.util.List;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.PhysicalMemory;

/**
 *
 * @author guilh
 */
public class InfoComponentes {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor cpu = hal.getProcessor();
        private long oldTicks[];
        public InfoComponentes() {
        this.oldTicks = new long[CentralProcessor.TickType.values().length];
    }
        
        

        
        //Atributos
//        private String tipoMemoria = hal.getMemory().getPhysicalMemory().get(0).getMemoryType();;
//        private long clockMemoria = hal.getMemory().getPhysicalMemory().get(0).getClockSpeed();
//        private String nomeMemoria = tipoMemoria + " " + clockMemoria/1000000;
        private String nomeCpu =  cpu.getProcessorIdentifier().getName();
        private String nomeDisco = hal.getDiskStores().get(0).getName();
       
//    public long getPorcetagemDisco() {
//        return porcetagemDisco;
//    }
//        
         long porcentagemCpu []  = hal.getProcessor().getSystemCpuLoadTicks();
        
        //MÃ©todos
        public Double getPorcentagemCPU(){
           
            double d = cpu.getSystemCpuLoadBetweenTicks(oldTicks);
            oldTicks = cpu.getSystemCpuLoadTicks();
            return d * 100.0;
//            this.porcentagemCpu = new long[CentralProcessor.TickType.values().length];
//            double d = cpu.getSystemCpuLoadBetweenTicks(porcentagemCpu);
////            porcentagemCpu = cpu.getSystemCpuLoadTicks();
//            return d*100;
        }
         public String getData(){
            Date data1 = new Date();
            String dataConvertida = String.format("%d-%d-%d %d:%d:%d",data1.getYear(), data1.getMonth(), data1.getDay(), data1.getHours(), data1.getMinutes(), data1.getSeconds());
            return dataConvertida;
        }
        
    public Double getPorcentagemMemoria() {
        Double total = Double.valueOf(hal.getMemory().getTotal());
        Double totalUsado = ((Double.valueOf(hal.getMemory().getTotal() - hal.getMemory().getAvailable()))/total)*100;
        return totalUsado;
    }
    public Double getMemoriaUtilizadaGB(){
        long memoriaUtilizada = (hal.getMemory().getTotal() - hal.getMemory().getAvailable());
        Double memoriaUtilizadaGB = Double.valueOf(((memoriaUtilizada/1024)/1024)/1024);
        return memoriaUtilizadaGB;
    }
    public Double getPorcentagemDisco(){
        Double espacoTotal = Double.valueOf(si.getOperatingSystem().getFileSystem().getFileStores().get(0).getTotalSpace());
        Double espacoLivre = Double.valueOf(si.getOperatingSystem().getFileSystem().getFileStores().get(0).getFreeSpace());
        Double espacoUsado = espacoTotal - (espacoLivre);
        Double espacoLivreConvertido = (((espacoLivre/1024)/1024)/1024);
        Double espacoTotalConvertido = (((espacoTotal/1024)/1024)/1024);
        Double espacoTotalUsadoConvertido = espacoTotalConvertido - espacoLivreConvertido;
        Double totalUsadoPorcentagem = (espacoTotalUsadoConvertido/espacoTotalConvertido)*100;
        return totalUsadoPorcentagem;
    }
//    public String getNomeMemoria() {
//        return nomeMemoria;
//    }

    public String getNomeCpu() {
        return nomeCpu;
    }
    public String getNomeDisco() {
        return nomeDisco;
    }
    public Double getTamanhoDiscoUtilizavel(){
        long total = si.getOperatingSystem().getFileSystem().getFileStores().get(0).getTotalSpace();
        Double totalConvertido = Double.valueOf(total);
        return (((totalConvertido/1024)/1024)/1024);
    }
    public Double getQtdEspacoLivre(){
        Double espacoLivre = Double.valueOf(si.getOperatingSystem().getFileSystem().getFileStores().get(0).getFreeSpace());
        return(((espacoLivre/1024)/1024)/1024);
        
    }
    public Double getVelocidadeTransferenciaDisco(){
        return Double.valueOf(hal.getDiskStores().get(0).getReadBytes());
    }
    public Double getVelocidadeRede(){
        long velocidade = ((hal.getNetworkIFs().get(0).getSpeed()/1024)/1024);
        return Double.valueOf(velocidade);
    }
    public String getNomeRede(){
        NetworkInterface nomeInterface = hal.getNetworkIFs().get(0).queryNetworkInterface();
        return nomeInterface.toString();
        
    }
    public Double getTotalRam(){
    List<PhysicalMemory> totalQtdRam = hal.getMemory().getPhysicalMemory();    
    long totalRam = 0;
    for(Integer i=0; i<totalQtdRam.size(); i++){
        long qtdMemoriaAtual = totalQtdRam.get(i).getCapacity();
        totalRam += (((qtdMemoriaAtual/1024)/1024)/1024);
        }
        return Double.valueOf(totalRam);
    }
    public String getTipoMemoria(){
        return hal.getMemory().getPhysicalMemory().get(0).getMemoryType();
    }
}
