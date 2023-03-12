package com.yamlgameswap.back.module;


import com.yamlgameswap.back.service.common.RedisService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 多语言模块
 * 将多语言存储到 redis 中
 */
@Slf4j(topic = "TranslationModule")
@Component
public class MultiLanguageModule implements moduleInter {
    @Getter
    private Map<String, Map<String, String>> languageData = new HashMap<>();

    @Value("${multilanguage}")
    private List<String> multiLangiage;

    @Value("${redis.languageKey}")
    private String languagekey;
    @Autowired
    private RedisService redisService;

    @PostConstruct
    public void init() {
        proc();
    }


    @Override
    public void proc() {
        // 导入 yaml 多语言
        loadi18nYaml();
        // 存入 redis
        storeRedis();
    }

    public void loadi18nYaml() {
        Yaml yaml = new Yaml();
        if (multiLangiage == null || multiLangiage.size() == 0) {
            log.error("将多语言存储到 redis 发生错误，配置文件没有配置");
            return;
        }
        for (String language : multiLangiage) {
            InputStream languageYaml = MultiLanguageModule.class.getClassLoader().getResourceAsStream("i18n/" + language + ".yaml");
            if (languageYaml == null) {
                log.error("翻译模块" + language + ".yaml 文件丢失");
                continue;
            }
            Map<String, Map<String, String>> simpleLanguageData = yaml.load(languageYaml);
            languageData.put(language, simpleLanguageData.get("Language"));
        }
    }

    public void storeRedis() {
        for (String language : languageData.keySet()) {
            redisService.setMap(languagekey + language, languageData.get(language));
        }
    }
}
