package com.linkedIn.post_service.clients;

import com.linkedIn.post_service.dto.PersonDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "connection-service", path = "/connections")
public interface ConnectionClient {

    @GetMapping("/core/firstConnect")
    List<PersonDto> getFirstConnection();

}
