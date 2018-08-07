package com.github.achuzhmarov.tutorial.controller.common;

import com.github.achuzhmarov.tutorial.jpa.CustomerRepository;
import com.github.achuzhmarov.tutorial.jpa.ProductRepository;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
/*If your test is @Transactional, it rolls back the transaction at the end of each test method by default. However, as using this arrangement with either RANDOM_PORT or DEFINED_PORT implicitly provides a real servlet environment, the HTTP client and server run in separate threads and, thus, in separate transactions. Any transaction initiated on the server does not roll back in this case.*/
public abstract class BaseServerIT {
    @Autowired
    protected TestRestTemplate restTemplate;

    @Autowired
    TransactionTemplate transactionTemplate;

    @Autowired
    protected ProductRepository productRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    protected void runInTransaction(Runnable function) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    function.run();
                } catch (Exception e) {
                    e.printStackTrace();
                    transactionStatus.setRollbackOnly();
                }
            }
        });
    }

    @After
    public void clearDataFromDatabase() {
        runInTransaction(() -> {
            customerRepository.deleteAll();
            productRepository.deleteAll();
        });
    }
}
