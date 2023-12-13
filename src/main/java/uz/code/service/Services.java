package uz.code.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.code.model.Dictionary;
import uz.code.model.QuizHistory;
import uz.code.repository.Repository;
import uz.code.utils.ScannerUtils;

import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class Services {
    @Autowired
    private Repository repository;
    boolean bool=false;
    @Autowired
    private ScannerUtils scanner;
    
    
    public void add(Dictionary dictionary) {

        bool=repository.add(dictionary);

        if(bool){
            System.out.println("Success✅✅✅");
        }else {
            System.out.println("Error❌❌❌");
        }
    }

    public List<Dictionary> list(){
        return repository.list();
    }

    public void quiz(QuizHistory quizHistory) {
        int score=0;
        int sum=0;
        while (true) {
            char option='A';

            List<Dictionary> list = repository.list();
            Random random = new Random();
            int i1 = random.nextInt(0, 4);
            Collections.shuffle(list);

            Dictionary dictionary2 = list.get(i1);
            System.out.println(dictionary2.getEng() + "?");

            int id = dictionary2.getId();
            int m = 0;
            String ss="";
            for (Dictionary dictionary1 : list) {
                System.out.println((char)(option+m)+") "+dictionary1.getUz());

                if(id==dictionary1.getId()){
                    ss=String.valueOf((char)(option+m));
                    ss+=dictionary1.getUz();
                }
                m++;
                if (m == 4) {
                    break;
                }
            }
            int id1 = 0;

            String s = scanner.nextLine("enter : ");
            if (s.equals("0")) {
                System.out.println("****Game over****");
                System.out.println("****your score : [ " +score+"/"+ sum + " ] ****");
                quizHistory.setAmountQuestion(sum);
                quizHistory.setAmountCorrectAnswer(score);
                repository.quizHistory(quizHistory);
                return;
            }

            sum++;
            if(s.equalsIgnoreCase(ss.substring(0,1))){
                for (Dictionary dictionary1 : repository.list()) {
                    if (dictionary1.getUz().equals(ss.substring(1))) {
                        id1 = dictionary1.getId();
                        break;
                    }
                }
            }

            if (id == id1) {
                score++;
                System.out.println("✅✅✅");
            } else {
                System.out.println("❌❌❌");
            }
        }
    }

    public void spelling(int id, String s) {
            int id1=0;
        for (Dictionary dictionary : list()) {
            if(dictionary.getEng().equals(s)){
                id1=dictionary.getId();
                break;
            }
        }

        if(id==id1){
            System.out.println("✅✅✅");
        }else {
            System.out.println("❌❌❌");
        }
    }

    public void searchByEng(String search) {

        for (Dictionary dictionary : list()) {
            if(dictionary.getEng().startsWith(search)){
                System.out.println(dictionary);
                bool=true;
            }
        }
        if(!bool){
            System.out.println("Not found❌");
        }
    }

    public void searchByUz(String search) {
        for (Dictionary dictionary : list()) {
            if(dictionary.getUz().startsWith(search)){
                System.out.println(dictionary);
                bool=true;
            }
        }
        if(!bool){
            System.out.println("Topilmadi❌");
        }
    }

    public void deleteById(int id) {
       bool=repository.delete(id);

        if (bool) {
            System.out.println("Word has deleted successfully✅✅✅");
        }else {
            System.out.println("Error❌❌❌");
        }
    }
}
