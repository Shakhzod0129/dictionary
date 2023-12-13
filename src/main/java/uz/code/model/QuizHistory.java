package uz.code.model;

import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Timestamp
@Component
public class QuizHistory {
    private Integer id;
    private String playerName;
    private int amountQuestion;
    private int amountCorrectAnswer;
    private LocalDateTime createdDate;
}
