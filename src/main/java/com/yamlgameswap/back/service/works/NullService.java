package com.yamlgameswap.back.service.works;

import com.yamlgameswap.back.service.works.factory.ServiceInter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j(topic = "service.NullService")
@Service
public class NullService implements ServiceInter {

    public void proc() {

    }
}
