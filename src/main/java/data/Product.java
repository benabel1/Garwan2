package data;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.cfg.defs.DigitsDef;

@Entity
@Table(name = "product_table")
public class Product {
	public static final String PRICE_COLUMN_NAME = "price";
	public static final String NAME_COLUMN_NAME = "name";
	public static final String DESCRIBTION_COLUMN_NAME = "description";

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productID;
	
	@Column(name = NAME_COLUMN_NAME)
	private String name;
	
	@Column(name = PRICE_COLUMN_NAME)
	@PositiveOrZero
	private BigDecimal price;
	
	@Enumerated(EnumType.STRING)
	private AnimalCategory animalCategory;
	
	@Column(name = DESCRIBTION_COLUMN_NAME)
	String description;
	
	//TODO:  
//	@OneToMany(mappedBy = "product_image", orphanRemoval = true)
	@OneToMany(mappedBy = "product_image")
	List<Links> gallery;
	
	public Product() {
		super();
	}
	
	
	/**
	 * 
	 * @param productID
	 * @param name
	 * @param price
	 * @param animalCategory
	 * @param description
	 * @param gallery
	 */
	public Product(long productID, String name, @PositiveOrZero BigDecimal price, AnimalCategory animalCategory, String description, List<Links> gallery) {
		super();
		this.productID = productID;
		this.name = name;
		this.price = price;
		this.animalCategory = animalCategory;
		this.description = description;
		this.gallery = gallery;
	}



	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public AnimalCategory getAnimalCategory() {
		return animalCategory;
	}

	public void setAnimalCategory(AnimalCategory animalCategory) {
		this.animalCategory = animalCategory;
	}

	public String getDesription() {
		return description;
	}

	public void setDesription(String decription) {
		this.description = decription;
	}

	public List<Links> getGallery() {
		return gallery;
	}

	public void setGallery(List<Links> gallery) {
		this.gallery = gallery;
	}
	
	@Override
	public String toString() {
		return String.format("Product[id=%d, price=%f, name=%s, description=%s]", getProductID(),getPrice(), getName(),getDesription());
	}

}
