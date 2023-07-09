package com.jerry.serviceprice.service;

import static com.jerry.common.response.StatusCode.PRICE_RULE_EMPTY;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jerry.common.dto.ForecastPriceDTO;
import com.jerry.common.dto.PriceRule;
import com.jerry.common.dto.resp.DirectionResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.util.BigDecimalUtil;
import com.jerry.serviceprice.mapper.PriceRuleMapper;
import com.jerry.serviceprice.remote.ServiceMapClient;

/**
 * description
 *
 * @author qijie
 * @date 2023/6/7
 */
@Service
public class PriceService {

    @Autowired
    private ServiceMapClient serviceMapClient;

    @Autowired
    private PriceRuleMapper priceRuleMapper;

    /**
     * 估价
     * 
     */
    public JsonRespWrapper forecastPrice(String depLongitude, String depLatitude, String destLongitude,
        String destLatitude, String cityCode, String vehicleType) {

        ForecastPriceDTO forecastPriceDTO = new ForecastPriceDTO();
        forecastPriceDTO.setDepLongitude(depLongitude);
        forecastPriceDTO.setDepLatitude(depLatitude);
        forecastPriceDTO.setDestLongitude(destLongitude);
        forecastPriceDTO.setDestLatitude(destLatitude);

        // 获取预估的距离和耗时
        JsonRespWrapper<DirectionResp> direction = serviceMapClient.direction(forecastPriceDTO);
        Integer distance = direction.getData().getDistance();
        Integer duration = direction.getData().getDuration();

        // 根据城市和车辆类型判断使用的规则
        QueryWrapper<PriceRule> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("city_code", cityCode);
        queryWrapper.eq("vehicle_type", vehicleType);
        queryWrapper.orderByDesc("fare_version");

        List<PriceRule> priceRules = priceRuleMapper.selectList(queryWrapper);
        if (priceRules.isEmpty()) {
            return JsonRespWrapper.failure(PRICE_RULE_EMPTY);
        }

        // 获取到计价规则之后估算价格
        PriceRule priceRule = priceRules.get(0);

        double price = doCalculatePrice(distance, duration, priceRule);

        return JsonRespWrapper.success(price);
    }

    /**
     * 根据距离、时长 和计价规则，计算最终价格
     * 
     * @param distance 距离
     * @param duration 时长
     * @param priceRule 计价规则
     * @return 估算价格
     */
    public double doCalculatePrice(Integer distance, Integer duration, PriceRule priceRule) {
        double price = 0;

        // 起步价
        double startFare = priceRule.getStartFare();
        price = BigDecimalUtil.add(price, startFare);

        // 里程费 = （总里程-起步里程） * 费率
        double distanceMile = BigDecimalUtil.divide(distance, 1000);
        double startMile = (double)priceRule.getStartMile();
        double distanceSubtract = BigDecimalUtil.subtract(distanceMile, startMile);
        double mile = distanceSubtract < 0 ? 0 : distanceSubtract;
        double unitPricePerMile = priceRule.getUnitPricePerMile();
        double mileFare = BigDecimalUtil.multiply(mile, unitPricePerMile);
        price = BigDecimalUtil.add(price, mileFare);

        // 时长费 = 耗时 * 费率
        double timeMinute = BigDecimalUtil.divide(duration, 60);
        double unitPricePerMinute = priceRule.getUnitPricePerMinute();
        double timeFare = BigDecimalUtil.multiply(timeMinute, unitPricePerMinute);
        price = BigDecimalUtil.add(price, timeFare);

        // 最终价格
        BigDecimal finalPrice = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);

        return finalPrice.doubleValue();
    }

    /**
     * 计算实际价格
     */
    public JsonRespWrapper calculatePrice(Integer distance, Integer duration, String cityCode, String vehicleType) {
        return null;
    }
}
