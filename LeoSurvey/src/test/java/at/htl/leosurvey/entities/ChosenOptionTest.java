package at.htl.leosurvey.entities;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.*;

import static org.assertj.db.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ChosenOptionTest {
    @Inject
    EntityManager em;
    @Inject
    UserTransaction tm;

    static final String DATABASE = "db";
    static final String USERNAME = "app";
    static final String PASSWORD = "app";
    public static final String URL = "jdbc:derby://localhost:1527/db";

    public static DataSource getDataSource() {
        ClientDataSource dataSource = new ClientDataSource();
        dataSource.setDatabaseName(DATABASE);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    }

    @Test
    void createAnswerTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire("Test", "Test of the Questionnaire");
        Question qn = new Question("Yes or No", 1, QuestionType.SINGLECHOICE, q);
        Answer a = new Answer("Yes", qn);
        AnswerOption a1 = new AnswerOption("Yes", 1, 1, qn);
        tm.begin();
        em.persist(q);
        em.persist(qn);
        em.persist(a);
        em.persist(a1);
        em.persist(new ChosenOption(a1, a, qn));
        tm.commit();
        Table chosenOption = new Table(getDataSource(), "chosenoption");
        assertThat(chosenOption).hasNumberOfRows(1);
        assertThat(chosenOption).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo(1);
    }
}