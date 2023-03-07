package com.zyc;

import com.zyc.component.MyComponent;
import com.zyc.entity.Role;
import com.zyc.proto.HelloGrpc;
import com.zyc.proto.HelloReply;
import com.zyc.proto.HelloRequest;
import com.zyc.proto.service.RoleMsg;
import com.zyc.proto.service.UserMsg;
import com.zyc.proto.service.UserService;
import io.quarkus.grpc.GrpcClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/hello")
public class ExampleResource {
    @GrpcClient
    HelloGrpc service;

    @Inject
    MyComponent myComponent;

    @GrpcClient
    UserService userService;

    public static List<String> list = new ArrayList<>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @Path("/h")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello2() {
        HelloReply helloReply = service.sayHello(HelloRequest.newBuilder().build()).await().atMost(Duration.ofSeconds(1));
        String message = helloReply.getMessage();
        return myComponent.method1() + "  " + message;
    }

    @Path("/insert")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes()
    @GET
    public String insert(HelloRequest request) {
        list.add("123");
        return "123";
    }

    @Path("/search/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public List<Role> getRoleListById(@PathParam("id") int id) {
        List<RoleMsg> roleList = userService.getRoleListById(UserMsg.newBuilder().setId(id).build())
            .await().atMost(Duration.ofSeconds(1)).getRoleList();
        return roleList.stream().map(Role::convert2Role).collect(Collectors.toList());
//        service.sayHello(HelloRequest.newBuilder().setName("setname").build()).await().atMost(Duration.ofSeconds(1));
    }
}
