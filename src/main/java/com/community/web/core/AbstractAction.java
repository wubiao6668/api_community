package com.community.web.core;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractAction<Service, Manager> {
    @Autowired
    protected Service service;
    @Autowired
    protected Manager manager;

}
