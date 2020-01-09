package guru.springframework.msscbreadservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbreadservice.bootstrap.BreadLoader;
import guru.springframework.msscbreadservice.services.BreadService;
import guru.springframework.msscbreadservice.web.model.BreadDto;
import guru.springframework.msscbreadservice.web.model.BreadStyleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
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
    @MockBean
    BreadService breadService;

    @Test
    void getBreadById() throws Exception {

        given(breadService.getById(any())).willReturn(getValidBreadDto());

        mockMvc.perform(get("/api/v1/bread/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBread() throws Exception {

        BreadDto breadDto = getValidBreadDto();
        String breadDtoJson = objectMapper.writeValueAsString(breadDto);

        given(breadService.getById(any())).willReturn(getValidBreadDto());

        mockMvc.perform(post("/api/v1/bread/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(breadDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBreadId() throws Exception {

        given(breadService.getById(any())).willReturn(getValidBreadDto());

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
                .breadStyle(BreadStyleEnum.ARABIC)
                .price(new BigDecimal("0.19"))
                .upc(BreadLoader.BREAD_3_UPC)
                .build();
    }

}