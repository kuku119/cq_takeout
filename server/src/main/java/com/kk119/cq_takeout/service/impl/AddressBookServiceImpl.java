package com.kk119.cq_takeout.service.impl;

import com.kk119.cq_takeout.entity.AddressBook;
import com.kk119.cq_takeout.mapper.AddressBookMapper;
import com.kk119.cq_takeout.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class AddressBookServiceImpl implements AddressBookService {
    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * @Description 条件查询
     * */
    @Override
    public List<AddressBook> list(AddressBook addressBook) {
        return addressBookMapper.list(addressBook);
    }

    /**
     * @Description 新增地址
     * */
    @Override
    public void save(AddressBook addressBook) {

    }

    @Override
    public AddressBook getById(Long id) {
        return null;
    }

    @Override
    public void update(AddressBook addressBook) {

    }

    @Override
    public void setDefault(AddressBook addressBook) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
