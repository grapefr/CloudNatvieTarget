package testab.infra;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import testab.config.kafka.KafkaProcessor;
import testab.domain.*;

@Service
public class MyRequestViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private MyRequestRepository myRequestRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_CREATE_1(@Payload Requested requested) {
        try {
            if (!requested.validate()) return;

            // view 객체 생성
            MyRequest myRequest = new MyRequest();
            // view 객체에 이벤트의 Value 를 set 함
            myRequest.setId(requested.getId());
            myRequest.setUserId(requested.getUserId());
            myRequest.setType(requested.getType());
            myRequest.setState(requested.getState());
            // view 레파지 토리에 save
            myRequestRepository.save(myRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenRequested_then_UPDATE_1(@Payload Requested requested) {
        try {
            if (!requested.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                requested.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(requested.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenTargetCompleted_then_UPDATE_2(
        @Payload TargetCompleted targetCompleted
    ) {
        try {
            if (!targetCompleted.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                targetCompleted.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(targetCompleted.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenApproved_then_UPDATE_3(@Payload Approved approved) {
        try {
            if (!approved.validate()) return;
            // view 객체 조회
            Optional<MyRequest> myRequestOptional = myRequestRepository.findById(
                approved.getId()
            );

            if (myRequestOptional.isPresent()) {
                MyRequest myRequest = myRequestOptional.get();
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                myRequest.setState(approved.getState());
                // view 레파지 토리에 save
                myRequestRepository.save(myRequest);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
