package manager;

import models.User;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderUser {

    @DataProvider
    public Iterator<Object[]> dataLogin(){

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"noa@gmail.com", "Nnoa12345$"});
        list.add(new Object[]{"sonya@gmail.com", "Ss12345$"});
        list.add(new Object[]{"totti10@mail.uz", "Ttotti10new$"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataModel(){
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{new User().withPassword("Ttotti10new$").withEmail("totti10@mail.uz")});
        list.add(new Object[]{new User().withPassword("Nnoa12345$").withEmail("noa@gmail.com")});
        list.add(new Object[]{new User().withPassword("Ss12345$").withEmail("sonya@gmail.com")});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> dataReg() throws IOException {

        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/registration.csv")));
        String line = reader.readLine();
        while (line!=null){
            String[] split = line.split(",");
            list.add(new Object[]{new User().withName(split[0]).withLastName(split[1]).withEmail(split[2]).withPassword(split[3])});
           line = reader.readLine();
        }
        return list.iterator();
    }

    @DataProvider // dugma Data Provider
    public Iterator<Object[]> XXX(){

        List<Object[]> list = new ArrayList<>();

        return list.iterator();
    }

}
