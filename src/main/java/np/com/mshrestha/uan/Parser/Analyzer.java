package np.com.mshrestha.uan.Parser;

import np.com.mshrestha.uan.model.CV;

import java.util.ArrayList;
import java.util.Set;

public class Analyzer {
    private static String skills_block;
    private static String experience_block;
    private static String education_block;
    private static String languages_block;
    //------------------------------------

    public static void AnalyzeAndrey(Set<Block> blocksSet, CV cv){
        while (blocksSet.size()>0) {
            // max value return Filters;
            Block maxBlock= findMaxBlock(blocksSet);
            // set text block in CV
            if(maxBlock!=null) {
                setTextToCV(maxBlock, cv);
                // wipe of type list
                wipeOffBlockSet(maxBlock.getTypeBlock(), blocksSet);
                // remove this Block from blockSet
                removeBlock(maxBlock, blocksSet);
                //-----------------------------------
            }else{
                return;
            }
        }
    }

    private static void wipeOffBlockSet(String typeList,Set<Block> blockSet){
        for(Block block:blockSet){
            block.wipeOffList(typeList);
        }
    }

    private static void removeBlock(Block block,Set<Block> blockSet){
        if(blockSet.contains(block))
            blockSet.remove(block);
    }

    private static void setTextToCV(Block block, CV cv){
        if("skills".equals(block.getTypeBlock()))
            cv.setSkills(block.getTextBlock());
        else if("experience".equals(block.getTypeBlock()))
            cv.setExp(block.getTextBlock());
        else if("education".equals(block.getTypeBlock()))
            cv.setEdu(block.getTextBlock());
        else if("languages".equals(block.getTypeBlock()))
            cv.setLang(block.getTextBlock());
        else if("trainings".equals(block.getTypeBlock()))
            cv.setTrainings(block.getTextBlock());
    }

    private static Block findMaxBlock(Set<Block> blockSet) {
        String type = "none";
        int maxPercent = 0;
        int wordWeight=0;
        Block maxBlock=null;
        for (Block block:blockSet) {
            type = block.getTypeBlock();
                if (type.equals("skills") && block.getPointSkills() > maxPercent&&block.getTextBlockSize()>wordWeight) {
                    maxPercent = block.getPointSkills();
                    maxBlock = block;
                    //---------------
                    wordWeight=block.getTextBlockSize();
                    //---------------
                } else if (type.equals("experience") && block.getPointExperience() > maxPercent&&block.getTextBlockSize()>wordWeight) {
                    maxPercent = block.getPointExperience();
                    maxBlock = block;
                    //---------------
                    wordWeight=block.getTextBlockSize();
                    //---------------
                } else if (type.equals("education") && block.getPointEducation() > maxPercent&& block.getTextBlockSize()>wordWeight) {
                    maxPercent = block.getPointEducation();
                    maxBlock = block;
                    //---------------
                    wordWeight=block.getTextBlockSize();
                    //---------------
                } else if (type.equals("languages") && block.getPointLanguages() > maxPercent&&block.getTextBlockSize()>wordWeight) {
                    maxPercent = block.getPointLanguages();
                    maxBlock = block;
                    //---------------
                    wordWeight=block.getTextBlockSize();
                    //---------------
                }else if (type.equals("trainings") && block.getPointLanguages() > maxPercent&&block.getTextBlockSize()>wordWeight) {
                    maxPercent = block.getPointTrainings();
                    maxBlock = block;
                    //---------------
                    wordWeight=block.getTextBlockSize();
                    //---------------
                }
            }
        return maxBlock;
    }


}
