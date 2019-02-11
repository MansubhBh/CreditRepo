package com.creditscore.controller;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.repository.CreditCardRepository;
import com.creditscore.repository.ElasticAsyncRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/v2")
public class AsyncCreditCardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncCreditCardController.class);
    //TODO to be removed once you implement all methods as async in ElasticAsyncRepository
    private CreditCardRepository creditCardRepository;

    private ElasticAsyncRepository elasticAsyncRepository;

    @Autowired
    AsyncCreditCardController(
            @Qualifier("elasticsearchCardRepository") CreditCardRepository creditCardRepository,
            @Qualifier("elasticsearchAsyncRepository") ElasticAsyncRepository elasticAsyncRepository) {
        this.creditCardRepository = creditCardRepository;
        this.elasticAsyncRepository = elasticAsyncRepository;
    }

    @GetMapping("listAll")
    public CompletionStage<ResponseEntity<List<CreditCardDetail>>> getAllAysnc() {
        return elasticAsyncRepository.listallCreditCardDetails().thenApply(list -> new ResponseEntity<>(list, HttpStatus.OK));
    }


    @PostMapping(value = "/addCreditCard")
    public ResponseEntity<CreditCardDetail> createCreditCardDetail(@RequestBody CreditCardDetail creditCardDetail) {
        CreditCardDetail savedCreditcard = creditCardRepository.createCreditCard(creditCardDetail);
        return new ResponseEntity<>(savedCreditcard, HttpStatus.OK);
    }

    @GetMapping("searchCreditCard")
    public ResponseEntity<List<CreditCardDetail>> searchCreditCard(@RequestParam("bank") String bank, @RequestParam(value = "type", required = false) String type) {
        List<CreditCardDetail> foundCreditcardList = new ArrayList<>();
        LOGGER.debug("data from request- > bank = \"{}\", type = \"{}\"", bank, type);
        if (type != null) {
            foundCreditcardList = creditCardRepository.searchDetail(bank, type);
        } else {
            foundCreditcardList = creditCardRepository.search(bank);
        }
        return new ResponseEntity<>(foundCreditcardList, HttpStatus.OK);
    }

}
