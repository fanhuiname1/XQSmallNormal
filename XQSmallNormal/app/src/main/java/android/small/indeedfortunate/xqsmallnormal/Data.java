package android.small.indeedfortunate.xqsmallnormal;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by 旧城丷 on 2018/12/3.
 */

public class Data implements IPickerViewData {
    private String name;
    private List<CityBean> city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }

    public static class CityBean {

        private String name;
        private List<String> area;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

    }

}
