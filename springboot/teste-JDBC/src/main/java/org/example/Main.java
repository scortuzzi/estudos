package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexao();

        con.execute("DROP TABLE IF EXISTS filme");

        con.execute("""
                CREATE TABLE filme (
                id INT PRIMARY KEY AUTO_INCREMENT,
                nome VARCHAR(255) NOT NULL,
                ano_lancamento INT
                )""");

        con.update("INSERT INTO filme (nome, ano_lancamento) VALUES ('Vingadores 2', 2017)");
        con.update("INSERT INTO filme (nome, ano_lancamento) VALUES ('Procurando Nemo', 2007)");
        con.update("INSERT INTO filme (nome, ano_lancamento) VALUES ('Os Incriveis', 2009)");

        List<Filme> filmesDoBanco = con.query("SELECT * FROM filme",
                new BeanPropertyRowMapper<>(Filme.class));

        System.out.println(filmesDoBanco);

        Filme filmeNovo = new Filme();
        filmeNovo.setNome("Shrek");
        filmeNovo.setAnoLancamento(2004);

        con.update("INSERT INTO filme (nome, ano_lancamento) VALUES (?, ?)",
                filmeNovo.getNome(), filmeNovo.getAnoLancamento());

        filmesDoBanco = con.query("SELECT * FROM filme",
                new BeanPropertyRowMapper<>(Filme.class));

        System.out.println(filmesDoBanco);

        //---------update

        con.update("UPDATE filme SET nome = ?,"
                        + "ano_lancamento = ? WHERE id = ?",
                "Shrek 2", 2001, 1);

        filmesDoBanco = con.query("SELECT * FROM filme",
                new BeanPropertyRowMapper<>(Filme.class));

        System.out.println(filmesDoBanco);

        //-----delete

        con.update("DELETE FROM filme WHERE id = ?", 2);

        filmesDoBanco = con.query("SELECT * FROM filme",
                new BeanPropertyRowMapper<>(Filme.class));

        System.out.println(filmesDoBanco);

        //-----busca personalizada

        filmesDoBanco = con.query("SELECT * FROM filme WHERE nome LIKE ?",
                new BeanPropertyRowMapper<>(Filme.class), "S%");

        System.out.println(filmesDoBanco);

        //-------sem necessidade de lista

        Filme filmeDoBanco = con.queryForObject("SELECT * FROM filme WHERE id = 1",
                new BeanPropertyRowMapper<>(Filme.class));

        System.out.println(filmeDoBanco);
    }
}