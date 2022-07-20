package hellojpa;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Item {

    @Id @GeneratedValue
    @Column(name= "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;


}
