package at.htl.leosurvey.control;

import at.htl.leosurvey.entities.Questionnaire;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import at.htl.leosurvey.misc.DataSource;

import javax.inject.Inject;
import javax.transaction.UserTransaction;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionnaireRepositoryTest {

    @Inject
    QuestionnaireRepository questionnaireRepository;

    @Inject
    UserTransaction userTransaction;

    Table t = new Table(DataSource.getDataSource(), "questionnaire");

    @Test
    @Order(10)
    void createQuestionnaireTest(){
        Questionnaire q = new Questionnaire("Test", "Test of the Questionnaire");
        questionnaireRepository.save(q);


        Table t = new Table(DataSource.getDataSource(), "questionnaire");
        assertThat(t).column("qn_id")
                .value().isEqualTo(1)
                .column("qn_name")
                .value().isEqualTo("Test")
                .column("qn_description")
                .value().isEqualTo("Test of the Questionnaire");

    }

    @Test
    @Order(20)
    void deleteQuestionnaireTest(){
        questionnaireRepository.delete(1);
        assertThat(t).hasNumberOfRows(0);
    }

}
