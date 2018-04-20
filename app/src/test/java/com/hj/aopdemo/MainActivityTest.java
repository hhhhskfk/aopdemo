package com.hj.aopdemo;

import com.hj.aoplib.annotation.DebugTrace;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2018/4/20.
 */
public class MainActivityTest {

    @Test
    @DebugTrace
    public void annotatedMethod() throws Exception {
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }
}