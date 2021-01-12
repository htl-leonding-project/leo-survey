package at.htl.leosurvey.entities;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.derby.jdbc.ClientDataSource;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
class QuestionTest {
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
    void createQuestionTest() throws SystemException, NotSupportedException,
            HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Questionnaire q = new Questionnaire("Test", "Test of the Questionnaire");
        tm.begin();
        em.persist(q);
        em.persist(new Question("Yes or No", 1, QuestionType.SINGLECHOICE, q));
        em.persist(new Question("No or Yes", 2, QuestionType.SINGLECHOICE, q));
        tm.commit();
        Table questions = new Table(getDataSource(), "question");
        assertThat(questions).hasNumberOfRows(2);
        assertThat(questions).row(0)
                .value().isEqualTo(1)
                .value().isEqualTo(1)
                .value().isEqualTo("Yes or No")
                .value().isEqualTo(0)
                .value().isEqualTo(1);
    }
}