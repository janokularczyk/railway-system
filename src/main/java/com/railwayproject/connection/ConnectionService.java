package com.railwayproject.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {

    private final ConnectionRepository connectionRepository;
    private final ConnectionRepositoryCriteria connectionRepositoryCriteria;

    @Autowired
    public ConnectionService(ConnectionRepository connectionRepository,
                             ConnectionRepositoryCriteria connectionRepositoryCriteria) {
        this.connectionRepository = connectionRepository;
        this.connectionRepositoryCriteria = connectionRepositoryCriteria;
    }

    public Page<Connection> getConnections(ConnectionPage connectionPage,
                                           ConnectionSearchCriteria connectionSearchCriteria) {
        return connectionRepositoryCriteria.findAllWithFilters(connectionPage, connectionSearchCriteria);
    }

    public Connection addConnection(Connection connection) {
        return connectionRepository.save(connection);
    }
}
