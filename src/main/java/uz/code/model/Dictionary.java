package uz.code.model;

import jdk.jfr.Timestamp;
import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Timestamp
@Component
public class Dictionary {
    private int id;
    private String eng;
    private String uz;
    private String engDescription;
    private String uzDescription;

}
