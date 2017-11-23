package com.mao.service.impl;

import com.mao.common.enums.ProductStatus;
import com.mao.common.enums.ResultEnum;
import com.mao.dto.CartDTO;
import com.mao.entity.ProductInfo;
import com.mao.exception.SellException;
import com.mao.repository.ProductInfoRepository;
import com.mao.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class ProductInfoServerImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatus.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = repository.findOne(cartDTO.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_INSUFFICIENT);
            }
            productInfo.setProductStock(result);

            repository.save(productInfo);
        }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productId == null) {
            log.error("[商品上架] 查询商品失败 productInfo={}", productInfo);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        if (productInfo.getProductStatusEnum().equals(ProductStatus.UP)) {
            log.error("[商品上架] 商品状态错误 productInfo={}", productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新商品状态为上架
        productInfo.setProductStatus(ProductStatus.UP.getCode());

        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo = repository.findOne(productId);
        if (productId == null) {
            log.error("[商品下架] 查询商品失败 productInfo={}", productInfo);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        if (productInfo.getProductStatusEnum().equals(ProductStatus.DOWN)) {
            log.error("[商品下架] 商品状态错误 productInfo={}", productInfo);
            throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }

        //更新商品状态为下架
        productInfo.setProductStatus(ProductStatus.DOWN.getCode());

        return repository.save(productInfo);
    }
}
