package com.instantsystem.parking.wfs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class ParkingWebClient {

    private static final XmlMapper XML_MAPPER = new XmlMapper();

    private final URI uri;

    private final HttpClient httpClient;

    @Autowired
    public BordeauxParkingWebClient(
            @Value("${wfs.ressource.parking.bordeaux.url}") String url,
            HttpClient httpClient
    ) throws URISyntaxException {
        this.uri = new URI(url);
        this.httpClient = httpClient;
    }

    // TODO Cacher la réponse, utiliser @Cacheable ?
    // D'après la documentation les données se rafraîchissent toutes les 2min30
    // C'est peut-être donc pas necessaire de faire des appels à chaque fois
    public ResponseRootElement getParkings() throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
        HttpResponse<InputStream> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofInputStream());
        if (resolveHttpStatus(httpResponse.statusCode()).is2xxSuccessful()) {
            InputStream xmlInputStream = httpResponse.body();
            return XML_MAPPER.readValue(xmlInputStream, ResponseRootElement.class);
        } else {
            throw new ParkingNearMeTechnicalException(String.format("API sent status code %d", httpResponse.statusCode()));
        }
    }

    private static HttpStatus resolveHttpStatus(int statusCode) {
        HttpStatus httpStatus = HttpStatus.resolve(statusCode);
        if (httpStatus == null) {
            // TODO Log
            throw new ParkingNearMeTechnicalException(String.format("Could not resolve status %d", statusCode));
        } else {
            return httpStatus;
        }
    }

}

