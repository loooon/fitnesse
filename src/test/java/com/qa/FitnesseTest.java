package com.qa;

import fitnesse.junit.FitNesseRunner;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 * fitnesse_toc
 * Created by yu on 2017/8/23.
 */
@RunWith(FitNesseRunner.class)
@FitNesseRunner.Suite("DefaultSuite")
@FitNesseRunner.FitnesseDir(".") //路径是执行环境路径
@FitNesseRunner.OutputDir("./build/fitnesse-results")
public class FitnesseTest {

}
