package com.bm.jw.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserParams {
    @Value("${UserId}")
    private String Username;          //获取用户登陆的USERNAME号
}
