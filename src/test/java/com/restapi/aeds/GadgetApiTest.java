package com.restapi.aeds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapi.aeds.model.Gadget;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class GadgetApiTest {

        @Autowired
        private MockMvc MockMvc;

        @Autowired
        private ObjectMapper mapper;

        @Test
        @Order(1)
        public void canAddGadget() throws JsonProcessingException, Exception {
                Gadget gadget = Gadget.builder().brand("ABC").model("123").price(100.0).build();
                this.MockMvc.perform(post("/store/gadget/register-product").contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(gadget)).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk()).andDo(print())
                                .andExpect(content().string(containsString("ABC")));
        }

        @Test
        @Order(2)
        public void canViewGadget() throws JsonProcessingException, Exception {
                this.MockMvc.perform(get("/store/gadget/view/1")).andExpect(status().isOk()).andDo(print())
                                .andExpect(content().string(containsString("ABC")));
        }

        @Test
        @Order(3)
        public void canEditGadget() throws JsonProcessingException, Exception {
                Gadget gadget = Gadget.builder().brand("XYZ").model("123").price(100.0).build();
                this.MockMvc.perform(put("/store/gadget/update/1").contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(gadget)).accept(MediaType.APPLICATION_JSON))
                                .andExpect(status().isOk()).andDo(print())
                                .andExpect(content().string(containsString("XYZ")));
        }

        @Test
        @Order(4)
        public void canGetAll() throws JsonProcessingException, Exception {
                this.MockMvc.perform(get("/store/gadget/get-all")).andExpect(status().isOk()).andDo(print())
                                .andExpect(content().string(containsString("XYZ")));
        }

        @Test
        @Order(5)
        public void canDeleteGadget() throws JsonProcessingException, Exception {
                this.MockMvc.perform(delete("/store/gadget/delete/1")).andExpect(status().isOk()).andDo(print())
                                .andExpect(content().string(containsString("XYZ")));
        }

}
