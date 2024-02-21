package testab.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import testab.config.kafka.KafkaProcessor;
import testab.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    TargetRepository targetRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='TargetCompleted'"
    )
    public void wheneverTargetCompleted_StateChange(
        @Payload TargetCompleted targetCompleted
    ) {
        TargetCompleted event = targetCompleted;
        System.out.println(
            "\n\n##### listener StateChange : " + targetCompleted + "\n\n"
        );

        // Sample Logic //
        Target.stateChange(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ModelCompleted'"
    )
    public void wheneverModelCompleted_StateChange(
        @Payload ModelCompleted modelCompleted
    ) {
        ModelCompleted event = modelCompleted;
        System.out.println(
            "\n\n##### listener StateChange : " + modelCompleted + "\n\n"
        );

        // Sample Logic //
        Target.stateChange(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='Approved'"
    )
    public void wheneverApproved_Request(@Payload Approved approved) {
        Approved event = approved;
        System.out.println("\n\n##### listener Request : " + approved + "\n\n");

        // Sample Logic //
        Target.request(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
