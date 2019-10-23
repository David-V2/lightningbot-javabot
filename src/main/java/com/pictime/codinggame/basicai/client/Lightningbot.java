package com.pictime.codinggame.basicai.client;

import com.pictime.codinggame.basicai.client.dto.*;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "lightningbot")
public interface Lightningbot {

    @RequestLine("GET /connect/{token}")
    ConnectResponse connect(@Param("token") String token);

    @RequestLine("GET /connect/{pseudo}")
    ConnectTestResponse connectTest(@Param("pseudo") String pseudo);

    @RequestLine("GET /info/{token}")
    InfoResponse info(@Param("token") String token);

    @RequestLine("GET /directions/{token}/{turn}")
    DirectionsResponse direction(@Param("token") String token, @Param("turn") int turn);

    @RequestLine("GET /move/{token}/{direction}/{turn}")
    MoveResponse move(@Param("token") String token, @Param("direction") int direction, @Param("turn") int turn);

}
