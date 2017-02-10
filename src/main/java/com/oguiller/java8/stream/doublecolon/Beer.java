package com.oguiller.java8.stream.doublecolon;

public class Beer {

    private Long id;
    private String name;
    private Float alcohol;
    private String country;

    public Beer(Long id, String name, Float alcohol, String country) {
        this.id = id;
        this.name = name;
        this.alcohol = alcohol;
        this.country = country;
    }

    public Beer(Long id, String name, Float alcohol) {
        this.id = id;
        this.name = name;
        this.alcohol = alcohol;
        this.country = "Netherlands";
    }

    public Beer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Float alcohol) {
        this.alcohol = alcohol;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void reduceAlcohol(){
        System.out.println("Adding water  ....");
        this.setAlcohol(this.getAlcohol() - 0.5f);
    }

    public void pourBeer(){
        System.out.println("Pouring Beer ...." + this.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Beer)) return false;

        Beer beer = (Beer) o;

        if (!getId().equals(beer.getId())) return false;
        if (!getName().equals(beer.getName())) return false;
        if (!getAlcohol().equals(beer.getAlcohol())) return false;
        return getCountry() != null ? getCountry().equals(beer.getCountry()) : beer.getCountry() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAlcohol().hashCode();
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcohol=" + alcohol +
                ", country='" + country + '\'' +
                '}';
    }
}
