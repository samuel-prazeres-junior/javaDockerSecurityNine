package conexao;

import java.awt.TextField;
import java.util.List;
import javax.swing.JList;
import model.Usuario;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {
    private BasicDataSource banco;

    public Conexao() {
        this.banco = new BasicDataSource();
        this.banco.setUrl("jdbc:mysql://localhost:3306/securityNine");
        this.banco.setUsername("root");
        this.banco.setPassword("urubu100");
        this.banco.setDriverClassName("com.mysql.jdbc.Driver");
    }

    public BasicDataSource getBanco() {
        return banco;
    }
    
   


}
