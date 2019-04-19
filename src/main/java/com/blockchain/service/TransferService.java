package com.blockchain.service;

public interface TransferService {

    void setBlock(String blockHash, boolean isNull);

    void setTransactionDetail(String TransactionHash);
}
