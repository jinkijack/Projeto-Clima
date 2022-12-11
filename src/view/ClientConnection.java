package view;

import com.thoughtworks.xstream.XStream;
import model.Cidade;
import model.Previsao;
import model.hashMap;
import model.service.WeatherForecastService;

import java.io.IOException;

public class ClientConnection {
    private Integer cityId;
    private String cityName;
    private String previsao = "";

    XStream xstream = new XStream();

    public void setCityId(Integer cityId){
        this.cityId = cityId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName.replaceAll(" ","%20");
        System.out.println(this.cityName);
    }
    public void getPrevisao(){
        Cidade c = (Cidade) xstream.fromXML(previsao);
        System.out.printf("Previsão para %s/%s.\n", c.getNome(), c.getUf());

        for (Previsao p : c.getPrevisoes()) {
            if (hashMap.map.containsKey(p.getTempo())) {
                p.setTempo(hashMap.map.get(p.getTempo()));
            }
            System.out.printf("Dia %s\nMinima %s\nMaxima %s\nTempo %s\n", p.getDia(), p.getMinima(), p.getMaxima(), p.getTempo());
        }
    }
    public void searchName(){
        try{
            String cidade = WeatherForecastService.searchCity(cityName);
            System.out.println(cidade);


        }catch(IOException e) {
            System.out.println("Erro ao consultar API de clima.");
            e.printStackTrace();
        }

    }

    public void getSevenDays(){
        try{
            this.previsao = WeatherForecastService.sevenDaysWeatherForecast(cityId);
            System.out.println(previsao);
            xstream.aliasAttribute("previsao", "tempo");
            // Ajuste de segurança do XStream
            Class<?>[] classes = new Class[]{Cidade.class, Previsao.class};
            xstream.allowTypes(classes);

            xstream.alias("cidade", Cidade.class);
            xstream.alias("previsao", Previsao.class);

            xstream.addImplicitCollection(Cidade.class, "previsoes");


        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
