package modelo;

public class Book {

	protected String title;
	protected String id;
	protected String isbn;
	protected String img;
	protected String urlGoogleBooks;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrlGoogleBooks() {
		return urlGoogleBooks;
	}

	public void setUrlGoogleBooks(String urlGoogleBooks) {
		this.urlGoogleBooks = urlGoogleBooks;
	}

}
