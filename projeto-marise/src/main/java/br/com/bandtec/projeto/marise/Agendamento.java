package br.com.bandtec.projeto.marise;

//import com.microsoft.sqlserver.jdbc.dataclassification.InformationType;
import controller.Validacoes;
import java.util.Date;
import java.util.TimerTask;
import model.Maquina;
import model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.InfoComponentes;

public class Agendamento{
    TimerTask task2;
    String execucao;

    public TimerTask getTask2() {
        return task2;
    }
    long delay;
    long periodo;
   
    public void executarAgendamento( long delay, long periodo, JdbcTemplate template, Integer maquina){
        
        java.util.Timer timer2 = new java.util.Timer("data2");
         this.task2 = new TimerTask() {
            @Override
            public void run() {
                InfoComponentes info = new InfoComponentes();
                
                Validacoes validar = new Validacoes();
                // tudo que estiver aqui será repetido
                    validar.insertComponenteMaquina(template, maquina, info.getPorcentagemCPU(), info.getPorcentagemMemoria(), info.getPorcentagemDisco());
                

            }
        };
         timer2.scheduleAtFixedRate(task2,delay,periodo);
//       timer2.scheduleAtFixedRate(task2,1000L,3000L);
        
    }
        
      public void printar(){
          System.out.println("Oii : "+new Date());
      }      
    public void cancelarAtualizacao(TimerTask task2) {
       task2.cancel();
    }   
}
                 