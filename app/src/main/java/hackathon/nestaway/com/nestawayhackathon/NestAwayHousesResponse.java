package hackathon.nestaway.com.nestawayhackathon;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * Created by renuyadav on 10/17/15.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class NestAwayHousesResponse extends ValueObject implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean no_results;
    private Integer type_search;
    private Integer page;

    List<House> houses;


    /**
     * "bhk_details":"3 BHK",
     * "house_type":"Independent",
     * "id":730,
     * "status_code":4,
     * "title":"303 - SV Paradise",
     * "lat_double":12.989512,
     * "long_double":77.711685,
     * "bed_available_count":6,
     * "min_rent":8000,
     * "slug":"730-fully-furnished-nest-for-boys-in-a-3-bhk-independent-in-303-sv-paradise-hoodi-bengaluru-karnataka-india-bangalore-560048",
     * "nestaway_id":"N730",
     * "shared":6,
     * "private":0,
     * "image_url":"http://d397nwo9hbvzsm.cloudfront.net/uploads/images/thumb_large_6babdf320e.jpg",
     * "gender":"boys",
     * "locality":"Hoodi"
     */
    public static class House implements Serializable {


        String bhk_details;
        String house_type;
        Integer id;
        Integer status_code;
        String title;
        Double lat_double;
        Double long_double;
        Integer bed_available_count;
        Integer min_rent;
        String slug;
        String nestaway_id;
        Integer shared;
        Integer is_private;
        String image_url;
        String gender;
        String locality;


        public String getBhk_details() {
            return bhk_details;
        }

        public void setBhk_details(String bhk_details) {
            this.bhk_details = bhk_details;
        }

        public String getHouse_type() {
            return house_type;
        }

        public void setHouse_type(String house_type) {
            this.house_type = house_type;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getStatus_code() {
            return status_code;
        }

        public void setStatus_code(Integer status_code) {
            this.status_code = status_code;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Double getLat_double() {
            return lat_double;
        }

        public void setLat_double(Double lat_double) {
            this.lat_double = lat_double;
        }

        public Double getLong_double() {
            return long_double;
        }

        public void setLong_double(Double long_double) {
            this.long_double = long_double;
        }

        public Integer getBed_available_count() {
            return bed_available_count;
        }

        public void setBed_available_count(Integer bed_available_count) {
            this.bed_available_count = bed_available_count;
        }

        public Integer getMin_rent() {
            return min_rent;
        }

        public void setMin_rent(Integer min_rent) {
            this.min_rent = min_rent;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getNestaway_id() {
            return nestaway_id;
        }

        public void setNestaway_id(String nestaway_id) {
            this.nestaway_id = nestaway_id;
        }

        public Integer getShared() {
            return shared;
        }

        public void setShared(Integer shared) {
            this.shared = shared;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getLocality() {
            return locality;
        }

        public void setLocality(String locality) {
            this.locality = locality;
        }

        public Integer getIs_private() {
            return is_private;
        }

        public void setIs_private(Integer is_private) {
            this.is_private = is_private;
        }
    }

    public Boolean getNo_results() {
        return no_results;
    }

    public void setNo_results(Boolean no_results) {
        this.no_results = no_results;
    }

    public Integer getType_search() {
        return type_search;
    }

    public void setType_search(Integer type_search) {
        this.type_search = type_search;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }
}
