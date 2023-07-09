package com.jerry.servicemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jerry.common.dto.DicDistrict;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.response.StatusCode;
import com.jerry.servicemap.mapper.DicDistrictMapper;
import com.jerry.servicemap.remote.AMapConstants;
import com.jerry.servicemap.remote.MapDicDistrictClient;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * description
 *
 * @author qijie
 * @date 2023/7/8
 */
@Service
public class DicDistrictService {

    @Autowired
    private MapDicDistrictClient mapDicDistrictClient;

    @Autowired
    private DicDistrictMapper dicDistrictMapper;

    public JsonRespWrapper initDicDistrict(String keywords) {
        // 请求地图获取行政区划
        String dicDistrictResult = mapDicDistrictClient.dicDistrict(keywords);
        // 解析结果
        JSONObject dicDistrictJsonObject = JSONObject.fromObject(dicDistrictResult);
        int status = dicDistrictJsonObject.getInt(AMapConstants.STATUS);
        if (status != 1) {
            return JsonRespWrapper.failure(StatusCode.MAP_DISTRICT_ERROR);
        }
        JSONArray countryJsonArray = dicDistrictJsonObject.getJSONArray(AMapConstants.DISTRICTS);
        for (int country = 0; country < countryJsonArray.size(); country++) {
            JSONObject countryJsonObject = countryJsonArray.getJSONObject(country);
            String countryAddressCode = countryJsonObject.getString(AMapConstants.ADCODE);
            String countryAddressName = countryJsonObject.getString(AMapConstants.NAME);
            String countryParentAddressCode = "0";
            String countryLevel = countryJsonObject.getString(AMapConstants.LEVEL);

            save(countryAddressCode, countryAddressName, countryLevel, countryParentAddressCode);

            JSONArray proviceJsonArray = countryJsonObject.getJSONArray(AMapConstants.DISTRICTS);
            for (int p = 0; p < proviceJsonArray.size(); p++) {
                JSONObject proviceJsonObject = proviceJsonArray.getJSONObject(p);
                String provinceAddressCode = proviceJsonObject.getString(AMapConstants.ADCODE);
                String provinceAddressName = proviceJsonObject.getString(AMapConstants.NAME);
                String provinceLevel = proviceJsonObject.getString(AMapConstants.LEVEL);

                save(provinceAddressCode, provinceAddressName, provinceLevel, countryAddressCode);

                JSONArray cityArray = proviceJsonObject.getJSONArray(AMapConstants.DISTRICTS);
                for (int city = 0; city < cityArray.size(); city++) {
                    JSONObject cityJsonObject = cityArray.getJSONObject(city);
                    String cityAddressCode = cityJsonObject.getString(AMapConstants.ADCODE);
                    String cityAddressName = cityJsonObject.getString(AMapConstants.NAME);
                    String cityLevel = cityJsonObject.getString(AMapConstants.LEVEL);

                    save(cityAddressCode, cityAddressName, cityLevel, provinceAddressCode);

                    JSONArray districtArray = cityJsonObject.getJSONArray(AMapConstants.DISTRICTS);
                    for (int d = 0; d < districtArray.size(); d++) {
                        JSONObject districtJsonObject = districtArray.getJSONObject(d);
                        String districtAddressCode = districtJsonObject.getString(AMapConstants.ADCODE);
                        String districtAddressName = districtJsonObject.getString(AMapConstants.NAME);
                        String districtLevel = districtJsonObject.getString(AMapConstants.LEVEL);

                        if (districtLevel.equals(AMapConstants.STREET)) {
                            continue;
                        }

                        save(districtAddressCode, districtAddressName, districtLevel, cityAddressCode);
                    }
                }
            }

        }

        return JsonRespWrapper.success("");
    }

    public void save(String addressCode, String addressName, String level, String parentAddressCode) {
        DicDistrict district = new DicDistrict();
        district.setAddressCode(addressCode);
        district.setAddressName(addressName);
        int levelInt = generateLevel(level);
        district.setLevel(levelInt);

        district.setParentAddressCode(parentAddressCode);

        // 插入数据库
        dicDistrictMapper.insert(district);
    }

    public int generateLevel(String level) {
        int levelInt = 0;
        switch (level.trim()) {
            case "country":
                break;
            case "province":
                levelInt = 1;
                break;
            case "city":
                levelInt = 2;
                break;
            case "district":
                levelInt = 3;
                break;
            default:
                levelInt = 5;
                break;
        }
        return levelInt;
    }

}
