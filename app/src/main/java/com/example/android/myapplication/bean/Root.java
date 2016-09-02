package com.example.android.myapplication.bean;

import java.util.List;

/**
 * Created by Android on 2016/9/1.
 */
public class Root extends BaseBean {
    private List<Data> data ;

    private int totalNum;

    private int pn;

    private int rn;

    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }
    public void setTotalNum(int totalNum){
        this.totalNum = totalNum;
    }
    public int getTotalNum(){
        return this.totalNum;
    }
    public void setPn(int pn){
        this.pn = pn;
    }
    public int getPn(){
        return this.pn;
    }
    public void setRn(int rn){
        this.rn = rn;
    }
    public int getRn(){
        return this.rn;
    }


   public class Data {
        private String id;

        private String title;

        private String tags;

        private String imtro;

        private String ingredients;

        private String burden;

        private List<String> albums ;

        private List<Steps> steps ;

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setTags(String tags){
            this.tags = tags;
        }
        public String getTags(){
            return this.tags;
        }
        public void setImtro(String imtro){
            this.imtro = imtro;
        }
        public String getImtro(){
            return this.imtro;
        }
        public void setIngredients(String ingredients){
            this.ingredients = ingredients;
        }
        public String getIngredients(){
            return this.ingredients;
        }
        public void setBurden(String burden){
            this.burden = burden;
        }
        public String getBurden(){
            return this.burden;
        }
        public void setString(List<String> albums){
            this.albums = albums;
        }
        public List<String> getString(){
            return this.albums;
        }
        public void setSteps(List<Steps> steps){
            this.steps = steps;
        }
        public List<Steps> getSteps(){
            return this.steps;
        }

       public class Steps {
           private String img;

           private String step;

           public void setImg(String img){
               this.img = img;
           }
           public String getImg(){
               return this.img;
           }
           public void setStep(String step){
               this.step = step;
           }
           public String getStep(){
               return this.step;
           }

           @Override
           public String toString() {
               return "Steps{" +
                       "img='" + img + '\'' +
                       ", step='" + step + '\'' +
                       '}';
           }
       }

       @Override
       public String toString() {
           return "Data{" +
                   "id='" + id + '\'' +
                   ", title='" + title + '\'' +
                   ", tags='" + tags + '\'' +
                   ", imtro='" + imtro + '\'' +
                   ", ingredients='" + ingredients + '\'' +
                   ", burden='" + burden + '\'' +
                   ", albums=" + albums +
                   ", steps=" + steps +
                   '}';
       }
   }


    public class Albums {

    }
}
