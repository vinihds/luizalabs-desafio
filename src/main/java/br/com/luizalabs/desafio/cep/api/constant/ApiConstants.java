package br.com.luizalabs.desafio.cep.api.constant;

public class ApiConstants {
    private ApiConstants() {
    }

    private static final String API_URL = "/api";
    private static final String V1_URL = "/v1";

    public static final String API_BASE_V1_URL = API_URL + V1_URL;

    public static class Cep {
        private Cep() {
        }

        private static final String CEP_URL = "/cep";
        private static final String CEP_PARAM_URL = "/{cep}";

        public static final String CEP_PARAM = "cep";
        public static final String CEP_PARAM_URN = CEP_URL + CEP_PARAM_URL;
    }

    public static class ViaCep {
        private ViaCep() {
        }

        private static final String CEP_PARAM_URL = "/{cep}";
        private static final String JSON_URL = "/json";

        public static final String CEP_PARAM = "cep";
        public static final String VIA_CEP_BASE_URL = "https://viacep.com.br/ws";
        public static final String CEP_PARAM_URN = CEP_PARAM_URL + JSON_URL;
    }
}
