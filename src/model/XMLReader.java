package model;

import com.thoughtworks.xstream.XStream;

import java.net.MalformedURLException;



public class XMLReader {

    public void searchCityXMLReader(String cityName) throws MalformedURLException {

        XStream xstream = new XStream();
        // Ajuste de seguranc ̧a do XStream
        Class<?>[] classes = new Class[] {Cidade.class};
        xstream.allowTypes(classes);
        Cidade cidade = null;
        String url = String.format("http://servicos.cptec.inpe.br/XML/listaCidades?city=%s",cityName);
        cidade = (Cidade) xstream.fromXML(url);

        if (cidade != null) {
            System.out.println(cidade);
        }
    }
    public void searchIdXMLReader(Integer cityId){
        XStream xstream = new XStream();
        // Ajuste de seguranc ̧a do XStream
        Class<?>[] classes = new Class[] {Cidade.class, Previsao.class};
        xstream.allowTypes(classes);
        Cidade cidade = null;
        String url = String.format("http://servicos.cptec.inpe.br/XML/cidade/7dias/%d/previsao.xml",cityId);
        cidade = (Cidade) xstream.fromXML(url);

        if (cidade != null) {
            System.out.println(cidade);
            System.out.println(cidade.getPrevisoes());
        }
    }

}
