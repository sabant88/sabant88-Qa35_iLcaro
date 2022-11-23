package manager;

import models.Car;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviderCar {

    @DataProvider
    public Iterator<Object[]> dataCar() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/car.csv")));
        String line =reader.readLine();
        while (line!=null){
          String[] split =  line.split(";");
          list.add(new Object[]{
                  Car.builder()
                          .location(split[0])
                          .make(split[1])
                          .model(split[2])
                          .year(split[3])
                          .gear(split[4])
                          .fuel(split[5])
                          .engine(split[6])
                          .wD(split[7])
                          .doors(split[8])
                          .seats(split[9])
                          .classCar(split[10])
                          .fuelConsumption(split[11])
                          .featureS(split[12])
                          .about(split[13])
                          .price(split[14])
                          .distance(split[15])
                          .carRegistrationNumber(split[16])
                          .build()

            });
          line= reader.readLine();
        }

        return list.iterator();
    }
}
