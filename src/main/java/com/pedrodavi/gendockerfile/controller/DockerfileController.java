package com.pedrodavi.gendockerfile.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DockerfileController {

//    @Autowired
//    private DockerHubApiConsumer apiConsumer;

    @GetMapping("/")
    public ResponseEntity<String> genDockerfile(@RequestParam(value = "appName", required = true) String name,
                                                @RequestParam(value = "platform", required = true) String platform,
                                                @RequestParam(value = "version", required = false) String version) {

        StringBuilder stringBuilder = new StringBuilder();

        switch (version) {
            case "11":
                stringBuilder.append("FROM registry.access.redhat.com/ubi8/openjdk-11:1.20-2.1730773730\n");
                stringBuilder.append("ARG JAR_FILE=target/*.jar\n");
                stringBuilder.append("COPY ${JAR_FILE} /app/").append(name).append(".jar\n");
                stringBuilder.append("WORKDIR /app\n");
                stringBuilder.append("EXPOSE 8080\n");
                stringBuilder.append("ENTRYPOINT [\"java\", \"-jar\", \"").append(name).append(".jar\"]");
                break;
            case "17":
                stringBuilder.append("FROM registry.access.redhat.com/ubi8/openjdk-17:1.20-2.1730773722\n");
                stringBuilder.append("ARG JAR_FILE=target/*.jar\n");
                stringBuilder.append("COPY ${JAR_FILE} /app/").append(name).append(".jar\n");
                stringBuilder.append("WORKDIR /app\n");
                stringBuilder.append("EXPOSE 8080\n");
                stringBuilder.append("ENTRYPOINT [\"java\", \"-jar\", \"").append(name).append(".jar\"]");
                break;
        }

        return ResponseEntity.ok(stringBuilder.toString());
    }

}
