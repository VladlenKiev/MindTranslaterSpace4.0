package np.com.mshrestha.uan.Parser;

import java.util.*;


public class Block {
    private List<String> skills=new ArrayList<>();
    private List<String> experience=new ArrayList<>();
    private List<String> education=new ArrayList<>();
    private List<String> languages=new ArrayList<>();
    private List<String> trainings=new ArrayList<>();
    //------------------------------------------------
    // запихнуть блок текста по которому фильтры бегали
    private String textBlock;
    private int textBlockSize;

    public boolean isBlockContent(){
        int content=skills.size()+experience.size()+education.size()+languages.size()+trainings.size();
        return (0==content)?false:true;
    }

    public String getTypeBlock(){
        HashMap<String,Integer> map=new HashMap<>();
        map.put("skills",skills.size());
        map.put("experience",experience.size());
        map.put("education",education.size());
        map.put("languages",languages.size());
        map.put("trainings",trainings.size());
        Set<Map.Entry<String,Integer>> set=map.entrySet();
        int size=0;
        String type="none";
        for(Map.Entry<String,Integer> entry:set){
            if (entry.getValue()>size) {
                size = entry.getValue();
                type = entry.getKey();
            }
        }
        return type;
    }

    public void wipeOffList(String typeList){
        if("skills".equals(typeList))
            skills=new ArrayList<>();
        else if("experience".equals(typeList))
            experience=new ArrayList<>();
        else if("education".equals(typeList))
            education=new ArrayList<>();
        else if("languages".equals(typeList))
            languages=new ArrayList<>();
        else if("trainings".equals(typeList))
            trainings=new ArrayList<>();
    }

    public int getPersentSkills(){
        return (getPointSkills()*100/textBlockSize);
    };
    public int getPersentExperience(){
        return (getPointExperience()*100/textBlockSize);
    }
    public int getPersentEducation(){return (getPointEducation()*100/textBlockSize);}
    public int getPersentLanguages(){return (getPointLanguages()*100/textBlockSize);}
    public int getPresentTrainings(){return (getPointTrainings()*100/textBlockSize);}

    public String getTextBlock() {
        return textBlock;
    }
    public void setTextBlock(String textBlock) {
        this.textBlock = textBlock;
    }

    public int getTextBlockSize() {
        return textBlockSize;
    }
    public void setTextBlockSize(int textBlockSize) {
        this.textBlockSize = textBlockSize;
    }

    public void addLanguages(String word){
        languages.add(word);
    }
    public List<String> getWordsLanguages(){return languages;}
    public int getPointLanguages(){return languages.size();}

    public void addExperience(String word){
        experience.add(word);
    }
    public List<String> getWordsExperience(){return experience;}
    public int getPointExperience(){return experience.size();}

    public void addSkills(String word){
        skills.add(word);
    }
    public List<String> getWordsSkills(){return skills;}
    public int getPointSkills(){return skills.size();}

    public void addEducation(String word){
        education.add(word);
    }
    public List<String> getWordsEducation(){return education;}
    public int getPointEducation(){return education.size();}

    public void addTrainings(String word){
        trainings.add(word);
    }
    public List<String> getWordsTrainings(){return trainings;}
    public int getPointTrainings(){return trainings.size();}

    @Override
    public String toString() {
        return "skills= " + skills.toString() +
                " experience= " + experience.toString() +
                " education= " + education.toString() +
                " languages= " + languages.toString();
    }
}
