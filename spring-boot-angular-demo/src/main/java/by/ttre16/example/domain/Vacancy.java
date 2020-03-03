package by.ttre16.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {

    private String title;
    private String salary;
    private String city;
    private String companyName;
    private String url;
    private String  date;

}
