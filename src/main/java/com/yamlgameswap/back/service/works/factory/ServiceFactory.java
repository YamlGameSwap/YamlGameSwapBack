package com.yamlgameswap.back.service.works.factory;

import com.yamlgameswap.back.enums.WorkEnum;
import com.yamlgameswap.back.service.works.NullService;
import com.yamlgameswap.back.service.works.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ServiceFactory {

    @Autowired
    private NullService nullService;
    @Autowired
    private UserService userService;

    public static Map<Integer, ServiceInter> serviceMap = new HashMap<>();

    @PostConstruct
    private void init() {
        serviceMap.put(WorkEnum.NULLSERVICE.getService(), nullService);
        serviceMap.put(WorkEnum.USERSERVICE.getService(), userService);
    }

    public ServiceInter getService(Integer service) {
        if (serviceMap.keySet().contains(service)) {
            return serviceMap.get(service);
        } else {
            ((NullService) (serviceMap.get(WorkEnum.NULLSERVICE.getService()))).proc();
            return null;
        }
    }
}
