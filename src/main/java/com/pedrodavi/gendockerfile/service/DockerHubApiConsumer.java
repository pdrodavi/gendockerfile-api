package com.pedrodavi.gendockerfile.service;

import com.pedrodavi.gendockerfile.model.dto.dockerhub.LoginRequestDTO;
import com.pedrodavi.gendockerfile.model.dto.dockerhub.LoginResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "https://hub.docker.com/v2", name = "apiDockerHub")
public interface DockerHubApiConsumer {

    @PostMapping("/v2/users/login")
    LoginResponseDTO login(@RequestBody LoginRequestDTO loginRequestDTO);

}
