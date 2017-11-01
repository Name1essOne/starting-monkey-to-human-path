package PO53.Abramov.wdad.learn.xml;
import  java.util.Calendar;

/**
 * Created by amax1 on 03.10.2017.
 */
public interface xml {
    public int earningsTotal(String officiantSecondName, Calendar calendar); // возвращающий суммарную выручку заданного официанта в заданный день
    public void removeDay(Calendar calendar); // удаляет информацию по заданному дню.
    public void changeOfficiantName(String oldFirstName, String oldSecondName, String newFirstName, String newSecondName); // изменяющий имя и фамилию официанта во всех днях и записывающий результат в этот же xml-файл
}
