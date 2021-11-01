package com.softserve.academy.electronicservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.electronicservice.configuration.AppConfig;
import com.softserve.academy.electronicservice.model.Device;
import com.softserve.academy.electronicservice.model.Owner;
import com.softserve.academy.electronicservice.service.DeviceService;
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
public class DeviceControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    String BASE_URL = "/device";
    private MockMvc mockMvc;

    @Mock
    private DeviceService deviceServiceMock;

    @InjectMocks
    private DeviceController deviceController;

    private ObjectMapper objectMapper;

    Device expectedDevice;

    @BeforeAll
    public void init() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
    }

    @BeforeEach
    public void setup() {
        Owner owner = new Owner("Ivan", "Ivanov", "password");
        expectedDevice = new Device(1L, "Phone", "iPhone", "1111_222_333_555", owner, "used");

    }

    @AfterEach
    public void testEnd() throws Exception {
        expectedDevice = null;
    }

//    @Test
    public void getTest() throws Exception {

        String url = BASE_URL + "/" + expectedDevice.getId();

        Mockito.when(deviceServiceMock.get(expectedDevice.getId())).thenReturn(expectedDevice);
        mockMvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(content().string("{\"id\":1,\"type\":\"Phone\",\"name\":\"iPhone\"," +
                        "\"code\":1111222333555,\"ownerId\":1,\"status\":\"used\",\"createdDate\":null,\"updateDate\":null}"))
                .andExpect(status().isOk());
        Mockito.verify(deviceServiceMock, Mockito.times(1)).get(expectedDevice.getId());
    }

//    @Test
    public void getAllTest() throws Exception {
        Owner owner = new Owner("Ivan", "Ivanov", "password");
        Device device = new Device(2L, "Phone", "iPhone10", "1111_222_333_666", owner, "used");
        Mockito.when(deviceServiceMock.getAll()).thenReturn(Arrays.asList(expectedDevice, device));
        mockMvc.perform(MockMvcRequestBuilders.get(BASE_URL))
                .andExpect(content().string("[{\"id\":1,\"type\":\"Phone\",\"name\":\"iPhone\"," +
                        "\"code\":1111222333555,\"ownerId\":1,\"status\":\"used\",\"createdDate\":null," +
                        "\"updateDate\":null},{\"id\":2,\"type\":\"Phone\",\"name\":\"iPhone10\",\"code\":1111222333666," +
                        "\"ownerId\":2,\"status\":\"used\",\"createdDate\":null,\"updateDate\":null}]"))
                .andExpect(status().isOk());
        Mockito.verify(deviceServiceMock, Mockito.times(1)).getAll();
    }

//    @Test
    public void saveTest() throws Exception {

        String url = BASE_URL + "/add";
        Mockito.when(deviceServiceMock.save(any(Device.class))).thenReturn(expectedDevice);
        String requestJson = objectMapper.writeValueAsString(expectedDevice);
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"type\":\"Phone\",\"name\":\"iPhone\"," +
                        "\"code\":\"1111222333555\",\"owner\":{\"id\":0,\"firstName\":\"Ivan\",\"lastName\":\"Ivanov\"," +
                        "\"createdDate\":null},\"status\":\"used\",\"createdDate\":null,\"updateDate\":null}"));
        Mockito.verify(deviceServiceMock, Mockito.times(1)).save(any(Device.class));
    }

    @Test
    public void updateTest() throws Exception {
        String url = BASE_URL + "/1";
        Mockito.when(deviceServiceMock.update(eq(1L), any(Device.class))).thenReturn(expectedDevice);
        String requestJson = objectMapper.writeValueAsString(expectedDevice);
        mockMvc.perform(MockMvcRequestBuilders.put(url).contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Device has been updated: Device{id=1, type='Phone', " +
                        "name='iPhone', code=1111_222_333_555, owner=Owner{id=0, firstName='Ivan', lastName='Ivanov'," +
                        " password='password', createdDate=null}, status='used', createdDate=null, updateDate=null}"));
        Mockito.verify(deviceServiceMock, Mockito.times(1)).update(eq(1L), any(Device.class));
    }

    @Test
    public void deleteTest() throws Exception {
        String url = BASE_URL + "/" + expectedDevice.getId();
        mockMvc.perform(MockMvcRequestBuilders.delete(url))
                .andExpect(content().string("Device with Id = 1 has been deleted successfully."))
                .andExpect(status().isOk());
        Mockito.verify(deviceServiceMock, Mockito.times(1)).delete(expectedDevice.getId());
    }

}