package com.dzq;

import com.dzq.bean.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author 秋风伊人
 * @date 2018/10/16 10:59
 * @desc https://www.cnblogs.com/3020815dzq/
 */
public class StudentDemo {
    Transaction transaction = null;
    Session session = null;

    @Before
    public void before() {
        //读取核心配置文件
        Configuration configuration = new Configuration().configure();
        //创建会话工厂
        SessionFactory factory = configuration.buildSessionFactory();
        //创建会话 Session 线程不安全
        session = factory.openSession();
        //开启事务
        transaction = session.beginTransaction();
    }

    @After
    public void after() {
        //提交事务
        transaction.commit();
        //关闭方法
        if (session != null) session.close();
    }

    /**
     * 创建数据
     */
    @Test
    public void testcreate() {
        //创建对象
        Student student = new Student(1, "小黑");
        //通过session操作对象
        session.save(student);
    }

    /**
     * 修改数据
     * 01：数据库中存在就修改
     * 02：不存在就抛出异常
     */
    @Test
    public void testUpdate() {
        //设置需要修改的对象
        Student student = new Student(1, "小白");
        session.update(student);//修改
    }

    /**
     * 删除数据
     * 数据中没有对应的数据就删除 提前先查询select 在 delete
     * 没有队友的数据 只查询select
     */
    @Test
    public void testDelete() {
        //设置需要删除的对象
        Student student = new Student();
        student.setId(1);
        session.delete(student);//删除
    }
}
