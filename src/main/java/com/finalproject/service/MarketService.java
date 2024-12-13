package com.finalproject.service;

import com.finalproject.DTO.AdministratorDTOs.*;
import com.finalproject.model.Market;
import com.finalproject.model.SetUpMarketPlace;
import com.finalproject.repository.MarketRepository;
import com.finalproject.repository.SetUpMarketPlaceRepository;
import com.finalproject.util.SnowflakeIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MarketService {

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private SetUpMarketPlaceRepository setUpMarketPlaceRepository;

    public String addMarket(AMModel model) throws IOException {
        // 生成唯一 ID
        SnowflakeIdGenerator generator = new SnowflakeIdGenerator();
        String uidb = "M" + generator.nextId();
        String picId = generator.nextId();

        // 读取图片数据
        //byte[] imageData = model.getImage().toString();

        // 保存 Market 数据
        Market market = new Market();
        market.setMarketId(uidb);
        market.setTheme(model.getTheme());
        market.setStartTime(model.getStartTime());
        market.setEndTime(model.getEndTime());
        market.setDetail(model.getDetail());
        //market.setPosterImg(imageData);
        market.setImageId(picId);
        marketRepository.save(market);

        // 保存 SetUpMarketplace 数据
        SetUpMarketPlace setUpMarketplace = new SetUpMarketPlace();
        setUpMarketplace.setMarketId(uidb);
        setUpMarketplace.setAdministratorAccountId(model.getAdminId());
        System.out.println(model.getAdminId());
        setUpMarketPlaceRepository.save(setUpMarketplace);

        return uidb;
    }

    public void deleteMarket(String marketId) {
        marketRepository.deleteById(marketId);
    }
}
