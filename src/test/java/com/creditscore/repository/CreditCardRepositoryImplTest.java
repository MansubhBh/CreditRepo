package com.creditscore.repository;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.entity.ProductDetail;
import junit.framework.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.Collections;
import java.util.List;

@RunWith(JUnitPlatform.class)
@DisplayName("Credit Card Repository Impl Test")
public class CreditCardRepositoryImplTest {

    private CreditCardRepository creditCardRepository;

    @BeforeEach
    public void beforeTest() {
        creditCardRepository = new CreditCardRepositoryImpl();
    }

    // bank name null -> null return
    //interest rate between 1 - 50 %

    @Test
    public void testIfDataIsSaved() {
        CreditCardDetail expectedToSave = new CreditCardDetail("Bank of China", 10.14, "Visa Card", 10000, 1000, new ProductDetail("Holiday Offer", 1500, "Macbook"));
        creditCardRepository.createCreditCard(expectedToSave);
        CreditCardDetail actualSave = creditCardRepository.createCreditCard(expectedToSave);
        Assertions.assertEquals(expectedToSave, actualSave);
    }

    @Test
    public void testThrowExceptionWhenInputIsNull() {
        Assertions.assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                creditCardRepository.createCreditCard(null);
            }
        });
    }

//    @Test
//    public void testIfAllListIsReturned() {
//        int size = creditCardRepository.listallCreditCardDetails().size();
//        Assertions.assertEquals(15, size);
//    }

    @Test
    public void testIfAllListIsReturned(){
      List<CreditCardDetail> creditcardlist = creditCardRepository.listallCreditCardDetails();
        CreditCardDetail crd = creditcardlist.get(0);
        Assertions.assertEquals("Bank of China", crd.getBank());
    }



    @Test
    public void testIfKeywordIsNullWithLambda() {
        Assertions.assertThrows(NullPointerException.class,
                () -> creditCardRepository.search(null));
    }

    @Test
    public void testIfKeywordIsBlank() {
        List<CreditCardDetail> actualcardlist = creditCardRepository.search("");
        List<CreditCardDetail> actualcardlist1 = creditCardRepository.search("    ");
        List<CreditCardDetail> expectedcardlist = creditCardRepository.listallCreditCardDetails();
        Assertions.assertEquals(expectedcardlist, actualcardlist);
        Assertions.assertEquals(expectedcardlist, actualcardlist1);
    }


}
