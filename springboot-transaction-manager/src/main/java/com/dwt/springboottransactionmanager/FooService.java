package com.dwt.springboottransactionmanager;

/**
 * @Package: com.dwt.springboottransactionmanager
 * @Description:
 * @Author: Sammy
 * @Date: 2020/3/22 21:05
 */

public interface FooService {
    void insertRecord();
    void insertThenRollback() throws RollbackException;
    void invokeInsertThenRollback() throws RollbackException;

}
