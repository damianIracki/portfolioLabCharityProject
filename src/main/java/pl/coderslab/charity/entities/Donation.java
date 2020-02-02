package pl.coderslab.charity.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue
    private long id;

    private int quantity;

    private String street;

    private String city;

    private String zipCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    private LocalTime pickUpTime;

    private String pickUpComment;

    @ManyToMany
    @JoinColumn(name = "id_category")
    private List<Category> categories;

    @OneToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private DonationStatus donationStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate receivedDate;

}
