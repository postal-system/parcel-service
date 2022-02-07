package io.codero.parcelservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codero.parcelservice.dto.ParcelDto;
import io.codero.parcelservice.dto.RawParcelDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ParcelControllerTest extends AbstractControllerTest {
    /*
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

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

        mvc.perform(post("/parcel")
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

        mvc.perform(post("/parcel")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));


        mvc.perform(post("/parcel")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is(409));
    }

    @Test
    void shouldReturnDtoEqualsDtoExpected() throws Exception {
        String jsonDto = objectMapper.writeValueAsString(getParcelDto());

        mvc.perform(post("/parcel")
                        .content(jsonDto)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        mvc.perform(get("/parcel/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonDto));
    }

    @Test
    void shouldReturn404ifNotExists() throws Exception {
        mvc.perform(get("/parcel/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldObjectUpdatedIfAllRight() throws Exception {
        String jsonDto = objectMapper.writeValueAsString(getParcelDto());
        mvc.perform(post("/parcel")
                        .content(jsonDto)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        ParcelDto newDto = getParcelDto();
        newDto.setReceiver("new receiver");
        String newJsonDto = objectMapper.writeValueAsString(newDto);

        mvc.perform(put("/parcel")
                        .content(newJsonDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(get("/parcel/" + id))
                .andExpect(status().isOk())
                .andExpect(content().json(newJsonDto));
    }

    @Test
    void shouldNotUpdateAndReturn404IfIdIsFake() throws Exception {
        ParcelDto newDto = getParcelDto();
        newDto.setReceiver("new receiver");
        String newJsonDto = objectMapper.writeValueAsString(newDto);

        mvc.perform(put("/parcel")
                        .content(newJsonDto)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldBeDeletedIfObjectIsExistAndReturn404IfWasDeleted() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post("/parcel")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        mvc.perform(delete("/parcel" + "/" + id))
                .andExpect(status().isOk());

        mvc.perform(get("/parcel/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldDeleteAllObjects() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post("/parcel")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        mvc.perform(delete("/parcel"))
                .andExpect(status().isOk());

        String emptyListJson = objectMapper.writeValueAsString(List.<ParcelDto>of());

        mvc.perform(get("/parcel"))
                .andExpect(status().isOk())
                .andExpect(content().json(emptyListJson));
    }

    @Test
    void shouldReturnListParcelDto() throws Exception {
        String requestJson = objectMapper.writeValueAsString(getParcelDto());
        String ExpectedJson = objectMapper.writeValueAsString(id);

        mvc.perform(post("/parcel")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().json(ExpectedJson));

        String listResponseJson = objectMapper.writeValueAsString(List.of(getParcelDto()));

        mvc.perform(get("/parcel"))
                .andExpect(status().isOk())
                .andExpect(content().json(listResponseJson));
    }

     */

}