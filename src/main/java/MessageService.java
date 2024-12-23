import io.grpc.stub.StreamObserver;
import stub.SalutationGrpc;
import stub.SalutationOuterClass;

public class MessageService extends SalutationGrpc.SalutationImplBase {

    @Override
    public StreamObserver<SalutationOuterClass.SalutRequest> message(StreamObserver<SalutationOuterClass.SalutResponse> responseObserver) {
        return new StreamObserver<>() {
            StringBuilder names=new StringBuilder();
            @Override
            public void onNext(SalutationOuterClass.SalutRequest request) {
                names.append(request.getName()).append(", ");

            }

            @Override
            public void onError(Throwable throwable) {
                System.err.println("Erreur reçue :"+ throwable.getMessage());
            }

            @Override
            public void onCompleted() {
                String responseMessage="Salut à tous :"+names.toString();
                SalutationOuterClass.SalutResponse response= SalutationOuterClass.SalutResponse.newBuilder()
                        .setMessage(responseMessage)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            }
        };
    }
}
