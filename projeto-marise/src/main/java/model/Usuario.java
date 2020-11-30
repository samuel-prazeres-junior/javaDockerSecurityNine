package model;

public class Usuario {
    private Integer idAluno;
    private  String raAluno;
    private String nomeAluno;
    private String emailAluno;
    private String senhaAluno;
    private Integer fkTurma;

    public Integer getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(Integer idAluno) {
        this.idAluno = idAluno;
    }

    public String getRaAluno() {
        return raAluno;
    }

    public void setRaAluno(String raAluno) {
        this.raAluno = raAluno;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getEmailAluno() {
        return emailAluno;
    }

    public void setEmailAluno(String emailAluno) {
        this.emailAluno = emailAluno;
    }

    public String getSenhaAluno() {
        return senhaAluno;
    }

    public void setSenhaAluno(String senhaAluno) {
        this.senhaAluno = senhaAluno;
    }

    public Integer getFkTurma() {
        return fkTurma;
    }

    public void setFkTurma(Integer fkTurma) {
        this.fkTurma = fkTurma;
    }

   


    @Override
    public String toString() {
        return "Usuario{" + "id=" + idAluno+", RA=" + raAluno + ", nome=" + nomeAluno + ", email=" + emailAluno + ", senha=" + senhaAluno + '}';
    }
    
    
}
