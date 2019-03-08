/**
 * Copyright 2009-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations
 * under the License.
 */
package org.apache.ibatis.personal.param;

import java.io.Reader;
import java.util.Arrays;
import org.apache.ibatis.BaseDataTest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParamTest {

    private SqlSessionFactory sqlSessionFactory;

    @BeforeEach
    void setUp() throws Exception {
        try (Reader reader = Resources.getResourceAsReader("org/apache/ibatis/personal/param/Config.xml")) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }
        BaseDataTest.runScript(sqlSessionFactory.getConfiguration().getEnvironment().getDataSource(),
            "org/apache/ibatis/personal/param/CreateDB.sql");
    }

    /**
     * 测试根据参数序号获取参数
     */
    @Test
    public void testUserParamIndex() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            System.out.println(mapper.testUserParamIndex(1, "zhuojl"));
            sqlSession.commit();
        }
    }

    /**
     * 测试单个参数
     */
    @Test
    public void testSingleParam() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            System.out.println(mapper.testSingleParam(1));
            sqlSession.commit();
        }
    }

    /**
     * 测试多参数，不加名称
     */
    @Test
    public void testMultiParamNoAnnotation() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            System.out.println(mapper.testMultiParamNoAnnotation(1, "zhuojl"));
            sqlSession.commit();
        }
    }


    /**
     * 测试直接获取 list的属性
     */
    @Test
    public void testList() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            System.out.println(mapper.testUseListMethod(Arrays.asList(1)));
            sqlSession.commit();
        }
    }

    /**
     * 测试foreach
     */
    @Test
    public void testForeach() {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            Mapper mapper = sqlSession.getMapper(Mapper.class);
            System.out.println(mapper.testForeach(Arrays.asList(1, 2)));
            sqlSession.commit();
        }
    }


}
