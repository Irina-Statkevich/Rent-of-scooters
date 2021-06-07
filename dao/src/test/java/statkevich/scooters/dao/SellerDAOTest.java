package statkevich.scooters.dao;

import statkevich.scooters.dao.IDao.ISellerDao;
import statkevich.scooters.entity.entities.Sellers;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(classes = {JPAConfig.class}, loader = AnnotationConfigContextLoader.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SellerDAOTest extends TestCase {

    @Autowired
    private ISellerDao sellerDao;

    @Test
    public void testRead() {
        Sellers resultSeller = sellerDao.read(1L);

        assertNotNull(resultSeller);
    }
}