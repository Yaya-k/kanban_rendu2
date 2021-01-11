package org.rygn.kanban;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.rygn.kanban.Int.ControllerTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "test")
public class DeveloperControllerTest extends ControllerTest {
    @Test
    public void testGetTasks() throws Exception {

        mvc.perform(get("/developers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].firstname", is("dev1")))
                .andExpect(jsonPath("$[0].lastname", is("dev1")))
                .andExpect(jsonPath("$[0].password", is("dev1")))
                .andExpect(jsonPath("$[0].email", is("dev1@dev.dev")))
        ;
    }

}
