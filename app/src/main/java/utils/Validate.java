package utils;

import android.util.Patterns;
import android.widget.EditText;
import android.view.View;

/**
 * Created by Allan-PC on 23/05/2016.
 */
public class Validate {

    public static boolean validarCampoUsuario(EditText campoUsuario) {
        boolean result = true;

        if (campoUsuario.getText().toString().length() < 5) {
            campoUsuario.setError("Nome de usuário deve ter de 5 à 20 caracteres");
            result = false;
        } else {
            campoUsuario.setError(null);
        }
        return result;
    }

    public static boolean validarCampoEmail(EditText campoEmail) {
        boolean result = true;

        if (campoEmail.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(campoEmail.getText().toString()).matches()) {
            campoEmail.setError("Email inválido");
            result = false;
        } else if (campoEmail.length() < 5 || campoEmail.length() > 45) {
            campoEmail.setError("Email deve conter entrer 5 e 45 caraceres");
            result = false;
        } else {
            campoEmail.setError(null);
        }
        return result;
    }

    public static boolean validarCampoSenha(EditText campoSenha) {
        boolean senhaOK = false;

        String[] lowerCase = { "q", "w", "e", "r", "t", "y", "u", "i", "o", "p", "a", "s", "d", "f", "g",
                "h", "j", "k", "l", "ç", "z", "x", "c", "v", "b", "n", "m" };
        String[] upperCase = { "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G",
                "H", "J", "K", "L", "Ç", "Z", "X", "C", "V", "B", "N", "M" };
        String[] numbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String[] caractespecial = { "!", "@", "_", "$", "%", "&", "*", ".", "/", "#", "?" };

        // verifica se há pelo menos 1 caracter de cada tipo
        if (campoSenha.getText().toString().length() > 7) {
            for (int i = 0; i < lowerCase.length; i++) {
                for (int j = 0; j < upperCase.length; j++) {
                    for (int l = 0; l < numbers.length; l++) {
                        for (int k = 0; k < caractespecial.length; k++) {
                            if (campoSenha.getText().toString().contains(lowerCase[i])
                                    && campoSenha.getText().toString().contains(upperCase[j])
                                    && campoSenha.getText().toString().contains(numbers[l])
                                    && campoSenha.getText().toString().contains(caractespecial[k])) {
                                senhaOK = true;
                            }
                        }
                    }
                }
            }
        }
        if(senhaOK == false) {
            campoSenha.setError("Senha de usuário deve ser de 8 à 20 caracteres e deverá conter: " +
                    "letra Minuscula, letra Maiuscula, número, e caracter especial[!, @, _, $, %, &, *, ., /, #, ?]");
        }

        return senhaOK;
    }

    public static boolean validarCampoConfirm(EditText campoSenha, EditText campoConfirm) {

        boolean result = true;

        if (!campoSenha.getText().toString().trim().equals(campoConfirm.getText().toString().trim())) {
            campoConfirm.setError("Senhas não conferem");
            result = false;
        } else {
            campoConfirm.setError(null);
        }

        return result;

    }

    public static boolean validarCampoTitle(EditText campoTitle) {
        boolean result = true;

        if (campoTitle.getText().toString().trim().isEmpty()) {
            campoTitle.setError("@string/campo_obrigatorio");
            result = false;
        } else if (campoTitle.getText().toString().length() <= 5 || campoTitle.getText().toString().length() > 20) {
            campoTitle.setError("Deve conter entre 5 e 20 caracteres");
            result = false;
        } else {
            campoTitle.setError(null);
        }
        return result;
    }

    public static boolean validarCampoDescription(EditText campoDescription) {
        boolean result = true;

        if (campoDescription.getText().toString().trim().isEmpty()) {
            campoDescription.setError("Deve conter uma descrição");
            result = false;
        } else if (campoDescription.getText().toString().length() <= 5 || campoDescription.getText().toString().length() > 100) {
            campoDescription.setError("Deve conter entre 10 e 100 caracteres");
            result = false;
        } else {
            campoDescription.setError(null);
        }
        return result;
    }

    public static boolean validarCampoValor(EditText campoValor) {
        boolean result = true;

        if (campoValor.getText().toString().trim().isEmpty()) {
            campoValor.setError("Deve conter um valor");
            result = false;
        } else {
            campoValor.setError(null);
        }
        return result;
    }

    public static boolean validarCampoPhone(EditText campoPhone) {
        boolean result = true;

        if (campoPhone.getText().toString().trim().isEmpty()) {
            campoPhone.setError("@string/campo_obrigatorio");
            result = false;
        } else {
            campoPhone.setError(null);
        }
        return result;
    }

    public static boolean validarCampoAddress(EditText campoAddress) {
        boolean result = true;

        if (campoAddress.getText().toString().trim().isEmpty()) {
            campoAddress.setError("@string/campo_obrigatorio");
            result = false;
        } else {
            campoAddress.setError(null);
        }
        return result;
    }

    public static boolean validarCampoStreet(EditText campoStreet) {
        boolean result = true;

        if (campoStreet.getText().toString().length() < 5) {
            campoStreet.setError("Rua deve conter mais de 5 caracteres");
            result = false;
        } else {
            campoStreet.setError(null);
        }
        return result;
    }

    public static boolean validarCampoNumber(EditText campoNumber) {
        boolean result = true;

        if (campoNumber.getText().toString().trim().isEmpty()) {
            campoNumber.setError("Número é obrigatório");
            result = false;
        } else {
            campoNumber.setError(null);
        }
        return result;
    }

    public static boolean validarCampoZipcod(EditText campoZipcode) {
        boolean result = true;

        if (campoZipcode.getText().toString().trim().isEmpty()) {
            campoZipcode.setError("CEP é obrigatório");
            result = false;
        } else {
            campoZipcode.setError(null);
        }
        return result;
    }

    public static boolean validarCampoDistrict(EditText campoDistrict) {
        boolean result = true;

        if (campoDistrict.getText().toString().trim().isEmpty()) {
            campoDistrict.setError("Bairro é obrigatório");
            result = false;
        } else {
            campoDistrict.setError(null);
        }
        return result;
    }

    public static boolean validarCampoCity(EditText campoCity) {
        boolean result = true;

        if (campoCity.getText().toString().trim().isEmpty()) {
            campoCity.setError("Cidade é obrigatório");
            result = false;
        } else {
            campoCity.setError(null);
        }
        return result;
    }

    public static boolean validarCpf(EditText campoCpf){
        boolean result = true;
        if (campoCpf.getText().toString().length() != 11) {
            campoCpf.setError("CPF Inválido");
            result = false;
        }
        return result;
    }
}
