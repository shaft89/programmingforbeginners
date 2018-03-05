/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.ws.io.dao.impl;

import com.app.ws.io.dao.DAO;
import com.app.ws.io.entity.DeviceEntity;
import com.app.ws.io.entity.UserEntity;
import com.app.ws.shared.dto.DeviceDTO;
import com.app.ws.shared.dto.UserDTO;
import com.app.ws.utils.HibernateUtils;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;

/**
 *
 * @author philippgottschalk
 */
public class MySQLDAO implements DAO{
    Session session;

    public void openConnection() {
        SessionFactory sessionFectory = HibernateUtils.getSessionFactory();
        session = sessionFectory.openSession();
    }

    public UserDTO getUserByPlayerID(String playerID) {
        UserDTO userDto = null;

        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("playerID"), playerID));

        // Fetch single result
        Query<UserEntity> query = session.createQuery(criteria);
        List<UserEntity> resultList = query.getResultList();
        if (resultList != null && resultList.size() > 0) {
            UserEntity userEntity = resultList.get(0);
            userDto = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDto);
        }

        return userDto;
    }
    
    public UserDTO getUser(String id) {
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<UserEntity> criteria = cb.createQuery(UserEntity.class);

        //Query roots always reference entitie
        Root<UserEntity> profileRoot = criteria.from(UserEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("userId"), id));

        // Fetch single result
        UserEntity userEntity = session.createQuery(criteria).getSingleResult();
        
        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDto);
        
        return userDto;       
    }
    
    public DeviceDTO getDevice(long id){ //Verstehe ich nicht wirklich was ich da gebaut habe...
        CriteriaBuilder cb = session.getCriteriaBuilder();

        //Create Criteria against a particular persistent class
        CriteriaQuery<DeviceEntity> criteria = cb.createQuery(DeviceEntity.class);

        //Query roots always reference entity
        Root<DeviceEntity> profileRoot = criteria.from(DeviceEntity.class);
        criteria.select(profileRoot);
        criteria.where(cb.equal(profileRoot.get("id"), id));

        // Fetch single result
        DeviceEntity deviceEntity = session.createQuery(criteria).getSingleResult();

        DeviceDTO deviceDto = new DeviceDTO();
        BeanUtils.copyProperties(deviceEntity, deviceDto);

        return deviceDto;

    }
  
    public DeviceDTO saveDevice (DeviceDTO device){
        
        DeviceEntity deviceEntity = new DeviceEntity();
        BeanUtils.copyProperties(device, deviceEntity);
        
        session.beginTransaction();
        session.save(deviceEntity);
        session.getTransaction().commit();
        
        DeviceDTO returnValue = new DeviceDTO();
        BeanUtils.copyProperties(deviceEntity, returnValue);
        
        return returnValue;
    }
    
    public UserDTO saveUser(UserDTO user){
        
        UserDTO returnValue = null;
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);
        
        session.beginTransaction();
        session.save(userEntity);
        session.getTransaction().commit();
        
        returnValue = new UserDTO();
        BeanUtils.copyProperties(userEntity, returnValue);
        
        return returnValue;
    
    }

    public void closeConnection() {
        if(session != null){
        session.close();
        }
    }

    public void updateUser(UserDTO userProfile) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userProfile, userEntity);
        
        session.beginTransaction();
        session.update(userEntity);
        session.getTransaction().commit();
    
    }
    
}
