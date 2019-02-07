package com.creditscore.controller;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CreditCardController {

    private CreditCardRepository creditCardRepository;

    @Autowired
    CreditCardController (@Qualifier("inmemoryCardRepository") CreditCardRepository creditCardRepository){
        this.creditCardRepository = creditCardRepository;
    }

    @GetMapping("listAll")
    public ResponseEntity<List<CreditCardDetail>> getAll(){
        return new ResponseEntity<>(creditCardRepository.listallCreditCardDetails(), HttpStatus.OK);
    }

    @PostMapping(value = "/addCreditCard")
    public ResponseEntity<CreditCardDetail> createCreditCardDetail(@RequestBody CreditCardDetail creditCardDetail){
        CreditCardDetail savedCreditcard = creditCardRepository.createCreditCard(creditCardDetail);
        return new ResponseEntity<>(savedCreditcard, HttpStatus.OK);
    }

    @GetMapping("searchCreditCard")
    public ResponseEntity<List<CreditCardDetail>> searchCreditCard(@RequestParam("keyword") String keyword){
        List<CreditCardDetail> foundCreditcardList = creditCardRepository.search(keyword);
            return new ResponseEntity<>(foundCreditcardList, HttpStatus.OK);

    }
}
