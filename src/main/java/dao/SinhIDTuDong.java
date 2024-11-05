package dao;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class SinhIDTuDong implements IdentifierGenerator {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String prefix;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws HibernateException {
        prefix = params.getProperty("prefix");
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        String query = "SELECT COUNT(e) FROM " + object.getClass().getSimpleName() + " e";
        Long count = session.createQuery(query, Long.class).uniqueResultOptional().orElse(0L);
        int idNumber = count.intValue() + 1;
        return prefix + String.format("%04d", idNumber); // Sinh mã dạng NV0001, KH0001, SP0001
    }

}
