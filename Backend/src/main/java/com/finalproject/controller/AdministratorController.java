package com.finalproject.controller;
import com.finalproject.DTO.AdministratorDTOs.*;
import com.finalproject.model.Account;
import com.finalproject.model.Administrator;
import com.finalproject.model.Market;
import com.finalproject.service.AdministratorService;
import com.finalproject.service.MarketService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/administrator")
public class AdministratorController {

    @Resource
    private AdministratorService administratorService;

    @Resource
    private MarketService marketService;

    @PostMapping("/get-all-authentication")
    public ResponseEntity<List<ShowAuthenticationDTO>> getAllAuthentication(@RequestBody GAAModel model) {
        List<ShowAuthenticationDTO> authentications = administratorService.getAllAuthentication(model.getAdminId());
        if (authentications.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authentications);
    }

    @PutMapping("/update-store-authentication")
    public ResponseEntity<String> updateStoreAuthentication(@RequestBody USAModel model) {
        // 调用 Service 层处理认证更新
        String result = administratorService.updateStoreAuthentication(model);

        if (result.startsWith("No authentication found") || result.startsWith("Store not found")) {
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }

    @PostMapping("/invite-stores")
    public ResponseEntity<String> inviteStores(@RequestBody ISModel model) {
        String result = administratorService.inviteStores(model);

        if (result.startsWith("传入错误的小分类Id")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }


    @PostMapping("/add-market")
    public ResponseEntity<String> addMarket(@RequestBody AMModel model) {
        try {
            String marketId = marketService.addMarket(model);
            return ResponseEntity.ok("市集添加成功，市集id: " + marketId);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the image: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/get-all-markets")
    public ResponseEntity<List<Market>> getAllMarkets() {
        List<Market> markets = administratorService.getAllMarkets();
        return ResponseEntity.ok(markets);
    }

    @DeleteMapping("/delete-market")
    public ResponseEntity<String> deleteMarket(@RequestBody DMModel model) {
        marketService.deleteMarket(model.getMarketId());
        return ResponseEntity.ok("Market deleted: Id= "+model.getMarketId());
    }
}
