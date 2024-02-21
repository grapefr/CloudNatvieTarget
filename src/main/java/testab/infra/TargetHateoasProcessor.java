package testab.infra;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import testab.domain.*;

@Component
public class TargetHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Target>> {

    @Override
    public EntityModel<Target> process(EntityModel<Target> model) {
        model.add(
            Link.of(model.getRequiredLink("self").getHref() + "/").withRel("")
        );

        return model;
    }
}
