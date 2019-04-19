package com.blockchain.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.blockchain.api.BitcoinApi;
import com.blockchain.controller.TransactionDetailController;
import com.blockchain.dao.blockMapper;
import com.blockchain.dao.transaction_detailMapper;
import com.blockchain.po.block;
import com.blockchain.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired(required = false)
    private blockMapper blockmapper;
    @Autowired(required = false)
    private transaction_detailMapper transactionDetailMapper;
    @Autowired(required = false)
    private BitcoinApi bitcoinApi;
    @Override
    @Async
    public void setBlock(String blockHash, boolean isNull) {
   if(isNull==true){
       blockmapper.truncate();
   }
        block block = new block();

        while (blockHash != null && !"".equals(blockHash + "")) {
            JSONObject findBlock = bitcoinApi.getBlock(blockHash);
            block.setBlockchainId(2);
            Integer height = findBlock.getInteger("height");
            block.setBlockHeight(height);
            String hash = findBlock.getString("hash");
            block.setBlockHash(hash);
            Long time = findBlock.getLong("time");
            Date date = new Date(time * 1000);
            block.setBlockTime(date);
            JSONArray tx = findBlock.getJSONArray("tx");
            block.setTxSize(tx.size());
            Long size = findBlock.getLong("size");
            block.setSizeOnDisk(size);
            String previousblockhash = findBlock.getString("previousblockhash");
            block.setPrevblockhash(previousblockhash);
            String nextblockhash = findBlock.getString("nextblockhash");
            block.setNextblockhash(nextblockhash);
            block.setMerkleRoot(findBlock.getString("merkleroot"));
            block.setDifficulty(findBlock.getDouble("difficulty"));
            blockmapper.insert(block);  //dao层无法自动注入，报空指针
            blockHash = block.getPrevblockhash();
        }
    }

    @Override
    public void setTransactionDetail(String TransactionHash) {
        while (TransactionHash != null && !"".equals(TransactionHash + "")) {
            JSONObject transaction = bitcoinApi.getTransaction(TransactionHash);

        }
    }


}
