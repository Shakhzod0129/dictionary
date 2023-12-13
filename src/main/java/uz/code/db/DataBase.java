package uz.code.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataBase {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void createTable(){
        String sql= """
                create table if not exists dictionary(
                id serial primary key,
                eng varchar not null,
                uz varchar not null,
                eng_description varchar,
                uz_description varchar
                );
                """;

        jdbcTemplate.execute(sql);
    }

    public void quizTable(){
        String sql= """
                create table if not exists quiz_history(
                id serial primary key,
                player_name varchar not null,
                amount_question int,
                amount_correct_answer int,
                created_date timestamp default now()
                );
                """;

        jdbcTemplate.execute(sql);
    }
}
