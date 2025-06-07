package com.example.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    public static void main(String[] args) throws Exception {
        Server server = ServerBuilder.forPort(50051)
                .addService(new GreeterServiceImpl())
                .build();

        System.out.println("Starting gRPC server on port 50051...");
        server.start();
        System.out.println("Server started.");

        server.awaitTermination();
    }
}

