package net.devh.examples.grpc.cloud;

import io.grpc.Channel;
import net.devh.springboot.autoconfigure.grpc.client.GrpcClient;
import org.springframework.stereotype.Service;
import wisfarm.tsdb.service.grpc.lib.Point;
import wisfarm.tsdb.service.grpc.lib.TSDBServiceGrpc;
import wisfarm.tsdb.service.grpc.lib.WriteResult;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/11/8
 */
@Service
public class GrpcClientService {

    //    @GrpcClient("cloud-grpc-server")
//    private Channel serverChannel;
//
//    public String sendMessage(String name) {
//        SimpleGrpc.SimpleBlockingStub stub = SimpleGrpc.newBlockingStub(serverChannel);
//        HelloReply response = stub.sayHello(HelloRequest.newBuilder().setName(name).build());
//        return response.getMessage();
//    }
    @GrpcClient("tsdb-server")
    private Channel serverChannel;

    public String sendMessage(String name) {
        TSDBServiceGrpc.TSDBServiceBlockingStub stub = TSDBServiceGrpc.newBlockingStub(serverChannel);
        WriteResult response = stub.writePoint(Point.newBuilder().setSensorId(name).build());
        return response.getMsg();
    }

}
