package br.com.ncs.literalura.utils;

public class IdiomaSiglaConverter {

    public static String getSigla(String idioma) {
        if (idioma == null) {
            return "Idioma não encontrado";
        }

        return switch (idioma.toLowerCase()) {
            case "árabe" -> "ar";
            case "arabe" -> "ar";
            case "português" -> "pt";
            case "portugues" -> "pt";
            case "búlgaro" -> "bg";
            case "bulgaro" -> "bg";
            case "checo" -> "cs";
            case "dinamarquês" -> "da";
            case "dinamarques" -> "da";
            case "holandês" -> "nl";
            case "holandes" -> "nl";
            case "inglês" -> "en";
            case "ingles" -> "en";
            case "finlandês" -> "fi";
            case "finlandes" -> "fi";
            case "francês" -> "fr";
            case "frances" -> "fr";
            case "alemão" -> "de";
            case "alemao" -> "de";
            case "grego" -> "el";
            case "hebraico" -> "he";
            case "hindi" -> "hi";
            case "húngaro" -> "hu";
            case "hungaro" -> "hu";
            case "indonésio" -> "id";
            case "indonesio" -> "id";
            case "italiano" -> "it";
            case "japonês" -> "ja";
            case "japones" -> "ja";
            case "coreano" -> "ko";
            case "norueguês" -> "no";
            case "noruegues" -> "no";
            case "polonês" -> "pl";
            case "polones" -> "pl";
            case "romeno" -> "ro";
            case "russo" -> "ru";
            case "chinês" -> "cn";
            case "chines" -> "cn";
            case "espanhol" -> "es";
            case "sueco" -> "sv";
            case "tailandes" -> "th";
            case "tailandês" -> "th";
            case "chinês tradicional" -> "zh-tw";
            case "chines tradicional" -> "zh-tw";
            case "turco" -> "tr";
            case "ucraniano" -> "uk";
            case "vietnamita" -> "vi";
            default -> "Idioma não encontrado";
        };
    }
}