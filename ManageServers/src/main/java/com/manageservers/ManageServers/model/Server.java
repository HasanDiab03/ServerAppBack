package com.manageservers.ManageServers.model;

import com.manageservers.ManageServers.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity // maps the class to a database entity
@Data // Lombak dependency to generate getter and setters
@NoArgsConstructor // generates no arg constructor
@AllArgsConstructor // generates all arg constructor
public class Server {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment for primary key
    private Long id;
    @Column(unique = true) // specifies that ip address should be unique
    @NotEmpty(message = "IP address canno be empty or null") // ip address can't be empty/null
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status; // Status is a enum
}
