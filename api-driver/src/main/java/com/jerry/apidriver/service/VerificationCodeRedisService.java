package com.jerry.apidriver.service;

import static com.jerry.common.response.StatusCode.VERIFICATION_CODE_ERROR;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jerry.apidriver.remote.ServiceDriverUserClient;
import com.jerry.apidriver.remote.VerificationCodeClient;
import com.jerry.common.cache.key.AccessTokenCacheKey;
import com.jerry.common.cache.key.VerificationCodeCacheKey;
import com.jerry.common.constast.DriverCarStatus;
import com.jerry.common.constast.UserIdentity;
import com.jerry.common.dto.TokenDTO;
import com.jerry.common.dto.resp.DriverUserExistsResp;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.response.StatusCode;
import com.jerry.common.util.JwtUtil;
import com.jerry.common.util.TokenType;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证码服务redis存储实现
 *
 * @author qijie
 * @date 2023/5/23
 */
@Slf4j
@Service
public class VerificationCodeRedisService implements VerificationCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private VerificationCodeClient verificationCodeClient;

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Override
    public JsonRespWrapper createVerificationCode(String phone) {
        // 判断司机是否已经存在
        JsonRespWrapper<DriverUserExistsResp> ifExists = serviceDriverUserClient.checkDriver(phone);
        DriverUserExistsResp data = ifExists.getData();
        int result = data.getExists();
        if (result == DriverCarStatus.DRIVER_NOT_EXISTS) {
            return JsonRespWrapper.failure(StatusCode.DRIVER_NOT_EXIST);
        }

        // 获取验证码
        JsonRespWrapper<String> response = verificationCodeClient.createCode(6);
        String code = response.getData();
        log.info("司机验证码" + code);
        // 调用三方接口发送验证码到司机手机

        redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone, UserIdentity.DRIVE))
            .set(response.getData(), Duration.ofMinutes(5));

        return JsonRespWrapper.success("");
    }

    @Override
    public JsonRespWrapper checkCode(String phone, String code) {
        String codeInServer =
            redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone, UserIdentity.DRIVE))
                .get();

        if (!StringUtils.hasText(codeInServer)) {
            return JsonRespWrapper.failure(VERIFICATION_CODE_ERROR);
        }
        if (!codeInServer.equals(code.trim())) {
            return JsonRespWrapper.failure(VERIFICATION_CODE_ERROR);
        }

        // 用户登录，颁发令牌
        String accessToken = JwtUtil.createToken(phone, UserIdentity.DRIVE, TokenType.ACCESS_TOKEN);
        String refreshToken = JwtUtil.createToken(phone, UserIdentity.DRIVE, TokenType.REFRESH_TOKEN);

        // token存入redis方便后续使用
        String accessTokenCacheKey = AccessTokenCacheKey.getAccessTokenCacheKey(phone, UserIdentity.DRIVE);
        String refreshTokenCacheKey = AccessTokenCacheKey.getRefreshTokenCacheKey(phone, UserIdentity.DRIVE);
        redisTemplate.opsForValue().set(accessTokenCacheKey, accessToken, 30, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(refreshTokenCacheKey, refreshToken, 31, TimeUnit.DAYS);

        TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);
        return JsonRespWrapper.success(tokenDTO);
    }

}
