package com.softserve.academy.electronicservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.electronicservice.configuration.AppConfig;
import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.OwnerService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OwnerControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    String BASE_URL = "/owner";

    private MockMvc mockMvc;

    @Mock
    private OwnerService ownerServiceMock;

    @InjectMocks
    private OwnerController ownerController;

    private ObjectMapper objectMapper;

    Owner expectedOwner;

    @BeforeAll
    public void init() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @BeforeEach
    public void setup() throws Exception {
        expectedOwner = new Owner(1L, "Ivan", "Ivanov", "password");

    }

    @AfterEach
    public void testEnd() {
        expectedOwner = null;
    }

    @Test
    public void getAllTest() throws Exception {
        String url = BASE_URL;
        Owner owner2 = new Owner(2L, "Petro", "Ivanov2", "password");
        Mockito.when(ownerServiceMock.getAll()).thenReturn(Arrays.asList(expectedOwner, owner2));
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(content().string("[{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"," +
                        "\"createdDate\":null},{\"id\":2,\"firstName\":\"Petro\",\"lastName\":" +
                        "\"Ivanov2\",\"createdDate\":null}]"))
                .andExpect(status().isOk());
        Mockito.verify(ownerServiceMock, Mockito.times(1)).getAll();
    }

    @Test
    public void getTest() throws Exception {
        String url = BASE_URL + "/" + expectedOwner.getId();
        Mockito.when(ownerServiceMock.get(1L)).thenReturn(expectedOwner);
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(content().string("{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"," +
                        "\"createdDate\":null,\"devices\":null}"))
                .andExpect(status().isOk());
        Mockito.verify(ownerServiceMock, Mockito.times(1)).get(expectedOwner.getId());
    }

    @Test
    public void saveTest() throws Exception {

        String url = BASE_URL + "/add";
        Mockito.when(ownerServiceMock.save(any(Owner.class))).thenReturn(expectedOwner);
        String requestJson = objectMapper.writeValueAsString(expectedOwner);
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\",\"createdDate\":null}"));
        Mockito.verify(ownerServiceMock, Mockito.times(1)).save(any(Owner.class));
    }

    @Test
    public void updateTest() throws Exception {
        String url = BASE_URL + "/1";
        Mockito.when(ownerServiceMock.update(eq(1L), any(Owner.class))).thenReturn(expectedOwner);
        String requestJson = objectMapper.writeValueAsString(expectedOwner);
        mockMvc.perform(MockMvcRequestBuilders.put(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Owner has been created : Owner{id=1, firstName='Ivan', lastName='Ivanov', password='password', createdDate=null}"));
        Mockito.verify(ownerServiceMock, Mockito.times(1)).update(eq(1L), any(Owner.class));
    }


    @Test
    public void deleteTest() throws Exception {
        String url = BASE_URL + "/" + expectedOwner.getId();
        // Mockito.when(ownerServiceMock.delete(expectedOwner.getId())).thenReturn(expectedOwner);
        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(content().string("Owner with Id = 1 has been deleted successfully."))
                .andExpect(status().isOk());
        Mockito.verify(ownerServiceMock, Mockito.times(1)).delete(expectedOwner.getId());
    }


}
