package com.blockchain.controller;

import com.alibaba.fastjson.JSONObject;
import com.blockchain.api.BitcoinApi;
import com.blockchain.api.BitcoinRpcClientJson;
import com.blockchain.po.block;
import com.blockchain.service.TransferService;
import jdk.nashorn.internal.ir.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransferController {
    @Autowired(required = false)
    private BitcoinApi bitcoinApi;
    @Autowired
    private BitcoinRpcClientJson bitcoinJsonRpcClient;
    @Autowired(required = false)
    private TransferService transferService;
    @GetMapping("/toBlock")
    public void getBlock(@RequestParam String blockHash,@RequestParam(defaultValue = "false",required = false)boolean isNull){

      transferService.setBlock(blockHash,isNull);

    }
    @GetMapping("/ttt")
    public JSONObject getChainInfo(){
        JSONObject chainInfo = bitcoinApi.getChainInfo();
        return  chainInfo;
    }
}
