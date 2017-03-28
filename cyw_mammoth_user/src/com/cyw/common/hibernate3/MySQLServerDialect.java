package com.cyw.common.hibernate3;

import java.sql.Types;

import org.hibernate.Hibernate;

public class MySQLServerDialect extends org.hibernate.dialect.SQLServerDialect  
{  
    public MySQLServerDialect()  
    {  
        super();  
        //very important, mapping char(n) to String  
        registerHibernateType(Types.CHAR, Hibernate.STRING.getName()); 
        registerHibernateType(Types.LONGVARBINARY, Hibernate.STRING.getName());   
    }  
}  
