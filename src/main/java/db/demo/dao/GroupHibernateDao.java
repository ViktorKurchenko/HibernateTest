package db.demo.dao;

import db.demo.security.Group;

import java.util.List;

public class GroupHibernateDao extends AbstractDao<Group, Long> implements GroupDao {

    @Override
    public List<Group> findByName(String name) {
        return null;
    }

}
