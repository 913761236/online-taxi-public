package com.jerry.apipassenger.service.vcode;

import static com.jerry.common.response.StatusCode.VERIFICATION_CODE_ERROR;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jerry.apipassenger.remote.user.ServicePassengerUserClient;
import com.jerry.apipassenger.remote.vcode.VerificationCodeClient;
import com.jerry.common.cache.key.AccessTokenCacheKey;
import com.jerry.common.cache.key.VerificationCodeCacheKey;
import com.jerry.common.constast.UserIdentity;
import com.jerry.common.dto.TokenDTO;
import com.jerry.common.dto.VerificationCodeDTO;
import com.jerry.common.response.JsonRespWrapper;
import com.jerry.common.util.JwtUtil;
import com.jerry.common.util.TokenType;

/**
 * 验证码服务redis存储实现
 *
 * @author qijie
 * @date 2023/5/23
 */
@Service
public class VerificationCodeRedisService implements VerificationCodeService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private VerificationCodeClient verificationCodeClient;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Override
    public JsonRespWrapper createVerificationCode(String phone) {
        JsonRespWrapper<String> response = verificationCodeClient.createCode(6);
        String code = response.getData();
        redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone, UserIdentity.PASSENGER))
            .set(response.getData(), Duration.ofMinutes(5));

        // 需要将验证码发送给短信服务商，例如阿里短信服务等

        VerificationCodeDTO dto = new VerificationCodeDTO();
        dto.setPassengerPhone(phone);
        dto.setCode(code);
        return JsonRespWrapper.success(dto);
    }

    @Override
    public JsonRespWrapper checkCode(String phone, String code) {
        String codeInServer =
            redisTemplate.boundValueOps(VerificationCodeCacheKey.getVerificationCodeKey(phone, UserIdentity.PASSENGER))
                .get();

        if (!StringUtils.hasText(codeInServer)) {
            return JsonRespWrapper.failure(VERIFICATION_CODE_ERROR);
        }
        if (!codeInServer.equals(code.trim())) {
            return JsonRespWrapper.failure(VERIFICATION_CODE_ERROR);
        }

        // 判断用户是否存在，不存在需要创建用户
        VerificationCodeDTO codeDTO = new VerificationCodeDTO();
        codeDTO.setPassengerPhone(phone);
        servicePassengerUserClient.loginOrRegister(codeDTO);

        // 用户登录，颁发令牌
        String accessToken = JwtUtil.createToken(phone, UserIdentity.PASSENGER, TokenType.ACCESS_TOKEN);
        String refreshToken = JwtUtil.createToken(phone, UserIdentity.PASSENGER, TokenType.REFRESH_TOKEN);

        // token存入redis方便后续使用
        String accessTokenCacheKey = AccessTokenCacheKey.getAccessTokenCacheKey(phone, UserIdentity.PASSENGER);
        String refreshTokenCacheKey = AccessTokenCacheKey.getRefreshTokenCacheKey(phone, UserIdentity.PASSENGER);
        redisTemplate.opsForValue().set(accessTokenCacheKey, accessToken, 30, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(refreshTokenCacheKey, refreshToken, 31, TimeUnit.DAYS);

        TokenDTO tokenDTO = new TokenDTO(accessToken, refreshToken);
        return JsonRespWrapper.success(tokenDTO);
    }

}
