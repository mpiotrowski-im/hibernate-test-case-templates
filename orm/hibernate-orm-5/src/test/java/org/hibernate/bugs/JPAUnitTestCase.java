package org.hibernate.bugs;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.hibernate.bugs.hhh12023.RootEntity;
import org.hibernate.bugs.hhh12023.SomeDataEntity;
import org.hibernate.bugs.hhh12023.TreeNodeEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM,
 * using the Java Persistence API.
 */
public class JPAUnitTestCase {

    private EntityManagerFactory entityManagerFactory;

    @Before
    public void init() {
        entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
    }

    @After
    public void destroy() {
        entityManagerFactory.close();
    }

    // Entities are auto-discovered, so just add them anywhere on class-path
    // Add your tests, using standard JUnit.
    @Test
    public void hhh12023Test() throws Exception {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        // Prepare data
        RootEntity re = new RootEntity();
        re.setId(1L);
        em.persist(re);
        TreeNodeEntity rootNode = new TreeNodeEntity();
        em.persist(rootNode);
        re.setRoot(rootNode);
        //child 1
        TreeNodeEntity child1 = new TreeNodeEntity();
        em.persist(child1);
        rootNode.getChildren().add(child1);
        SomeDataEntity child1Data = new SomeDataEntity();
        em.persist(child1Data);
        child1.setData(child1Data);
        child1Data.getSomeMap().put("key1", "value1");
        child1Data.getSomeMap().put("key2", "value2");
        //child 2
        TreeNodeEntity child2 = new TreeNodeEntity();
        em.persist(child2);
        rootNode.getChildren().add(child2);
        SomeDataEntity child2Data = new SomeDataEntity();
        em.persist(child2Data);
        child2.setData(child2Data);
        child2Data.getSomeMap().put("key3", "value3");
        child2Data.getSomeMap().put("key4", "value4");
        em.getTransaction().commit();

        //now read data
        em.clear();
        em.getTransaction().begin();
        RootEntity readRe = em.find(RootEntity.class, 1L);
        //Assert.assertEquals(2, readRe.getRoot().getChildren().size());
        assert 2 == readRe.getRoot().getChildren().size() : "Expected child count 2, read " + readRe.getRoot().getChildren().size() + " children";

        em.getTransaction().commit();
        em.close();
    }
}
