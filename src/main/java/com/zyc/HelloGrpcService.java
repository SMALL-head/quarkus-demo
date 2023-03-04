package com.zyc;

import com.zyc.proto.HelloGrpc;
import com.zyc.proto.HelloReply;
import com.zyc.proto.HelloRequest;
import io.quarkus.grpc.GrpcService;

import io.smallrye.mutiny.Uni;

@GrpcService
public class HelloGrpcService implements HelloGrpc {

    @Override
    public Uni<HelloReply> sayHello(HelloRequest request) {
        return Uni.createFrom().item("Hello " + request.getName() + "!")
            .map(msg -> HelloReply.newBuilder().setMessage(msg).build());
    }

}
