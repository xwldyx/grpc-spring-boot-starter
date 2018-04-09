package net.devh.examples.grpc.cloud;


import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import wisfarm.tsdb.service.grpc.lib.Point;
import wisfarm.tsdb.service.grpc.lib.Points;
import wisfarm.tsdb.service.grpc.lib.TSDBServiceGrpc;
import wisfarm.tsdb.service.grpc.lib.WriteResult;

/**
 * User: Michael
 * Email: yidongnan@gmail.com
 * Date: 2016/11/8
 */

//@GrpcService(SimpleGrpc.class)

//public class GrpcServerService extends SimpleGrpc.SimpleImplBase {
//
//    @Override
//    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//        HelloReply reply = HelloReply.newBuilder().setMessage("Hello =============> " + req.getName()).build();
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//    }
//}
@GrpcService(TSDBServiceGrpc.class)
public class GrpcServerService extends TSDBServiceGrpc.TSDBServiceImplBase {
    @Override
    public void writePoints(Points request, StreamObserver<WriteResult> responseObserver) {
        //TODO: 写入tsdb数据库
        WriteResult writeResult = WriteResult.newBuilder().setMsg(request.getPointsList().toString()).build();
        responseObserver.onNext(writeResult);
        responseObserver.onCompleted();
    }

    @Override
    public void writePoint(Point request, StreamObserver<WriteResult> responseObserver) {
        WriteResult writeResult = WriteResult.newBuilder().setMsg(request.toString()).build();
        responseObserver.onNext(writeResult);
        responseObserver.onCompleted();
    }
}