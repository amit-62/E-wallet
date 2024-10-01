package com.amit.TransactionServiceApplication.controller;

import com.amit.TransactionServiceApplication.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;

    @PostMapping("/initTxn")
    public String initTxn(@RequestParam("receiver") String receiver,
                          @RequestParam("amount") String amount,
                          @RequestParam("purpose") String purpose){

        return "";
    }

}
