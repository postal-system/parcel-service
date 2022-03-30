package io.codero.parcelservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codero.parcelservice.dto.ParcelDto;
import io.codero.parcelservice.dto.RawParcelDto;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.autoconfigure.exclude=io.codero.interceptor.context.WebContextAutoConfiguration")
class ParcelControllerTest extends AbstractControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String URL = "/api/parcels";

    private final UUID id = UUID.randomUUID();

    private ParcelDto getParcelDto() {
        ParcelDto dto = new ParcelDto();
        dto.setId(id);
        dto.setSender("Government Department of Transportation");
        dto.setReceiver("mr. Tea");
        dto.setRawParcelDto(new RawParcelDto());
        dto.setPostOfficeId(666_666);
        return dto;
    }

    private String toJson(ParcelDto dto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(dto);
    }

    @Test
    void shouldReturnUUIDifGoodDataAndStatusCode200() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));
    }

    @Test
    void shouldTrowExceptionIfObjectAlreadyExist() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));


        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(409));
    }

    @Test
    void shouldReturnDtoEqualsDtoExpected() throws Exception {
        String jsonDto = objectMapper.writeValueAsString(getParcelDto());

        mvc.perform(post(URL)
                        .content(jsonDto)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        mvc.perform(get(URL +"/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonDto));
    }

    @Test
    void shouldReturn404ifNotExists() throws Exception {
        mvc.perform(get(URL +"/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldObjectUpdatedIfAllRight() throws Exception {
        String jsonDto = objectMapper.writeValueAsString(getParcelDto());
        mvc.perform(post(URL)
                        .content(jsonDto)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        ParcelDto newDto = getParcelDto();
        newDto.setReceiver("new receiver");
        String newJsonDto = objectMapper.writeValueAsString(newDto);

        mvc.perform(put(URL)
                        .content(newJsonDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get(URL +"/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(newJsonDto));
    }

    @Test
    void shouldNotUpdateAndReturn404IfIdIsFake() throws Exception {
        ParcelDto newDto = getParcelDto();
        newDto.setReceiver("new receiver");
        String newJsonDto = objectMapper.writeValueAsString(newDto);

        mvc.perform(put(URL)
                        .content(newJsonDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldBeDeletedIfObjectIsExistAndReturn404IfWasDeleted() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        mvc.perform(delete(URL + "/" + id))
                .andExpect(status().isOk());

        mvc.perform(get(URL +"/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllObjects() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        mvc.perform(delete(URL))
                .andExpect(status().isOk());

        String emptyListJson = objectMapper.writeValueAsString(List.<ParcelDto>of());

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(emptyListJson));
    }

    @Test
    void shouldReturnListParcelDto() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post(URL)
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        String listResponseJson = objectMapper.writeValueAsString(List.of(getParcelDto()));

        mvc.perform(get(URL))
                .andExpect(status().isOk())
                .andExpect(content().json(listResponseJson));
    }
}
