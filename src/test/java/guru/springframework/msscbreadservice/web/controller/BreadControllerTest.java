package guru.springframework.msscbreadservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import guru.springframework.msscbreadservice.web.model.BreadStypeEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BreadController.class)
class BreadControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getBreadById() throws Exception {

        mockMvc.perform(get("/api/v1/bread/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBread() throws Exception {

        BreadDto breadDto = getValidBreadDto();
        String breadDtoJson = objectMapper.writeValueAsString(breadDto);

        mockMvc.perform(post("/api/v1/bread/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(breadDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBreadId() throws Exception {

        BreadDto breadDto = getValidBreadDto();
        String breadDtoJson = objectMapper.writeValueAsString(breadDto);

        mockMvc.perform(put("/api/v1/bread/"+UUID.randomUUID().toString())
        .contentType(MediaType.APPLICATION_JSON)
        .content(breadDtoJson))
        .andExpect(status().isNoContent());
    }

    BreadDto getValidBreadDto(){
        return BreadDto.builder()
                .breadName("My bread")
                .breadStyle(BreadStypeEnum.ARABIC)
                .price(new BigDecimal("0.19"))
                .upc(172356176537L)
                .build();
    }

}