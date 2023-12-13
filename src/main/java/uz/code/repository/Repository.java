package uz.code.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.code.model.Dictionary;
import uz.code.model.QuizHistory;

import java.util.List;

@org.springframework.stereotype.Repository
public class Repository {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Dictionary> list(){
        String sql="select * from dictionary";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Dictionary.class));
    }

    public boolean add(Dictionary dictionary) {

        String sql="insert into dictionary (eng,uz, eng_description, uz_description) values(?,?,?,?)";

        return jdbcTemplate.update(sql,dictionary.getEng(),dictionary.getUz(),dictionary.getEngDescription(),dictionary.getUzDescription())>0;
    }

    public void quizHistory(QuizHistory quizHistory) {
        String sql="insert into quiz_history (player_name, amount_question, amount_correct_answer) values (?,?,?)";

        jdbcTemplate.update(sql,quizHistory.getPlayerName(),quizHistory.getAmountQuestion(),quizHistory.getAmountCorrectAnswer());
    }

    public boolean delete(int id) {
        String sql="delete from dictionary where id=?";
        int update = jdbcTemplate.update(sql, id);

        return update>0;

    }
}
