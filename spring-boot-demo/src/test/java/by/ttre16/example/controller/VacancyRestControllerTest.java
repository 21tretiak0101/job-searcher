package by.ttre16.example.controller;

import by.ttre16.example.service.VacancyServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class VacancyRestControllerTest {

  /*  @Autowired
    private MockMvc mockMvc;*/

/*   @InjectMocks
    private VacancyRestController vacancyRestController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vacancyRestController).build();
    }*/
    @InjectMocks
    private VacancyRestController restController;

    @Mock
    private VacancyServiceImpl service;

    /*@Test
    void getVacancies() throws Exception {
        VacancyDto dto = new VacancyDto();
        dto.setCity("Минск");
        dto.setSiteName("belmeta");
        dto.setTechnology("Java");
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/api/vacancies", dto))
                .andDo(print());
    }*/
}