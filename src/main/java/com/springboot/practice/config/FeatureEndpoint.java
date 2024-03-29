package com.springboot.practice.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint {
    private Map<String, Feature> map = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        map.put("Department", new Feature(true));
        map.put("User", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> getFeatures() {
        return map;
    }

    @ReadOperation
    public Feature getFeature(@Selector String featureName) {
        return map.get(featureName);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Feature {
        private boolean isEnabled;
    }
}

