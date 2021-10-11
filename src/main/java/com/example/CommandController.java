package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;

@Controller
public class CommandController {

    private final CommandService commandService;


    public CommandController(CommandService commandService) {
        this.commandService = commandService;
    }

    @Get("/put")
    public CommandResponse put(@QueryValue("key") String key, @QueryValue("value") String value) {
        commandService.put(key, value);
        return new CommandResponse(value);
    }

    @Get("/get")
    public CommandResponse get(@QueryValue("key") String key) {

        System.out.println("*****GET - process id:" + ProcessHandle.current().pid());

        String value = commandService.get(key) + " - Process id" +ProcessHandle.current().pid();
        return new CommandResponse(value);
    }


}