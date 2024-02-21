package testab.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import testab.TargetApplication;
import testab.domain.Approved;
import testab.domain.Rejected;
import testab.domain.Requested;

@Entity
@Table(name = "Target_table")
@Data
//<<< DDD / Aggregate Root
public class Target {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String type;

    private String state;

    private String modelRequestId;

    @PostPersist
    public void onPostPersist() {
        // Approved approved = new Approved(this);
        // approved.publishAfterCommit();
        // Rejected rejected = new Rejected(this);
        // rejected.publishAfterCommit();

        Requested requested = new Requested(this);
        requested.publishAfterCommit();
        
    }

    @PostUpdate
    public void onPostUpdate() {
        if (this.state.equals("approved")) {
            Approved approved = new Approved(this);
            approved.publishAfterCommit();
        }
        else if (this.state.equals("rejected")) {
            Rejected rejected = new Rejected(this);
            rejected.publishAfterCommit();
        }
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static TargetRepository repository() {
        TargetRepository targetRepository = TargetApplication.applicationContext.getBean(
            TargetRepository.class
        );
        return targetRepository;
    }

    //<<< Clean Arch / Port Method
    public void approve() {
        //implement business logic here:

    }
    public Target approve(Target target) {
        //implement business logic here:
        target.setState("approved");
        return target;
    }

    public Target reject(Target target) {
        //implement business logic here:
        target.setState("rejected");
        return target;
    }

    public Target request(Target target) {
        //implement business logic here:
        target.setState("requested");
        return target;
    }
    //>>> Clean Arch / Port Method

    //<<< Clean Arch / Port Method
    public static void stateChange(TargetCompleted targetCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Target target = new Target();
        repository().save(target);

        */

        /** Example 2:  finding and process
        
        repository().findById(targetCompleted.get???()).ifPresent(target->{
            
            target // do something
            repository().save(target);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void stateChange(ModelCompleted modelCompleted) {
        //implement business logic here:

        /** Example 1:  new item 
        Target target = new Target();
        repository().save(target);

        */

        /** Example 2:  finding and process
        
        repository().findById(modelCompleted.get???()).ifPresent(target->{
            
            target // do something
            repository().save(target);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void request(Approved approved) {
        //implement business logic here:

        /** Example 1:  new item 
        Target target = new Target();
        repository().save(target);

        */

        /** Example 2:  finding and process
        
        repository().findById(approved.get???()).ifPresent(target->{
            
            target // do something
            repository().save(target);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
