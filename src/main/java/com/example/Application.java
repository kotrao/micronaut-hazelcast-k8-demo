package com.example;

import io.micronaut.runtime.Micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;

@Controller
public class Application {

    @Get()
    public String homepage(){
        return "Homepage\n";
    }

    public static void main(String[] args) {
        String port = "8080";
        if (args != null && args.length > 0){
            port = args[0];
        }
        else
        {
            System.out.println("No port received!");
        }
        System.out.println("Port: " + port);
        Micronaut.run(Application.class, "-Dmicronaut.server.port=" + port);
    }
}
