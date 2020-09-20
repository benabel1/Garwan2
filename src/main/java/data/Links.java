package data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Links {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Column(name = "url", nullable = false)
	@NotNull
	String url;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	
	Product product_image;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Product getProduct_image() {
		return product_image;
	}

	public void setProduct_image(Product product_image) {
		this.product_image = product_image;
	}

	
	
}
