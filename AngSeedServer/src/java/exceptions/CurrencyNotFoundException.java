/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author simon
 */
public class CurrencyNotFoundException extends Exception {

    public CurrencyNotFoundException(String noCurrencyWithThatId) {
        super(noCurrencyWithThatId);
    }
}