package com.ems.empApp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ems.service.EmployeeService;


@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() 
    {
        register(EmployeeService.class);
    }
}
