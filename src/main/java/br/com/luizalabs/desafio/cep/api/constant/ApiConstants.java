package br.com.luizalabs.desafio.cep.api.constant;

public class ApiConstants {
    private ApiConstants() {
    }

    private static final String API_URL = "/api";
    private static final String V1_URL = "/v1";

    public static final String BASE_V1_URL = API_URL + V1_URL;

    public class Cep {
        private Cep() {
        }

        private static final String CEP_URL = "/cep";
        private static final String CEP_PARAM = "/{cep}";

        public static final String CEP_PARAM_URI = CEP_URL + CEP_PARAM;
    }
}
