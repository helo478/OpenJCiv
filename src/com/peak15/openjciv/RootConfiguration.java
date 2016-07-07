package com.peak15.openjciv;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The root Spring configuration.
 */
@Configuration
@ComponentScan(basePackages = { "com.peak15.openjciv.board", "com.peak15.openjciv.turn"})
public class RootConfiguration {

}
