import io.grpc.stub.StreamObserver;
import stub.MultiplicationGrpc;
import stub.SalutationOuterClass;

import static java.lang.Thread.*;

public class MultiplicationService extends MultiplicationGrpc.MultiplicationImplBase {

    @Override
    public void getMultiplicationTable(SalutationOuterClass.MultiplicationRequest request, StreamObserver<SalutationOuterClass.MultiplicatonResponse> responseObserver) {
        int nombre=request.getNombre();
        int limite=request.getLimite();
        for(int i=1;i<=limite;i++){
            String result=nombre +" x "+ i+" = "+(nombre*i);
            SalutationOuterClass.MultiplicatonResponse response= SalutationOuterClass.MultiplicatonResponse.newBuilder()
                    .setResultat(result)
                    .build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        responseObserver.onCompleted();
    }
}
