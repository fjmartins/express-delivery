package utils;

import android.widget.EditText;
import android.view.View;

/**
 * Created by Allan-PC on 23/05/2016.
 */
public class Validate {

    public static boolean validarCampoUsuario(EditText campoUsuario) {
        boolean result = true;

        if (campoUsuario.getText().toString().length() < 5 || campoUsuario.getText().toString().length() > 20) {
            campoUsuario.setError("Nome de usuário deve ser maior que 5 e menor que 20 caracteres");
            result = false;
        } else {
            campoUsuario.setError(null);
        }
        return result;
    }

    public static boolean validarCampoSenha(EditText campoSenha) {
        boolean result = true;

        if (campoSenha.getText().toString().length() < 7 || campoSenha.getText().toString().length() > 20) {
            campoSenha.setError("Senha de usuário deve ser de 8 à 20 caracteres");
            result = false;
        } if(!campoSenha.getText().toString().contains("@") ||
                !campoSenha.getText().toString().contains("#") ||
                !campoSenha.getText().toString().contains("$")||
                !campoSenha.getText().toString().contains("%")||
                !campoSenha.getText().toString().contains("&")||
                !campoSenha.getText().toString().contains("*")){
            campoSenha.setError("Senha de usuário deve conter algum desses caracteres especiais[@,#,$,&,*,%]");
            result = false;
        } if(!campoSenha.getText().toString().contains("0")||
                !campoSenha.getText().toString().contains("1")||
                !campoSenha.getText().toString().contains("2")||
                !campoSenha.getText().toString().contains("3")||
                !campoSenha.getText().toString().contains("4")||
                !campoSenha.getText().toString().contains("5")||
                !campoSenha.getText().toString().contains("6")||
                !campoSenha.getText().toString().contains("7")||
                !campoSenha.getText().toString().contains("8")||
                !campoSenha.getText().toString().contains("9")){
            campoSenha.setError("Senha de usuário deve conter algum número");
            result = false;
        }

        else {
            campoSenha.setError(null);
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
