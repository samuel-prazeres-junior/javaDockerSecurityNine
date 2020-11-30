package controller;


import java.util.Date;
import java.util.List;
import model.Componente;
import model.Maquina;
import model.Usuario;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import oshi.InfoComponentes;

/**
 *
 * @author Samuel Junior
 */
public class Validacoes {
        InfoComponentes info = new InfoComponentes();
        String tipoCPU = "cpu";
        String tipoMemoria = "memória";
        String tipoDisco = "disco";
        Integer idMaquina;
        public void usuarioLogado(JdbcTemplate template ,Usuario user){
            Maquina maquina = new Maquina();
            //select da tabela componente
            List cpuResultados = template.queryForList("SELECT idComponente FROM Componente where nomeComponente = ? and tipoComponente = ?;", info.getNomeCpu(), tipoCPU);
            //System.out.println(cpuResultados);
            List nomeMemoriaResultados = template.queryForList("SELECT idComponente FROM Componente where nomeComponente = ? and tipoComponente = ?;", info.getNomeMemoria(), tipoMemoria);
            //System.out.println(nomeMemoriaResultados);
            List nomeDiscoResultados = template.queryForList("SELECT idComponente FROM Componente where nomeComponente = ? and tipoComponente = ?;", info.getNomeDisco(), tipoDisco);
            //System.out.println(nomeDiscoResultados);
            List maquinaAlunoResultados = template.queryForList("SELECT idMaquina FROM Maquina WHERE hostName = ? AND fkAluno = ?",maquina.getHostNameMaquinaOshi(), user.getIdAluno());
        
        //Verificando se o componente já existe no banco
        if(maquinaAlunoResultados.isEmpty()){
            template.update("INSERT INTO Maquina Values(?, ?)", maquina.getHostNameMaquinaOshi(), user.getIdAluno());
        }
        
        
        if(cpuResultados.isEmpty()){
            template.update("INSERT INTO Componente VALUES(?, ?);", info.getNomeCpu(),tipoCPU);
        }
        
        if(nomeMemoriaResultados.isEmpty()){
            template.update("INSERT INTO Componente VALUES(?, ?);", info.getNomeMemoria(),tipoMemoria);
        }
        
        if(nomeDiscoResultados.isEmpty()){
            template.update("INSERT INTO Componente VALUES(?, ?);", info.getNomeDisco(),tipoDisco);
        }

     }
        public Componente componenteMaquina(JdbcTemplate template, String nome){
            List<Componente> componente = template.query("SELECT idComponente FROM Componente WHERE nomeComponente = ?", new BeanPropertyRowMapper<>(Componente.class), nome);
            if(!componente.isEmpty()){
                return  componente.get(0);
            }
            System.out.println("Componente Não encontrado validações linha 58");
            return null;
        }
        public void insertComponenteMaquina(JdbcTemplate template,Integer idMaquina, Double porcentagemCpu, Double porcentagemMemoria, Double porcentagemDisco){
            Componente cpu  = componenteMaquina(template, info.getNomeCpu());
            Componente memoria = componenteMaquina(template, info.getNomeMemoria());
            Componente disco = componenteMaquina(template, info.getNomeDisco());
            Date data1 = new Date();
            if(cpu != null && memoria != null && disco != null){
                template.update("INSERT INTO ComponenteMaquina VALUES(?, ?, ?, ?)", idMaquina, memoria.getIdComponente(), porcentagemMemoria,data1);
                template.update("INSERT INTO ComponenteMaquina VALUES(?, ?, ?, ?)", idMaquina, cpu.getIdComponente(), porcentagemCpu, data1);
                template.update("INSERT INTO ComponenteMaquina VALUES(?, ?, ?, ?)", idMaquina, disco.getIdComponente(), porcentagemDisco, data1);
                System.out.println("Componente Inserido Com Sucesso");
            }
            else{
                System.out.println("Erro ao inserir no banco");
            }
        }
    
  public Usuario verificarLogin(JdbcTemplate template, String login, String senha){
       List<Usuario> usuarios;

       usuarios = template.query("SELECT * FROM aluno WHERE emailAluno = ? AND senhaAluno = ?", new BeanPropertyRowMapper<>(Usuario.class), login, senha);
       
         if(!usuarios.isEmpty()){ 
                Usuario user = usuarios.get(0);
                usuarioLogado(template, user);
                return user;
            }
            System.out.println("Usuario não encontrado");
            return null;    
   }
   
    public Maquina maquina(JdbcTemplate template, Integer idUsuario){
       Maquina maquina = new Maquina();
        List<Maquina> maquinaBanco = template.query("SELECT idMaquina FROM Maquina WHERE hostName = ? AND fkAluno = ?",new BeanPropertyRowMapper<>(Maquina.class),maquina.getHostNameMaquinaOshi(), idUsuario);
       if(!maquinaBanco.isEmpty()){
           return maquinaBanco.get(0);
       }
        System.out.println("Erro na validações Metodo maquina linha 97");
       return null;
        
    }
   public void lgOut(){
//   Usuario user = new Usuario();
//   user.setAutenticado(false);
//   user.setEmail(null);
//   user.setSenha(null);
//   user.setId(null);
   }
}
