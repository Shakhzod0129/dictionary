package uz.code.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import uz.code.db.DataBase;
import uz.code.model.Dictionary;
import uz.code.model.QuizHistory;
import uz.code.service.Services;
import uz.code.utils.ScannerUtils;

import java.util.Collections;
import java.util.List;

@Controller
public class Control {
    @Autowired
    private ScannerUtils scanner;
    @Autowired
    private DataBase dataBase;
    @Autowired
    private Services service;
    @Autowired
    private Dictionary dictionary;
    @Autowired
    private QuizHistory quizHistory;

    public void start(){

        dataBase.createTable();
        dataBase.quizTable();

        while (true){
            System.out.println("""
                ********MENU********
                1.Add word
                2.Word list
                3.Multiple choice
                4.Spelling
                5.Searching Eng
                6.Searching Uz
                7.Delete by id
                0.exit""");

            int i = scanner.nextInt("Choose option : ");
            switch (i){
                case 1-> addWord();
                case 2-> wordList();
                case 3-> quiz();
                case 4-> spelling();
                case 5-> searchingByEng();
                case 6-> searchingByUz();
                case 7-> delete();
                case 0->{
                    return;
                }
            }
        }
    }

    private void addWord() {
        String eng = scanner.nextLine("Enter english word : ");
        String uz = scanner.nextLine("Enter uz word : ");
        String engDescription = scanner.nextLine("Enter english description : ");
        String uzDescription = scanner.nextLine("Enter uz description : ");

        dictionary.setEng(eng);
        dictionary.setUz(uz);
        dictionary.setEngDescription(engDescription);
        dictionary.setUzDescription(uzDescription);
        service.add(dictionary);
    }

    private void wordList() {
        int i=0;
        if(service.list().isEmpty()){
            System.out.println("Dictionary list is empty🗑️");
        }else {
            for (Dictionary dictionary1 : service.list()) {
                System.out.println(dictionary1);
            }
        }

    }

    private void quiz() {
        String name = scanner.nextLine("Enter your name : ");
        quizHistory.setPlayerName(name);
        service.quiz(quizHistory);
    }

    private void spelling() {
        List<Dictionary> list = service.list();
        while (true){
            Collections.shuffle(list);
            Dictionary dictionary1 = list.get(0);
            int id = dictionary1.getId();
            System.out.println("**** "+dictionary1.getUz()+"****");
            String s = scanner.nextLine("Write spelling in english : ");
            if(s.equals("0")){
                return;
            }
            service.spelling(id,s);
        }
    }

    private void searchingByEng() {
        String search = scanner.nextLine("Enter word or letter to search : ");

        service.searchByEng(search);
    }

    private void searchingByUz() {
        String search = scanner.nextLine("Qidirish uchun so'z yoki harf kriting : ");

        service.searchByUz(search);
    }

    private void delete() {
        int id = scanner.nextInt("Enter id of word to delete it : ");
        service.deleteById(id);
    }
}
