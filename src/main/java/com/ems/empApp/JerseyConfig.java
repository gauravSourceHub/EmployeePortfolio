package com.ems.empApp;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.ems.service.EmployeeService;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;


@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig
{
    public JerseyConfig() 
    {
        register(EmployeeService.class);
        configureSwagger();
    }
    
    public void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);
        BeanConfig config = new BeanConfig();
        config.setConfigId("Employment Management System");
        config.setTitle("Employment Management System");
        config.setVersion("1.0.0");
        config.setBasePath("/rest");
        config.setResourcePackage("com.ems.service");
        config.setScan(true);
    }
}
