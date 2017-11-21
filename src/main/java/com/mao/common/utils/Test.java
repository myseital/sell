package com.mao.common.utils;

import com.mao.common.enums.ResultEnum;
import com.mao.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;

/**
 * Created by myseital  on 2017/11/17.
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId("wxd898fcb01713c658");
        wxMpConfigStorage.setSecret("47ccc303338cee6e62894fxxxxxxxxxxx");
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage);
        try {
            log.info("[微信网页授权] 1{}", wxMpService);
            wxMpOAuth2AccessToken  = wxMpService.oauth2getAccessToken(null);
            log.info("[微信网页授权] 1.1{}", wxMpOAuth2AccessToken);
        } catch (WxErrorException e) {
            log.info("[微信网页授权] 2{}", e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        log.info("[微信网页授权] 3{}", openid);
    }
}
