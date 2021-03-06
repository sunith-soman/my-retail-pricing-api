package com.myretail.pricing.dao;

import com.myretail.pricing.dto.UserDTO;
import com.myretail.pricing.entity.User;
import com.myretail.pricing.utility.PricingUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.query.Criteria;
import org.springframework.data.cassandra.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * @author Sunith Soman
 */
@Component
public class UserDaoImpl implements UserDao{

    private CassandraTemplate cassandraTemplate;

    @Autowired
    public UserDaoImpl(CassandraTemplate cassandraTemplate){
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public UserDTO getUserByUserName(String userName) {
        UserDTO userDTO = null;
        User user = cassandraTemplate.selectOne(Query.query(Criteria.where("user_name").is(userName)), User.class);
        if(user!=null) {
            userDTO = PricingUtility.getUserDTO(user);
        }
        return userDTO;
    }
}
