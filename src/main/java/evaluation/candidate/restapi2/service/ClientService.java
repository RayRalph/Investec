
package evaluation.candidate.restapi2.service;

import evaluation.candidate.restapi2.data.dao.ClientDao;
import evaluation.candidate.restapi2.data.persistance.ClientRepository;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    private static final String QUERY = "SELECT * FROM assessment_schema.Client i where i";
    @Autowired
    JdbcTemplate template;
    
    @Autowired
    ClientRepository clientRepository;
    
        public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    
    public ClientDao getClientByIDNumber(String idnumber) {
        String sql = String.format("%s.idnumber = '%s'", QUERY, idnumber);
        RowMapper<ClientDao> rm = BeanPropertyRowMapper.newInstance(ClientDao.class);
        if(template.query(sql, rm).size() == 0){
            return null;
        }
        ClientDao clientDao = template.queryForObject(sql, rm);
        return clientDao;
    }
    
    public ClientDao getClientByMobileNumber(String mobileNumber) {
        String sql = String.format("%s.mobilenumber = '%s'", QUERY, mobileNumber);
        RowMapper<ClientDao> rm = BeanPropertyRowMapper.newInstance(ClientDao.class);
        return template.query(sql, rm).get(0);
    }
    
    public List<ClientDao> getClientByFirstName(String firstName) {
        StringBuilder sql = new StringBuilder(String.format("%s.firstname = '%s'", QUERY, firstName));
        RowMapper<ClientDao> rm = BeanPropertyRowMapper.newInstance(ClientDao.class);
        return this.template.query(sql.toString(), rm);
    }

    @Transactional
    public void saveOrUpdate(ClientDao client) {
        ClientDao dao = clientRepository.save(client);
    }
    
}
