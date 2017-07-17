package db.demo.dao;

import db.demo.security.Group;

import java.util.List;

public interface GroupDao extends Dao<Group, Long> {

    List<Group> findByName(String name);

}
