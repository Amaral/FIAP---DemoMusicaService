package br.com.felipe.demomusicaservice.modal;

public class Musica {

    private String nome;
    private String arquivo;

    public Musica(String nome, String arquivo) {
        setNome(nome);
        setArquivo(arquivo);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
