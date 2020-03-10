package by.ttre16.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {

    private String title;
    private String salary;
    private String text;
    private String address;
    private String companyName;
    private String vacancyURL;
    private String date;

}
