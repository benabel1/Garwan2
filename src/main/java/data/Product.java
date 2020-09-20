package data;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "product_table")
public class Product {
	public static final String PRICE_COLUMN_NAME = "price";
	public static final String NAME_COLUMN_NAME = "name";
	public static final String DESCRIBTION_COLUMN_NAME = "description";

	@Id
	@Column(name = "product_id")
	private long productID;
	
	@Column(name = NAME_COLUMN_NAME)
	private String name;
	
	@Column(name = PRICE_COLUMN_NAME)
	@PositiveOrZero
	private double price;
	
	@Enumerated(EnumType.STRING)
	private AnimalCategory animalCategory;
	
	@Column(name = DESCRIBTION_COLUMN_NAME)
	String description;
	
	@OneToMany(mappedBy = "product_image", orphanRemoval = true)
	List<Links> gallery;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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
	

}
