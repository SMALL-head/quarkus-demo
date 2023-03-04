package com.zyc;

import com.zyc.component.MyComponent;
import com.zyc.proto.HelloGrpc;
import com.zyc.proto.HelloReply;
import com.zyc.proto.HelloRequest;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.runtime.annotations.QuarkusMain;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class ExampleResource {
    @GrpcClient
    HelloGrpc service;

    @Inject
    MyComponent myComponent;

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
}
