package np.com.mshrestha.uan.Parser;

import np.com.mshrestha.uan.model.CV;
import np.com.mshrestha.uan.model.UploadedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The user enters text into a search box. This class is used
 * to parse that text into specific search terms (or tokens).
 * It eliminates common words, and allows for the quoting of text, using
 * double quotes.
 * JDK 7+.
 */
public final class SearchBoxParser {

    private static List<CV> cvList=new ArrayList<>();


    public static List<CV> getcvList() {
        List<CV> cvListTemp = new ArrayList<>();
        cvListTemp = cvList;
        cvList = new ArrayList<>();
        return cvListTemp;
    }

    static CV cv;

    public static CV getCv() {
        return cv;
    }

    public static void setCv(CV cv) {
        SearchBoxParser.cv = cv;
    }

    public static void CVparser(List<UploadedFile> activeFilesInSession){
        for(UploadedFile file:activeFilesInSession){
            System.out.println("1");
            String[] strings=null;
            if(fyleTypeIsPDF(file.getName())){
            System.out.println("2");
            strings=pdfParse(file.getName());
            }else{
                strings=docParse(file.getName());
            }
            if(strings.length<40) {
                System.out.println("3");
                System.out.println();
                //CV cv = BoxParser.parseResume(Arrays.asList(strings));
                System.out.println("4");
                if (isCV(cv)) {
                    cvList.add(cv);
                }
            }
            System.out.println("5");
        }
    }

    private static Boolean isCV(CV cv){
        int sizeCV=sizeStr(cv.getSkills())+sizeStr(cv.getExp())+sizeStr(cv.getEdu())+sizeStr(cv.getLang());
        int resSize=sizeCV;
        if(resSize>0)
            return true;
        return false;
    }

    private static int sizeStr(String string){
        if(string!=null)
            return string.length();
        return 0;
    }

    private static boolean fyleTypeIsPDF(String fName){
        String[] parts=fName.split("\\.");
        if("pdf".equals(parts[parts.length-1])){
            return true;
        }else {
            return false;
        }
    }
    private static String[] pdfParse(String fName){
        String[] strings=null;
        try {
            PDDocument document = null;
            File file = new File("D:/uploaded-files/"+fName);
            document = PDDocument.load(file);
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper Tstripper = new PDFTextStripper();

                String st = Tstripper.getText(document);

                strings = st.trim().split("(?m)(?=^\\s{3})");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    private static String[] docParse(String fName){
        File file=new File("C:/uploaded-files/"+fName);
        FileInputStream fis= null;
        String[] strings=null;
        try {
            fis = new FileInputStream(file);
            HWPFDocument document=new HWPFDocument(fis);
            WordExtractor extractor=new WordExtractor(document);
            strings=extractor.getParagraphText();
            //------------
            for(String s:strings)
                System.out.println(" 1 "+s);
            //------------
            //return strings;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    public static String parseStatus(UploadedFile activeFileInSession){
            String fName=activeFileInSession.getName();
            String[] strings=null;
            if(fyleTypeIsPDF(fName)){
            strings=pdfParse(fName);
            }else{
                strings=docParse(fName);
            }
            if(strings.length<40) {
                return " - parsed successful";
            }
            return " - not parsed";
        }
} 