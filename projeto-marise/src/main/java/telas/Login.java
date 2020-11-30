/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import br.com.bandtec.projeto.marise.Agendamento;
import conexao.Conexao;
import controller.Validacoes;
import java.util.Scanner;
import model.Maquina;
import model.Usuario;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Samuel Junior
 */
public class Login {
    public static void main(String[] args) {
        String login;
        String senha;
        Conexao con = new Conexao();
       JdbcTemplate template = new JdbcTemplate(con.getBanco());
        Validacoes validar = new Validacoes();
        Agendamento agendar = new Agendamento();
        Scanner in = new Scanner(System.in);
        
        System.out.println("Digite seu Login:");
        login = in.nextLine();
        
        System.out.println("Digite sua Senha");
        senha = in.nextLine();
        
        Usuario usuario =  validar.verificarLogin(template, login, senha);
        Maquina maquina = validar.maquina(template, usuario.getIdAluno());
        if(usuario != null){
            System.out.println("Logado Com Sucesso");
             agendar.executarAgendamento(1000L, 1000L, template, maquina.getIdMaquina());
             
        }
     
    }
    
}
