package com.jerry.servicemap.remote;

import org.springframework.stereotype.Component;

import com.jerry.common.dto.req.PointDTO;
import com.jerry.servicemap.configuration.MapProperties;

/**
 * 处理地图服务路径
 *
 * @author qijie
 * @date 2023/7/7
 */
@Component
public class MapServicePathResolver {

    /**
     * 路径规划
     */
    private static final String DIRECTION_DRIVING = "/direction/driving";

    /**
     * 行政区域查询
     */
    private static final String DISTRICT_URL = "/config/district";

    private static final String MAP_SERVICE_ADD_URL = "https://tsapi.amap.com/v1/track/service/add";

    private static final String MAP_TERMINAL_ADD_URL = "https://tsapi.amap.com/v1/track/terminal/add";

    private static final String MAP_TERMINAL_AROUND_SEARCH_URL =
        "https://tsapi.amap.com/v1/track/terminal/aroundsearch";

    private static final String MAP_TERMINAL_TR_SEARCH_URL = "https://tsapi.amap.com/v1/track/terminal/trsearch";

    private static final String MAP_TRACK_ADD_URL = "https://tsapi.amap.com/v1/track/trace/add";

    private static final String MAP_POINT_UPLOAD_URL = "https://tsapi.amap.com/v1/track/point/upload";

    private final MapProperties properties;

    public MapServicePathResolver(MapProperties properties) {
        this.properties = properties;
    }

    public String getDirectionDrivingPath(String depLongitude, String depLatitude, String destLongitude,
        String destLatitude) {
        String str = properties.getServerAddress() + DIRECTION_DRIVING;
        str += "?origin=" + depLongitude + "," + depLatitude + "&destination=" + destLongitude + "," + destLatitude
            + "&extensions=base&output=json&key=" + properties.getKey();
        return str;
    }

    public String getDistrictUrl(String keyWords) {
        String str = properties.getServerAddress() + DISTRICT_URL;
        str += "?keywords=" + keyWords + "&subdistrict=3&key=" + properties.getKey();
        return str;
    }

    public String getMapServiceAddUrl(String name) {
        return MAP_SERVICE_ADD_URL + "?key=" + properties.getKey() + "&name=" + name;
    }

    public String getMapTerminalAddUrl(String name, String desc) {
        return MAP_TERMINAL_ADD_URL + "?key=" + properties.getKey() + "&sid=" + properties.getSid() + "&name=" + name
            + "&desc=" + desc;
    }

    public String getMapTerminalAroundSearchUrl(String center, Integer radius) {
        return MAP_TERMINAL_AROUND_SEARCH_URL + "?key=" + properties.getKey() + "&sid=" + properties.getSid()
            + "&center=" + center + "&radius=" + radius;
    }

    public String getMapTerminalTrSearchUrl(String tid, Long startTime, Long endTime) {
        return MAP_TERMINAL_TR_SEARCH_URL + "?key=" + properties.getKey() + "&sid=" + properties.getSid() + "&tid="
            + tid + "&starttime=" + startTime + "&endTime=" + endTime;
    }

    public String getMapTrackAddUrl(String tid) {
        return MAP_TRACK_ADD_URL + "?key=" + properties.getKey() + "&sid=" + properties.getSid() + "&tid=" + tid;
    }

    public String getMapPointUploadUrl(String tid, String trId, PointDTO[] pointArray) {
        String url =
            MAP_POINT_UPLOAD_URL + "?key=" + properties.getKey() + "&sid=" + properties.getSid() + "&tid=" + tid;
        StringBuilder sb = new StringBuilder(url).append("%5B");
        for (PointDTO pointDTO : pointArray) {
            sb.append("%7B%22location%22%3A%22");
            sb.append(pointDTO.getLocation());
            sb.append("%22%2C%22locatetime%22%3A");
            sb.append(pointDTO.getLocateTime());
            sb.append("%7D");
        }
        sb.append("%5D");
        return sb.toString();
    }

}
