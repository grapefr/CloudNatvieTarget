package testab.infra;
import testab.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javafx.application.Application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/targets")
@Transactional
public class TargetController {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    TargetRepository targetRepository;

    @RequestMapping(value = "/targets",
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    public Target reqeust(HttpServletRequest request, HttpServletResponse response, @RequestBody Target target
        ) throws Exception {
            System.out.println("##### /target/approve  called ##### target : " + target.getUserId() );
            logger.info("##### /target/approve  called ##### target : {} " , target.getUserId());
            target.setState("requested");
            targetRepository.save(target);
            return target;
    }

    @RequestMapping(value = "/targets",
            method = RequestMethod.PUT,
            produces = "application/json;charset=UTF-8")
    public Target approve(HttpServletRequest request, HttpServletResponse response, @RequestBody Approved approved
        ) throws Exception {
            System.out.println("##### /target/approve  called ##### target approve :  " + approved.getState()  );
            Optional<Target> optionalTarget = targetRepository.findById(approved.getId());
            optionalTarget.get().setState("approved");
            targetRepository.save(optionalTarget.get());
            return optionalTarget.get();
    }

    @RequestMapping(value = "/targets/{id}",
            method = RequestMethod.PATCH,
            produces = "application/json;charset=UTF-8")
    public Target reject(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id
        ) throws Exception {
            System.out.println("##### /target/approve  called ##### target reject: " + id);
            Optional<Target> optionalTarget = targetRepository.findById(id);
            optionalTarget.get().setState("rejected");
            targetRepository.save(optionalTarget.get());
            return optionalTarget.get();
    }

    // 타겟 취소 API 작성
    @RequestMapping(value = "/targets/{id}",
            method = RequestMethod.DELETE,
            produces = "application/json;charset=UTF-8")
    public Target cancel(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") Long id) throws Exception {
        System.out.println("##### /target/cancel  called ##### target cancel: " + id);
        Optional<Target> optionalTarget = targetRepository.findById(id);
        optionalTarget.get().setState("cancelled");
        targetRepository.save(optionalTarget.get());
        return optionalTarget.get();
    }
}
//>>> Clean Arch / Inbound Adaptor
