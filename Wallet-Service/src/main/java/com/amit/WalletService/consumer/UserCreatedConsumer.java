package com.amit.WalletService.consumer;

import com.amit.Utilities.CommonConstants;
import com.amit.WalletService.model.Wallet;
import com.amit.WalletService.repositary.WalletRepositary;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${user.creation.time.value}")
    private Double balance;

    @Autowired
    private WalletRepositary walletRepositary;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    private static Logger logger = LoggerFactory.getLogger(UserCreatedConsumer.class);

    @KafkaListener(topics = {CommonConstants.USER_CREATED_TOPIC}, groupId = "wallet-group")
    public void createWallet(String msg) throws JsonProcessingException {
        JSONObject jsonObject = objectMapper.readValue(msg, JSONObject.class);
        Integer userId = (Integer) jsonObject.get(CommonConstants.USER_ID);
        String contact = (String) jsonObject.get(CommonConstants.USER_CONTACT);

        Wallet wallet = Wallet.builder().
                contact(contact).
                userId(userId).
                balance(balance).
                build();
        walletRepositary.save(wallet);

        logger.info("wallet created for the user");

        JSONObject object = new JSONObject();
        object.put(CommonConstants.USER_ID, userId);
        object.put(CommonConstants.WALLET_BALANCE, balance);

        kafkaTemplate.send(CommonConstants.WALLET_CREATED_TOPIC, objectMapper.writeValueAsString(object));
        logger.info("prodiced the wallet creating message in queue "+ userId);
    }
}
