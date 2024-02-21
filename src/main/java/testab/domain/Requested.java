package testab.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import testab.domain.*;
import testab.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class Requested extends AbstractEvent {

    private Long id;
    private String userId;
    private String type;
    private String state;

    public Requested(Target aggregate) {
        super(aggregate);
    }

    public Requested() {
        super();
    }
}
//>>> DDD / Domain Event
