package org.ageadoc.gtd.hibernate.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class TinyIntegerToBoolean  implements UserType {
    public int[] sqlTypes() {
        return new int[]{Types.TINYINT};
    }

    public Class returnedClass() {
        return Boolean.class;
    }

    /* boilerplate... */
    public boolean isMutable() {
        return false;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == null || y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    public int hashCode(Object x) throws HibernateException {
        assert (x != null);
        return x.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet resultSet, String[] strings, SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException, SQLException {
        return (resultSet.getByte(strings[0]) != 0);
    }

    @Override
    public void nullSafeSet(PreparedStatement preparedStatement, Object v, int i, SharedSessionContractImplementor sharedSessionContractImplementor) throws HibernateException, SQLException {
        preparedStatement.setByte(i, Boolean.TRUE.equals(v) ? (byte) 1 : (byte) 0);
    }

    public Object deepCopy(Object value) throws HibernateException {
        return value;
    }

    public Object replace(Object original, Object target, Object owner)
            throws HibernateException {
        return original;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public Object assemble(Serializable cached, Object owner)
            throws HibernateException {
        return cached;
    }
}
