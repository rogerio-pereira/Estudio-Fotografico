/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

/**
 *
 * @author Rogério Eduardo Pereira
 * Data Criação: 06/09/2013
 */

/*
 * Classe para verificar CPF e CNPJ
 */
public class ValidaDocumentos 
{
    /*
     * Valida CPF
     * @param String cpf
     */
    public boolean validaCPF(String cpf)
    {
        try
        {
            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");

            if(this.validaTamanhoCPF(cpf))
            {
                if(this.validaSequenciaCPF(cpf))
                {
                    if(this.validaPrimeiroDigitoCPF(cpf))
                    {
                        if(this.validaSegundoDigitoCPF(cpf))
                            return true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }
    
    /*
     * Verifica o tamanho da string do cpf
     * @param String cpf
     */
    private boolean validaTamanhoCPF(String cpf)
    {
        try
        {
            if(cpf.length() == 11)
                return true;
        }
        catch(Exception e)
        {
            return false;
        }
        
        return false;
    }
    
    /*
     * Valida Sequencia CPF
     * @param String cpf
     */
    private boolean validaSequenciaCPF(String cpf)
    {
        try
        {
            //CPF COM SEQUENCIA DE NUMEROS SEMPRE SÃO INVALIDOS
            if(
                    cpf.equals("11111111111")||
                    cpf.equals("22222222222")||
                    cpf.equals("33333333333")||
                    cpf.equals("44444444444")||
                    cpf.equals("55555555555")||
                    cpf.equals("66666666666")||
                    cpf.equals("77777777777")||
                    cpf.equals("88888888888")||
                    cpf.equals("99999999999")||
                    cpf.equals("00000000000")
              )
                    return false;
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    /*
     * Valida primeiro digito verificador do cpf
     * @param String cpf
     */
    private boolean validaPrimeiroDigitoCPF(String cpf)
    {
        try
        {
            int i;
            int soma=0;
            int peso=10;

            //CALCULA SOMA
            for(i=0;i<9;i++)
            {
                soma = soma + (peso * Integer.parseInt(cpf.substring(i, i + 1)) );
                peso--;
            }
            //CALCULA PRIMEIRO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR 0 OU 1
            if((soma % 11 == 0)||(soma % 11 == 1))
            {
                if(0 != Integer.parseInt(cpf.substring(9,10)) )
                    return false;
            }
            //CALCULA PRIMEIRO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR MAIOR QUE 1
            else if(soma % 11 > 1)
            {
                if(Integer.parseInt(cpf.substring(9,10)) != (11 - (soma % 11)) )
                    return false;
            }
	
            return true;
        }
        catch(Exception exp)
        {
            return false;
        }
    }
    
    /*
     * Valida segundo digito verificador do CPF
     * @param String cpf
     */
    private boolean validaSegundoDigitoCPF(String cpf)
    {
        try
        {
            int i;
            int soma=0;
            int peso=11;

            //CALCULA SOMA
            for(i=0;i<10;i++)
            {
                soma = soma + (peso * Integer.parseInt(cpf.substring(i, i + 1)) );
                peso--;
            }

            //CALCULA SEGUNDO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR 0 OU 1
            if((soma % 11 == 0)||(soma % 11 == 1))
            {
                if(0 != Integer.parseInt(cpf.substring(10,11)))
                    return false;
            }
            //CALCULA SEGUNDO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR MAIOR QUE 1
            else if(soma % 11 > 1)
            {
                if(Integer.parseInt(cpf.substring(10,11)) != (11 - (soma % 11)) )
                    return false;
            }
	
            return true;
        }
        catch(Exception exp)
        {
            //janelaErroCalcularCPF
	
            return false;
        }
    }
    
    
    
    
    /*
     * VALIDA CNPJ
     * @param String cnpj
     */
    public boolean validaCNPJ(String cnpj)
    {
        try
        {
            cnpj = cnpj.replace(".", "");
            cnpj = cnpj.replace("/", "");
            cnpj = cnpj.replace("-", "");
            
            if(this.validaTamanhoCNPJ(cnpj));
            {
                if(this.validaSequenciaCNPJ(cnpj))
                {
                    if(this.validaPrimeiroDigitoCNPJ(cnpj))
                    {
                        if(this.validaSegundoDigitoCNPJ(cnpj))
                            return true;
                    }
                }
            }
        }
        catch (Exception e)
        {
            return false;
        }
        return false;
    }
    
    /*
     * Verifica tamanho cnpj
     * @param String cnpj
     */
    private boolean validaTamanhoCNPJ(String cnpj)
    {
        try
        {
            if(cnpj.length() == 14)
                return true;
        }
        catch(Exception e)
        {
            return false;
        }
        
        return false;
    }
    
    /*
     * Verifica se o cnpj é uma sequencia
     * @param String cnpj
     */
    private boolean validaSequenciaCNPJ(String cnpj)
    {
        try
        {
            if(
                    cnpj.equals("11111111111111")||
                    cnpj.equals("22222222222222")||
                    cnpj.equals("33333333333333")||
                    cnpj.equals("44444444444444")||
                    cnpj.equals("55555555555555")||
                    cnpj.equals("66666666666666")||
                    cnpj.equals("77777777777777")||
                    cnpj.equals("88888888888888")||
                    cnpj.equals("99999999999999")||
                    cnpj.equals("00000000000000")
              )
                    return false;
            
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
    /*
     * Valida primeiro digito CNPJ
     * @param String cnpj
     */
    private boolean validaPrimeiroDigitoCNPJ(String cnpj)
    {
        try
        {
            int soma = 0,
                peso = 5;
            
            //REALIZA SOMA DOS DIGITOS PARA VERIFICACAO
            for(int i=0; i<12; i++)
            {
                if(i == 4)
                    peso = 9;
                
                soma = soma + (peso * Integer.parseInt(cnpj.substring(i, i+1)));
                peso--;
            }
            
            //CALCULA PRIMEIRO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR 0 OU 1
            if((soma % 11 == 0)||(soma % 11 == 1))
            {
                if(0 != Integer.parseInt(cnpj.substring(12,13)) )
                    return false;
            }
            //CALCULA PRIMEIRO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR MAIOR QUE 1
            else if(soma % 11 > 1)
            {
                if(Integer.parseInt(cnpj.substring(12,13)) != (11 - (soma % 11)) )
                {
                    return false;
                }
            }
            
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    /*
     * Valida segundo digito verificador do cnpj
     * @param String cnpj
     */
    private boolean validaSegundoDigitoCNPJ(String cnpj)
    {
        try
        {
            int soma = 0,
                peso = 6;
            
            
            //REALIZA SOMA DOS DIGITOS PARA VERIFICACAO
            for(int i=0; i<13;i++)
            {
                if(i == 5)
                    peso = 9;
                
                soma = soma + (peso * Integer.parseInt(cnpj.substring(i, i+1)));
                peso--;
            }
            
            //CALCULA SEGUNDO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR 0 OU 1
            if((soma % 11 == 0)||(soma % 11 == 1))
            {
                if(0 != Integer.parseInt(cnpj.substring(13,14)) )
                    return false;
            }
            //CALCULA SEGUNDO DIGITO SE O RESTO DA DIVISAO DA SOMA POR 11 FOR MAIOR QUE 1
            else if(soma % 11 > 1)
            {
                if(Integer.parseInt(cnpj.substring(13,14)) != (11 - (soma % 11)) )
                    return false;
            }
            
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
}
