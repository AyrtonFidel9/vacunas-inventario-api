package com.vacunas.inventario.services;

import org.springframework.stereotype.Service;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

@Service
public class ValidacionesService implements iValidacionesService{
    @Override
    public boolean cedulaValida(String cedula) {
        if(cedula.length()==10){
            int indice = 0;

            int sumar = 0;

            int RESTARNUEVE = 9;

            CharacterIterator digito = new StringCharacterIterator(cedula);
            while(digito.current() != CharacterIterator.DONE){
                int newDigit = Character.getNumericValue(digito.current());
                if(indice % 2 == 0){
                    int digitoMultiplicado = Character.getNumericValue(digito.current());
                    newDigit = digitoMultiplicado * 2;
                    if(newDigit >= 10){
                        newDigit = newDigit - RESTARNUEVE;
                    }
                }

                sumar += newDigit;

                digito.next();
                indice++;
            }

            int modulo = sumar % 10;
            int resultado = 10 - modulo;

            System.out.println("MODULO->> "+modulo);

            System.out.println("RESUUULTADO ->> " + resultado);
            System.out.println("Ultimo caracter->> "+Character.getNumericValue(cedula.charAt(9)));

            if(modulo == 0)
                return true;

            return resultado == Character.getNumericValue(cedula.charAt(9));

        }
        else{
            return false;
        }
    }
}
