public class Calısan {

    private int id;
    private String ad;
    private String soyad;
    private String departman;
    private String maaş;

    public Calısan(int id, String ad, String soyad, String departman, String maaş) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.departman = departman;
        this.maaş = maaş;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getDepartman() {
        return departman;
    }

    public void setDepartman(String departman) {
        this.departman = departman;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaaş() {
        return maaş;
    }

    public void setMaaş(String maaş) {
        this.maaş = maaş;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
}
