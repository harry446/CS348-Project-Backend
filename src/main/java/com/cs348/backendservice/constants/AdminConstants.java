package com.cs348.backendservice.constants;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class AdminConstants {
    public Set<Integer> adminUID = new HashSet<>(Arrays.asList(0, 1, 2, 3, 4));
}
