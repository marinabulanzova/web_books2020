package form;

public class BookSearch {
    private String title;
    private String genre;
    private String publishing_house;
    private Integer min_p_year;
    private Integer max_p_year;
    private Integer min_p_count;
    private Integer max_p_count;
    private Integer count;
    private String cover;
    private Double min_price;
    private Double max_price;
    private String name_author;

    public BookSearch() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public Integer getMin_p_year() {
        return min_p_year;
    }

    public void setMin_p_year(Integer min_p_year) {
        this.min_p_year = min_p_year;
    }

    public Integer getMax_p_year() {
        return max_p_year;
    }

    public void setMax_p_year(Integer max_p_year) {
        this.max_p_year = max_p_year;
    }

    public Integer getMin_p_count() {
        return min_p_count;
    }

    public void setMin_p_count(Integer min_p_count) {
        this.min_p_count = min_p_count;
    }

    public Integer getMax_p_count() {
        return max_p_count;
    }

    public void setMax_p_count(Integer max_p_count) {
        this.max_p_count = max_p_count;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public String getName_author() {
        return name_author;
    }

    public void setName_author(String name_author) {
        this.name_author = name_author;
    }
}
