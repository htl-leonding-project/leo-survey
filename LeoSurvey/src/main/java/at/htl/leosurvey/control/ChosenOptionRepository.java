package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.ChosenOption;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class ChosenOptionRepository implements PanacheRepository<ChosenOption> {

    @Transactional
    public ChosenOption save(ChosenOption chosenOption){
        return getEntityManager().merge(chosenOption);
    }

}
