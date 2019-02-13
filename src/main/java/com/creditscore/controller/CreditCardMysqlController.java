package com.creditscore.controller;

import com.creditscore.entity.CreditCardDetail;
import com.creditscore.repository.CreditCardRepository;
import com.creditscore.service.UserTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v3")
public class CreditCardMysqlController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardMysqlController.class);

    private CreditCardRepository creditCardRepository;
    private UserTokenService userTokenService;

    @Autowired
    CreditCardMysqlController(@Qualifier("mysqlCardRepository") CreditCardRepository creditCardRepository,
                              UserTokenService userTokenService
    ){
        this.creditCardRepository = creditCardRepository;
        this.userTokenService = userTokenService;
    }

    @GetMapping("listAll")
    public ResponseEntity<List<CreditCardDetail>> getAll(){
        return new ResponseEntity<>(creditCardRepository.listallCreditCardDetails(), HttpStatus.OK);
    }

    @PostMapping(value = "/addCreditCard")
    public ResponseEntity<CreditCardDetail> createCreditCardDetail(@RequestBody CreditCardDetail creditCardDetail,
                                                                   @RequestParam("token") String token){
        if (userTokenService.checkUserToken(token)){
            CreditCardDetail savedCreditcard = creditCardRepository.createCreditCard(creditCardDetail);
            return new ResponseEntity<>(savedCreditcard, HttpStatus.OK);
        }
        throw new RuntimeException("Invalid token");
    }

    @GetMapping("searchCreditCard")
    public ResponseEntity<List<CreditCardDetail>> searchCreditCard(@RequestParam("bank") String bank, @RequestParam(value = "type", required = false) String type) {
        List<CreditCardDetail> foundCreditcardList = new ArrayList<>();
        LOGGER.debug("data from request- > bank = \"{}\", type = \"{}\"" , bank, type);
        if (type != null){
            foundCreditcardList = creditCardRepository.searchDetail(bank, type);
        }else {
            foundCreditcardList = creditCardRepository.search(bank);
        }
            return new ResponseEntity<>(foundCreditcardList, HttpStatus.OK);
    }

}
