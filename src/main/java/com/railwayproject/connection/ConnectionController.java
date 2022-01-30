package com.railwayproject.connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/connections")
public class ConnectionController {

    private final ConnectionService connectionService;

    @Autowired
    public ConnectionController(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @GetMapping
    public ResponseEntity<Page<Connection>> getConnections(ConnectionPage connectionPage,
                                                           ConnectionSearchCriteria connectionSearchCriteria) {
        return new ResponseEntity<>(connectionService.getConnections(connectionPage, connectionSearchCriteria), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Connection> addConnection(@RequestBody Connection connection) {
        return new ResponseEntity<>(connectionService.addConnection(connection), HttpStatus.OK);
    }
}
